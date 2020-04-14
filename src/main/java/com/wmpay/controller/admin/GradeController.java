package com.wmpay.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.VO.GradeVO;
import com.wmpay.bean.WmGrade;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmGradeService;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/grade")
public class GradeController {

    @Autowired
    WmGradeService wmGradeService;

    /**
     * 加载班级首页
     * @return
     */
    @RequestMapping(value = "gradeView", method = RequestMethod.GET)
    public String gradeView() {
        return "admin/grade/index";
    }

    /**
     * 加载班级信息列表
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
     * 加载添加班级视图
     * @return
     */
    public String addGradeView() {
        return "admin/grade/add";
    }

    /**
     *  添加班级
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
     * 删除班级操作
     * @param wmGradeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delGrade",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delGrade(@RequestParam("wmGradeId")Integer wmGradeId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmGradeId == null){
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg("班级ID不可为空");
            responseBean.setTipMsg("班级ID不可为空");
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

}
