package com.wmpay.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WmGradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WmGradeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andWmGradeIdIsNull() {
            addCriterion("wm_grade_id is null");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdIsNotNull() {
            addCriterion("wm_grade_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdEqualTo(Integer value) {
            addCriterion("wm_grade_id =", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdNotEqualTo(Integer value) {
            addCriterion("wm_grade_id <>", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdGreaterThan(Integer value) {
            addCriterion("wm_grade_id >", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_grade_id >=", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdLessThan(Integer value) {
            addCriterion("wm_grade_id <", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_grade_id <=", value, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdIn(List<Integer> values) {
            addCriterion("wm_grade_id in", values, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdNotIn(List<Integer> values) {
            addCriterion("wm_grade_id not in", values, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_grade_id between", value1, value2, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmGradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_grade_id not between", value1, value2, "wmGradeId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdIsNull() {
            addCriterion("wm_school_id is null");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdIsNotNull() {
            addCriterion("wm_school_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdEqualTo(Integer value) {
            addCriterion("wm_school_id =", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdNotEqualTo(Integer value) {
            addCriterion("wm_school_id <>", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdGreaterThan(Integer value) {
            addCriterion("wm_school_id >", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_school_id >=", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdLessThan(Integer value) {
            addCriterion("wm_school_id <", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_school_id <=", value, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdIn(List<Integer> values) {
            addCriterion("wm_school_id in", values, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdNotIn(List<Integer> values) {
            addCriterion("wm_school_id not in", values, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_school_id between", value1, value2, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andWmSchoolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_school_id not between", value1, value2, "wmSchoolId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria and修改时间IsNull() {
            addCriterion("修改时间 is null");
            return (Criteria) this;
        }

        public Criteria and修改时间IsNotNull() {
            addCriterion("修改时间 is not null");
            return (Criteria) this;
        }

        public Criteria and修改时间EqualTo(Date value) {
            addCriterion("修改时间 =", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotEqualTo(Date value) {
            addCriterion("修改时间 <>", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间GreaterThan(Date value) {
            addCriterion("修改时间 >", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间GreaterThanOrEqualTo(Date value) {
            addCriterion("修改时间 >=", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间LessThan(Date value) {
            addCriterion("修改时间 <", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间LessThanOrEqualTo(Date value) {
            addCriterion("修改时间 <=", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间In(List<Date> values) {
            addCriterion("修改时间 in", values, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotIn(List<Date> values) {
            addCriterion("修改时间 not in", values, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间Between(Date value1, Date value2) {
            addCriterion("修改时间 between", value1, value2, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotBetween(Date value1, Date value2) {
            addCriterion("修改时间 not between", value1, value2, "修改时间");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}