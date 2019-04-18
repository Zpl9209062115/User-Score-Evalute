package com.atguigu.userscore.entities;

import javax.persistence.*;

/**
 * @ClassName loanUser
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:21
 * @Version 1.0
 **/
@Entity
@Table(name = "loan_user")
public class LoanUser {

    /**
     * 贷款人数据库存储ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer loanUser_id;

    /**
     * 贷款人姓名
     */
    private String loanUser_name;

    /**
     * 贷款人年龄
     */
    private Integer age;

    /**
     * 贷款人收入
     */
    private Double loanUser_income;

    /**
     * 贷款人孩子数量
     */
    private Integer loanUser_childNum;

    /**
     * 贷款人工作时间
     */
    private Integer loanUser_workTime;

    /**
     * 贷款人居住年限
     */
    private Integer loanUser_liveLife;

    public Integer getLoanUser_id() {
        return loanUser_id;
    }

    public void setLoanUser_id(Integer loanUser_id) {
        this.loanUser_id = loanUser_id;
    }

    public String getLoanUser_name() {
        return loanUser_name;
    }

    public void setLoanUser_name(String loanUser_name) {
        this.loanUser_name = loanUser_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getLoanUser_income() {
        return loanUser_income;
    }

    public void setLoanUser_income(Double loanUser_income) {
        this.loanUser_income = loanUser_income;
    }

    public Integer getLoanUser_childNum() {
        return loanUser_childNum;
    }

    public void setLoanUser_childNum(Integer loanUser_childNum) {
        this.loanUser_childNum = loanUser_childNum;
    }

    public Integer getLoanUser_workTime() {
        return loanUser_workTime;
    }

    public void setLoanUser_workTime(Integer loanUser_workTime) {
        this.loanUser_workTime = loanUser_workTime;
    }

    public Integer getLoanUser_liveLife() {
        return loanUser_liveLife;
    }

    public void setLoanUser_liveLife(Integer loanUser_liveLife) {
        this.loanUser_liveLife = loanUser_liveLife;
    }
}
