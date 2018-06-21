package com.system.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseController
 * 所有控制器的基类，里面有控制类必须的信息。控制类在处理流程的过程中，需要仔细规划哪些行为是在控制器中直接操作，
 * 哪些行为则是在对应时间点或者事件的情况下触发执行，而不是将所有可能通用的部分都紧耦合到基类中来。
 * 目前基类中支持的有：
 * Created by yang on 2017/6/27.
 */
public abstract class BaseController {
    @SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
}
