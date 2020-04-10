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

	/**
	 * 根据角色组查询关联信息
	 * @param wmAuthGroupId
	 * @return
	 */
	public WmAuthGroupAccess getAccessByAuthGroupId(Integer wmAuthGroupId) {
		WmAuthGroupAccess result = accessDAO.selectOne(new QueryWrapper<WmAuthGroupAccess>().eq("wm_auth_group_id", wmAuthGroupId));
		return result;
	}

	/**
	 * 根据管理员查询关联信息
	 * @param wmAdminId
	 * @return
	 */
	public WmAuthGroupAccess getAccessByAdminId(Integer wmAdminId) {
		return accessDAO.selectOne(new QueryWrapper<WmAuthGroupAccess>().eq("wm_admin_id", wmAdminId));
	}

	public void updateGroupIdById(WmAuthGroupAccess authGroupAccess) {
		int result = accessDAO.updateById(authGroupAccess);
	}

	public Boolean insertGroup(WmAuthGroupAccess authGroupAccess) {
		int result = accessDAO.insert(authGroupAccess);
		return result > 0;
	}
}
