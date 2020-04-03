package com.wmpay.bean;

import java.util.ArrayList;
import java.util.List;

public class WmAuthGroupAccessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WmAuthGroupAccessExample() {
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

        public Criteria andWmAuthGroupAccessIdIsNull() {
            addCriterion("wm_auth_group_access_id is null");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdIsNotNull() {
            addCriterion("wm_auth_group_access_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdEqualTo(Integer value) {
            addCriterion("wm_auth_group_access_id =", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdNotEqualTo(Integer value) {
            addCriterion("wm_auth_group_access_id <>", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdGreaterThan(Integer value) {
            addCriterion("wm_auth_group_access_id >", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_auth_group_access_id >=", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdLessThan(Integer value) {
            addCriterion("wm_auth_group_access_id <", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_auth_group_access_id <=", value, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdIn(List<Integer> values) {
            addCriterion("wm_auth_group_access_id in", values, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdNotIn(List<Integer> values) {
            addCriterion("wm_auth_group_access_id not in", values, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_auth_group_access_id between", value1, value2, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupAccessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_auth_group_access_id not between", value1, value2, "wmAuthGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdIsNull() {
            addCriterion("wm_auth_group_id is null");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdIsNotNull() {
            addCriterion("wm_auth_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdEqualTo(Integer value) {
            addCriterion("wm_auth_group_id =", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdNotEqualTo(Integer value) {
            addCriterion("wm_auth_group_id <>", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdGreaterThan(Integer value) {
            addCriterion("wm_auth_group_id >", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_auth_group_id >=", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdLessThan(Integer value) {
            addCriterion("wm_auth_group_id <", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_auth_group_id <=", value, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdIn(List<Integer> values) {
            addCriterion("wm_auth_group_id in", values, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdNotIn(List<Integer> values) {
            addCriterion("wm_auth_group_id not in", values, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_auth_group_id between", value1, value2, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAuthGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_auth_group_id not between", value1, value2, "wmAuthGroupId");
            return (Criteria) this;
        }

        public Criteria andWmAdminIsNull() {
            addCriterion("wm_admin is null");
            return (Criteria) this;
        }

        public Criteria andWmAdminIsNotNull() {
            addCriterion("wm_admin is not null");
            return (Criteria) this;
        }

        public Criteria andWmAdminEqualTo(Integer value) {
            addCriterion("wm_admin =", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminNotEqualTo(Integer value) {
            addCriterion("wm_admin <>", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminGreaterThan(Integer value) {
            addCriterion("wm_admin >", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_admin >=", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminLessThan(Integer value) {
            addCriterion("wm_admin <", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminLessThanOrEqualTo(Integer value) {
            addCriterion("wm_admin <=", value, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminIn(List<Integer> values) {
            addCriterion("wm_admin in", values, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminNotIn(List<Integer> values) {
            addCriterion("wm_admin not in", values, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminBetween(Integer value1, Integer value2) {
            addCriterion("wm_admin between", value1, value2, "wmAdmin");
            return (Criteria) this;
        }

        public Criteria andWmAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_admin not between", value1, value2, "wmAdmin");
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