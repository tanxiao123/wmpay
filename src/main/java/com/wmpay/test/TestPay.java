//package com.wmpay.test;
//
//import com.winwin.pay.bean.request.PayOrderQueryRequest;
//import com.winwin.pay.bean.request.WeixinJsPayRequest;
//import com.winwin.pay.bean.request.WeixinQrPayRequest;
//import com.winwin.pay.bean.result.PayOrderQueryResult;
//import com.winwin.pay.bean.result.WeixinJsPayResult;
//import com.winwin.pay.config.PayConfig;
//import com.winwin.pay.exception.PayException;
//import com.winwin.pay.service.impl.PayServiceAbstractImpl;
//import com.winwin.pay.service.impl.PayServiceApacheHttpImpl;
//
//public class TestPay extends PayServiceApacheHttpImpl {
//
//    public void pay() throws PayException {
//        PayConfig config = new PayConfig();
//        config.setApiBaseUri("http://openapi.cuntutu.com/pay/gateway");
//        config.setVersion("1.0.0");
//        config.setSignType("MD5");
//        config.setAppId("12012428");
//        config.setAppKey("b8bf970246629b88c8d9f832c149529a");
//        config.setDeviceId("a42n7");
//
//        super.setConfig(config);
//
//        WeixinJsPayRequest request =  new WeixinJsPayRequest();
//        request.setBody("测试商品");
//        request.setOutTradeNo("TEST00000000000");
//        request.setTotalFee(1);
//        request.setSpbillCreateIp("192.168.6.100");
//        request.setAppid("12012428");
//        request.setOpenId("oe0UO5HA57SyxlD1l9x3DEZk1UCw");
//        request.setNotifyUrl("http://localhost:8080");
//
//        PayOrderQueryRequest orderQueryRequest = new PayOrderQueryRequest();
//        orderQueryRequest.setOutTradeNo("TEST00000000000");
//        orderQueryRequest.setAppid("12012428");
//        orderQueryRequest.setDeviceId("a42n7");
//
////        super.weixinQrPay(request);
//        PayOrderQueryResult result = super.queryOrder(orderQueryRequest);
//        System.out.println(result);
//    }
//
//
//}
