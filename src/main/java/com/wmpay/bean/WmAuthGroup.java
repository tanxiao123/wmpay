package com.wmpay.bean;

import java.util.Date;

public class WmAuthGroup {
    private Integer wmAuthGroupId;

    private Integer parentId;

    private String name;

    private Date createdTime;

    private Date updatedTime;

    private String status;

    private String rules;

    public Integer getWmAuthGroupId() {
        return wmAuthGroupId;
    }

    public void setWmAuthGroupId(Integer wmAuthGroupId) {
        this.wmAuthGroupId = wmAuthGroupId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }
}