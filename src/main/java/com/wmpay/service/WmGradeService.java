package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.GradeVO;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmGrade;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmGradeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 年级Service
 */
@Service
public class WmGradeService {

    @Autowired
    WmGradeDAO wmGradeDAO;

    @Autowired
    HttpServletRequest request;


    public WmGrade getGradeById(Integer wmGradeId) {
        return wmGradeDAO.selectById(wmGradeId);
    }

    public IPage<GradeVO> getGradeList(PageTools pageTools) {
        Integer gradeId = null; // 年级ID
        Integer schoolId = null; // 学校ID
        // 验证年级权限
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE) );
        switch (adminType){
            case WM_ADDITION_ADMIN:
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                // TODO: 当前类型等于年级级别  那么只查询当前年级信息 并操作 如当前类型为学校 那么查询该学校下的年级信息
                if (admin.getType() != null){
                    String userType = admin.getType();
                    switch (userType){
                        case "1": // 学校类型
                            schoolId = admin.getUserId();
                            break;
                        case "2": // 年级类型
                            gradeId = admin.getUserId();
                            break;
                        case "3": // 班级类型 无操作权限
                            return null;
                    }
                }else{
                    return null;
                }
                break;
        }
        return wmGradeDAO.selectGradeList(new Page<WmGrade>(pageTools.getStart(), pageTools.getLength()), gradeId, schoolId);
    }


    public String isPay() {
        StringBuffer payStr = new StringBuffer();
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE) );
        payStr.append("<a  id='edit' href='javascript:;'  title='编辑'>编辑</a>");
        payStr.append("<a  id='del' href='javascript:;'  title='删除'>删除</a>");
        switch (adminType){
            case WM_SYSTEM_ADMIN:
                payStr.append("<a id='payConfig' href='javascript:;' title='支付配置'>支付配置</a>");
                break;
            case WM_ADDITION_ADMIN:
                // 验证当前登陆用户权限
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                if (admin.getType() != null) {
                    String userType = admin.getType();
                    switch (userType) {
                        case "1": // 学校类型
                            payStr.append("<a id='payConfig' href='javascript:;' title='支付配置'>支付配置</a>");
                    }
                }
        }
        return payStr.toString();
    }

    public Boolean addGrade(WmGrade wmGrade) {
        wmGrade.setUpdatedTime(new Date());
        int result = wmGradeDAO.insert(wmGrade);
        return result > 0;
    }

    public Boolean editGrade(WmGrade wmGrade) {
        int result = wmGradeDAO.updateById(wmGrade);
        return result > 0;
    }

    public Boolean delGrade(Integer wmGradeId) {
        int result = wmGradeDAO.deleteById(wmGradeId);
        return result > 0;
    }

    /**
     * 根据学校ID查询班级
     * @param wmSchoolId
     * @return
     */
    public List<WmGrade> selectGradeBySchoolId(Integer wmSchoolId) {
        return wmGradeDAO.selectList(new QueryWrapper<WmGrade>().eq("wm_school_id", wmSchoolId));
    }
}