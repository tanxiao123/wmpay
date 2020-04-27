package com.wmpay.util;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.template.ResponseEnum;

import javax.servlet.http.HttpServletRequest;

public class Auth {


    public ResponseBean authCheck(HttpServletRequest request) {
        WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE) );
        switch (adminType){
            case WM_SYSTEM_ADMIN:
                // 管理员/ 学校权限
                return AppResponse.error(ResponseEnum.OK_ACCESS);
            case WM_ADDITION_ADMIN:
                if (admin.getType() != null){
                    switch (admin.getType() ) {
                        case "1": // 学校类型
                            return AppResponse.error(ResponseEnum.OK_ACCESS);
                        default: // 无权限  返回正常状态
                            return AppResponse.error(ResponseEnum.NO_ACCESS);
                    }
                }else{
                    return AppResponse.error(ResponseEnum.NOT_ADMIN_TYPE);
                }
        }
        return AppResponse.error(ResponseEnum.NOT_PERMISSION);
    }

}
