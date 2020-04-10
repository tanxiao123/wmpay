package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.WmArea;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmAreaService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return "/admin/area/index";
    }

    /**
     * 分页获取地区列表信息
     * @param pageTools
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAreaList", produces = "application/json; charset=utf-8")
    public DataTableResponse getAreaList(@Validated  PageTools pageTools) {
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

}
