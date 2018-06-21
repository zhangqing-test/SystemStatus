package com.system.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.system.common.util.JdbcConMSS;
import com.system.common.util.SystemParamConfigUtil;
import com.system.quartz.bean.SpidEntity;

@SuppressWarnings("unused")
public class KillDeadlockJob extends QuartzJobBean {
	private static final Logger LOG = LoggerFactory.getLogger(KillDeadlockJob.class);

	private int timeout;
	private static int i = 0;

	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		String url = SystemParamConfigUtil.getParamValueByParam("kill.jdbc.url");
		String username = SystemParamConfigUtil.getParamValueByParam("kill.jdbc.username");
		String password = SystemParamConfigUtil.getParamValueByParam("kill.jdbc.password");
		killprocess(url, username, password, "0");

		List<String> organcds = SystemParamConfigUtil.getOrganCode();
		for (String organcd : organcds) {
			String thisurl = SystemParamConfigUtil.getParamValueByParam("jdbc.url" + organcd);
			thisurl = thisurl.replace("HealthCIS", "master");
			killprocess(thisurl, "cis", "cis", organcd);
		}
	}

	public void killprocess(String url, String username, String password, String organcd) {

		LOG.info("搜索死锁进程数据开始【" + url + "】");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> processMap = new HashMap<String, String>();
		Map<String, SpidEntity> spidMap = new HashMap<String, SpidEntity>();
		Map<String, String> blockedMap = new HashMap<String, String>();
		try {
			con = JdbcConMSS.getCon(url, username, password);
			String sql = "select spid, blocked, loginame, last_batch, status, cmd, hostname, program_name " + " from sysprocesses order by last_batch";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SpidEntity entity = new SpidEntity();
				String spid = rs.getString(1);
				String blockid = rs.getString(2);

				if (blockedMap.get(blockid) == null || "".equals(blockedMap.get(blockid)))
					blockedMap.put(blockid, spid);

				entity.setSpid(spid);
				entity.setLoginname(rs.getString(3));
				entity.setLastBatch(rs.getString(4));
				entity.setCmd(rs.getString(6));
				entity.setHostname(rs.getString(7));
				entity.setProgram_name(rs.getString(8));
				spidMap.put(spid, entity);

				if ("0".equals(blockid)) {
					continue;
				}

				processMap.put(spid, blockid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		StringBuffer topSpid = new StringBuffer();
		while (processMap.size() > 0) {
			String topValue = "";
			StringBuffer subValue = new StringBuffer();
			Iterator<String> itr = processMap.keySet().iterator();
			String key = itr.next();
			subValue.append(key);
			topValue = key;

			while (processMap.get(key) != null && !"".equals(processMap.get(key))) {
				key = processMap.get(key);
				if (subValue.indexOf(key) > 0) {
					break;
				}
				topValue = key;
				subValue.append("," + key);
			}
			// 去除已处理的spid
			processMap = cleanUpMap(processMap, subValue);

			if (topSpid.indexOf(topValue) < 0)
				topSpid.append(topValue + ",");
		}

		String[] tops = topSpid.toString().split(",");
		// 杀进程
		for (String str : tops) {
			SpidEntity entity = spidMap.get(str);
			String blockedSpid = blockedMap.get(str);
			killSpid(str, entity, blockedSpid, url, username, password, organcd);
		}
		LOG.info("死锁进程数据结束【" + url + "】");
	}

	@SuppressWarnings("resource")
	public void killSpid(String spid, SpidEntity spidEntity, String blockedSpid, String url, String username, String password, String organcd) {
		if (spid == null || "".equals(spid))
			return;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String killsql = "kill " + spid;
		try {
			con = JdbcConMSS.getCon(url, username, password);

			String sql = "select DATEDIFF(Minute,start_time,getdate()),CAST(csql.text AS varchar(255)) AS CallingSQL,er.session_id from master.sys.dm_exec_requests er WITH (NOLOCK)" + " CROSS APPLY fn_get_sql (er.sql_handle) csql where er.session_id =" + blockedSpid + " or er.session_id =" + spid;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Integer difTime = 0;
			String waitsql = "";
			while (rs.next()) {
				String thisspid = rs.getString(3);
				if (spid.equals(thisspid)) {
					waitsql = rs.getString(2);
				}
				if (blockedSpid.equals(thisspid)) {
					difTime = rs.getInt(1);
				}

			}

			if (difTime > 10) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
				String isql = "";
				if (waitsql != null && !"".equals(waitsql)) {
					// 记录sql日志
					isql = "insert into spidKillLog(id,spid,killtime,sql,loginname,cmd,hostname,programname,lastBatch,organcd)" + "values('" + uuid + "','" + spidEntity.getSpid() + "',getdate(),'" + waitsql + "','" + spidEntity.getLoginname() + "','" + spidEntity.getCmd() + "','" + spidEntity.getHostname() + "','" + spidEntity.getProgram_name() + "','" + spidEntity.getLastBatch() + "','" + organcd + "')";
				} else {
					isql = "insert into spidKillLog(id,spid,killtime,loginname,cmd,hostname,programname,lastBatch,organcd)" + "values('" + uuid + "','" + spidEntity.getSpid() + "',getdate(),'" + spidEntity.getLoginname() + "','" + spidEntity.getCmd() + "','" + spidEntity.getHostname() + "','" + spidEntity.getProgram_name() + "','" + spidEntity.getLastBatch() + "','" + organcd + "')";
				}

				// 日志数据入库
				/********************************/
				isqlsave(isql);
				ps = con.prepareStatement(killsql);
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void isqlsave(String isql) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JdbcConMSS.getCon("jdbc:sqlserver://128.1.10.91:1433;DatabaseName=DataLog", "cis", "cis");
			ps = con.prepareStatement(isql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> cleanUpMap(Map<String, String> processMap, StringBuffer subValue) {
		String[] substr = subValue.toString().split(",");
		for (String str : substr) {
			processMap.remove(str);
		}
		return processMap;
	}
}
