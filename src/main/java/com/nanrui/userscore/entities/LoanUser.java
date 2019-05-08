package com.nanrui.userscore.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 通过前端页面导入的需要进行评分的用户信息，
 *      分为两个部分，单个用户的信息，和多个用户的信息，这个是相对应的JavaBean
 * @ClassName loanUser
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:21
 * @Version 1.0
 **/
@Entity(name = "loan_user")
@Table(name = "loan_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LoanUser {

    /**
     * 贷款人数据库存储ID
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")//自增主键
    private String loanUser_id;

    /**
     * 贷款人姓名
     */
    private String loanUser_name;

    /**
     * 贷款人年龄
     */
    private String age;

    /**
     * 性别及婚姻状态
     */
    private String personalStatusAndSex;

    /**
     * 社保缴纳时间
     */
    private String durationMonth;

    /**
     * 分期付款率占可支配收入的百分比
     */
    private String installmentIncome;

    /**
     * 从业经历
     */
    private String employmentSince;

    /**
     * 住房情况
     */
    private String housing;

    /**
     * 其他分期付款计划
     */
    private String installmentPlans;

    /**
     * 储蓄存款及债券
     */
    private String savingsAccountAndBonds;

    /**
     * 信用历史
     */
    private String creditHistory;

    /**
     * 欠款金额
     */
    private String creditAmount;

    /**
     * 现有支票帐户的状况
     */
    private String statusSxistingCheckingAccount;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 其他债务人或保证人
     */
    private String otherDebtorsOrGuarantors;

    public LoanUser() {
    }

    public LoanUser(String loanUser_name, String age, String personalStatusAndSex, String durationMonth, String installmentIncome, String employmentSince, String housing, String installmentPlans, String savingsAccountAndBonds, String creditHistory, String creditAmount, String statusSxistingCheckingAccount, String purpose, String otherDebtorsOrGuarantors) {
        this.loanUser_name = loanUser_name;
        this.age = age;
        this.personalStatusAndSex = personalStatusAndSex;
        this.durationMonth = durationMonth;
        this.installmentIncome = installmentIncome;
        this.employmentSince = employmentSince;
        this.housing = housing;
        this.installmentPlans = installmentPlans;
        this.savingsAccountAndBonds = savingsAccountAndBonds;
        this.creditHistory = creditHistory;
        this.creditAmount = creditAmount;
        this.statusSxistingCheckingAccount = statusSxistingCheckingAccount;
        this.purpose = purpose;
        this.otherDebtorsOrGuarantors = otherDebtorsOrGuarantors;
    }

    public String getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(String loanUser_id) {
        this.loanUser_id = loanUser_id;
    }

    public String getLoanUser_name() {
        return loanUser_name;
    }

    public void setLoanUser_name(String loanUser_name) {
        this.loanUser_name = loanUser_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPersonalStatusAndSex() {
        return personalStatusAndSex;
    }

    public void setPersonalStatusAndSex(String personalStatusAndSex) {
        this.personalStatusAndSex = personalStatusAndSex;
    }

    public String getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(String durationMonth) {
        this.durationMonth = durationMonth;
    }

    public String getInstallmentIncome() {
        return installmentIncome;
    }

    public void setInstallmentIncome(String installmentIncome) {
        this.installmentIncome = installmentIncome;
    }

    public String getEmploymentSince() {
        return employmentSince;
    }

    public void setEmploymentSince(String employmentSince) {
        this.employmentSince = employmentSince;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getInstallmentPlans() {
        return installmentPlans;
    }

    public void setInstallmentPlans(String installmentPlans) {
        this.installmentPlans = installmentPlans;
    }

    public String getSavingsAccountAndBonds() {
        return savingsAccountAndBonds;
    }

    public void setSavingsAccountAndBonds(String savingsAccountAndBonds) {
        this.savingsAccountAndBonds = savingsAccountAndBonds;
    }

    public String getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(String creditHistory) {
        this.creditHistory = creditHistory;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getStatusSxistingCheckingAccount() {
        return statusSxistingCheckingAccount;
    }

    public void setStatusSxistingCheckingAccount(String statusSxistingCheckingAccount) {
        this.statusSxistingCheckingAccount = statusSxistingCheckingAccount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOtherDebtorsOrGuarantors() {
        return otherDebtorsOrGuarantors;
    }

    public void setOtherDebtorsOrGuarantors(String otherDebtorsOrGuarantors) {
        this.otherDebtorsOrGuarantors = otherDebtorsOrGuarantors;
    }

    @Override
    public String toString() {
        return "LoanUser{" +
                "loanUser_id=" + loanUser_id +
                ", loanUser_name='" + loanUser_name + '\'' +
                ", age='" + age + '\'' +
                ", personalStatusAndSex='" + personalStatusAndSex + '\'' +
                ", durationMonth='" + durationMonth + '\'' +
                ", installmentIncome='" + installmentIncome + '\'' +
                ", employmentSince='" + employmentSince + '\'' +
                ", housing='" + housing + '\'' +
                ", installmentPlans='" + installmentPlans + '\'' +
                ", savingsAccountAndBonds='" + savingsAccountAndBonds + '\'' +
                ", creditHistory='" + creditHistory + '\'' +
                ", creditAmount='" + creditAmount + '\'' +
                ", statusSxistingCheckingAccount='" + statusSxistingCheckingAccount + '\'' +
                ", purpose='" + purpose + '\'' +
                ", otherDebtorsOrGuarantors='" + otherDebtorsOrGuarantors + '\'' +
                '}';
    }
}
