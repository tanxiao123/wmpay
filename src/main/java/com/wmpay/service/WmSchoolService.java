package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weimai.tools.Wm;
import com.wmpay.bean.AO.SchoolAO;
import com.wmpay.bean.VO.SchoolVO;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmGrade;
import com.wmpay.bean.WmSchool;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmSchoolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class WmSchoolService {

    @Autowired
    private WmSchoolDAO wmSchoolDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private WmGradeService wmGradeService;


    /**
     * 查询学校列表信息
     * @param pageTools
     * @return
     */
    public IPage<SchoolVO> selectListPage(PageTools pageTools) {
        Integer schoolId = null;
        // 验证学校权限
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE) );
        switch (adminType){
            case WM_ADDITION_ADMIN:
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                // 验证当前类型如果为学校 那么继续执行条件查询 否则当前状态不为学校等级  则返回空数据
                if (admin.getType() != null && Integer.parseInt(admin.getType()) <= 1){
                    schoolId = admin.getUserId();
                }else{
                    return null;
                }
                break;
        }
        return wmSchoolDAO.selectParentSchool(new Page<WmSchool>(pageTools.getStart(), pageTools.getLength() ), schoolId );
    }

    /**
     * 查询分校区
     * @param pageTools
     * @return
     */
    public IPage<SchoolVO> selectPointListPage(PageTools pageTools, Integer wmSchoolId){
        return wmSchoolDAO.selectPointSchool(new Page<WmSchool>(pageTools.getStart(), pageTools.getLength() ), wmSchoolId );
    }

    /**
     * 根据后台权限查询列表数据
     * @return
     */
    public List<WmSchool> selectList() {
        Integer wmSchoolId = null;
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
                            wmSchoolId = admin.getUserId();
                            break;
                        case "2": // 班级类型
                            WmGrade wmGrade = wmGradeService.getGradeById(admin.getUserId() );
                            wmSchoolId = wmGrade.getWmSchoolId();
                            break;
                        default:
                            return null;
                    }
                }else{
                    return null;
                }
                break;
        }
        return wmSchoolDAO.selectSchoolList(wmSchoolId);
    }

    /**
     * 根据地区查询学校列表信息
     * @param wmAreaId
     * @return
     */
    public List<SchoolAO> selectListApi(Integer wmAreaId) {
        return wmSchoolDAO.selectSchoolListApi(wmAreaId);
    }

    public Boolean delSchool(Integer wmSchoolId) {
        int result = wmSchoolDAO.deleteById(wmSchoolId);
        return result > 0;
    }

    public Boolean addSchool(WmSchool wmSchool) {
        wmSchool.setCreatedTime(new Date());
        wmSchool.setStatus("1");
        int result = wmSchoolDAO.insert(wmSchool);
        return result > 0;
    }

    public Boolean editSchool(WmSchool wmSchool) {
        wmSchool.setUpdatedTime(new Date());
        int result = wmSchoolDAO.updateById(wmSchool);
        return result > 0;
    }

    public WmSchool getSchoolById(Integer wmSchoolId) {
         return wmSchoolDAO.selectById(wmSchoolId);
    }
}
