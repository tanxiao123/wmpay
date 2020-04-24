package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmAdditionGroupAccess {
    private Integer wmAuthGroupId;

    private Integer wmAdditionAdminId;

    @TableId(value = "wm_addition_group_access_id", type = IdType.AUTO)
    private Integer wmAdditionGroupAccessId;

    public Integer getWmAuthGroupId() {
        return wmAuthGroupId;
    }

    public void setWmAuthGroupId(Integer wmAuthGroupId) {
        this.wmAuthGroupId = wmAuthGroupId;
    }

    public Integer getWmAdditionAdminId() {
        return wmAdditionAdminId;
    }

    public void setWmAdditionAdminId(Integer wmAdditionAdminId) {
        this.wmAdditionAdminId = wmAdditionAdminId;
    }

    public Integer getWmAdditionGroupAccessId() {
        return wmAdditionGroupAccessId;
    }

    public void setWmAdditionGroupAccessId(Integer wmAdditionGroupAccessId) {
        this.wmAdditionGroupAccessId = wmAdditionGroupAccessId;
    }
}