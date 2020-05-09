package com.wmpay.bean.AO;

import java.util.Date;

public class DetailOrderAO {

    private Integer wmOrderId;

    private String outTradeNo;

    private String body;

    private Integer totalFee;

    private Date createdTime;

    private String status;

    private Integer wmPatriarchId;

    private String wmPatriarchName;

    private String wmAdditionAdminId;

    private String wmStudentName;

    public Integer getWmOrderId() {
        return wmOrderId;
    }

    public void setWmOrderId(Integer wmOrderId) {
        this.wmOrderId = wmOrderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }

    public String getWmPatriarchName() {
        return wmPatriarchName;
    }

    public void setWmPatriarchName(String wmPatriarchName) {
        this.wmPatriarchName = wmPatriarchName;
    }

    public String getWmAdditionAdminId() {
        return wmAdditionAdminId;
    }

    public void setWmAdditionAdminId(String wmAdditionAdminId) {
        this.wmAdditionAdminId = wmAdditionAdminId;
    }

    public String getWmStudentName() {
        return wmStudentName;
    }

    public void setWmStudentName(String wmStudentName) {
        this.wmStudentName = wmStudentName;
    }
}
