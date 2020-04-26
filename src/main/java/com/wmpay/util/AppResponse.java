package com.wmpay.util;

import com.weimai.tools.ResponseBean;
import com.wmpay.template.ResponseEnum;

import javax.xml.ws.Response;

public class AppResponse {

    private static ResponseBean bean = new ResponseBean();

    public static ResponseBean success(Object data) {
        bean.setStatus(ResponseEnum.SUCCESS.status);
        bean.setCusMsg(ResponseEnum.SUCCESS.msg);
        bean.setTipMsg(ResponseEnum.SUCCESS.msg);
        bean.setData(data);
        return bean;
    }

    public static ResponseBean success() {
        bean.setStatus(ResponseEnum.SUCCESS.status);
        bean.setCusMsg(ResponseEnum.SUCCESS.msg);
        bean.setTipMsg(ResponseEnum.SUCCESS.msg);
        bean.setData(null);
        return bean;
    }

    public static ResponseBean error(Integer status, String msg) {
        bean.setStatus(status);
        bean.setCusMsg(msg);
        bean.setTipMsg(msg);
        bean.setData(null);
        return bean;
    }

    public static ResponseBean error(ResponseEnum enums) {
        bean.setStatus(enums.status);
        bean.setCusMsg(enums.msg);
        bean.setTipMsg(enums.msg);
        bean.setData(null);
        return bean;
    }

    public static ResponseBean error(ResponseEnum enums, Object data) {
        bean.setStatus(enums.status);
        bean.setCusMsg(enums.msg);
        bean.setTipMsg(enums.msg);
        bean.setData(data);
        return bean;
    }
}
