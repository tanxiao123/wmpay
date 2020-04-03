package com.wmpay.bean;

import java.util.Date;

public class WmGrade {
    private Integer wmGradeId;

    private Integer wmSchoolId;

    private String name;

    private Date createdTime;

    private Date 修改时间;

    public Integer getWmGradeId() {
        return wmGradeId;
    }

    public void setWmGradeId(Integer wmGradeId) {
        this.wmGradeId = wmGradeId;
    }

    public Integer getWmSchoolId() {
        return wmSchoolId;
    }

    public void setWmSchoolId(Integer wmSchoolId) {
        this.wmSchoolId = wmSchoolId;
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

    public Date get修改时间() {
        return 修改时间;
    }

    public void set修改时间(Date 修改时间) {
        this.修改时间 = 修改时间;
    }
}