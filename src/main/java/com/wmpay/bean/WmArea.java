package com.wmpay.bean;

import java.util.Date;

public class WmArea {
    private Integer wmAreaId;

    private String name;

    private String code;

    private Integer parentId;

    private Date createdTime;

    private Date updatedTime;

    public Integer getWmAreaId() {
        return wmAreaId;
    }

    public void setWmAreaId(Integer wmAreaId) {
        this.wmAreaId = wmAreaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}