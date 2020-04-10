package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmAuthGroupAccess {

    @TableId(value = "wm_auth_group_access_id", type = IdType.AUTO)
    private Integer wmAuthGroupAccessId;

    private Integer wmAuthGroupId;

    private Integer wmAdminId;

    public Integer getWmAuthGroupAccessId() {
        return wmAuthGroupAccessId;
    }

    public void setWmAuthGroupAccessId(Integer wmAuthGroupAccessId) {
        this.wmAuthGroupAccessId = wmAuthGroupAccessId;
    }

    public Integer getWmAuthGroupId() {
        return wmAuthGroupId;
    }

    public void setWmAuthGroupId(Integer wmAuthGroupId) {
        this.wmAuthGroupId = wmAuthGroupId;
    }

	public Integer getWmAdminId() {
		return wmAdminId;
	}

	public void setWmAdminId(Integer wmAdminId) {
		this.wmAdminId = wmAdminId;
	}

    
}