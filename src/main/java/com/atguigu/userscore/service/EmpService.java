package com.atguigu.userscore.service;

import com.atguigu.userscore.dao.EmpDao;
import com.atguigu.userscore.entities.LoanUser;
import com.atguigu.userscore.utils.GenerativeRuleUtils;
import com.atguigu.userscore.utils.IntervalUtil;
import com.atguigu.userscore.utils.ReadRegulationCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    Map<String,String> ruleMap = new HashMap<>();

    @Autowired
    EmpDao empDao;

    public static IntervalUtil a = new IntervalUtil();

    /**
     * 查询所有用户
     * @return
     */
    public List<LoanUser> getAllLoanUser(){
        List<LoanUser> list = empDao.findAll();
        for (Map.Entry<String,String> map : ruleMap.entrySet()){
            System.out.println(map.getKey() + "--->" + map.getValue());
        }
        return list;
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
    public Map<String,String> Rule(){
        System.out.println("启动时加载-------------");
        String filePath = "D:/aa/3.csv";
        System.out.println("=========服务启动执行===========");
        try {
            /**
             * 读取已经生成的规则csv文件
             */
            ReadRegulationCsv csv = new ReadRegulationCsv();
            Map<String,String> mapRuleCsv = null;
            mapRuleCsv = csv.readCSV(filePath);
            /**
             * 根据文件生成规则:把生成的mapRuleCsv放入处理工具类
             */
            GenerativeRuleUtils generativeRuleUtils = new GenerativeRuleUtils();
            ruleMap = generativeRuleUtils.GenerativeRule(mapRuleCsv);
            for (Map.Entry<String,String> mapConsole : ruleMap.entrySet()){
                System.out.println(mapConsole.getKey() + "    " + mapConsole.getValue());
            }
            return ruleMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
