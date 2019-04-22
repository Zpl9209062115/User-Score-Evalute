package com.nanrui.userscore.dao;

import com.nanrui.userscore.entities.RuleBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName RuleDao
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 18:09
 * @Version 1.0
 **/
public interface RuleDao extends JpaRepository<RuleBean,Integer> {
    @Query(value = "SELECT DISTINCT(variable) FROM rule_wide_table")
    public List<String> selectRuleLabel();
}
