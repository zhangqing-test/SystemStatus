package com.system.service;

import java.util.Map;

import com.system.quartz.bean.SpidEntity;

public interface DeadlockService {

	public void queryDeadlock(Map<String, String> processMap, Map<String, SpidEntity> spidMap, Map<String, String> blockedMap, String url, String username, String password, String organcd);

	public String[] queryNeededKillSpids(Map<String, String> processMap);

	public String kill(String spid, String code);
}
