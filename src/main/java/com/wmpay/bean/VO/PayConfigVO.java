package com.wmpay.bean.VO;

import com.wmpay.bean.WmThirdPayConfig;
import com.wmpay.bean.WmWechatPayConfig;

public class PayConfigVO {

    private WmThirdPayConfig wmThirdPayConfig;

    private WmWechatPayConfig weChatPayConfig;

    private String defaultPay;

    public WmThirdPayConfig getWmThirdPayConfig() {
        return wmThirdPayConfig;
    }

    public void setWmThirdPayConfig(WmThirdPayConfig wmThirdPayConfig) {
        this.wmThirdPayConfig = wmThirdPayConfig;
    }

    public WmWechatPayConfig getWeChatPayConfig() {
        return weChatPayConfig;
    }

    public void setWeChatPayConfig(WmWechatPayConfig weChatPayConfig) {
        this.weChatPayConfig = weChatPayConfig;
    }

    public String getDefaultPay() {
        return defaultPay;
    }

    public void setDefaultPay(String defaultPay) {
        this.defaultPay = defaultPay;
    }
}
