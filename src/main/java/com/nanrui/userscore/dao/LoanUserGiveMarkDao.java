package com.nanrui.userscore.dao;

import com.nanrui.userscore.entities.LoanUser_GiveMark;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName LoanUser_GiveMarkDao
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/26 17:23
 * @Version 1.0
 **/
public interface LoanUserGiveMarkDao extends JpaRepository<LoanUser_GiveMark,String> {
}
