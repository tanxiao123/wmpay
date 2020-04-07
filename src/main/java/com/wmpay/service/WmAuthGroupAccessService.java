package com.wmpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmpay.bean.WmAuthGroupAccess;
import com.wmpay.dao.WmAuthGroupAccessDAO;

@Service
public class WmAuthGroupAccessService {

	@Autowired
	private WmAuthGroupAccessDAO accessDAO;
	
	public WmAuthGroupAccess getAccessByAuthGroupId(Integer wmAuthGroupId) {
		WmAuthGroupAccess result = accessDAO.selectOne(new QueryWrapper<WmAuthGroupAccess>().eq("wm_auth_group_id", wmAuthGroupId));
		return result;
	}
}
