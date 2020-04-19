package com.wmpay.bean.VO;

import java.util.Date;

public class OrderVO {

    // 订单ID
    private Integer wmOrderId;

    // 家长姓名
    private String wmPatriarchName;

    // 支付类型
    private String payTypeName;

    // 家长ID
    private Integer wmPatriarchId;

    // 订单类型ID
    private Integer wmOrderTypeId;

    // 商户订单编号
    private String outTradeNo;

    // 平台订单编号
    private String transactionId;

    // 商品描述
    private String body;

    // 下单金额
    private Integer totalFee;

    // 终端下单IP
    private String spbillCreateIp;

    // 创建时间
    private Date createdTime;

    public Integer getWmOrderId() {
        return wmOrderId;
    }

    public void setWmOrderId(Integer wmOrderId) {
        this.wmOrderId = wmOrderId;
    }

    public String getWmPatriarchName() {
        return wmPatriarchName;
    }

    public void setWmPatriarchName(String wmPatriarchName) {
        this.wmPatriarchName = wmPatriarchName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
