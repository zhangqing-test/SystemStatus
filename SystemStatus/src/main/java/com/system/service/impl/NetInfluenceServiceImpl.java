package com.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.NetInfluence;
import com.system.mapper.NetInfluenceMapper;
import com.system.service.INetInfluenceService;

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
public class NetInfluenceServiceImpl extends ServiceImpl<NetInfluenceMapper, NetInfluence> implements INetInfluenceService {

	@Autowired
	private NetInfluenceMapper mapper;

	public String selectInfluenceNames(Integer id, int type) {
		List<NetInfluence> list = mapper.selectList(new EntityWrapper<NetInfluence>().eq("relation_id", id).eq("type", type));
		if (null != list && list.size() > 0) {
			String[] arr = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				arr[i] = list.get(i).getIp();
			}
			String str = StringUtils.join(arr, ",");
			return str;
		} else {
			return null;
		}

	}

}
