package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.winwin.pay.bean.request.PayOrderQueryRequest;
import com.winwin.pay.bean.request.WeixinJsPayRequest;
import com.winwin.pay.bean.result.PayOrderQueryResult;
import com.winwin.pay.bean.result.WeixinJsPayResult;
import com.winwin.pay.config.PayConfig;
import com.winwin.pay.exception.PayException;
import com.winwin.pay.service.impl.PayServiceApacheHttpImpl;
import com.wmpay.util.AppResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller("payOrderController")
@RequestMapping("/api/payOrder/")
public class PayOrderController extends PayServiceApacheHttpImpl {

    public PayOrderController() {

    }

    @ResponseBody
    @RequestMapping(value = "doPay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean doPay() throws PayException {
        String outTradeNo = String.valueOf(new Date().getTime() );

        System.out.println("生成的订单号为："+outTradeNo);

        PayConfig config = new PayConfig();
        config.setApiBaseUri("http://openapi.cuntutu.com/pay/gateway");
        config.setVersion("1.0.0");
        config.setSignType("MD5");
        config.setAppId("12012428");
        config.setAppKey("b8bf970246629b88c8d9f832c149529a");
        config.setDeviceId("a42n7");

        super.setConfig(config);
        WeixinJsPayRequest request =  new WeixinJsPayRequest();
        request.setBody("测试商品");
        request.setOutTradeNo( outTradeNo );
        request.setTotalFee(1);
        request.setSpbillCreateIp("192.168.6.100");
        request.setAppid("12012428");
        request.setOpenId("oe0UO5HA57SyxlD1l9x3DEZk1UCw");
        request.setNotifyUrl("http://localhost:8080");

//        PayOrderQueryRequest orderQueryRequest = new PayOrderQueryRequest();
//        orderQueryRequest.setOutTradeNo(outTradeNo);
//        orderQueryRequest.setAppid("12012428");
//        orderQueryRequest.setDeviceId("a42n7");

        WeixinJsPayResult result = super.weixinJsPay(request);
        //ayOrderQueryResult result = super.weixinJsPay(orderQueryRequest);
        System.out.println(result);
        return AppResponse.success(result);
    }
}
