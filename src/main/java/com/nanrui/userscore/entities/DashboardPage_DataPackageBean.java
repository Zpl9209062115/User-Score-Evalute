package com.nanrui.userscore.entities;

/**
 * @ClassName DashboardPage_DataPackageBean
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/9 11:47
 * @Version 1.0
 **/
public class DashboardPage_DataPackageBean {

    /**
     * 总分，如果是多个且分组情况下，应该是平均分
     */
    private Integer sum_point;

    /**
     * SQL中失约用户或守约用户个数
     */
    private Long count;

    /**
     * 执行sql
     */
    private String sql;

    /**
     * 失约用户/总体用户
     */
    private Double specific_value;

    /**
     * 条件区间
     */
    private String section;

    /**
     * 失约与守约的标识，0为守约，1为失约
     */
    private String creditability;

    public DashboardPage_DataPackageBean(Integer sum_point, Long count, String sql, Double specific_value, String section, String creditability) {
        this.sum_point = sum_point;
        this.count = count;
        this.sql = sql;
        this.specific_value = specific_value;
        this.section = section;
        this.creditability = creditability;
    }

    public DashboardPage_DataPackageBean() {
    }

    public Integer getSum_point() {
        return sum_point;
    }

    public void setSum_point(Integer sum_point) {
        this.sum_point = sum_point;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Double getSpecific_value() {
        return specific_value;
    }

    public void setSpecific_value(Double specific_value) {
        this.specific_value = specific_value;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCreditability() {
        return creditability;
    }

    public void setCreditability(String creditability) {
        this.creditability = creditability;
    }
}
