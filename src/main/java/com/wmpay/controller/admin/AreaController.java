package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmArea;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmAreaService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Update;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@Controller
@RequestMapping("/admin/area")
public class AreaController {

    @Autowired
    WmAreaService wmAreaService;

    /**
     * 加载地区视图
     * @return
     */
    @RequestMapping(value = "areaView", method = RequestMethod.GET)
    public String areaView() {
        return "admin/area/index";
    }

    /**
     * 分页获取地区列表信息
     * @param pageTools
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAreaList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getAreaList(@Validated @RequestBody PageTools pageTools) {
        DataTableResponse responseBean = new DataTableResponse();
        IPage<WmArea> areaList = wmAreaService.selectListPage(pageTools);
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setRecordsTotal(areaList.getPages());
        responseBean.setRecordsFiltered(areaList.getTotal());
        responseBean.setData(areaList.getRecords());
        return responseBean;
    }


    /**
     * 跳转编辑地区界面
     * @param wmAreaId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "editAreaView", method = RequestMethod.GET)
    public String editAreaView(@RequestParam("wmAreaId")Integer wmAreaId, HttpServletRequest request, HttpServletResponse response) {
        ResponseBean responseBean = new ResponseBean();

        WmArea wmAreaServer = wmAreaService.getWmAreaById(wmAreaId);
        if (wmAreaServer != null){
            request.setAttribute("area", wmAreaServer);
            return "admin/area/edit";
        } else {
            request.setAttribute("errorMessage", "抱歉，没有找到地区信息");
            return "error-500";
        }
    }

    /**
     * 编辑地区接口
     * @param wmArea
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editArea", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean editArea( @Validated({Update.class}) WmArea wmArea, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmAreaService.updateWmArea(wmArea)){
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        }else{
            responseBean.setStatus(ResponseEnum.ERROR.status);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
        }
        return responseBean;
    }

    /**
     * 跳转添加地区界面
     * @return
     */
    @RequestMapping(value = "addAreaView", method = RequestMethod.GET)
    public String addAreaView() {
        return "admin/area/add";
    }

    /**
     * 添加地区操作
     * @param wmArea
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addArea", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean addArea(@Validated WmArea wmArea, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmAreaService.addWmArea(wmArea)){
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        }else{
            responseBean.setStatus(ResponseEnum.ERROR.status);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
        }

        return responseBean;
    }

    /**
     * 删除地区
     * @param wmAreaId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delArea", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delArea(@RequestParam("wmAreaId")Integer wmAreaId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmAreaId == null){
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg("地区ID不可为空");
            responseBean.setTipMsg("地区ID不可为空");
            return responseBean;
        }

        if (wmAreaService.delWmArea(wmAreaId) ){
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        }else{
            responseBean.setStatus(ResponseEnum.ERROR.status);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
        }

        return responseBean;
    }

}
