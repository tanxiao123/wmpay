package com.wmpay.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class WmWechatPayConfig {

    @TableId(value = "wm_wechat_pay_config_id", type = IdType.AUTO)
    private Integer wmWeChatPayConfigId;

    private String appId;

    private String appKey;

    private String mchId;

    private String mchKey;

    private String payType;


    public Integer getWmWeChatPayConfigId() {
        return wmWeChatPayConfigId;
    }

    public void setWmWeChatPayConfigId(Integer wmWeChatPayConfigId) {
        this.wmWeChatPayConfigId = wmWeChatPayConfigId;
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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
