package com.nanrui.userscore.entities;

import java.util.List;

/**
 * @ClassName JpaJavaBean
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/5 15:18
 * @Version 1.0
 **/
public class JpaJavaBean {
    private String variableLabel;

    /**
     * 区间   [26,28)
     */
    private String section;

    private String sql_packageing;

    private String section_type;

    private List<String> section_count;

    public JpaJavaBean() {
    }

    public JpaJavaBean(String variableLabel, String section, String sql_packageing, String section_type, List<String> section_count) {
        this.variableLabel = variableLabel;
        this.section = section;
        this.sql_packageing = sql_packageing;
        this.section_type = section_type;
        this.section_count = section_count;
    }

    public String getVariableLabel() {
        return variableLabel;
    }

    public void setVariableLabel(String variableLabel) {
        this.variableLabel = variableLabel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSql_packageing() {
        return sql_packageing;
    }

    public void setSql_packageing(String sql_packageing) {
        this.sql_packageing = sql_packageing;
    }

    public String getSection_type() {
        return section_type;
    }

    public void setSection_type(String section_type) {
        this.section_type = section_type;
    }

    public List<String> getSection_count() {
        return section_count;
    }

    public void setSection_count(List<String> section_count) {
        this.section_count = section_count;
    }
}
