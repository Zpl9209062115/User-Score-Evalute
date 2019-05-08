package com.nanrui.userscore.entities;

import javax.persistence.*;

/**
 * 评分比较规则宽表，根据源数据通过R计算后生成的规则，读取到数据库中，这个是相对应的JavaBean
 * @ClassName Rule
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 17:24
 * @Version 1.0
 **/
@Entity(name = "rule_wide_table")
@Table(name = "rule_wide_table")
public class RuleBean {

    /**
     * 规则主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rule_id;

    /**
     * 指标名称
     */
    private String variable;

    /**
     * 指标名称
     */
    private String variable_secondLabel;

    /**
     * 指标判定规则
     */
    private String bin;

    /**
     * 指标得分
     */
    private Integer points;

    /**
     * 计算单位
     */
    private String unit;

    /**
     * 规则类型
     *      sectionType分为两个部分
     *          string : delay in paying off in the past...
     *          num    : [0,200)
     * @return
     */
    private String section_type;

    public RuleBean(String variable, String variable_secondLabel, String bin, Integer points, String unit, String section_type) {
        this.variable = variable;
        this.variable_secondLabel = variable_secondLabel;
        this.bin = bin;
        this.points = points;
        this.unit = unit;
        this.section_type = section_type;
    }

    public RuleBean() {
    }

    public Integer getRule_id() {
        return rule_id;
    }

    public void setRule_id(Integer rule_id) {
        this.rule_id = rule_id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getVariable_secondLabel() {
        return variable_secondLabel;
    }

    public void setVariable_secondLabel(String variable_secondLabel) {
        this.variable_secondLabel = variable_secondLabel;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSection_type() {
        return section_type;
    }

    public void setSection_type(String section_type) {
        this.section_type = section_type;
    }
}
