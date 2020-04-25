package com.wmpay.controller.admin;

import com.weimai.tools.ResponseBean;
import com.wmpay.service.WmGradeService;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 获取当前用户是否拥有年级支付权限控制
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGradePayAuth", method = RequestMethod.GET)
    public ResponseBean getGradePayAuth() {
        return AppResponse.success(wmGradeService.isPay());
    }

    /**
     * 获取当前用户是否拥有班级支付权限控制
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSquadPayAuth", method = RequestMethod.GET)
    public String getSquadPayAuth() {
        return "";
    }

}
