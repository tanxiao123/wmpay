package com.wmpay.bean.VO;

import java.util.Date;

public class GradeVO {
    private Integer wmGradeId;

    private Integer wmSchoolId;

    private String name;

    private String status;

    private Date createdTime;

    private Date updatedTime;

    private String wmSchoolName;

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
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getWmSchoolName() {
        return wmSchoolName;
    }

    public void setWmSchoolName(String wmSchoolName) {
        this.wmSchoolName = wmSchoolName;
    }
}
