package com.nanrui.userscore.service;

import com.nanrui.userscore.dao.LoanUserDao;
import com.nanrui.userscore.dao.RuleDao;
import com.nanrui.userscore.dao.SourceDataDao;
import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.entities.SourceData;
import com.nanrui.userscore.utils.GenerativeRuleUtils;
import com.nanrui.userscore.utils.IntervalUtil;
import com.nanrui.userscore.utils.ReadRegulationCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName empService
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:29
 * @Version 1.0
 **/
@Service
public class EmpService {

    List<RuleBean> listColon = new ArrayList<>();

    List<RuleBean> listOther = new ArrayList<>();

    List<RuleBean> listAll = new ArrayList<>();

    Map<String,Map<String,String>> csvMap = new HashMap<>();

    List<RuleBean> ruleList = new ArrayList<>();

    @Autowired
    LoanUserDao empDao;

    @Autowired
    RuleDao ruleDao;

    @Autowired
    SourceDataDao sourceDataDao;

    @PersistenceContext
    private EntityManager entityManager;

    public static IntervalUtil a = new IntervalUtil();

    public List<String> getRuleVariable(){

        return null;
    }

    @CachePut(value = "ruleCache")
    public List<RuleBean> getRule(){
        ruleList = ruleDao.findAll();
        return ruleList;
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<RuleBean> getAllLoanUser(){
        List<RuleBean> ruleAll = getRule();
        return ruleAll;
    }

    /**
     * 用户与指定规则的对比
     * @param user
     * @return
     */
    public List<Map<String,Object>> JudgeScore(LoanUser user){
        a.isInTheInterval(String.valueOf(user.getAge()),"");
        return null;
    }

    /**
     * 生成规则，并放入缓存
     */
    @PostConstruct
    public void Rule(){
        System.out.println("启动时加载-------------");
        String filePath = "D:/aa/3.csv";
        String filePathSourceData = "D:/aa/train1.csv";
        System.out.println("=========服务启动执行===========");
        try {
            ReadRegulationCsv csv = new ReadRegulationCsv();
            /**
             * 1、读取已经生成的规则csv文件
             */
            csvMap = csv.readCSV(filePath);

            /**
             * 根据文件生成规则:把生成的mapRuleCsv放入处理工具类
             */
            GenerativeRuleUtils generativeRuleUtils = new GenerativeRuleUtils();

            /**
             * 处理带冒号和不带冒号的
             *      剩下的判别在对应的里面进行
             */
            for (Map.Entry<String, Map<String, String>> mapRule : csvMap.entrySet()){
                if (("colonNotMap").equals(mapRule.getKey())){
                    listOther = generativeRuleUtils.colonNotMapDispose(mapRule.getValue());
                }
            }

            listAll.addAll(listColon);
            listAll.addAll(listOther);

            if (0 != listAll.size()){
                ruleDao.deleteAll();
                ruleDao.save(listAll);
            }



            /**
             * 2、读取原始数据
             */
            List<SourceData> sourceDataList = csv.readSourceData(filePathSourceData);
            if (null != sourceDataList){
                sourceDataDao.deleteAll();
                sourceDataDao.save(sourceDataList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
