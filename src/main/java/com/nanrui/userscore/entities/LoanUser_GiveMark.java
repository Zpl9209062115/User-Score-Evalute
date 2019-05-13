package com.nanrui.userscore.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * 通过用户导入的数据，经过规则的比较，计算，得出得分，并导入数据库，这个是相对应的JavaBean
 *
 * @ClassName loanUser
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:21
 * @Version 1.0
 **/
@Entity(name = "loan_user_give_mark")
@Table(name = "loan_user_give_mark")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LoanUser_GiveMark {
    @Id
    @GeneratedValue(generator = "jpa-uuid")//自增主键
    private String loanUser_id_giveMark;

    /**
     * 贷款人姓名
     */
    private String loanUser_name_giveMark;

    /**
     * 贷款人年龄
     */
    private Integer ageScore_giveMark;

    /**
     * 性别及婚姻状态
     */
    private Integer personalStatusAndSexScore_giveMark;

    /**
     * 社保缴纳时间
     */
    private Integer durationMonthScore_giveMark;

    /**
     * 分期付款率占可支配收入的百分比
     */
    private Integer installmentIncomeScore_giveMark;

    /**
     * 从业经历
     */
    private Integer employmentSinceScore_giveMark;

    /**
     * 住房情况
     */
    private Integer housingScore_giveMark;

    /**
     * 其他分期付款计划
     */
    private Integer installmentPlansScore_giveMark;

    /**
     * 储蓄存款及债券
     */
    private Integer savingsAccountAndBondsScore_giveMark;

    /**
     * 信用历史
     */
    private Integer creditHistoryScore_giveMark;

    /**
     * 欠款金额
     */
    private Integer creditAmountScore_giveMark;

    /**
     * 现有支票帐户的状况
     */
    private Integer statusSxistingCheckingAccountScore_giveMark;

    /**
     * 用途
     */
    private Integer purposeScore_giveMark;

    /**
     * 其他债务人或保证人
     */
    private Integer otherDebtorsOrGuarantorsScore_giveMark;

    /**
     * 得到总分
     */
    private Integer sumPoints;

    /**
     * 基础分
     */
    private Integer basicPoints = 451;

    private String loanUser_id;

    public LoanUser_GiveMark() {
    }

    public LoanUser_GiveMark(String loanUser_name_giveMark, Integer ageScore_giveMark, Integer personalStatusAndSexScore_giveMark, Integer durationMonthScore_giveMark, Integer installmentIncomeScore_giveMark, Integer employmentSinceScore_giveMark, Integer housingScore_giveMark, Integer installmentPlansScore_giveMark, Integer savingsAccountAndBondsScore_giveMark, Integer creditHistoryScore_giveMark, Integer creditAmountScore_giveMark, Integer statusSxistingCheckingAccountScore_giveMark, Integer purposeScore_giveMark, Integer otherDebtorsOrGuarantorsScore_giveMark, Integer sumPoints, Integer basicPoints, String loanUser_id) {
        this.loanUser_name_giveMark = loanUser_name_giveMark;
        this.ageScore_giveMark = ageScore_giveMark;
        this.personalStatusAndSexScore_giveMark = personalStatusAndSexScore_giveMark;
        this.durationMonthScore_giveMark = durationMonthScore_giveMark;
        this.installmentIncomeScore_giveMark = installmentIncomeScore_giveMark;
        this.employmentSinceScore_giveMark = employmentSinceScore_giveMark;
        this.housingScore_giveMark = housingScore_giveMark;
        this.installmentPlansScore_giveMark = installmentPlansScore_giveMark;
        this.savingsAccountAndBondsScore_giveMark = savingsAccountAndBondsScore_giveMark;
        this.creditHistoryScore_giveMark = creditHistoryScore_giveMark;
        this.creditAmountScore_giveMark = creditAmountScore_giveMark;
        this.statusSxistingCheckingAccountScore_giveMark = statusSxistingCheckingAccountScore_giveMark;
        this.purposeScore_giveMark = purposeScore_giveMark;
        this.otherDebtorsOrGuarantorsScore_giveMark = otherDebtorsOrGuarantorsScore_giveMark;
        this.sumPoints = sumPoints;
        this.basicPoints = basicPoints;
        this.loanUser_id = loanUser_id;
    }

    public String getLoanUser_id_giveMark() {
        return loanUser_id_giveMark;
    }

    public void setLoanUser_id_giveMark(String loanUser_id_giveMark) {
        this.loanUser_id_giveMark = loanUser_id_giveMark;
    }

    public String getLoanUser_name_giveMark() {
        return loanUser_name_giveMark;
    }

    public void setLoanUser_name_giveMark(String loanUser_name_giveMark) {
        this.loanUser_name_giveMark = loanUser_name_giveMark;
    }

    public Integer getAgeScore_giveMark() {
        return ageScore_giveMark;
    }

    public void setAgeScore_giveMark(Integer ageScore_giveMark) {
        this.ageScore_giveMark = ageScore_giveMark;
    }

    public Integer getPersonalStatusAndSexScore_giveMark() {
        return personalStatusAndSexScore_giveMark;
    }

    public void setPersonalStatusAndSexScore_giveMark(Integer personalStatusAndSexScore_giveMark) {
        this.personalStatusAndSexScore_giveMark = personalStatusAndSexScore_giveMark;
    }

    public Integer getDurationMonthScore_giveMark() {
        return durationMonthScore_giveMark;
    }

    public void setDurationMonthScore_giveMark(Integer durationMonthScore_giveMark) {
        this.durationMonthScore_giveMark = durationMonthScore_giveMark;
    }

    public Integer getInstallmentIncomeScore_giveMark() {
        return installmentIncomeScore_giveMark;
    }

    public void setInstallmentIncomeScore_giveMark(Integer installmentIncomeScore_giveMark) {
        this.installmentIncomeScore_giveMark = installmentIncomeScore_giveMark;
    }

    public Integer getEmploymentSinceScore_giveMark() {
        return employmentSinceScore_giveMark;
    }

    public void setEmploymentSinceScore_giveMark(Integer employmentSinceScore_giveMark) {
        this.employmentSinceScore_giveMark = employmentSinceScore_giveMark;
    }

    public Integer getHousingScore_giveMark() {
        return housingScore_giveMark;
    }

    public void setHousingScore_giveMark(Integer housingScore_giveMark) {
        this.housingScore_giveMark = housingScore_giveMark;
    }

    public Integer getInstallmentPlansScore_giveMark() {
        return installmentPlansScore_giveMark;
    }

    public void setInstallmentPlansScore_giveMark(Integer installmentPlansScore_giveMark) {
        this.installmentPlansScore_giveMark = installmentPlansScore_giveMark;
    }

    public Integer getSavingsAccountAndBondsScore_giveMark() {
        return savingsAccountAndBondsScore_giveMark;
    }

    public void setSavingsAccountAndBondsScore_giveMark(Integer savingsAccountAndBondsScore_giveMark) {
        this.savingsAccountAndBondsScore_giveMark = savingsAccountAndBondsScore_giveMark;
    }

    public Integer getCreditHistoryScore_giveMark() {
        return creditHistoryScore_giveMark;
    }

    public void setCreditHistoryScore_giveMark(Integer creditHistoryScore_giveMark) {
        this.creditHistoryScore_giveMark = creditHistoryScore_giveMark;
    }

    public Integer getCreditAmountScore_giveMark() {
        return creditAmountScore_giveMark;
    }

    public void setCreditAmountScore_giveMark(Integer creditAmountScore_giveMark) {
        this.creditAmountScore_giveMark = creditAmountScore_giveMark;
    }

    public Integer getStatusSxistingCheckingAccountScore_giveMark() {
        return statusSxistingCheckingAccountScore_giveMark;
    }

    public void setStatusSxistingCheckingAccountScore_giveMark(Integer statusSxistingCheckingAccountScore_giveMark) {
        this.statusSxistingCheckingAccountScore_giveMark = statusSxistingCheckingAccountScore_giveMark;
    }

    public Integer getPurposeScore_giveMark() {
        return purposeScore_giveMark;
    }

    public void setPurposeScore_giveMark(Integer purposeScore_giveMark) {
        this.purposeScore_giveMark = purposeScore_giveMark;
    }

    public Integer getOtherDebtorsOrGuarantorsScore_giveMark() {
        return otherDebtorsOrGuarantorsScore_giveMark;
    }

    public void setOtherDebtorsOrGuarantorsScore_giveMark(Integer otherDebtorsOrGuarantorsScore_giveMark) {
        this.otherDebtorsOrGuarantorsScore_giveMark = otherDebtorsOrGuarantorsScore_giveMark;
    }

    public Integer getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(Integer sumPoints) {
        this.sumPoints = sumPoints;
    }

    public Integer getBasicPoints() {
        return basicPoints;
    }

    public void setBasicPoints(Integer basicPoints) {
        this.basicPoints = basicPoints;
    }

    public String getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(String loanUser_id) {
        this.loanUser_id = loanUser_id;
    }
}