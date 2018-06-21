package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.entity.NetInfluence;

/**
 * <p>
 * ip影响 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public interface INetInfluenceService extends IService<NetInfluence> {

	String selectInfluenceNames(Integer id, int i);

}
