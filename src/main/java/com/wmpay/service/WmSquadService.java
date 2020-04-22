package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.AO.SquadAO;
import com.wmpay.bean.VO.SquadVO;
import com.wmpay.bean.WmGradeSquad;
import com.wmpay.bean.WmSquad;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmGradeSquadDAO;
import com.wmpay.dao.WmSquadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class WmSquadService {

    @Autowired
    private WmSquadDAO wmSquadDAO;

    @Autowired
    private WmGradeSquadDAO wmGradeSquadDAO;

    /**
     * 获取班级列表
     * @param pageTools
     * @return
     */
    public IPage<SquadVO> selectWmSquadList(PageTools pageTools) {
        return wmSquadDAO.selectListPage(new Page<SquadVO>(pageTools.getStart(), pageTools.getLength() ) );
    }

    /**
     * 获取班级详细信息
     * @param wmSquadId
     * @return
     */
    public WmSquad selectWmSquadOne(Integer wmSquadId) {
        return wmSquadDAO.selectById(wmSquadId);
    }

    /**
     * 修改班级信息
     * @param wmSquad
     * @return
     */
    public Boolean updateWmSquad(WmSquad wmSquad) {
        int result = wmSquadDAO.updateById(wmSquad);
        return result > 0;
    }

    /**
     * 删除班级信息
     * @param wmSquadId
     * @return
     */
    public Boolean deleteWmSquad(Integer wmSquadId) {
        int result = wmSquadDAO.deleteById(wmSquadId);
        return result > 0;
    }
    
    
    public Boolean addWmSquad(SquadAO squadAO) {
        WmSquad wmSquad = new WmSquad();
        wmSquad.setName(squadAO.getName() );
        wmSquad.setTeacherName(squadAO.getTeacherName() );
        wmSquad.setTeacherMobile(squadAO.getTeacherMobile() );
        wmSquad.setStatus("1");
        wmSquad.setCreatedTime(new Date() );
        wmSquad.setUpdatedTime(new Date());
        int wmSquadInsert = wmSquadDAO.insert(wmSquad);
        if (wmSquadInsert > 0){
            // 新增关系表建立
            WmGradeSquad wmGradeSquad = new WmGradeSquad();
            wmGradeSquad.setWmSquadId(squadAO.getWmSquadId() );
            wmGradeSquad.setWmGradeId(squadAO.getWmGradeId() );
            int wmGradeSquadInsert = wmGradeSquadDAO.insert(wmGradeSquad);
            if (wmGradeSquadInsert > 0){
                return true;
            }
        }
        return false;
    }

}
