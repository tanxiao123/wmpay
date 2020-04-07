package com.wmpay.controller.admin;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdmin;
import com.wmpay.bean.WmAuthGroup;
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
	public Map<String,Object> permissionList(@Valid PageTools pageTools, BindingResult result) {
		ResponseBean response = new ResponseBean();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (result.hasErrors() ) {
			response.setStatus(-3);
			response.setCusMsg(result.getFieldError().getDefaultMessage());
			response.setTipMsg(result.getFieldError().getDefaultMessage());
			return resultMap;
		}
		
		IPage<WmAuthGroup> serviceResponse = wmAuthGroupService.getAuthGroupList(pageTools);
		response.setStatus(1);
		response.setCusMsg("SUCCESS");
		response.setTipMsg("SUCCESS");
		resultMap.put("data", serviceResponse.getRecords() );
		resultMap.put("recordsTotal", serviceResponse.getPages() );
		resultMap.put("recordsFiltered", serviceResponse.getTotal());
		response.setData(resultMap);
		return resultMap;
	}

	/**
	 * 添加角色组
	 * @param wmAuthGroup
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "addPermission", method = RequestMethod.POST)
	public ResponseBean addPermission(@Valid WmAuthGroup wmAuthGroup, BindingResult result) {
		ResponseBean responseBean = new ResponseBean();
		if (result.hasErrors() ){
			responseBean.setStatus(-3);
			responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
			responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
			return responseBean;
		}
		if (wmAuthGroupService.addPermission(wmAuthGroup)){
			responseBean.setStatus(1);
			responseBean.setCusMsg("SUCCESS");
			responseBean.setTipMsg("SUCCESS");
		}else{
			responseBean.setStatus(-2);
			responseBean.setCusMsg("添加角色失败");
			responseBean.setCusMsg("添加角色失败");
		}
		return responseBean;
	}
	
	
	
}
