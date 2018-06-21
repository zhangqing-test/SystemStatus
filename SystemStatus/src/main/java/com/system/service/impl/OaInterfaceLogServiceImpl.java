package com.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.OaInterfaceLog;
import com.system.mapper.OaInterfaceLogMapper;
import com.system.service.IOaInterfaceLogService;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-03
 */
@Service
@Transactional
public class OaInterfaceLogServiceImpl extends ServiceImpl<OaInterfaceLogMapper, OaInterfaceLog> implements IOaInterfaceLogService {

}
