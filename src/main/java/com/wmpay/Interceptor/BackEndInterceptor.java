package com.wmpay.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wmpay.bean.WmAdmin;
import com.wmpay.common.AdminCommon;

public class BackEndInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		WmAdmin admin =  (WmAdmin) request.getSession().getAttribute(AdminCommon.USER_SESSION);
		if (admin != null) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/views/admin/login.jsp");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
