package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmCommodity;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmCommodityService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "apiCommodity")
@RequestMapping(value = "/api/commodity")
public class CommodityController {

    @Autowired
    WmCommodityService wmCommodityService;


    /**
     * 获取商品列表
     * @param wmUserId
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommodityList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getCommodityList(@RequestParam("wmUserId")Integer wmUserId, @RequestParam("type")String type) {
        if (wmUserId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "用户ID不可为空");
        }else if(type == null) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "用户类型不可为空");
        }
        List<WmCommodity> commodityList = wmCommodityService.getWmCommodityList(wmUserId, type);
        return AppResponse.success(commodityList);
    }

}
