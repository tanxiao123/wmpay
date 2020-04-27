package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.javafx.image.impl.ByteIndexed;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmCommodity;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmCommodityService;
import com.wmpay.template.Insert;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Select;
import com.wmpay.template.Update;
import com.wmpay.util.AppResponse;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/commodity/")
public class CommodityController {

    @Autowired
    WmCommodityService wmCommodityService;

    @Autowired
    HttpServletRequest request;

    /**
     * 加载商品列表视图
     * @return String
     */
    @RequestMapping(value = "getCommodityView", method = RequestMethod.GET)
    public String getCommodityView() {
        return "admin/commodity/index";
    }

    /**
     * 加载商品列表数据
     * @param pageTools
     * @param result
     * @return ResponseBean
     */
    @ResponseBody
    @RequestMapping(value = "getCommodityList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getCommodityList(@Validated(Select.class) @RequestBody PageTools pageTools, BindingResult result) {
        DataTableResponse response = new DataTableResponse();
        if (result.hasErrors()) {
            response.setStatus(ResponseEnum.FIELD_ERROR.status);
            response.setTipMsg(result.getFieldError().getDefaultMessage() );
            response.setCusMsg(result.getFieldError().getDefaultMessage() );
            return response;
        }
        // 此处只查询自己添加得商品信息
        WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
        IPage<WmCommodity> wmCommodityList = wmCommodityService.getWmCommodityList(pageTools, admin.getUserId(), admin.getType());

        response.setStatus(ResponseEnum.SUCCESS.status );
        response.setCusMsg(ResponseEnum.SUCCESS.msg);
        response.setCusMsg(ResponseEnum.SUCCESS.msg);
        response.setRecordsTotal(wmCommodityList.getPages());
        response.setRecordsFiltered(wmCommodityList.getTotal());
        response.setData(wmCommodityList.getRecords());
        return response;
    }

    /**
     * 加载添加商品视图
     * @return
     */
    @RequestMapping(value = "getAddCommodityView", method = RequestMethod.GET)
    public String getAddCommodityView() {
        return "admin/commodity/add";
    }

    /**
     * 添加商品API
     * @param wmCommodity
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addCommodity", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean addCommodity(@Validated(Insert.class)WmCommodity wmCommodity, BindingResult result) {
        if (result.hasErrors()) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage() );
        }
        return wmCommodityService.addWmCommodity(wmCommodity) ? AppResponse.success() : AppResponse.error(ResponseEnum.ERROR);
    }

    /**
     * 删除商品API
     * @param wmCommodityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delCommodity", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delCommodity(@RequestParam("wmCommodityId")Integer wmCommodityId) {
        if (wmCommodityId == null){
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, "商品主键不可为空" );
        }
        return wmCommodityService.delWmCommodity(wmCommodityId) ? AppResponse.success() : AppResponse.error(ResponseEnum.ERROR);
    }

    /**
     * 加载编辑商品视图
     * @param wmCommodityId
     * @param request
     * @return
     */
    @RequestMapping(value = "editCommodityView", method = RequestMethod.GET)
    public String editCommodityView(@RequestParam("wmCommodityId")Integer wmCommodityId,HttpServletRequest request) {
        if (wmCommodityId == null){
            request.setAttribute("errorMessage","商品主键不可为空");
            return "error-500";
        }
        WmCommodity wmCommodity = wmCommodityService.getCommodityById(wmCommodityId);
        request.setAttribute("wmCommodity", wmCommodity);
        return "admin/commodity/edit";
    }

    /**
     * 编辑商品API
     * @param wmCommodity
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editCommodity", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean editCommodity(@Validated(Update.class)WmCommodity wmCommodity, BindingResult result) {
        if (result.hasErrors()) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage() );
        }
        return wmCommodityService.updateCommodity(wmCommodity) ? AppResponse.success() : AppResponse.error(ResponseEnum.ERROR);
    }

}
