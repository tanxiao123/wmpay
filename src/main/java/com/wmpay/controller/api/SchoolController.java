package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.service.WmSchoolService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("apiSchoolController")
@RequestMapping(value = "/api/school")
public class SchoolController {

    @Autowired
    WmSchoolService wmSchoolService;

    @ResponseBody
    @RequestMapping(value = "getSchoolList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getSchoolList(@RequestParam("wmAreaId")Integer wmAreaId) {
        if (wmAreaId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "地区ID不可为空");
        }
        return AppResponse.success(wmSchoolService.selectListApi(wmAreaId) );
    }

}
