package com.nanrui.userscore.service;

import com.nanrui.userscore.dao.*;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.entities.SourceData;
import com.nanrui.userscore.entities.SourceData_GiveMark;
import com.nanrui.userscore.utils.*;
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



    List<RuleBean> listAll = new ArrayList<>();

    Map<String,Map<String,String>> csvMap = new HashMap<>();

    List<RuleBean> ruleList = new ArrayList<>();

    List<SourceData_GiveMark> listSourceDataGiveMark = new ArrayList<>();

    @Autowired
    LoanUserDao empDao;

    @Autowired
    RuleDao ruleDao;

    @Autowired
    SourceDataDao sourceDataDao;

    @Autowired
    SourceDataGiveMarkDao sourceDataGiveMarkDao;

    @PersistenceContext
    private EntityManager entityManager;


    PropertiesReadUtils propertiesLoader = new PropertiesReadUtils("rule.properties");

    public static IntervalUtil a = new IntervalUtil();

    @CachePut(value = "ruleCache")
    public List<RuleBean> getRule(){
        ruleList = ruleDao.findAll();
        return ruleList;
    }

    /**
     * 生成规则，并放入缓存
     */
    @PostConstruct
    public void Rule(){
        System.out.println("启动时加载-------------");
        String filePath = propertiesLoader.getProperty("filePath");
        String filePathSourceData = propertiesLoader.getProperty("filePathSourceData");
        System.out.println("=========服务启动执行===========");
        try {
            ReadRegulationCsv csv = new ReadRegulationCsv();
            /**
             * 1、读取已经生成的规则csv文件
             */
            csvMap = csv.readCSV(filePath);

            /**
             * 处理规则，生成规则对应的JavaBean
             */
            DataDisposeUtils dataDisposeUtils = new DataDisposeUtils();
            Map<String, List<RuleBean>> stringListMap = dataDisposeUtils.generative_rule(csvMap);

            /**
             * 程序启动时存储
             */
            listAll.addAll(stringListMap.get("listColon"));
            listAll.addAll(stringListMap.get("listOther"));
            if (0 != listAll.size()){
                ruleDao.deleteAll();
                ruleDao.save(listAll);
            }

            /**
             * 2、读取原始数据
             */
            List<SourceData> sourceDataList = csv.readSourceData(filePathSourceData);

            /**
             * 程序启动时存储
             */
            if (null != sourceDataList){
                sourceDataDao.deleteAll();
                sourceDataDao.save(sourceDataList);
            }

            /**
             * 3、对于原始数据进行评分-打分
             * 原始数据     sourceDataList
             * 规则           listAll
             */

            /**
             * 规则数据准备
             */
            Map<String,List<Object[]>> ruleMap = new HashMap<>();
            List<String> list = ruleDao.selectRuleLabel();
            for (int i = 0;i<list.size();i++){
                String variable = list.get(i);
                List<Object[]> objects = ruleDao.selectRuleLabel_bin(variable);
                ruleMap.put(variable,objects);
            }

            /**
             * 对于现有数据进行评分，并生成对应的JavaBean，存储
             */
            DataSourceGiveMarkUtils dataSourceGiveMarkUtils = new DataSourceGiveMarkUtils();
            listSourceDataGiveMark = dataSourceGiveMarkUtils.giveMarkToDataSourceUtil(ruleMap,sourceDataList);
            sourceDataGiveMarkDao.deleteAll();
            sourceDataGiveMarkDao.save(listSourceDataGiveMark);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
