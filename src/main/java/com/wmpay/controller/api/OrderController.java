package com.wmpay.controller.api;

import com.weimai.tools.ResponseBean;
import com.wmpay.bean.AO.DetailOrderAO;
import com.wmpay.bean.VO.OrderVO;
import com.wmpay.service.WmOrderService;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单控制器
 */
@Controller("apiOrderController")
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    WmOrderService wmOrderService;

    /**
     * 获取订单列表
     * @param wmPatriarchId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getOrderList(@RequestParam("wmPatriarchId") Integer wmPatriarchId) {
        List<OrderVO> wmOrderList = wmOrderService.selectOrderByWmPatriarchId(wmPatriarchId);
        return AppResponse.success(wmOrderList);
    }


    /**
     * 查询订单详情
     * @param wmOrderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getOrderDetail(@RequestParam("wmOrderId")Integer wmOrderId) {
        DetailOrderAO detailOrderAO = wmOrderService.selectOrderDetail(wmOrderId);
        return AppResponse.success(detailOrderAO);
    }

}
