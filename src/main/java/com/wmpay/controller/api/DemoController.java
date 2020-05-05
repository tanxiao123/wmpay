package com.wmpay.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.weimai.tools.ResponseBean;
import com.weimai.tools.wechat.WeChatApp;
import com.weimai.tools.wechat.WeChatBaseConfig;
import com.weimai.tools.wechat.WechatOrderConfig;
import com.wmpay.bean.*;
import com.wmpay.bean.AO.DemoPayAO;
import com.wmpay.bean.AO.RegisterAO;
import com.wmpay.bean.AO.WmPatriarchAO;
import com.wmpay.service.*;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import com.wmpay.util.FileUtil;
import com.wmpay.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/demo")
public class DemoController {

    @Autowired
    WmOrderService wmOrderService;

    @Autowired
    WmPatriarchService wmPatriarchService;

    @Autowired
    WmStudentService wmStudentService;

    @Autowired
    WmWechatService wmWechatService;

    WeChatBaseConfig weChatBaseConfig;

    WeChatApp payUtil = new WeChatApp();

    WechatOrderConfig orderConfig = new WechatOrderConfig();

    String host = "http://localhost:8000/api/wechat/jsapi";


    DemoController () {
        FileUtil.loadProperties("payment_config.properties");
        weChatBaseConfig = new WeChatBaseConfig(FileUtil.getPropertiesValue("wechat.pay.appid"),
                FileUtil.getPropertiesValue("wechat.pay.mchid"), FileUtil.getPropertiesValue("wechat.pay.secret"), FileUtil.getPropertiesValue("wechat.pay.paykey"));
    }

