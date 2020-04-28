package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.service.WmSquadService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("apiSquadController")
@RequestMapping(value = "/api/squad")
public class SquadController {

    @Autowired
    WmSquadService wmSquadService;


    /**
     * 根据年级获取班级列表
     * @param wmGradeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSquadList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getSquadList(@RequestParam("wmGradeId")Integer wmGradeId) {
        if (wmGradeId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "年级ID不可为空");
        }
        return AppResponse.success(wmSquadService.getSquadListByGradeId(wmGradeId) );
    }
}
