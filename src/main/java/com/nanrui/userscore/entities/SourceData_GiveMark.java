package com.nanrui.userscore.entities;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * 对于原始数据集进行打分，通过打分后获得每个用户的分值及是否是违约用户
 * @ClassName SourceData_GiveMark
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/8 10:40
 * @Version 1.0
 **/
@Entity(name = "source_data_give_mark")
@Table(name = "source_data_give_mark")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SourceData_GiveMark {

    /**
     * 贷款人数据库存储ID
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")//自增主键
    private String loanUser_id;

    /**
     * 现有支票帐户的状况
     */
    @Column(name = "status_sxisting_checking_account_giveMark")
    private Integer status_sxisting_checking_account_giveMark;

    /**
     * 社保缴纳时间
     */
    @Column(name = "duration_month_giveMark")
    private Integer duration_month_giveMark;

    /**
     * 信用历史
     */
    @Column(name = "credit_history_giveMark")
    private Integer credit_history_giveMark;

    /**
     * 用途
     */
    @Column(name = "purpose_giveMark")
    private Integer purpose_giveMark;

    /**
     * 欠款金额
     */
    @Column(name = "credit_amount_giveMark")
    private Integer credit_amount_giveMark;

    /**
     * 储蓄存款及债券
     */
    @Column(name = "savings_account_and_bonds_giveMark")
    private Integer savings_account_and_bonds_giveMark;

    /**
     * 从业经历
     */
    @Column(name = "employment_since_giveMark")
    private Integer employment_since_giveMark;

    /**
     * 分期付款率占可支配收入的百分比
     */
    @Column(name = "installment_income_giveMark")
    private Integer installment_income_giveMark;

    /**
     * 性别及婚姻状态
     */
    @Column(name = "personal_status_and_sex_giveMark")
    private Integer personal_status_and_sex_giveMark;

    /**
     * 其他债务人或保证人
     */
    @Column(name = "other_debtors_or_guarantors_giveMark")
    private Integer other_debtors_or_guarantors_giveMark;

    /**
     * 财产
     */
    @Column(name = "property_giveMark")
    private Integer property_giveMark;

    /**
     * 贷款人年龄
     */
    @Column(name = "age_giveMark")
    private Integer age_giveMark;

    /**
     * 其他分期付款计划
     */
    @Column(name = "installment_plans_giveMark")
    private Integer installment_plans_giveMark;

    /**
     * 住房情况
     */
    @Column(name = "housing_giveMark")
    private Integer housing_giveMark;

    /**
     * 基础分
     */
    @Column(name = "basic_points")
    private Integer basicPoints = 451;

    /**
     * 可信性-违约标识
     */
    @Column(name = "creditability_giveMark")
    private Integer creditability_giveMark;

    /**
     * 总分
     */
    @Column(name = "sum_point")
    private Integer sum_point;

    /**
     * 原始数据是否经过处理的标识
     */
    @Column(name = "identifying_giveMark")
    private Integer identifying_giveMark;

    public SourceData_GiveMark() {
    }

    public SourceData_GiveMark(Integer status_sxisting_checking_account_giveMark, Integer duration_month_giveMark, Integer credit_history_giveMark, Integer purpose_giveMark, Integer credit_amount_giveMark, Integer savings_account_and_bonds_giveMark, Integer employment_since_giveMark, Integer installment_income_giveMark, Integer personal_status_and_sex_giveMark, Integer other_debtors_or_guarantors_giveMark, Integer property_giveMark, Integer age_giveMark, Integer installment_plans_giveMark, Integer housing_giveMark, Integer basicPoints, Integer creditability_giveMark, Integer sum_point, Integer identifying_giveMark) {
        this.status_sxisting_checking_account_giveMark = status_sxisting_checking_account_giveMark;
        this.duration_month_giveMark = duration_month_giveMark;
        this.credit_history_giveMark = credit_history_giveMark;
        this.purpose_giveMark = purpose_giveMark;
        this.credit_amount_giveMark = credit_amount_giveMark;
        this.savings_account_and_bonds_giveMark = savings_account_and_bonds_giveMark;
        this.employment_since_giveMark = employment_since_giveMark;
        this.installment_income_giveMark = installment_income_giveMark;
        this.personal_status_and_sex_giveMark = personal_status_and_sex_giveMark;
        this.other_debtors_or_guarantors_giveMark = other_debtors_or_guarantors_giveMark;
        this.property_giveMark = property_giveMark;
        this.age_giveMark = age_giveMark;
        this.installment_plans_giveMark = installment_plans_giveMark;
        this.housing_giveMark = housing_giveMark;
        this.basicPoints = basicPoints;
        this.creditability_giveMark = creditability_giveMark;
        this.sum_point = sum_point;
        this.identifying_giveMark = identifying_giveMark;
    }

    public String getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(String loanUser_id) {
        this.loanUser_id = loanUser_id;
    }

    public Integer getStatus_sxisting_checking_account_giveMark() {
        return status_sxisting_checking_account_giveMark;
    }

    public void setStatus_sxisting_checking_account_giveMark(Integer status_sxisting_checking_account_giveMark) {
        this.status_sxisting_checking_account_giveMark = status_sxisting_checking_account_giveMark;
    }

    public Integer getDuration_month_giveMark() {
        return duration_month_giveMark;
    }

    public void setDuration_month_giveMark(Integer duration_month_giveMark) {
        this.duration_month_giveMark = duration_month_giveMark;
    }

    public Integer getCredit_history_giveMark() {
        return credit_history_giveMark;
    }

    public void setCredit_history_giveMark(Integer credit_history_giveMark) {
        this.credit_history_giveMark = credit_history_giveMark;
    }

    public Integer getPurpose_giveMark() {
        return purpose_giveMark;
    }

    public void setPurpose_giveMark(Integer purpose_giveMark) {
        this.purpose_giveMark = purpose_giveMark;
    }

    public Integer getCredit_amount_giveMark() {
        return credit_amount_giveMark;
    }

    public void setCredit_amount_giveMark(Integer credit_amount_giveMark) {
        this.credit_amount_giveMark = credit_amount_giveMark;
    }

    public Integer getSavings_account_and_bonds_giveMark() {
        return savings_account_and_bonds_giveMark;
    }

    public void setSavings_account_and_bonds_giveMark(Integer savings_account_and_bonds_giveMark) {
        this.savings_account_and_bonds_giveMark = savings_account_and_bonds_giveMark;
    }

    public Integer getEmployment_since_giveMark() {
        return employment_since_giveMark;
    }

    public void setEmployment_since_giveMark(Integer employment_since_giveMark) {
        this.employment_since_giveMark = employment_since_giveMark;
    }

    public Integer getInstallment_income_giveMark() {
        return installment_income_giveMark;
    }

    public void setInstallment_income_giveMark(Integer installment_income_giveMark) {
        this.installment_income_giveMark = installment_income_giveMark;
    }

    public Integer getPersonal_status_and_sex_giveMark() {
        return personal_status_and_sex_giveMark;
    }

    public void setPersonal_status_and_sex_giveMark(Integer personal_status_and_sex_giveMark) {
        this.personal_status_and_sex_giveMark = personal_status_and_sex_giveMark;
    }

    public Integer getOther_debtors_or_guarantors_giveMark() {
        return other_debtors_or_guarantors_giveMark;
    }

    public void setOther_debtors_or_guarantors_giveMark(Integer other_debtors_or_guarantors_giveMark) {
        this.other_debtors_or_guarantors_giveMark = other_debtors_or_guarantors_giveMark;
    }

    public Integer getProperty_giveMark() {
        return property_giveMark;
    }

    public void setProperty_giveMark(Integer property_giveMark) {
        this.property_giveMark = property_giveMark;
    }

    public Integer getAge_giveMark() {
        return age_giveMark;
    }

    public void setAge_giveMark(Integer age_giveMark) {
        this.age_giveMark = age_giveMark;
    }

    public Integer getInstallment_plans_giveMark() {
        return installment_plans_giveMark;
    }

    public void setInstallment_plans_giveMark(Integer installment_plans_giveMark) {
        this.installment_plans_giveMark = installment_plans_giveMark;
    }

    public Integer getHousing_giveMark() {
        return housing_giveMark;
    }

    public void setHousing_giveMark(Integer housing_giveMark) {
        this.housing_giveMark = housing_giveMark;
    }

    public Integer getCreditability_giveMark() {
        return creditability_giveMark;
    }

    public void setCreditability_giveMark(Integer creditability_giveMark) {
        this.creditability_giveMark = creditability_giveMark;
    }

    public Integer getIdentifying_giveMark() {
        return identifying_giveMark;
    }

    public void setIdentifying_giveMark(Integer identifying_giveMark) {
        this.identifying_giveMark = identifying_giveMark;
    }

    public Integer getSum_point() {
        return sum_point;
    }

    public void setSum_point(Integer sum_point) {
        this.sum_point = sum_point;
    }

    public Integer getBasicPoints() {
        return basicPoints;
    }

    public void setBasicPoints(Integer basicPoints) {
        this.basicPoints = basicPoints;
    }
}
