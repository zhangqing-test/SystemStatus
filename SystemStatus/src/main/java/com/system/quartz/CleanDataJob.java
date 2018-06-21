package com.system.quartz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.constants.StatusConstants;
import com.system.common.constants.SystemConfig;
import com.system.entity.PingAndPortInfo;
import com.system.entity.ServerInfo;
import com.system.service.IPingAndPortInfoService;
import com.system.service.IServerInfoService;

@SuppressWarnings("unused")
public class CleanDataJob extends QuartzJobBean {
	private static final Logger LOG = LoggerFactory.getLogger(CleanDataJob.class);

	private int timeout;
	private static int i = 0;

	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	private IPingAndPortInfoService pingAndPortInfoService;

	private IServerInfoService serverInfoService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		pingAndPortInfoService = webApplicationContext.getBean(IPingAndPortInfoService.class);
		serverInfoService = webApplicationContext.getBean(IServerInfoService.class);

		LOG.info("清理数据任务开始>>>>>>");
		Integer diff = Integer.valueOf(SystemConfig.getProperty(StatusConstants.CLEAN_DATE));
		// 获取时间
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -diff);
		Date date = cal.getTime();

		// 删除serverInfo数据
		EntityWrapper<ServerInfo> sew = new EntityWrapper<ServerInfo>();
		sew.le("datetime", date);
		boolean sdelete = serverInfoService.delete(sew);
		LOG.info("删除serverInfo表:" + sdelete);

		// 删除pingInfo数据
		EntityWrapper<PingAndPortInfo> pew = new EntityWrapper<PingAndPortInfo>();
		pew.le("datetime", date);
		boolean pdelete = pingAndPortInfoService.delete(pew);
		LOG.info("删除PingAndPortInfo表:" + pdelete);

		// 删除LOG日志
		// 时间后缀
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String suffix = sdf.format(new Date());

		String TOMCAT_PATH = System.getProperty("catalina.base") + File.separator;// 服务器路径

		File logs = new File(TOMCAT_PATH + "logs");

		if (logs.exists() && logs.isDirectory()) {
			File[] listFiles = logs.listFiles();
			for (File file : listFiles) {
				if (!file.getName().contains(suffix)) {
					if (!file.getName().equals("systemStatus")) {
						delete(file);
					}
				}
			}
		}
	}

	/**
	 * 删除目录或文件
	 * 
	 * @param target
	 * @return
	 */
	private static boolean delete(File target) {
		boolean result = false;
		if ((target != null) && (target.exists())) {
			if (target.isFile()) {
				result = target.delete();
			} else if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (int i = 0; i < files.length && delete(files[i]); i++) {
					delete(files[i]);
				}
				return result;
			}
		}
		return result;
	}

}
