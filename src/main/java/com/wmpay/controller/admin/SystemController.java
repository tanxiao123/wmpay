package com.wmpay.controller.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdmin;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmAdminService;
import com.wmpay.service.WmAuthGroupService;

@Controller
@RequestMapping("/admin/system")
public class SystemController {
	
	@Autowired
	WmAdminService wmAdminService;
	
	
	@Autowired
	WmAuthGroupService wmAuthGroupService;
	
	/**
	 *  登陆
	 * @param wmAdmin
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="doLogin", method = RequestMethod.POST)
	public ResponseBean doAdminLogin(@Valid WmAdmin wmAdmin, BindingResult result) {
		if ( result.hasErrors() ) {
			ResponseBean responseError = new ResponseBean();
			responseError.setStatus(-3);
			responseError.setCusMsg(result.getFieldError().getDefaultMessage());
			responseError.setTipMsg(result.getFieldError().getDefaultMessage());
			return responseError;
		}else {
			return wmAdminService.login(wmAdmin);
		}
	}
	
	/**
	 *   跳转首页
	 * @return
	 */
	@RequestMapping(value = "index")
	public String redirectIndex(HttpServletRequest request, HttpServletResponse response)
	{
		Integer adminId = ((WmAdmin) (request.getSession().getAttribute(AdminCommon.USER_SESSION))).getWmAdminId();
		String menu = wmAdminService.getMenuHTML(adminId);
		request.setAttribute("menu", menu);
		return "/admin/index";
	}
	
	
	
	//====================================================权限模块==========================================================//
	
	/**
	 *  跳转权限页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "permission")
	public String redirectAuth(HttpServletRequest request, HttpServletResponse response) {
		return "/admin/permission";
	}
	
	/**
	 * 获取权限列表
	 * @param pageTools
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "permissionList", method = RequestMethod.GET)
	public ResponseBean permissionList(@Valid PageTools pageTools, BindingResult result) {
		ResponseBean response = new ResponseBean();
		if (result.hasErrors() ) {
			response.setStatus(-3);
			response.setCusMsg(result.getFieldError().getDefaultMessage());
			response.setTipMsg(result.getFieldError().getDefaultMessage());
			return response;
		}
		response.setStatus(1);
		response.setCusMsg("SUCCESS");
		response.setTipMsg("SUCCESS");
		response.setData(wmAuthGroupService.getAuthGroupList(pageTools));
		return response;
	}
	
	
	
}
