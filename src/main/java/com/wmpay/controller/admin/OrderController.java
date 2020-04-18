package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.common.PageTools;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.DataTableResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    /**
     * 加载订单首页
     * @return
     */
    @RequestMapping(value = "gradeView", method = RequestMethod.GET)
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
}
