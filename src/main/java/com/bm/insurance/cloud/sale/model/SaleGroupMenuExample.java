package com.bm.insurance.cloud.sale.model;

import java.util.ArrayList;
import java.util.List;

public class SaleGroupMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SaleGroupMenuExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Long> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Long> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Long value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Long value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Long value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Long value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Long value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Long> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Long> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Long value1, Long value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Long value1, Long value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andOperatesIsNull() {
            addCriterion("operates is null");
            return (Criteria) this;
        }

        public Criteria andOperatesIsNotNull() {
            addCriterion("operates is not null");
            return (Criteria) this;
        }

        public Criteria andOperatesEqualTo(String value) {
            addCriterion("operates =", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesNotEqualTo(String value) {
            addCriterion("operates <>", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesGreaterThan(String value) {
            addCriterion("operates >", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesGreaterThanOrEqualTo(String value) {
            addCriterion("operates >=", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesLessThan(String value) {
            addCriterion("operates <", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesLessThanOrEqualTo(String value) {
            addCriterion("operates <=", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesLike(String value) {
            addCriterion("operates like", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesNotLike(String value) {
            addCriterion("operates not like", value, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesIn(List<String> values) {
            addCriterion("operates in", values, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesNotIn(List<String> values) {
            addCriterion("operates not in", values, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesBetween(String value1, String value2) {
            addCriterion("operates between", value1, value2, "operates");
            return (Criteria) this;
        }

        public Criteria andOperatesNotBetween(String value1, String value2) {
            addCriterion("operates not between", value1, value2, "operates");
            return (Criteria) this;
        }

        public Criteria andExp1IsNull() {
            addCriterion("exp1 is null");
            return (Criteria) this;
        }

        public Criteria andExp1IsNotNull() {
            addCriterion("exp1 is not null");
            return (Criteria) this;
        }

        public Criteria andExp1EqualTo(String value) {
            addCriterion("exp1 =", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1NotEqualTo(String value) {
            addCriterion("exp1 <>", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1GreaterThan(String value) {
            addCriterion("exp1 >", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1GreaterThanOrEqualTo(String value) {
            addCriterion("exp1 >=", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1LessThan(String value) {
            addCriterion("exp1 <", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1LessThanOrEqualTo(String value) {
            addCriterion("exp1 <=", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1Like(String value) {
            addCriterion("exp1 like", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1NotLike(String value) {
            addCriterion("exp1 not like", value, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1In(List<String> values) {
            addCriterion("exp1 in", values, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1NotIn(List<String> values) {
            addCriterion("exp1 not in", values, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1Between(String value1, String value2) {
            addCriterion("exp1 between", value1, value2, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp1NotBetween(String value1, String value2) {
            addCriterion("exp1 not between", value1, value2, "exp1");
            return (Criteria) this;
        }

        public Criteria andExp2IsNull() {
            addCriterion("exp2 is null");
            return (Criteria) this;
        }

        public Criteria andExp2IsNotNull() {
            addCriterion("exp2 is not null");
            return (Criteria) this;
        }

        public Criteria andExp2EqualTo(String value) {
            addCriterion("exp2 =", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2NotEqualTo(String value) {
            addCriterion("exp2 <>", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2GreaterThan(String value) {
            addCriterion("exp2 >", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2GreaterThanOrEqualTo(String value) {
            addCriterion("exp2 >=", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2LessThan(String value) {
            addCriterion("exp2 <", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2LessThanOrEqualTo(String value) {
            addCriterion("exp2 <=", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2Like(String value) {
            addCriterion("exp2 like", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2NotLike(String value) {
            addCriterion("exp2 not like", value, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2In(List<String> values) {
            addCriterion("exp2 in", values, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2NotIn(List<String> values) {
            addCriterion("exp2 not in", values, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2Between(String value1, String value2) {
            addCriterion("exp2 between", value1, value2, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp2NotBetween(String value1, String value2) {
            addCriterion("exp2 not between", value1, value2, "exp2");
            return (Criteria) this;
        }

        public Criteria andExp3IsNull() {
            addCriterion("exp3 is null");
            return (Criteria) this;
        }

        public Criteria andExp3IsNotNull() {
            addCriterion("exp3 is not null");
            return (Criteria) this;
        }

        public Criteria andExp3EqualTo(String value) {
            addCriterion("exp3 =", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3NotEqualTo(String value) {
            addCriterion("exp3 <>", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3GreaterThan(String value) {
            addCriterion("exp3 >", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3GreaterThanOrEqualTo(String value) {
            addCriterion("exp3 >=", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3LessThan(String value) {
            addCriterion("exp3 <", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3LessThanOrEqualTo(String value) {
            addCriterion("exp3 <=", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3Like(String value) {
            addCriterion("exp3 like", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3NotLike(String value) {
            addCriterion("exp3 not like", value, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3In(List<String> values) {
            addCriterion("exp3 in", values, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3NotIn(List<String> values) {
            addCriterion("exp3 not in", values, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3Between(String value1, String value2) {
            addCriterion("exp3 between", value1, value2, "exp3");
            return (Criteria) this;
        }

        public Criteria andExp3NotBetween(String value1, String value2) {
            addCriterion("exp3 not between", value1, value2, "exp3");
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