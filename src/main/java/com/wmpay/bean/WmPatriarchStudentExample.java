package com.wmpay.bean;

import java.util.ArrayList;
import java.util.List;

public class WmPatriarchStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WmPatriarchStudentExample() {
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

        public Criteria andWmPatriarchStudentIdIsNull() {
            addCriterion("wm_patriarch_student_id is null");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdIsNotNull() {
            addCriterion("wm_patriarch_student_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdEqualTo(Integer value) {
            addCriterion("wm_patriarch_student_id =", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdNotEqualTo(Integer value) {
            addCriterion("wm_patriarch_student_id <>", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdGreaterThan(Integer value) {
            addCriterion("wm_patriarch_student_id >", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_patriarch_student_id >=", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdLessThan(Integer value) {
            addCriterion("wm_patriarch_student_id <", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_patriarch_student_id <=", value, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdIn(List<Integer> values) {
            addCriterion("wm_patriarch_student_id in", values, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdNotIn(List<Integer> values) {
            addCriterion("wm_patriarch_student_id not in", values, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_patriarch_student_id between", value1, value2, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_patriarch_student_id not between", value1, value2, "wmPatriarchStudentId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdIsNull() {
            addCriterion("wm_patriarch_id is null");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdIsNotNull() {
            addCriterion("wm_patriarch_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdEqualTo(Integer value) {
            addCriterion("wm_patriarch_id =", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdNotEqualTo(Integer value) {
            addCriterion("wm_patriarch_id <>", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdGreaterThan(Integer value) {
            addCriterion("wm_patriarch_id >", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_patriarch_id >=", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdLessThan(Integer value) {
            addCriterion("wm_patriarch_id <", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_patriarch_id <=", value, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdIn(List<Integer> values) {
            addCriterion("wm_patriarch_id in", values, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdNotIn(List<Integer> values) {
            addCriterion("wm_patriarch_id not in", values, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_patriarch_id between", value1, value2, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmPatriarchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_patriarch_id not between", value1, value2, "wmPatriarchId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdIsNull() {
            addCriterion("wm_student_id is null");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdIsNotNull() {
            addCriterion("wm_student_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdEqualTo(Integer value) {
            addCriterion("wm_student_id =", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdNotEqualTo(Integer value) {
            addCriterion("wm_student_id <>", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdGreaterThan(Integer value) {
            addCriterion("wm_student_id >", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_student_id >=", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdLessThan(Integer value) {
            addCriterion("wm_student_id <", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_student_id <=", value, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdIn(List<Integer> values) {
            addCriterion("wm_student_id in", values, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdNotIn(List<Integer> values) {
            addCriterion("wm_student_id not in", values, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_student_id between", value1, value2, "wmStudentId");
            return (Criteria) this;
        }

        public Criteria andWmStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_student_id not between", value1, value2, "wmStudentId");
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