package com.nanrui.userscore.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 原始数据读取，规则生成的依赖数据，规则根据现有的原始数据生成
 * 这是对应的JavaBean
 * @ClassName SourceData
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/4 8:53
 * @Version 1.0
 **/
@Entity(name = "source_data")
@Table(name = "source_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SourceData {

    /**
     * 贷款人数据库存储ID
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")//自增主键
    private String loanUser_id;

    /**
     * 贷款人年龄
     */
    @Column(name = "age")
    private String age;

    /**
     * 性别及婚姻状态
     */
    @Column(name = "personal_status_and_sex")
    private String personal_status_and_sex;

    /**
     * 社保缴纳时间
     */
    @Column(name = "duration_month")
    private String duration_month;

    /**
     * 分期付款率占可支配收入的百分比
     */
    @Column(name = "installment_income")
    private String installment_income;

    /**
     * 从业经历
     */
    @Column(name = "employment_since")
    private String employment_since;

    /**
     * 住房情况
     */
    @Column(name = "housing")
    private String housing;

    /**
     * 其他分期付款计划
     */
    @Column(name = "installment_plans")
    private String installment_plans;

    /**
     * 储蓄存款及债券
     */
    @Column(name = "savings_account_and_bonds")
    private String savings_account_and_bonds;

    /**
     * 信用历史
     */
    @Column(name = "credit_history")
    private String credit_history;

    /**
     * 欠款金额
     */
    @Column(name = "credit_amount")
    private String credit_amount;

    /**
     * 现有支票帐户的状况
     */
    @Column(name = "status_sxisting_checking_account")
    private String status_sxisting_checking_account;

    /**
     * 用途
     */
    @Column(name = "purpose")
    private String purpose;

    /**
     * 其他债务人或保证人
     */
    @Column(name = "other_debtors_or_guarantors")
    private String other_debtors_or_guarantors;

    /**
     * 财产
     */
    @Column(name = "property")
    private String property;

    /**
     * 可信性
     */
    @Column(name = "creditability")
    private String creditability;

    /**
     * 原始数据是否经过处理的标识
     */
    @Column(name = "section")
    private String section;

    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 原始数据是否经过处理的标识
     *      0       未处理
     *      1       处理过
     */
    @Column(name = "dispose_sign")
    private String dispose_sign;

    public SourceData() {
    }

    public SourceData(String age, String personal_status_and_sex, String duration_month, String installment_income, String employment_since, String housing, String installment_plans, String savings_account_and_bonds, String credit_history, String credit_amount, String status_sxisting_checking_account, String purpose, String other_debtors_or_guarantors, String property, String creditability, String section, String unit, String dispose_sign) {
        this.age = age;
        this.personal_status_and_sex = personal_status_and_sex;
        this.duration_month = duration_month;
        this.installment_income = installment_income;
        this.employment_since = employment_since;
        this.housing = housing;
        this.installment_plans = installment_plans;
        this.savings_account_and_bonds = savings_account_and_bonds;
        this.credit_history = credit_history;
        this.credit_amount = credit_amount;
        this.status_sxisting_checking_account = status_sxisting_checking_account;
        this.purpose = purpose;
        this.other_debtors_or_guarantors = other_debtors_or_guarantors;
        this.property = property;
        this.creditability = creditability;
        this.section = section;
        this.unit = unit;
        this.dispose_sign = dispose_sign;
    }

    public String getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(String loanUser_id) {
        this.loanUser_id = loanUser_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPersonal_status_and_sex() {
        return personal_status_and_sex;
    }

    public void setPersonal_status_and_sex(String personal_status_and_sex) {
        this.personal_status_and_sex = personal_status_and_sex;
    }

    public String getDuration_month() {
        return duration_month;
    }

    public void setDuration_month(String duration_month) {
        this.duration_month = duration_month;
    }

    public String getInstallment_income() {
        return installment_income;
    }

    public void setInstallment_income(String installment_income) {
        this.installment_income = installment_income;
    }

    public String getEmployment_since() {
        return employment_since;
    }

    public void setEmployment_since(String employment_since) {
        this.employment_since = employment_since;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getInstallment_plans() {
        return installment_plans;
    }

    public void setInstallment_plans(String installment_plans) {
        this.installment_plans = installment_plans;
    }

    public String getSavings_account_and_bonds() {
        return savings_account_and_bonds;
    }

    public void setSavings_account_and_bonds(String savings_account_and_bonds) {
        this.savings_account_and_bonds = savings_account_and_bonds;
    }

    public String getCredit_history() {
        return credit_history;
    }

    public void setCredit_history(String credit_history) {
        this.credit_history = credit_history;
    }

    public String getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(String credit_amount) {
        this.credit_amount = credit_amount;
    }

    public String getStatus_sxisting_checking_account() {
        return status_sxisting_checking_account;
    }

    public void setStatus_sxisting_checking_account(String status_sxisting_checking_account) {
        this.status_sxisting_checking_account = status_sxisting_checking_account;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOther_debtors_or_guarantors() {
        return other_debtors_or_guarantors;
    }

    public void setOther_debtors_or_guarantors(String other_debtors_or_guarantors) {
        this.other_debtors_or_guarantors = other_debtors_or_guarantors;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCreditability() {
        return creditability;
    }

    public void setCreditability(String creditability) {
        this.creditability = creditability;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDispose_sign() {
        return dispose_sign;
    }

    public void setDispose_sign(String dispose_sign) {
        this.dispose_sign = dispose_sign;
    }
}
