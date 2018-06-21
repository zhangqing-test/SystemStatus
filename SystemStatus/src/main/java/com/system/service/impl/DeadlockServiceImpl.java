package com.system.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.common.util.JdbcConMSS;
import com.system.common.util.SystemParamConfigUtil;
import com.system.quartz.bean.SpidEntity;
import com.system.service.DeadlockService;

@Service
@Transactional
public class DeadlockServiceImpl implements DeadlockService {

	public void queryDeadlock(Map<String, String> processMap, Map<String, SpidEntity> spidMap, Map<String, String> blockedMap, String url, String username, String password, String organcd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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
	}

	public String[] queryNeededKillSpids(Map<String, String> processMap) {
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

		return topSpid.toString().split(",");
	}

	public Map<String, String> cleanUpMap(Map<String, String> processMap, StringBuffer subValue) {
		String[] substr = subValue.toString().split(",");
		for (String str : substr) {
			processMap.remove(str);
		}
		return processMap;
	}

	public String kill(String spid, String code) {
		String flag = null;
		String url = SystemParamConfigUtil.getParamValueByParam("jdbc.url" + code);
		url = url.replace("HealthCIS", "master");
		String username = "cis";
		String password = "cis";
		Connection con = null;
		PreparedStatement ps = null;
		String killsql = "kill " + spid;
		try {
			con = JdbcConMSS.getCon(url, username, password);
			ps = con.prepareStatement(killsql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			flag = "false";
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
		flag = "success";
		return flag;
	}

}
