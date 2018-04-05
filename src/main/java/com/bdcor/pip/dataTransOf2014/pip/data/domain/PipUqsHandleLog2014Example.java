package com.bdcor.pip.dataTransOf2014.pip.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipUqsHandleLog2014Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipUqsHandleLog2014Example() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("PROJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("PROJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("PROJECT_ID =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("PROJECT_ID <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("PROJECT_ID >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("PROJECT_ID <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("PROJECT_ID like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("PROJECT_ID not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("PROJECT_ID in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("PROJECT_ID not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("PROJECT_ID between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("PROJECT_ID not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("PATIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("PATIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("PATIENT_NAME =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("PATIENT_NAME <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("PATIENT_NAME >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("PATIENT_NAME <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("PATIENT_NAME like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("PATIENT_NAME not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("PATIENT_NAME in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("PATIENT_NAME not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andUqsidIsNull() {
            addCriterion("UQSID is null");
            return (Criteria) this;
        }

        public Criteria andUqsidIsNotNull() {
            addCriterion("UQSID is not null");
            return (Criteria) this;
        }

        public Criteria andUqsidEqualTo(String value) {
            addCriterion("UQSID =", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidNotEqualTo(String value) {
            addCriterion("UQSID <>", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidGreaterThan(String value) {
            addCriterion("UQSID >", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidGreaterThanOrEqualTo(String value) {
            addCriterion("UQSID >=", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidLessThan(String value) {
            addCriterion("UQSID <", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidLessThanOrEqualTo(String value) {
            addCriterion("UQSID <=", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidLike(String value) {
            addCriterion("UQSID like", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidNotLike(String value) {
            addCriterion("UQSID not like", value, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidIn(List<String> values) {
            addCriterion("UQSID in", values, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidNotIn(List<String> values) {
            addCriterion("UQSID not in", values, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidBetween(String value1, String value2) {
            addCriterion("UQSID between", value1, value2, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsidNotBetween(String value1, String value2) {
            addCriterion("UQSID not between", value1, value2, "uqsid");
            return (Criteria) this;
        }

        public Criteria andUqsnameIsNull() {
            addCriterion("UQSName is null");
            return (Criteria) this;
        }

        public Criteria andUqsnameIsNotNull() {
            addCriterion("UQSName is not null");
            return (Criteria) this;
        }

        public Criteria andUqsnameEqualTo(String value) {
            addCriterion("UQSName =", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameNotEqualTo(String value) {
            addCriterion("UQSName <>", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameGreaterThan(String value) {
            addCriterion("UQSName >", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameGreaterThanOrEqualTo(String value) {
            addCriterion("UQSName >=", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameLessThan(String value) {
            addCriterion("UQSName <", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameLessThanOrEqualTo(String value) {
            addCriterion("UQSName <=", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameLike(String value) {
            addCriterion("UQSName like", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameNotLike(String value) {
            addCriterion("UQSName not like", value, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameIn(List<String> values) {
            addCriterion("UQSName in", values, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameNotIn(List<String> values) {
            addCriterion("UQSName not in", values, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameBetween(String value1, String value2) {
            addCriterion("UQSName between", value1, value2, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsnameNotBetween(String value1, String value2) {
            addCriterion("UQSName not between", value1, value2, "uqsname");
            return (Criteria) this;
        }

        public Criteria andUqsversionIsNull() {
            addCriterion("UQSVersion is null");
            return (Criteria) this;
        }

        public Criteria andUqsversionIsNotNull() {
            addCriterion("UQSVersion is not null");
            return (Criteria) this;
        }

        public Criteria andUqsversionEqualTo(String value) {
            addCriterion("UQSVersion =", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionNotEqualTo(String value) {
            addCriterion("UQSVersion <>", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionGreaterThan(String value) {
            addCriterion("UQSVersion >", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionGreaterThanOrEqualTo(String value) {
            addCriterion("UQSVersion >=", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionLessThan(String value) {
            addCriterion("UQSVersion <", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionLessThanOrEqualTo(String value) {
            addCriterion("UQSVersion <=", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionLike(String value) {
            addCriterion("UQSVersion like", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionNotLike(String value) {
            addCriterion("UQSVersion not like", value, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionIn(List<String> values) {
            addCriterion("UQSVersion in", values, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionNotIn(List<String> values) {
            addCriterion("UQSVersion not in", values, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionBetween(String value1, String value2) {
            addCriterion("UQSVersion between", value1, value2, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andUqsversionNotBetween(String value1, String value2) {
            addCriterion("UQSVersion not between", value1, value2, "uqsversion");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateIsNull() {
            addCriterion("VersionCreateDate is null");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateIsNotNull() {
            addCriterion("VersionCreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateEqualTo(Date value) {
            addCriterion("VersionCreateDate =", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateNotEqualTo(Date value) {
            addCriterion("VersionCreateDate <>", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateGreaterThan(Date value) {
            addCriterion("VersionCreateDate >", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("VersionCreateDate >=", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateLessThan(Date value) {
            addCriterion("VersionCreateDate <", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateLessThanOrEqualTo(Date value) {
            addCriterion("VersionCreateDate <=", value, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateIn(List<Date> values) {
            addCriterion("VersionCreateDate in", values, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateNotIn(List<Date> values) {
            addCriterion("VersionCreateDate not in", values, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateBetween(Date value1, Date value2) {
            addCriterion("VersionCreateDate between", value1, value2, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andVersioncreatedateNotBetween(Date value1, Date value2) {
            addCriterion("VersionCreateDate not between", value1, value2, "versioncreatedate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateIsNull() {
            addCriterion("QUESTION_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateIsNotNull() {
            addCriterion("QUESTION_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateEqualTo(Date value) {
            addCriterion("QUESTION_START_DATE =", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateNotEqualTo(Date value) {
            addCriterion("QUESTION_START_DATE <>", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateGreaterThan(Date value) {
            addCriterion("QUESTION_START_DATE >", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("QUESTION_START_DATE >=", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateLessThan(Date value) {
            addCriterion("QUESTION_START_DATE <", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateLessThanOrEqualTo(Date value) {
            addCriterion("QUESTION_START_DATE <=", value, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateIn(List<Date> values) {
            addCriterion("QUESTION_START_DATE in", values, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateNotIn(List<Date> values) {
            addCriterion("QUESTION_START_DATE not in", values, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateBetween(Date value1, Date value2) {
            addCriterion("QUESTION_START_DATE between", value1, value2, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionStartDateNotBetween(Date value1, Date value2) {
            addCriterion("QUESTION_START_DATE not between", value1, value2, "questionStartDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateIsNull() {
            addCriterion("QUESTION_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateIsNotNull() {
            addCriterion("QUESTION_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateEqualTo(Date value) {
            addCriterion("QUESTION_END_DATE =", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateNotEqualTo(Date value) {
            addCriterion("QUESTION_END_DATE <>", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateGreaterThan(Date value) {
            addCriterion("QUESTION_END_DATE >", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("QUESTION_END_DATE >=", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateLessThan(Date value) {
            addCriterion("QUESTION_END_DATE <", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateLessThanOrEqualTo(Date value) {
            addCriterion("QUESTION_END_DATE <=", value, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateIn(List<Date> values) {
            addCriterion("QUESTION_END_DATE in", values, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateNotIn(List<Date> values) {
            addCriterion("QUESTION_END_DATE not in", values, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateBetween(Date value1, Date value2) {
            addCriterion("QUESTION_END_DATE between", value1, value2, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andQuestionEndDateNotBetween(Date value1, Date value2) {
            addCriterion("QUESTION_END_DATE not between", value1, value2, "questionEndDate");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneIsNull() {
            addCriterion("uqsTimeZone is null");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneIsNotNull() {
            addCriterion("uqsTimeZone is not null");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneEqualTo(String value) {
            addCriterion("uqsTimeZone =", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneNotEqualTo(String value) {
            addCriterion("uqsTimeZone <>", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneGreaterThan(String value) {
            addCriterion("uqsTimeZone >", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneGreaterThanOrEqualTo(String value) {
            addCriterion("uqsTimeZone >=", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneLessThan(String value) {
            addCriterion("uqsTimeZone <", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneLessThanOrEqualTo(String value) {
            addCriterion("uqsTimeZone <=", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneLike(String value) {
            addCriterion("uqsTimeZone like", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneNotLike(String value) {
            addCriterion("uqsTimeZone not like", value, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneIn(List<String> values) {
            addCriterion("uqsTimeZone in", values, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneNotIn(List<String> values) {
            addCriterion("uqsTimeZone not in", values, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneBetween(String value1, String value2) {
            addCriterion("uqsTimeZone between", value1, value2, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andUqstimezoneNotBetween(String value1, String value2) {
            addCriterion("uqsTimeZone not between", value1, value2, "uqstimezone");
            return (Criteria) this;
        }

        public Criteria andOperateridIsNull() {
            addCriterion("OperaterID is null");
            return (Criteria) this;
        }

        public Criteria andOperateridIsNotNull() {
            addCriterion("OperaterID is not null");
            return (Criteria) this;
        }

        public Criteria andOperateridEqualTo(String value) {
            addCriterion("OperaterID =", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridNotEqualTo(String value) {
            addCriterion("OperaterID <>", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridGreaterThan(String value) {
            addCriterion("OperaterID >", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridGreaterThanOrEqualTo(String value) {
            addCriterion("OperaterID >=", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridLessThan(String value) {
            addCriterion("OperaterID <", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridLessThanOrEqualTo(String value) {
            addCriterion("OperaterID <=", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridLike(String value) {
            addCriterion("OperaterID like", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridNotLike(String value) {
            addCriterion("OperaterID not like", value, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridIn(List<String> values) {
            addCriterion("OperaterID in", values, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridNotIn(List<String> values) {
            addCriterion("OperaterID not in", values, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridBetween(String value1, String value2) {
            addCriterion("OperaterID between", value1, value2, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperateridNotBetween(String value1, String value2) {
            addCriterion("OperaterID not between", value1, value2, "operaterid");
            return (Criteria) this;
        }

        public Criteria andOperaternameIsNull() {
            addCriterion("OperaterName is null");
            return (Criteria) this;
        }

        public Criteria andOperaternameIsNotNull() {
            addCriterion("OperaterName is not null");
            return (Criteria) this;
        }

        public Criteria andOperaternameEqualTo(String value) {
            addCriterion("OperaterName =", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameNotEqualTo(String value) {
            addCriterion("OperaterName <>", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameGreaterThan(String value) {
            addCriterion("OperaterName >", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameGreaterThanOrEqualTo(String value) {
            addCriterion("OperaterName >=", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameLessThan(String value) {
            addCriterion("OperaterName <", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameLessThanOrEqualTo(String value) {
            addCriterion("OperaterName <=", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameLike(String value) {
            addCriterion("OperaterName like", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameNotLike(String value) {
            addCriterion("OperaterName not like", value, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameIn(List<String> values) {
            addCriterion("OperaterName in", values, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameNotIn(List<String> values) {
            addCriterion("OperaterName not in", values, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameBetween(String value1, String value2) {
            addCriterion("OperaterName between", value1, value2, "operatername");
            return (Criteria) this;
        }

        public Criteria andOperaternameNotBetween(String value1, String value2) {
            addCriterion("OperaterName not between", value1, value2, "operatername");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeIsNull() {
            addCriterion("HospitalCode is null");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeIsNotNull() {
            addCriterion("HospitalCode is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeEqualTo(String value) {
            addCriterion("HospitalCode =", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeNotEqualTo(String value) {
            addCriterion("HospitalCode <>", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeGreaterThan(String value) {
            addCriterion("HospitalCode >", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeGreaterThanOrEqualTo(String value) {
            addCriterion("HospitalCode >=", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeLessThan(String value) {
            addCriterion("HospitalCode <", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeLessThanOrEqualTo(String value) {
            addCriterion("HospitalCode <=", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeLike(String value) {
            addCriterion("HospitalCode like", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeNotLike(String value) {
            addCriterion("HospitalCode not like", value, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeIn(List<String> values) {
            addCriterion("HospitalCode in", values, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeNotIn(List<String> values) {
            addCriterion("HospitalCode not in", values, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeBetween(String value1, String value2) {
            addCriterion("HospitalCode between", value1, value2, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andHospitalcodeNotBetween(String value1, String value2) {
            addCriterion("HospitalCode not between", value1, value2, "hospitalcode");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNull() {
            addCriterion("PATIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNotNull() {
            addCriterion("PATIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdEqualTo(String value) {
            addCriterion("PATIENT_ID =", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotEqualTo(String value) {
            addCriterion("PATIENT_ID <>", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThan(String value) {
            addCriterion("PATIENT_ID >", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID >=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThan(String value) {
            addCriterion("PATIENT_ID <", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID <=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLike(String value) {
            addCriterion("PATIENT_ID like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotLike(String value) {
            addCriterion("PATIENT_ID not like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIn(List<String> values) {
            addCriterion("PATIENT_ID in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotIn(List<String> values) {
            addCriterion("PATIENT_ID not in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdBetween(String value1, String value2) {
            addCriterion("PATIENT_ID between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotBetween(String value1, String value2) {
            addCriterion("PATIENT_ID not between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPersonidIsNull() {
            addCriterion("PersonID is null");
            return (Criteria) this;
        }

        public Criteria andPersonidIsNotNull() {
            addCriterion("PersonID is not null");
            return (Criteria) this;
        }

        public Criteria andPersonidEqualTo(String value) {
            addCriterion("PersonID =", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotEqualTo(String value) {
            addCriterion("PersonID <>", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidGreaterThan(String value) {
            addCriterion("PersonID >", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidGreaterThanOrEqualTo(String value) {
            addCriterion("PersonID >=", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidLessThan(String value) {
            addCriterion("PersonID <", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidLessThanOrEqualTo(String value) {
            addCriterion("PersonID <=", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidLike(String value) {
            addCriterion("PersonID like", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotLike(String value) {
            addCriterion("PersonID not like", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidIn(List<String> values) {
            addCriterion("PersonID in", values, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotIn(List<String> values) {
            addCriterion("PersonID not in", values, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidBetween(String value1, String value2) {
            addCriterion("PersonID between", value1, value2, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotBetween(String value1, String value2) {
            addCriterion("PersonID not between", value1, value2, "personid");
            return (Criteria) this;
        }

        public Criteria andPatientnameIsNull() {
            addCriterion("PatientName is null");
            return (Criteria) this;
        }

        public Criteria andPatientnameIsNotNull() {
            addCriterion("PatientName is not null");
            return (Criteria) this;
        }

        public Criteria andPatientnameEqualTo(String value) {
            addCriterion("PatientName =", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameNotEqualTo(String value) {
            addCriterion("PatientName <>", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameGreaterThan(String value) {
            addCriterion("PatientName >", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameGreaterThanOrEqualTo(String value) {
            addCriterion("PatientName >=", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameLessThan(String value) {
            addCriterion("PatientName <", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameLessThanOrEqualTo(String value) {
            addCriterion("PatientName <=", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameLike(String value) {
            addCriterion("PatientName like", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameNotLike(String value) {
            addCriterion("PatientName not like", value, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameIn(List<String> values) {
            addCriterion("PatientName in", values, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameNotIn(List<String> values) {
            addCriterion("PatientName not in", values, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameBetween(String value1, String value2) {
            addCriterion("PatientName between", value1, value2, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientnameNotBetween(String value1, String value2) {
            addCriterion("PatientName not between", value1, value2, "patientname");
            return (Criteria) this;
        }

        public Criteria andPatientcodeIsNull() {
            addCriterion("PatientCode is null");
            return (Criteria) this;
        }

        public Criteria andPatientcodeIsNotNull() {
            addCriterion("PatientCode is not null");
            return (Criteria) this;
        }

        public Criteria andPatientcodeEqualTo(String value) {
            addCriterion("PatientCode =", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeNotEqualTo(String value) {
            addCriterion("PatientCode <>", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeGreaterThan(String value) {
            addCriterion("PatientCode >", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PatientCode >=", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeLessThan(String value) {
            addCriterion("PatientCode <", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeLessThanOrEqualTo(String value) {
            addCriterion("PatientCode <=", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeLike(String value) {
            addCriterion("PatientCode like", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeNotLike(String value) {
            addCriterion("PatientCode not like", value, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeIn(List<String> values) {
            addCriterion("PatientCode in", values, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeNotIn(List<String> values) {
            addCriterion("PatientCode not in", values, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeBetween(String value1, String value2) {
            addCriterion("PatientCode between", value1, value2, "patientcode");
            return (Criteria) this;
        }

        public Criteria andPatientcodeNotBetween(String value1, String value2) {
            addCriterion("PatientCode not between", value1, value2, "patientcode");
            return (Criteria) this;
        }

        public Criteria andUqsisholdIsNull() {
            addCriterion("UQSIsHold is null");
            return (Criteria) this;
        }

        public Criteria andUqsisholdIsNotNull() {
            addCriterion("UQSIsHold is not null");
            return (Criteria) this;
        }

        public Criteria andUqsisholdEqualTo(String value) {
            addCriterion("UQSIsHold =", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdNotEqualTo(String value) {
            addCriterion("UQSIsHold <>", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdGreaterThan(String value) {
            addCriterion("UQSIsHold >", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdGreaterThanOrEqualTo(String value) {
            addCriterion("UQSIsHold >=", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdLessThan(String value) {
            addCriterion("UQSIsHold <", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdLessThanOrEqualTo(String value) {
            addCriterion("UQSIsHold <=", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdLike(String value) {
            addCriterion("UQSIsHold like", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdNotLike(String value) {
            addCriterion("UQSIsHold not like", value, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdIn(List<String> values) {
            addCriterion("UQSIsHold in", values, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdNotIn(List<String> values) {
            addCriterion("UQSIsHold not in", values, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdBetween(String value1, String value2) {
            addCriterion("UQSIsHold between", value1, value2, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsisholdNotBetween(String value1, String value2) {
            addCriterion("UQSIsHold not between", value1, value2, "uqsishold");
            return (Criteria) this;
        }

        public Criteria andUqsremarkIsNull() {
            addCriterion("UQSRemark is null");
            return (Criteria) this;
        }

        public Criteria andUqsremarkIsNotNull() {
            addCriterion("UQSRemark is not null");
            return (Criteria) this;
        }

        public Criteria andUqsremarkEqualTo(String value) {
            addCriterion("UQSRemark =", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkNotEqualTo(String value) {
            addCriterion("UQSRemark <>", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkGreaterThan(String value) {
            addCriterion("UQSRemark >", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkGreaterThanOrEqualTo(String value) {
            addCriterion("UQSRemark >=", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkLessThan(String value) {
            addCriterion("UQSRemark <", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkLessThanOrEqualTo(String value) {
            addCriterion("UQSRemark <=", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkLike(String value) {
            addCriterion("UQSRemark like", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkNotLike(String value) {
            addCriterion("UQSRemark not like", value, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkIn(List<String> values) {
            addCriterion("UQSRemark in", values, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkNotIn(List<String> values) {
            addCriterion("UQSRemark not in", values, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkBetween(String value1, String value2) {
            addCriterion("UQSRemark between", value1, value2, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andUqsremarkNotBetween(String value1, String value2) {
            addCriterion("UQSRemark not between", value1, value2, "uqsremark");
            return (Criteria) this;
        }

        public Criteria andLccCodeIsNull() {
            addCriterion("LCC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLccCodeIsNotNull() {
            addCriterion("LCC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLccCodeEqualTo(String value) {
            addCriterion("LCC_CODE =", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotEqualTo(String value) {
            addCriterion("LCC_CODE <>", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThan(String value) {
            addCriterion("LCC_CODE >", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LCC_CODE >=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThan(String value) {
            addCriterion("LCC_CODE <", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThanOrEqualTo(String value) {
            addCriterion("LCC_CODE <=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLike(String value) {
            addCriterion("LCC_CODE like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotLike(String value) {
            addCriterion("LCC_CODE not like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeIn(List<String> values) {
            addCriterion("LCC_CODE in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotIn(List<String> values) {
            addCriterion("LCC_CODE not in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeBetween(String value1, String value2) {
            addCriterion("LCC_CODE between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotBetween(String value1, String value2) {
            addCriterion("LCC_CODE not between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andFtpIpIsNull() {
            addCriterion("FTP_IP is null");
            return (Criteria) this;
        }

        public Criteria andFtpIpIsNotNull() {
            addCriterion("FTP_IP is not null");
            return (Criteria) this;
        }

        public Criteria andFtpIpEqualTo(String value) {
            addCriterion("FTP_IP =", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpNotEqualTo(String value) {
            addCriterion("FTP_IP <>", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpGreaterThan(String value) {
            addCriterion("FTP_IP >", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_IP >=", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpLessThan(String value) {
            addCriterion("FTP_IP <", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpLessThanOrEqualTo(String value) {
            addCriterion("FTP_IP <=", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpLike(String value) {
            addCriterion("FTP_IP like", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpNotLike(String value) {
            addCriterion("FTP_IP not like", value, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpIn(List<String> values) {
            addCriterion("FTP_IP in", values, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpNotIn(List<String> values) {
            addCriterion("FTP_IP not in", values, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpBetween(String value1, String value2) {
            addCriterion("FTP_IP between", value1, value2, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpIpNotBetween(String value1, String value2) {
            addCriterion("FTP_IP not between", value1, value2, "ftpIp");
            return (Criteria) this;
        }

        public Criteria andFtpPortIsNull() {
            addCriterion("FTP_PORT is null");
            return (Criteria) this;
        }

        public Criteria andFtpPortIsNotNull() {
            addCriterion("FTP_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andFtpPortEqualTo(Short value) {
            addCriterion("FTP_PORT =", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortNotEqualTo(Short value) {
            addCriterion("FTP_PORT <>", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortGreaterThan(Short value) {
            addCriterion("FTP_PORT >", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortGreaterThanOrEqualTo(Short value) {
            addCriterion("FTP_PORT >=", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortLessThan(Short value) {
            addCriterion("FTP_PORT <", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortLessThanOrEqualTo(Short value) {
            addCriterion("FTP_PORT <=", value, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortIn(List<Short> values) {
            addCriterion("FTP_PORT in", values, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortNotIn(List<Short> values) {
            addCriterion("FTP_PORT not in", values, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortBetween(Short value1, Short value2) {
            addCriterion("FTP_PORT between", value1, value2, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPortNotBetween(Short value1, Short value2) {
            addCriterion("FTP_PORT not between", value1, value2, "ftpPort");
            return (Criteria) this;
        }

        public Criteria andFtpPathIsNull() {
            addCriterion("FTP_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFtpPathIsNotNull() {
            addCriterion("FTP_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFtpPathEqualTo(String value) {
            addCriterion("FTP_PATH =", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathNotEqualTo(String value) {
            addCriterion("FTP_PATH <>", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathGreaterThan(String value) {
            addCriterion("FTP_PATH >", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_PATH >=", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathLessThan(String value) {
            addCriterion("FTP_PATH <", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathLessThanOrEqualTo(String value) {
            addCriterion("FTP_PATH <=", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathLike(String value) {
            addCriterion("FTP_PATH like", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathNotLike(String value) {
            addCriterion("FTP_PATH not like", value, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathIn(List<String> values) {
            addCriterion("FTP_PATH in", values, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathNotIn(List<String> values) {
            addCriterion("FTP_PATH not in", values, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathBetween(String value1, String value2) {
            addCriterion("FTP_PATH between", value1, value2, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpPathNotBetween(String value1, String value2) {
            addCriterion("FTP_PATH not between", value1, value2, "ftpPath");
            return (Criteria) this;
        }

        public Criteria andFtpUserIsNull() {
            addCriterion("FTP_USER is null");
            return (Criteria) this;
        }

        public Criteria andFtpUserIsNotNull() {
            addCriterion("FTP_USER is not null");
            return (Criteria) this;
        }

        public Criteria andFtpUserEqualTo(String value) {
            addCriterion("FTP_USER =", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserNotEqualTo(String value) {
            addCriterion("FTP_USER <>", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserGreaterThan(String value) {
            addCriterion("FTP_USER >", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_USER >=", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserLessThan(String value) {
            addCriterion("FTP_USER <", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserLessThanOrEqualTo(String value) {
            addCriterion("FTP_USER <=", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserLike(String value) {
            addCriterion("FTP_USER like", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserNotLike(String value) {
            addCriterion("FTP_USER not like", value, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserIn(List<String> values) {
            addCriterion("FTP_USER in", values, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserNotIn(List<String> values) {
            addCriterion("FTP_USER not in", values, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserBetween(String value1, String value2) {
            addCriterion("FTP_USER between", value1, value2, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFtpUserNotBetween(String value1, String value2) {
            addCriterion("FTP_USER not between", value1, value2, "ftpUser");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andZipfileNameIsNull() {
            addCriterion("ZIPFILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZipfileNameIsNotNull() {
            addCriterion("ZIPFILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZipfileNameEqualTo(String value) {
            addCriterion("ZIPFILE_NAME =", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameNotEqualTo(String value) {
            addCriterion("ZIPFILE_NAME <>", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameGreaterThan(String value) {
            addCriterion("ZIPFILE_NAME >", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZIPFILE_NAME >=", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameLessThan(String value) {
            addCriterion("ZIPFILE_NAME <", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameLessThanOrEqualTo(String value) {
            addCriterion("ZIPFILE_NAME <=", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameLike(String value) {
            addCriterion("ZIPFILE_NAME like", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameNotLike(String value) {
            addCriterion("ZIPFILE_NAME not like", value, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameIn(List<String> values) {
            addCriterion("ZIPFILE_NAME in", values, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameNotIn(List<String> values) {
            addCriterion("ZIPFILE_NAME not in", values, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameBetween(String value1, String value2) {
            addCriterion("ZIPFILE_NAME between", value1, value2, "zipfileName");
            return (Criteria) this;
        }

        public Criteria andZipfileNameNotBetween(String value1, String value2) {
            addCriterion("ZIPFILE_NAME not between", value1, value2, "zipfileName");
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