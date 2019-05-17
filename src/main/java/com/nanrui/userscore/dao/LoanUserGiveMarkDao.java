package com.nanrui.userscore.dao;

import com.nanrui.userscore.entities.LoanUser_GiveMark;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Iterator;

/**
 * @ClassName LoanUser_GiveMarkDao
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/26 17:23
 * @Version 1.0
 **/
public interface LoanUserGiveMarkDao extends JpaRepository<LoanUser_GiveMark,String> {
}
