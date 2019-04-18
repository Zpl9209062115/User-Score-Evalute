package com.nanrui.userscore.dao;

import com.nanrui.userscore.entities.LoanUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName empDao
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:29
 * @Version 1.0
 **/
//继承JpaRepository来完成对数据库的操作
public interface EmpDao extends JpaRepository<LoanUser,Integer> {

}
