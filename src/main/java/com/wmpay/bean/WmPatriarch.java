package com.wmpay.bean;

import java.util.Date;

public class WmPatriarch {
    private Integer wmPatriarchId;

    private Integer wmWechatId;

    private String name;

    private String mobile;

    private Date createdTime;

    private Date updatedTime;

    private String status;

    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }

    public Integer getWmWechatId() {
        return wmWechatId;
    }

    public void setWmWechatId(Integer wmWechatId) {
        this.wmWechatId = wmWechatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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
}