package com.wmpay.controller;



import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmpay.util.GeneralTools;
import com.wmpay.util.Msg;


@Controller
@RequestMapping("/wechat")
public class WeChatController {
	
//	@Autowired
//	WeChatBaseConfig weChatBaseConfig;
//	
//	@Autowired
//	WeChatApp payUtil;
//	
//	@Autowired
//	WechatOrderConfig orderConfig;
//	
//	@ResponseBody
//	@RequestMapping(value="/hello", method = RequestMethod.GET)
//	public String hello() {
//		return "HelloWorld";
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/getOpenIdUnionId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//	public Msg getOpenIdUnionId(@RequestParam("code") String code) {
//		payUtil.setWeChatBaseConfig(weChatBaseConfig);
//		ResponseBean result = payUtil.getOpenIdUnionId(code);
//		return result.getStatus() == 1 ? Msg.success().add("data", result.getData() ) : Msg.fail();
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/doPay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//	public Msg doPay(String openId) {
//		payUtil.setWeChatBaseConfig(weChatBaseConfig);
//		String orderNum = GeneralTools.GetUniqueSerial("TEST");
//		orderConfig.setBody("WEIXIN");
//		orderConfig.setNotify_url("http://localtion:8080/");
//		orderConfig.setOut_trade_no("TEST20032318500703047153");
//		orderConfig.setTrade_type(WeChatBaseConfig.TRADE_TYPE_JSAPI);
//		orderConfig.setOpenid(openId );
//		orderConfig.setTotal_fee(1);
//		
//		ResponseBean bean = payUtil.orderQuery(orderConfig);
//		HashMap<String, String> data = (HashMap<String, String>)bean.getData();
//		return bean.getStatus() == 1 ? Msg.success().add("data", data) : Msg.fail();
//	}
//	
	
}
