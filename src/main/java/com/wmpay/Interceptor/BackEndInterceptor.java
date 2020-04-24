package com.wmpay.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.common.AdminTypeEnum;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wmpay.bean.WmAdmin;
import com.wmpay.common.AdminCommon;

public class BackEndInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object admin = null;
        if (request.getSession().getAttribute(AdminCommon.USER_TYPE) != null) {
            // 验证类型
            AdminTypeEnum adminTypeEnum = (AdminTypeEnum) request.getSession().getAttribute(AdminCommon.USER_TYPE);
            switch (adminTypeEnum) {
                case WM_SYSTEM_ADMIN:
                    admin = (WmAdmin) request.getSession().getAttribute(AdminCommon.USER_SESSION);
                    break;
                case WM_ADDITION_ADMIN:
                    admin = (WmAdditionAdmin) request.getSession().getAttribute(AdminCommon.USER_SESSION);
                    break;
            }
        }
        // 验证是否为NULL
        if (admin != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/views/admin/login.jsp");
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
