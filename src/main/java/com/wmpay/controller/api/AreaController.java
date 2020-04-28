package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.service.WmAreaService;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("apiAreaController")
@RequestMapping(value = "/api/area/")
public class AreaController {

    @Autowired
    WmAreaService areaService;

    /**
     * 获取地区
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAreaList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getAreaList() {
        return AppResponse.success(areaService.selectListNoPage() );
    }


}
