package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wmpay.template.Update;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class WmThirdPayConfig {

    @TableId(value = "wm_third_pay_config_id", type = IdType.AUTO)
    private Integer wmThirdPayConfigId;


    @NotBlank(message = "网关地址不可为空")
    private String apiBaseUri;

    @NotBlank(message = "应用ID不可为空")
    private String appId;

    @NotBlank(message = "应用密钥不可为空")
    private String appKey;

    @NotBlank(message = "设备ID不可为空")
    private String deviceId;


    public Integer getWmThirdPayConfigId() {
        return wmThirdPayConfigId;
    }

    public void setWmThirdPayConfigId(Integer wmThirdPayConfigId) {
        this.wmThirdPayConfigId = wmThirdPayConfigId;
    }


    public String getApiBaseUri() {
        return apiBaseUri;
    }

    public void setApiBaseUri(String apiBaseUri) {
        this.apiBaseUri = apiBaseUri;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
