package com.wmpay.bean;

import java.util.Date;

public class WmSchool {
    private Integer wmSchoolId;

    private Integer wmAreaId;

    private String name;

    private Integer parenyId;

    private Date createdTime;

    public Integer getWmSchoolId() {
        return wmSchoolId;
    }

    public void setWmSchoolId(Integer wmSchoolId) {
        this.wmSchoolId = wmSchoolId;
    }

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

    public Integer getParenyId() {
        return parenyId;
    }

    public void setParenyId(Integer parenyId) {
        this.parenyId = parenyId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}