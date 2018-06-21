package com.system.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.web.vo.ProcessInfo;

public class SingleModel {

	private Map<String, List<ProcessInfo>> process = new HashMap<String, List<ProcessInfo>>();

	public Map<String, List<ProcessInfo>> getProcess() {
		return process;
	}

	public void setProcess(Map<String, List<ProcessInfo>> process) {
		this.process = process;
	}

	private static SingleModel singleModel = null;

	public static SingleModel getInstance() {

		if (singleModel == null) {
			singleModel = new SingleModel();
		}

		return singleModel;
	}

}
