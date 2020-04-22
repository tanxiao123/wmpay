package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.AO.SquadAO;
import com.wmpay.bean.VO.SquadVO;
import com.wmpay.bean.WmSquad;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmSquadService;
import com.wmpay.template.Insert;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Update;
import com.wmpay.util.AppResponse;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@Controller
@RequestMapping("/admin/squad")
public class SquadController {


    @Autowired
    WmSquadService squadService;

    /**
     * 跳转班级管理首页
     * @return
     */
    @RequestMapping(value = "getSquadView", method = RequestMethod.GET)
    public String getSquadView() {
        return "/admin/squad/index";
    }

    /**
     * 获取班级列表接口
     * @param pageTools
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSquadList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public DataTableResponse getSquadList(@RequestBody PageTools pageTools) {
        DataTableResponse response = new DataTableResponse();
        IPage<SquadVO> result = squadService.selectWmSquadList(pageTools);
        response.setStatus(ResponseEnum.SUCCESS.status);
        response.setCusMsg(ResponseEnum.SUCCESS.msg);
        response.setTipMsg(ResponseEnum.SUCCESS.msg);
        response.setData(result.getRecords() );
        response.setRecordsTotal(result.getPages() );
        response.setRecordsFiltered(result.getTotal() );
        return response;
    }

    /**
     * 加载班级修改视图
     * @param wmSquadId
     * @param request
     * @return
     */
    @RequestMapping(value = "updateSquadView", method = RequestMethod.GET)
    public String updateSquadView(@RequestParam("wmSquadId") Integer wmSquadId, HttpServletRequest request) {
        WmSquad result = squadService.selectWmSquadOne(wmSquadId);
        request.setAttribute("result", result);
        return "/admin/squad/edit";
    }

    /**
     * 修改班级信息
     * @param wmSquad
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateSquad", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean updateSquad(@Validated(Update.class) WmSquad wmSquad) {
        if (squadService.updateWmSquad(wmSquad) ) {
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 删除班级
     * @param wmSquadId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteSquad", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean deleteSquad(Integer wmSquadId) {
        if (squadService.deleteWmSquad(wmSquadId) ){
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 加载新增班级视图
     * @return
     */
    @RequestMapping(value = "addSquadView", method = RequestMethod.GET)
    public String addSquadView() {
        return "/admin/squad/add";
    }


    /**
     * 新增班级
     * @param squadAO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addSquad", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean addSquad(@Validated({Insert.class}) SquadAO squadAO) {
        if (squadService.addWmSquad(squadAO) ){
            return AppResponse.success();
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

}
