package com.nanrui.userscore.service;

import com.nanrui.userscore.dao.LoanUserGiveMarkDao;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName RuleService
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/22 9:22
 * @Version 1.0
 **/
@Service
public class LoanUserService {

    @Autowired
    LoanUserGiveMarkDao giveMarkDao;

    /**
     * list页面显示评分后的员工并排名
     * @return
     */
    public List<LoanUser_GiveMark> selectAllLoanUser_GiveMark(){
        Sort sort = new Sort(Sort.Direction.DESC,"sumPoints");
        List<LoanUser_GiveMark> loanUserList_giveMark = giveMarkDao.findAll(sort);
        return loanUserList_giveMark;
    }


    public LoanUser_GiveMark selectOneById(String id){
        LoanUser_GiveMark loanUser_giveMark = giveMarkDao.findOne(id);
        return loanUser_giveMark;
    }

}
