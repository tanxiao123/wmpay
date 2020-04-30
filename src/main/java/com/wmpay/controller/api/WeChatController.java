package com.wmpay.controller.api;



import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wmpay.bean.AO.WmPatriarchAO;
import com.wmpay.bean.WmPatriarch;
import com.wmpay.service.WmPatriarchService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.weimai.tools.ResponseBean;
import com.weimai.tools.wechat.WeChatApp;
import com.weimai.tools.wechat.WeChatBaseConfig;
import com.weimai.tools.wechat.WechatOrderConfig;
import com.wmpay.util.FileUtil;
import com.wmpay.util.GeneralTools;
import com.wmpay.util.Msg;


@Controller
@RequestMapping("/wechat")
public class WeChatController {

	@Autowired
	WmPatriarchService wmPatriarchService;
	
	WeChatBaseConfig weChatBaseConfig;

	WeChatApp payUtil = new WeChatApp();

	WechatOrderConfig orderConfig = new WechatOrderConfig();

	@ResponseBody
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String hello() {
		return "HelloWorld";
	}

	public WeChatController() {
		FileUtil.loadProperties("payment_config.properties");
		weChatBaseConfig = new WeChatBaseConfig(FileUtil.getPropertiesValue("wechat.pay.appid"),
				FileUtil.getPropertiesValue("wechat.pay.mchid"), FileUtil.getPropertiesValue("wechat.pay.secret"), FileUtil.getPropertiesValue("wechat.pay.paykey"));
	}

	@ResponseBody
	@RequestMapping(value="/getOpenIdUnionId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseBean getOpenIdUnionId(@RequestParam("code") String code) {
		WmPatriarchAO wmPatriarchAO = new WmPatriarchAO();
		payUtil.setWeChatBaseConfig(weChatBaseConfig);
		ResponseBean result = payUtil.getOpenIdUnionId(code);
		if (result.getStatus() == 1){ // 用户授权成功  查询是否存在该用户  不存在注册
			JSONObject jsonObject = (JSONObject) result.getData();
			String openid = (String) jsonObject.get("openid");
			wmPatriarchAO = wmPatriarchService.getWmPatriarchInfoByOpenId(openid);
			if (wmPatriarchAO == null){
				// 新增用户操作

			}
			return AppResponse.success(wmPatriarchAO);
		}
		return AppResponse.error(ResponseEnum.ERROR);
	}

	@ResponseBody
	@RequestMapping(value="/doPay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Msg doPay(String openId) {

		payUtil.setWeChatBaseConfig(weChatBaseConfig);
		String orderNum = GeneralTools.GetUniqueSerial("TEST");
		orderConfig.setAppid(weChatBaseConfig.getAppId() );
		orderConfig.setMch_id(weChatBaseConfig.getMchId() );
		orderConfig.setBody("WEIXIN");
		orderConfig.setNotify_url("http://localtion:8080/");
		orderConfig.setOut_trade_no(orderNum);
		orderConfig.setTrade_type(WeChatBaseConfig.TRADE_TYPE_JSAPI);
		orderConfig.setOpenid(openId);
		orderConfig.setTotal_fee(1);

		ResponseBean bean = payUtil.createOrder(orderConfig);
		HashMap<String, String> data = (HashMap<String, String>)bean.getData();
		return bean.getStatus() == 1 ? Msg.success().add("data", data) : Msg.fail();
	}

	
}
