package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmpay.bean.WmPatriarchStudent;
import com.wmpay.bean.WmStudent;
import com.wmpay.dao.WmPatriarchStudentDAO;
import com.wmpay.dao.WmStudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmStudentService {

    @Autowired
    WmStudentDAO wmStudentDAO;

    @Autowired
    WmPatriarchStudentDAO wmPatriarchStudentDAO;

    /**
     * 添加学生
     * @param wmStudent
     * @return
     */
    public Integer addStudent(WmStudent wmStudent) {
        int result = wmStudentDAO.insert(wmStudent);
        return result > 0 ? wmStudent.getWmStudentId() : result;
    }

    /**
     * 添加学生与家长绑定
     * @param wmPatriarchStudent
     * @return
     */
    public Boolean addStudentPatriarch(WmPatriarchStudent wmPatriarchStudent) {
        int result = wmPatriarchStudentDAO.insert(wmPatriarchStudent);
        return result > 0;
    }

    /**
     * 根据学生ID查询当前学生是否已做关联
     * @param studentId
     * @return
     */
    public WmPatriarchStudent getStudentPatriarchByStudentId(Integer studentId) {
        QueryWrapper<WmPatriarchStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wm_student_id", studentId);
        return wmPatriarchStudentDAO.selectOne(queryWrapper);
    }

    /**
     * 根据学生姓名获取学生信息
     * @param name
     * @return
     */
    public WmStudent getStudentByName(String name) {
        return wmStudentDAO.selectOne(new QueryWrapper<WmStudent>().eq("name",name));
    }

}
