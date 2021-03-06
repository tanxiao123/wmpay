package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.VO.GradeVO;
import com.wmpay.bean.WmGrade;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmGradeService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Update;
import com.wmpay.util.AppResponse;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/admin/grade")
public class GradeController {

    @Autowired
    WmGradeService wmGradeService;

    /**
     * 加载年级首页
     * @return
     */
    @RequestMapping(value = "gradeView", method = RequestMethod.GET)
    public String gradeView() {
        return "admin/grade/index";
    }

    /**
     * 加载年级信息列表
     * @param pageTools
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGradeList",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public DataTableResponse getGradeList(@RequestBody @Validated PageTools pageTools, BindingResult result) {
        DataTableResponse responseBean = new DataTableResponse();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        IPage<GradeVO> gradeList = wmGradeService.getGradeList(pageTools);
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setRecordsTotal(gradeList.getPages());
        responseBean.setRecordsFiltered(gradeList.getTotal());
        responseBean.setData(gradeList.getRecords());
        return responseBean;
    }

    /**
     * 加载添加年级视图
     * @return
     */
    @RequestMapping(value = "addGradeView", method = RequestMethod.GET)
    public String addGradeView() {
        return "admin/grade/add";
    }

    /**
     *  添加年级
     * @param wmGrade
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGrade",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean addGrade(@RequestBody @Validated WmGrade wmGrade, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }

        if (wmGradeService.addGrade(wmGrade)){
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
     * 跳转删除编辑View
     * @param wmGradeId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "editGradeView", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public String editGradeView(@RequestParam("wmGradeId") Integer wmGradeId, HttpServletRequest request, HttpServletResponse response) {
        WmGrade wmGrade = wmGradeService.getGradeById(wmGradeId);
        if (wmGrade != null){
            request.setAttribute("wmGrade", wmGrade);
            return "admin/grade/edit";
        }else{
            request.setAttribute("errorMessage", "抱歉，年级ID不可为空");
            return "error-500";
        }
    }

    /**
     * 编辑年级API
     * @param wmGrade
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editGrade", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean editGrade(@RequestBody @Validated({Update.class}) WmGrade wmGrade, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmGradeService.editGrade(wmGrade) ){
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
     * 删除年级操作
     * @param wmGradeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delGrade",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delGrade(@RequestParam("wmGradeId")Integer wmGradeId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmGradeId == null){
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg("年级ID不可为空");
            responseBean.setTipMsg("年级ID不可为空");
        }

        if (wmGradeService.delGrade(wmGradeId)){
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
     * 根据学校ID查询班级列表
     * @param wmSchoolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGradeBySchoolId",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean getGradeBySchoolId(@RequestParam("wmSchoolId")Integer wmSchoolId) {
        List<WmGrade> result = wmGradeService.selectGradeBySchoolId(wmSchoolId);
        return AppResponse.success(result);
    }

}
