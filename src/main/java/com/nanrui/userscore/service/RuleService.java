package com.nanrui.userscore.service;

import com.nanrui.userscore.dao.LoanUserDao;
import com.nanrui.userscore.dao.LoanUserGiveMarkDao;
import com.nanrui.userscore.dao.RuleDao;
import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.utils.RuleSectionJudgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    LoanUserDao loanUserDao;

    @Autowired
    LoanUserGiveMarkDao giveMarkDao;

    Map<String, List<String>> ruleAllLabelVariable = new HashMap<>();


    List<String> listBin = null;

    String key = "";

    /**
     * 获取所有的规则，并放入缓存
     * @return
     */
    @CachePut(value = "ruleAll" ,key = "#result.get(#key)")
    public Map<String,Integer> getAllRule(){
        List<RuleBean> all = ruleDao.findAll();
        String key = "";
        Integer value = 0;
        Map<String,Integer> ruleAllMap = new HashMap<>();
        for (int i = 0;i<all.size();i++){
            RuleBean ruleBean = all.get(i);
            key = ruleBean.getVariable() + "@" + ruleBean.getBin();
            value = ruleBean.getPoints();
            ruleAllMap.put(key,value);
        }
        return ruleAllMap;
    }

    public Map<String, List<String>> findAll() {
        List<RuleBean> allRule = ruleDao.findAll();
        for (int i = 0; i < allRule.size(); i++) {
            listBin = new ArrayList<>();
            RuleBean ruleBean = allRule.get(i);
            String key = ruleBean.getVariable();
            String value = ruleBean.getBin();

            listBin = ruleAllLabelVariable.get(key);
            if (null == listBin) {
                listBin = new ArrayList<>();
            }
            if (!listBin.contains(value)) {
                listBin.add(value);
            }
            ruleAllLabelVariable.put(key, listBin);
        }
        return ruleAllLabelVariable;
    }

    public Map<String, List<String>> findLabelByTag() {
        Map<String, List<String>> mapTag = new HashMap<>();
        List<String> listTag = ruleDao.selectRuleLabel();
        for (int i = 0; i < listTag.size(); i++) {
            List<String> list = ruleDao.selectRuleLabelByTag(listTag.get(i));
            key = listTag.get(i);
            //listBin = utils.ComparisonLableTranslate(key, list);
            mapTag.put(key, list);
        }
        return mapTag;
    }

    public List<LoanUser_GiveMark> giveMarkForLoanUser(LoanUser loanUser) throws Exception {

        /**
         * 第一步：将录入需要评分的用户保存到数据库
         */
        LoanUser loanUserToMySQL = preserveLoanUser(loanUser);

        /**
         * 第二步：将录入需要评分的用户进行评分
         */
        RuleSectionJudgeUtils utils = new RuleSectionJudgeUtils();
        Map<String, Integer> allRule = getAllRule();
        List<LoanUser_GiveMark> loanUser_giveMarks = utils.ruleSectionJudge(loanUserToMySQL.getLoanUser_name(),loanUserToMySQL.getLoanUser_id().toString(), loanUser, allRule);
        giveMarkDao.save(loanUser_giveMarks);
        return loanUser_giveMarks;
    }

    public String ruleSection(LoanUser loanUser){

        return null;
    }

    /**
     * 前台输入用户信息进行评分，首先将用户信息进行保存
     * @param loanUser
     * @return
     * @throws Exception
     */
    public LoanUser preserveLoanUser(LoanUser loanUser) throws Exception{
        LoanUser save = loanUserDao.save(loanUser);
        return save;
    }

}
