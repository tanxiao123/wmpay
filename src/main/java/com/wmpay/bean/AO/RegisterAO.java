package com.wmpay.bean.AO;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class RegisterAO {

    @NotBlank(message = "家长姓名不可为空")
    private String wmPatriarchName;

    @NotBlank(message = "家长手机号不可为空")
    private String wmPatriarchMobile;

    @NotNull(message = "微信凭证不可为空")
    private Integer wechatId;


    public String getWmPatriarchName() {
        return wmPatriarchName;
    }

    public void setWmPatriarchName(String wmPatriarchName) {
        this.wmPatriarchName = wmPatriarchName;
    }

    public String getWmPatriarchMobile() {
        return wmPatriarchMobile;
    }

    public void setWmPatriarchMobile(String wmPatriarchMobile) {
        this.wmPatriarchMobile = wmPatriarchMobile;
    }

    public Integer getWechatId() {
        return wechatId;
    }

    public void setWechatId(Integer wechatId) {
        this.wechatId = wechatId;
    }
}
