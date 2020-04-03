package com.wmpay.bean;

import java.util.Date;

public class WmOrderType {
    private Integer wmOrderTypeId;

    private String name;

    private String title;

    private String status;

    private Date createdTime;

    private Date updatedTime;

    public Integer getWmOrderTypeId() {
        return wmOrderTypeId;
    }

    public void setWmOrderTypeId(Integer wmOrderTypeId) {
        this.wmOrderTypeId = wmOrderTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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