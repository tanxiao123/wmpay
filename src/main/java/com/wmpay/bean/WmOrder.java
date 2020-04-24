package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class WmOrder {
    @TableId(value = "wm_order_id", type = IdType.AUTO)
    private Integer wmOrderId;

    private Integer wmPatriarchId;

    private Integer wmOrderTypeId;

    private Integer wmAdditionAdminId;

    private String outTradeNo;

    private String transactionId;

    private String body;

    private Integer totalFee;

    private String spbillCreateIp;

    private Date createdTime;

    private Date updatedTime;

    private String status;

    public Integer getWmOrderId() {
        return wmOrderId;
    }

    public void setWmOrderId(Integer wmOrderId) {
        this.wmOrderId = wmOrderId;
    }

    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }

    public Integer getWmOrderTypeId() {
        return wmOrderTypeId;
    }

    public void setWmOrderTypeId(Integer wmOrderTypeId) {
        this.wmOrderTypeId = wmOrderTypeId;
    }

    public Integer getWmAdditionAdminId() {
        return wmAdditionAdminId;
    }

    public void setWmAdditionAdminId(Integer wmAdditionAdminId) {
        this.wmAdditionAdminId = wmAdditionAdminId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp == null ? null : spbillCreateIp.trim();
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