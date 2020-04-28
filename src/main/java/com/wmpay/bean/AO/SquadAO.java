package com.wmpay.bean.AO;

/**
 * 该类用于班级 添加 修改使用
 */
public class SquadAO {

    private Integer wmSquadId;

    private Integer wmSchoolId;

    private Integer wmGradeId;

    private String name;

    private String teacherName;

    private String teacherMobile;

    public Integer getWmSquadId() {
        return wmSquadId;
    }

    public void setWmSquadId(Integer wmSquadId) {
        this.wmSquadId = wmSquadId;
    }

    public Integer getWmSchoolId() {
        return wmSchoolId;
    }

    public void setWmSchoolId(Integer wmSchoolId) {
        this.wmSchoolId = wmSchoolId;
    }

    public Integer getWmGradeId() {
        return wmGradeId;
    }

    public void setWmGradeId(Integer wmGradeId) {
        this.wmGradeId = wmGradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }
}
