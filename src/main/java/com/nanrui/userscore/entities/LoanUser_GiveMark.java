package com.nanrui.userscore.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;

/**
 * 导入评分结果
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
    private String ageScore_giveMark;

    /**
     * 性别及婚姻状态
     */
    private String personalStatusAndSexScore_giveMark;

    /**
     * 社保缴纳时间
     */
    private String durationMonthScore_giveMark;

    /**
     * 分期付款率占可支配收入的百分比
     */
    private String installmentIncomeScore_giveMark;

    /**
     * 从业经历
     */
    private String employmentSinceScore_giveMark;

    /**
     * 住房情况
     */
    private String housingScore_giveMark;

    /**
     * 其他分期付款计划
     */
    private String installmentPlansScore_giveMark;

    /**
     * 储蓄存款及债券
     */
    private String savingsAccountAndBondsScore_giveMark;

    /**
     * 信用历史
     */
    private String creditHistoryScore_giveMark;

    /**
     * 欠款金额
     */
    private String creditAmountScore_giveMark;

    /**
     * 现有支票帐户的状况
     */
    private String statusSxistingCheckingAccountScore_giveMark;

    /**
     * 用途
     */
    private String purposeScore_giveMark;

    /**
     * 其他债务人或保证人
     */
    private String otherDebtorsOrGuarantorsScore_giveMark;

    /**
     * 得到总分
     */
    private String sumPoints;

    /**
     * 基础分
     */
    private String basicPoints = "451";

    private String loanUser_id;

    public LoanUser_GiveMark() {
    }

    public LoanUser_GiveMark(String loanUser_name_giveMark, String ageScore_giveMark, String personalStatusAndSexScore_giveMark, String durationMonthScore_giveMark, String installmentIncomeScore_giveMark, String employmentSinceScore_giveMark, String housingScore_giveMark, String installmentPlansScore_giveMark, String savingsAccountAndBondsScore_giveMark, String creditHistoryScore_giveMark, String creditAmountScore_giveMark, String statusSxistingCheckingAccountScore_giveMark, String purposeScore_giveMark, String otherDebtorsOrGuarantorsScore_giveMark, String sumPoints, String loanUser_id) {
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

    public String getAgeScore_giveMark() {
        return ageScore_giveMark;
    }

    public void setAgeScore_giveMark(String ageScore_giveMark) {
        this.ageScore_giveMark = ageScore_giveMark;
    }

    public String getPersonalStatusAndSexScore_giveMark() {
        return personalStatusAndSexScore_giveMark;
    }

    public void setPersonalStatusAndSexScore_giveMark(String personalStatusAndSexScore_giveMark) {
        this.personalStatusAndSexScore_giveMark = personalStatusAndSexScore_giveMark;
    }

    public String getDurationMonthScore_giveMark() {
        return durationMonthScore_giveMark;
    }

    public void setDurationMonthScore_giveMark(String durationMonthScore_giveMark) {
        this.durationMonthScore_giveMark = durationMonthScore_giveMark;
    }

    public String getInstallmentIncomeScore_giveMark() {
        return installmentIncomeScore_giveMark;
    }

    public void setInstallmentIncomeScore_giveMark(String installmentIncomeScore_giveMark) {
        this.installmentIncomeScore_giveMark = installmentIncomeScore_giveMark;
    }

    public String getEmploymentSinceScore_giveMark() {
        return employmentSinceScore_giveMark;
    }

    public void setEmploymentSinceScore_giveMark(String employmentSinceScore_giveMark) {
        this.employmentSinceScore_giveMark = employmentSinceScore_giveMark;
    }

    public String getHousingScore_giveMark() {
        return housingScore_giveMark;
    }

    public void setHousingScore_giveMark(String housingScore_giveMark) {
        this.housingScore_giveMark = housingScore_giveMark;
    }

    public String getInstallmentPlansScore_giveMark() {
        return installmentPlansScore_giveMark;
    }

    public void setInstallmentPlansScore_giveMark(String installmentPlansScore_giveMark) {
        this.installmentPlansScore_giveMark = installmentPlansScore_giveMark;
    }

    public String getSavingsAccountAndBondsScore_giveMark() {
        return savingsAccountAndBondsScore_giveMark;
    }

    public void setSavingsAccountAndBondsScore_giveMark(String savingsAccountAndBondsScore_giveMark) {
        this.savingsAccountAndBondsScore_giveMark = savingsAccountAndBondsScore_giveMark;
    }

    public String getCreditHistoryScore_giveMark() {
        return creditHistoryScore_giveMark;
    }

    public void setCreditHistoryScore_giveMark(String creditHistoryScore_giveMark) {
        this.creditHistoryScore_giveMark = creditHistoryScore_giveMark;
    }

    public String getCreditAmountScore_giveMark() {
        return creditAmountScore_giveMark;
    }

    public void setCreditAmountScore_giveMark(String creditAmountScore_giveMark) {
        this.creditAmountScore_giveMark = creditAmountScore_giveMark;
    }

    public String getStatusSxistingCheckingAccountScore_giveMark() {
        return statusSxistingCheckingAccountScore_giveMark;
    }

    public void setStatusSxistingCheckingAccountScore_giveMark(String statusSxistingCheckingAccountScore_giveMark) {
        this.statusSxistingCheckingAccountScore_giveMark = statusSxistingCheckingAccountScore_giveMark;
    }

    public String getPurposeScore_giveMark() {
        return purposeScore_giveMark;
    }

    public void setPurposeScore_giveMark(String purposeScore_giveMark) {
        this.purposeScore_giveMark = purposeScore_giveMark;
    }

    public String getOtherDebtorsOrGuarantorsScore_giveMark() {
        return otherDebtorsOrGuarantorsScore_giveMark;
    }

    public void setOtherDebtorsOrGuarantorsScore_giveMark(String otherDebtorsOrGuarantorsScore_giveMark) {
        this.otherDebtorsOrGuarantorsScore_giveMark = otherDebtorsOrGuarantorsScore_giveMark;
    }

    public String getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(String loanUser_id) {
        this.loanUser_id = loanUser_id;
    }

    public String getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(String sumPoints) {
        this.sumPoints = sumPoints;
    }

    public String getBasicPoints() {
        return basicPoints;
    }

    public void setBasicPoints(String basicPoints) {
        this.basicPoints = basicPoints;
    }

    /*
    public void set(String lableSection, String points){
        switch (lableSection){
            case "age" :
                this.ageScore_giveMark = points;
            case "creditHistory" :
                this.creditHistoryScore_giveMark = points;
            case "durationMonth" :
                this.durationMonthScore_giveMark = points;
            case "employmentSince" :
                this.employmentSinceScore_giveMark = points;
            case "housing" :
                this.housingScore_giveMark = points;
            case "savingsAccountAndBonds" :
                this.savingsAccountAndBondsScore_giveMark = points;
            case "installmentPlans" :
                this.installmentPlansScore_giveMark = points;
            case "statusSxistingCheckingAccount" :
                this.statusSxistingCheckingAccountScore_giveMark = points;
            case "installmentIncome" :
                this.installmentIncomeScore_giveMark = points;
            case "creditAmount" :
                this.creditAmountScore_giveMark = points;
            case "purpose" :
                this.purposeScore_giveMark = points;
            case "otherDebtorsOrGuarantors" :
                this.otherDebtorsOrGuarantorsScore_giveMark = points;
        }
    }*/
}