package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.service.WmGradeService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("apiGradeController")
@RequestMapping(value = "/api/grade")
public class GradeController {

    @Autowired
    WmGradeService wmGradeService;

    /**
     * 根据学校ID获取年级列表
     * @param wmSchoolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGradeList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getGradeList(@RequestParam("wmSchoolId")Integer wmSchoolId) {
        if (wmSchoolId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "学校ID不可为空");
        }
        return AppResponse.success(wmGradeService.selectGradeBySchoolId(wmSchoolId) );
    }
}
