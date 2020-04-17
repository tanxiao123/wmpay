package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.GradeVO;
import com.wmpay.bean.WmGrade;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmGradeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WmGradeService {

    @Autowired
    WmGradeDAO wmGradeDAO;


    public WmGrade getGradeById(Integer wmGradeId) {
        return wmGradeDAO.selectById(wmGradeId);
    }

    public IPage<GradeVO> getGradeList(PageTools pageTools) {
        return wmGradeDAO.selectGradeList(new Page<WmGrade>(pageTools.getStart(), pageTools.getLength()));
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

}