package com.wmpay.controller.admin;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.VO.PayConfigVO;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmThirdPayConfig;
import com.wmpay.bean.WmWechatPayConfig;
import com.wmpay.service.WmAdditionAdminService;
import com.wmpay.service.WmPayConfigurationService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Update;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/pay")
public class PayController {

    @Autowired
    WmPayConfigurationService wmPayConfigurationService;

    @Autowired
    WmAdditionAdminService wmAdditionAdminService;

    /**
     * 跳转用户编辑支付配置界面
     * @param wmKeyId
     * @param request
     * @return
     */
    @RequestMapping(value = "getEditSysPayView", method = RequestMethod.GET)
    public String getEditSysPayView(@RequestParam("wmKeyId")Integer wmKeyId, HttpServletRequest request) {
        if (wmKeyId == null){
            request.setAttribute("errorMessage", "抱歉，没有找到权限信息");
            return "error-500";
        }
        PayConfigVO result = wmPayConfigurationService.getPayConfig(wmKeyId);
        request.setAttribute("result", result);
        request.setAttribute("wmKeyId", wmKeyId);
        return "/admin/pay/edit";
    }


    /**
     * 编辑第三方支付API
     * @param payConfiguration
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editThirdPayConfig", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean editThirdPayConfig(@Validated(value = {Update.class}) @RequestBody WmThirdPayConfig payConfiguration) {
        if (wmPayConfigurationService.editThirdPayConfig(payConfiguration) ){
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 编辑微信支付
     * @param payConfiguration
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editWechatPayConfig", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean editWechatPayConfig(@Validated(value = {Update.class}) @RequestBody WmWechatPayConfig payConfiguration) {
        if (wmPayConfigurationService.editWechatPayConfig(payConfiguration) ){
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 修改默认配置接口
     * @param wmKeyId
     * @param defaultId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editPayDefault", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean editPayDefault(@RequestParam("wmKeyId")Integer wmKeyId, @RequestParam("defaultId") String defaultId) {
        if (wmKeyId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "默认用户ID不可为空");
        }
        if (defaultId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "默认支付信息不可为空");
        }

        WmAdditionAdmin additionAdmin = new WmAdditionAdmin();
        additionAdmin.setWmAdditionAdminId(wmKeyId);
        additionAdmin.setIsDefaultPay(defaultId);

        if (wmAdditionAdminService.updateAdditionById(additionAdmin) ){
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 生成设备编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "makeDeviceId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean makeDeviceId() {
        String deviceId = wmPayConfigurationService.getDeviceId();
        return "".equals(deviceId) ? AppResponse.error(ResponseEnum.ERROR) : AppResponse.success(deviceId);
    }
}
