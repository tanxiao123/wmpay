package com.wmpay.controller.admin;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.service.WmAdditionAdminService;
import com.wmpay.service.WmGradeService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import com.wmpay.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * 权限控制器
 *  验证用户是否拥有编辑权限
 *  只有管理员/学校可以开通 支付配置 年级  班级权限均不可开通使用
 */
@Controller
@RequestMapping("/admin/auth/")
public class AuthController {

    @Autowired
    WmGradeService wmGradeService;

    @Autowired
    WmAdditionAdminService wmAdditionAdminService;

    @Autowired
    HttpServletRequest request;

    /**
     * 获取当前用户是否拥有年级支付权限控制
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGradePayAuth",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getGradePayAuth() {
        return AppResponse.success(wmGradeService.isPay());
    }

    /**
     * 获取当前用户是否拥有班级支付权限控制
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSquadPayAuth",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public String getSquadPayAuth() {
        return "";
    }


    /**
     * 检测用户是否开通了账号权限
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isAddition",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean isAddition(@RequestParam("type") String type, @RequestParam("userId")Integer userId) {
        if (type == null || "".equals(type) ){
            AppResponse.error(ResponseEnum.FIELD_ERROR.status,"用户类型不可为空");
        }
        if (userId == null || userId <= 0){
            AppResponse.error(ResponseEnum.FIELD_ERROR.status, "用户ID不可为空");
        }

        if (!wmAdditionAdminService.isFound(type, userId) ){ // 未注册
            // 验证当前用户类型
            AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE) );
            switch (adminType){
                case WM_SYSTEM_ADMIN:
                    return AppResponse.error(ResponseEnum.SUCCESS_NOT_FOUND_ADD);
                case WM_ADDITION_ADMIN:
                    // 验证当前登陆用户权限
                    WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                    if (admin.getType() != null) {
                        String userType = admin.getType();
                        switch (userType) {
                            case "1": // 学校类型
                                return AppResponse.error(ResponseEnum.SUCCESS_NOT_FOUND_ADD);
                            default: // 无权限  返回正常状态
                                return AppResponse.success();
                        }
                    }
            }
            return AppResponse.success();
        }else{ // 已注册
            return AppResponse.error(ResponseEnum.SUCCESS_FOUND);
        }
    }

    /**
     * 检测用户是否开通了支付权限
     * @param type
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isAdditionPay",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean isAdditionPay(@RequestParam("type") String type, @RequestParam("userId")Integer userId) {

        Auth auth = new Auth();

        if (type == null || "".equals(type) ){
            AppResponse.error(ResponseEnum.FIELD_ERROR.status,"用户类型不可为空");
        }
        if (userId == null || userId <= 0){
            AppResponse.error(ResponseEnum.FIELD_ERROR.status, "用户ID不可为空");
        }


        WmAdditionAdmin wmAdditionResult = wmAdditionAdminService.getWmAddition(type, userId);
        if (wmAdditionResult != null){
            // 检测下面支付配置是否存在在
            if (wmAdditionResult.getWmThirdPayConfigId() != null && wmAdditionResult.getWmWechatPayConfigId() != null){
                return AppResponse.error(ResponseEnum.SUCCESS_PAY);
            }else{
                return auth.authCheck(request);
            }
        }else{
            return auth.authCheck(request).getStatus() >= 1 ?  AppResponse.error(ResponseEnum.OK_ACCESS) : AppResponse.error(ResponseEnum.NO_ACCESS);
        }


    }

    /**
     * 根据userId获取代理用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAdditionAdminByUserId",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getAdditionAdminByUserId(@RequestParam("userId")Integer userId, @RequestParam("type")String type) {
        return AppResponse.success(wmAdditionAdminService.getWmAdditionByUserId(userId, type) );
    }

    /**
     * 开通支付配置
     * @param userId
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addPayConfig",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean addPayConfig(@RequestParam("userId") Integer userId, @RequestParam("type")String type) {
        if (userId == null){
            return AppResponse.error(ResponseEnum.NOT_PERMISSION);
        }
        if (wmAdditionAdminService.getWmAdditionByUserId(userId,type) == null){
            return AppResponse.error(ResponseEnum.NOT_PERMISSION.status, "无该账户信息，请先添加账户后开通支付");
        }
        WmAdditionAdmin result = wmAdditionAdminService.getWmAdditionByUserId(userId,type);
        if (result != null){
            if (wmAdditionAdminService.addPayConfig(result.getWmAdditionAdminId() ) ){
                return AppResponse.success();
            }
        }
        return AppResponse.error(ResponseEnum.ERROR);
    }

}
