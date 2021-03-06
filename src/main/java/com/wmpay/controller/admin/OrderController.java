package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.AO.DayNumberStatisticsAO;
import com.wmpay.bean.VO.OrderVO;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmOrderService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class OrderController {


    @Autowired
    WmOrderService wmOrderService;

    /**
     * 加载订单首页
     * @return
     */
    @RequestMapping(value = "getOrderView", method = RequestMethod.GET)
    public String getOrderView() {
        return "admin/order/index";
    }

    /**
     * 加载订单列表
     * @param pageTools
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderList",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getOrderList(@RequestBody @Validated PageTools pageTools, BindingResult result) {
        DataTableResponse responseBean = new DataTableResponse();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        IPage<OrderVO> orderList = wmOrderService.getOrderList(pageTools);
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setData(orderList.getRecords());
        responseBean.setRecordsTotal(orderList.getPages());
        responseBean.setRecordsFiltered(orderList.getTotal());
        return responseBean;
    }


    /**
     * 删除订单接口
     * @param wmOrderId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delOrderView",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delOrderView(@RequestParam("wmOrderId")Integer wmOrderId, HttpServletRequest request, HttpServletResponse response) {
        ResponseBean responseBean = new ResponseBean();
        if (wmOrderId == null){
            responseBean.setStatus(ResponseEnum.NOT_PERMISSION.status);
            responseBean.setCusMsg(ResponseEnum.NOT_PERMISSION.msg);
            responseBean.setTipMsg(ResponseEnum.NOT_PERMISSION.msg);
            return responseBean;
        }
        return responseBean;
    }

    /**
     * 加载订单统计视图
     * @return String
     */
    @RequestMapping(value = "getStatisticsView", method = RequestMethod.GET)
    public String getStatisticsView() {
        return "/admin/order/statistics";
    }


    /**
     * 获取线性统计数据
     * @return ResponseBean
     */
    @ResponseBody
    @RequestMapping(value = "getDayNumberStatistics", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getDayNumberStatistics() {
        return AppResponse.success(wmOrderService.selectDayNumber());
    }

    /**
     * 获取饼状统计数据
     * @param daysType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCakeStatistics", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getCakeStatistics(@RequestParam("daysType") Integer daysType) {
        return AppResponse.success(wmOrderService.selectCake(daysType) );
    }
}
