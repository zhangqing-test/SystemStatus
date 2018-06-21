package com.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.ServicesInfluence;
import com.system.mapper.ServicesInfluenceMapper;
import com.system.service.IServicesInfluenceService;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
@Service
@Transactional
public class ServicesInfluenceServiceImpl extends ServiceImpl<ServicesInfluenceMapper, ServicesInfluence> implements IServicesInfluenceService {

}
