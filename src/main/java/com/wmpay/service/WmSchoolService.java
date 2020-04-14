package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weimai.tools.Wm;
import com.wmpay.bean.VO.SchoolVO;
import com.wmpay.bean.WmSchool;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmSchoolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WmSchoolService {

    @Autowired
    private WmSchoolDAO wmSchoolDAO;

    public IPage<SchoolVO> selectListPage(PageTools pageTools) {
        return wmSchoolDAO.selectParentSchool(new Page<WmSchool>(pageTools.getStart(), pageTools.getLength()));
    }

    /**
     * 查询分校区
     * @param pageTools
     * @return
     */
    public IPage<SchoolVO> selectPointListPage(PageTools pageTools){
        return wmSchoolDAO.selectPointSchool(new Page<WmSchool>(pageTools.getStart(), pageTools.getLength())) ;
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
