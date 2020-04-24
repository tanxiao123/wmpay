package com.wmpay.controller.agency;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmAdmin;
import com.wmpay.common.AdminCommon;
import com.wmpay.service.WmAdditionAdminService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Select;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 代理用户控制器
 */
@Controller("agency")
@RequestMapping("/agency/system")
public class SystemController {

    @Autowired
    WmAdditionAdminService wmAdditionAdminService;

    /**
     * 加载代理用户登陆视图
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "agency/login";
    }

    /**
     * 代理用户登陆API
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "doLogin",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean doLogin(@Validated(value = {Select.class}) WmAdditionAdmin admin, BindingResult result) {
        if (result.hasErrors()) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage());
        }
        return wmAdditionAdminService.login(admin);
    }

    /**
     * 代理用户首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        Integer adminId = ((WmAdditionAdmin) (request.getSession().getAttribute(AdminCommon.USER_SESSION))).getWmAdditionAdminId();
        String menu = wmAdditionAdminService.getMenuHTML(adminId);
        request.setAttribute("menu", menu);
        return "agency/index";
    }
}
