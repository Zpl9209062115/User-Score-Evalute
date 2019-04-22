package com.nanrui.userscore.service;

import com.nanrui.userscore.dao.RuleDao;
import com.nanrui.userscore.entities.RuleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RuleService
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/22 9:22
 * @Version 1.0
 **/
@Service
public class RuleService {

    @Autowired
    RuleDao ruleDao;

    public List<String> findRuleLabel(){
        List<String> listRule = ruleDao.selectRuleLabel();
        return listRule;
    }


}
