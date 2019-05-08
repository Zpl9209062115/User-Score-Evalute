package com.nanrui.userscore.dao;

import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.entities.SourceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName RuleDao
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/18 18:09
 * @Version 1.0
 **/
public interface SourceDataDao extends JpaRepository<SourceData,String> {

    @Query(value = "select age,count(s) as count from source_data s group by age")
    public List<Object[]> selectSourceDataCount();
}
