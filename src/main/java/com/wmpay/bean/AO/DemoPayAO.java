package com.wmpay.bean.AO;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class DemoPayAO {

    // 订单金额
    @NotNull(message = "订单金额不可为空")
    private Integer totalFee;

    @NotNull(message = "当前下单家长不可为空")
    private Integer wmPatriarchId;

    // 下单人OPENID
    @NotBlank(message = "openId不可为空")
    private String openId;

    // 学生姓名
    @NotBlank(message = "学生姓名不可为空")
    private String wmStudentName;

    @NotNull(message = "所选用户ID不可为空")
    private Integer userId;


    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWmStudentName() {
        return wmStudentName;
    }

    public void setWmStudentName(String wmStudentName) {
        this.wmStudentName = wmStudentName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
