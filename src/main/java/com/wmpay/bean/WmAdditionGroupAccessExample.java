package com.wmpay.bean;

import java.util.ArrayList;
import java.util.List;

public class WmAdditionGroupAccessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WmAdditionGroupAccessExample() {
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

        public Criteria andWmAdditionAdminIdIsNull() {
            addCriterion("wm_addition_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdIsNotNull() {
            addCriterion("wm_addition_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdEqualTo(Integer value) {
            addCriterion("wm_addition_admin_id =", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdNotEqualTo(Integer value) {
            addCriterion("wm_addition_admin_id <>", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdGreaterThan(Integer value) {
            addCriterion("wm_addition_admin_id >", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_addition_admin_id >=", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdLessThan(Integer value) {
            addCriterion("wm_addition_admin_id <", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_addition_admin_id <=", value, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdIn(List<Integer> values) {
            addCriterion("wm_addition_admin_id in", values, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdNotIn(List<Integer> values) {
            addCriterion("wm_addition_admin_id not in", values, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_addition_admin_id between", value1, value2, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_addition_admin_id not between", value1, value2, "wmAdditionAdminId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdIsNull() {
            addCriterion("wm_addition_group_access_id is null");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdIsNotNull() {
            addCriterion("wm_addition_group_access_id is not null");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdEqualTo(Integer value) {
            addCriterion("wm_addition_group_access_id =", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdNotEqualTo(Integer value) {
            addCriterion("wm_addition_group_access_id <>", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdGreaterThan(Integer value) {
            addCriterion("wm_addition_group_access_id >", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wm_addition_group_access_id >=", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdLessThan(Integer value) {
            addCriterion("wm_addition_group_access_id <", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdLessThanOrEqualTo(Integer value) {
            addCriterion("wm_addition_group_access_id <=", value, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdIn(List<Integer> values) {
            addCriterion("wm_addition_group_access_id in", values, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdNotIn(List<Integer> values) {
            addCriterion("wm_addition_group_access_id not in", values, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdBetween(Integer value1, Integer value2) {
            addCriterion("wm_addition_group_access_id between", value1, value2, "wmAdditionGroupAccessId");
            return (Criteria) this;
        }

        public Criteria andWmAdditionGroupAccessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wm_addition_group_access_id not between", value1, value2, "wmAdditionGroupAccessId");
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