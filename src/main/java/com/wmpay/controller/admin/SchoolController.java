package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.VO.SchoolVO;
import com.wmpay.bean.WmSchool;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmSchoolService;
import com.wmpay.template.Insert;
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

@Controller
@RequestMapping("/admin/school")
public class SchoolController {

    @Autowired
    WmSchoolService wmSchoolService;

    /**
     * 加载学校视图
     * @return
     */
    @RequestMapping(value = "schoolView", method = RequestMethod.GET)
    public String schoolView() {
        return "admin/school/index";
    }

    /**
     * 分页获取学校列表数据
     * @param pageTools
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSchoolList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getSchoolList(@Validated @RequestBody PageTools pageTools) {
        DataTableResponse responseBean = new DataTableResponse();
        IPage<SchoolVO> schoolList = wmSchoolService.selectListPage(pageTools);
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setRecordsTotal(schoolList.getPages());
        responseBean.setRecordsFiltered(schoolList.getTotal());
        responseBean.setData(schoolList.getRecords());
        return responseBean;
    }

    /**
     * 分页获取分校区列表数据
     * @param pageTools
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPointSchoolList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getPointSchoolList(@Validated @RequestBody PageTools pageTools, @RequestParam("wmSchoolId")Integer wmSchoolId) {
        DataTableResponse responseBean = new DataTableResponse();
        IPage<SchoolVO> schoolList = wmSchoolService.selectPointListPage(pageTools, wmSchoolId);
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setRecordsTotal(schoolList.getPages());
        responseBean.setRecordsFiltered(schoolList.getTotal());
        responseBean.setData(schoolList.getRecords());
        return responseBean;
    }

    /**
     * 加载添加学校视图渲染
     * @return
     */
    @RequestMapping(value = "addSchoolView", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public String addSchoolView() {
        return "admin/school/add";
    }

    /**
     * 加载添加分校区
     * @return
     */
    @RequestMapping(value = "addPointSchoolView", method = RequestMethod.GET)
    public String addPointSchoolView(@RequestParam("wmSchoolId") Integer wmSchoolId, HttpServletRequest request, HttpServletResponse response){
        if (wmSchoolId ==  null){
            request.setAttribute("errorMessage", "抱歉，学校ID为空");
            return "error-500";
        }
        WmSchool wmSchool = wmSchoolService.getSchoolById(wmSchoolId);
        request.setAttribute("wmSchool", wmSchool);
        return "admin/school/point/add";
    }


    /**
     * 加载分校区首页视图渲染
     * @return
     */
    @RequestMapping(value = "pointSchoolView")
    public String pointSchoolView(@RequestParam("wmSchoolId")Integer wmSchoolId,HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("wmSchoolId", wmSchoolId);
        return "admin/school/point/index";
    }

    /**
     * 添加学校接口
     * @param wmSchool
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addSchool", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean addSchool(@Validated({Insert.class}) @RequestBody WmSchool wmSchool, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmSchoolService.addSchool(wmSchool)){
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
     * 加载编辑学校视图
     * @param wmSchoolId
     * @return
     */
    @RequestMapping(value = "editSchoolView", method = RequestMethod.GET)
    public String editSchoolView(@RequestParam("wmSchoolId") Integer wmSchoolId, HttpServletRequest request, HttpServletResponse response) {
        WmSchool result = wmSchoolService.getSchoolById(wmSchoolId);
        request.setAttribute("school",result);
        return "admin/school/edit";
    }

    /**
     * 不分页获取全部的学校数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSchoolListNoPage", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getSchoolListNoPage() {
        return AppResponse.success(wmSchoolService.selectList() );
    }

    /**
     * 编辑学校API
     * @param wmSchool
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editSchool", method = RequestMethod.POST)
    public ResponseBean editSchool(@Validated({Insert.class}) @RequestBody WmSchool wmSchool) {
        ResponseBean responseBean = new ResponseBean();
        if (wmSchoolService.editSchool(wmSchool)){
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
     * 删除学校
     * @param wmSchoolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delSchool", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delSchool(@RequestParam("wmSchoolId") Integer wmSchoolId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmSchoolId == null){
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg("地区ID不可为空");
            responseBean.setTipMsg("地区ID不可为空");
            return responseBean;
        }
        if(wmSchoolService.delSchool(wmSchoolId)){
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
