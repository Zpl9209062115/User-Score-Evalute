package com.nanrui.userscore.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nanrui.userscore.dao.LoanUserGiveMarkDao;
import com.nanrui.userscore.dao.RuleDao;
import com.nanrui.userscore.dao.SourceDataDao;
import com.nanrui.userscore.entities.DashboardPage_DataPackageBean;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.utils.PropertiesReadUtils;
import com.nanrui.userscore.utils.SqlPackageing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataViewService
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/5 11:47
 * @Version 1.0
 **/
@Service
public class DataViewService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SourceDataDao sourceDataDao;

    @Autowired
    LoanUserGiveMarkDao loanUserGiveMarkDao;

    @Autowired
    RuleDao ruleDao;

    PropertiesReadUtils propertiesLoader = new PropertiesReadUtils("rule.properties");

    public LoanUser_GiveMark labelView(String label) {
        LoanUser_GiveMark oneBean = loanUserGiveMarkDao.findOne(label);
        return oneBean;
    }

    /**
     * 查看在现有规则下，每个指标的用户有多少个
     *[-Inf,220)','[220,283)','[283,345)','[345,408)','[408,470)','[470,532)','[532,595)','[595,657)','[657,720)','[720,Inf)
     * @return
     */
    public List<DashboardPage_DataPackageBean> labelView_dashBoard() {
        List<String> ruleSectionList = new ArrayList<>();
        List<DashboardPage_DataPackageBean> beanFinallyList = new ArrayList<>();
        String ruleSection = propertiesLoader.getProperty("ruleSection");
        String[] split = ruleSection.split("@");
        /**
         * 获得页面查询的区间
         */
        for (int i = 0;i<split.length;i++){
            ruleSectionList.add(split[i]);
        }

        SqlPackageing sqlPackageing = new SqlPackageing();
        /**
         * 要查询的字段
         */
        String field = "sum_point";
        /**
         * 条件
         */
        String condition = " creditability_give_mark = 0 " + "@" + " creditability_give_mark = 1 ";
        /**
         * 要查询的表名
         */
        String tableName = "source_data_give_mark";
        String characteristic = "num";
        List<DashboardPage_DataPackageBean> listBean = sqlPackageing.sqlPackageingForView(ruleSectionList, field, condition, tableName, characteristic);

        for (int i = 0;i<listBean.size();i++){
            DashboardPage_DataPackageBean beanList = listBean.get(i);
            String sql = beanList.getSql();
            Query query = entityManager.createQuery(sql);
            List<Object[]> resultList = query.getResultList();
            DashboardPage_DataPackageBean bean = sqlPackageing.dataPackageBeans(beanList,resultList);
            beanFinallyList.add(bean);
        }

        return beanFinallyList;
    }
}