    /**
     * 获取用户信息接口
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOpenIdUnionId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getOpenIdUnionId(@RequestParam("code")String code) {
        Map<String,Object> resultMap = new HashMap<>();
        payUtil.setWeChatBaseConfig(weChatBaseConfig);
        ResponseBean result = payUtil.getOpenIdUnionId(code);
        if (result.getStatus() == 1){
            Integer wmWechatId = null;
            JSONObject jsonObject = (JSONObject) result.getData();
            String openid = (String) jsonObject.get("openid");

            // 1.添加检测当前微信表是否存在该用户信息  如果存在 那么直接返回  如果不存在  新增微信信息并返回主键
            WmWechat wmWechat = wmWechatService.selectByOpenId(openid);
            if (wmWechatService.selectByOpenId(openid) == null ){
                WmWechat dbWechat = new WmWechat();
                dbWechat.setOpenid(openid);
                wmWechatId = wmWechatService.addWmWechat(dbWechat);
            }else{
                wmWechatId = wmWechat.getWmWechatId();
            }
            // 2. 根据openid查询当前微信号是否已经绑定了家长信息
            WmPatriarchAO wmPatriarch = wmPatriarchService.getWmPatriarchInfoByOpenId(openid);
            String isReg = wmPatriarch != null ? "1" : "0";
            resultMap.put("wmWechatId", wmWechatId);
            resultMap.put("isReg", isReg);
            resultMap.put("wmPatriarch", wmPatriarch);
            return AppResponse.success(resultMap);
        }
        return AppResponse.error(ResponseEnum.NOT_PERMISSION);
    }

    /**
     * 家长注册信息
     * @param registerAO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean register(@Validated RegisterAO registerAO, BindingResult result) {
        if (result.hasErrors() ){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage());
        }
        WmWechat wechat = wmWechatService.selectByWechatId(registerAO.getWechatId() );
        if (wechat != null){
            // 添加家长信息
            WmPatriarch dbPatriarch = new WmPatriarch();
            dbPatriarch.setWmWechatId(wechat.getWmWechatId());
            dbPatriarch.setStatus("1");
            dbPatriarch.setName(registerAO.getWmPatriarchName());
            dbPatriarch.setMobile(registerAO.getWmPatriarchMobile() );
            dbPatriarch.setType("2");
            dbPatriarch.setCreatedTime(new Date());
            dbPatriarch.setUpdatedTime(new Date());
            wmPatriarchService.addWmPatriarch(dbPatriarch);
            // 回显查询数据
            return AppResponse.success(wmPatriarchService.getWmPatriarchInfoByOpenId(wechat.getOpenid() ));
        }
        return AppResponse.error(ResponseEnum.NOT_PERMISSION.status, "微信凭证不存在");
    }


    /**
     * 支付接口
     * @param demoPayAO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "doPay",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean doPay(@Validated DemoPayAO demoPayAO, BindingResult result) {
        if (result.hasErrors() ){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage());
        }

        // 查询学生信息是否存在  如果不存在则创建
        Integer wmStudentId = null;
        WmStudent wmStudent = wmStudentService.getStudentByName(demoPayAO.getWmStudentName() );
        if (wmStudent == null){
            WmStudent dbStudent = new WmStudent();
            dbStudent.setName(demoPayAO.getWmStudentName() );
            wmStudentId = wmStudentService.addStudent(dbStudent);
        }else{
            wmStudentId = wmStudent.getWmStudentId();
        }

        // 查询关联表是否存在关联数据
        WmPatriarchStudent wmPatriarchStudent = wmStudentService.getStudentPatriarchByStudentId(wmStudentId);
        if (wmPatriarchStudent != null ){
            if (!demoPayAO.getWmPatriarchId().equals(wmPatriarchStudent.getWmStudentId() )){
                return AppResponse.error(ResponseEnum.NOT_PERMISSION.status,"无权限访问,当前学生已被绑定");
            }
        }

        ResponseBean responseBean = wmWechatService.getPayAdminUserId(demoPayAO.getUserId(),"demo");
        if (responseBean.getStatus() == 1){
            HashMap<String, Object> responseData = (HashMap<String, Object>) responseBean.getData();
            // 1. 根据传入的信息 拿到openid  拿到下单金额
            String openId = demoPayAO.getOpenId();
            Integer totalFee = demoPayAO.getTotalFee();
            // 2. 调用统一下单网络API接口 测试调用 传入相对应的值
            HashMap<String,String> requestMap = new HashMap<>();
            requestMap.put("appid", (String) responseData.get("appId"));
            requestMap.put("appKey",(String) responseData.get("appKey"));
            requestMap.put("mchId", (String) responseData.get("mchId"));
            requestMap.put("mchKey",(String) responseData.get("mchKey"));
            requestMap.put("notifyUrl", "http://www.baidu.com");
            requestMap.put("body","测试商品信息");
            requestMap.put("openid", openId);
            requestMap.put("total_fee", String.valueOf(totalFee));
            String response = HttpUtil.sendHttpPost(host,requestMap);
            JSONObject responseObject = (JSONObject) JSONObject.parse(response);
            // 3. 根据返回的信息验证是否成功  如果成功  将订单写入数据库  将传入的学生信息，家长信息  新建
            if (responseObject != null && responseObject.get("status").equals(1) ){
                System.out.println(responseObject.get("data"));
                JSONObject dataObject = (JSONObject) responseObject.get("data");
                // 写入订单
                WmOrder wmOrder = new WmOrder();
                wmOrder.setOutTradeNo(String.valueOf(dataObject.get("outTradeNo") ) );
                wmOrder.setWmPatriarchId(demoPayAO.getWmPatriarchId());
                wmOrder.setBody("测试商品信息");
                wmOrder.setCreatedTime(new Date() );
                wmOrder.setUpdatedTime(new Date());
                wmOrder.setWmOrderTypeId(1);
                wmOrder.setTotalFee(totalFee);
                wmOrder.setStatus("0");
                wmOrderService.addOrder(wmOrder);
                return AppResponse.success(responseObject);
            }
            return AppResponse.error(ResponseEnum.ERROR);
        }
        return responseBean;
    }
}
