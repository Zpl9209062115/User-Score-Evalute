package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.DashboardPage_DataPackageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SqlPackageing
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/6 20:34
 * @Version 1.0
 **/
public class SqlPackageing {

    String strWhereSection = "";

    List<DashboardPage_DataPackageBean> sFinallyList = null;

    public String sqlPackage(String rule,String field,String section,String section_type){
        String sql = "";
        if (section_type.equals("num")){
            /**
             * 处理数字区间
             */
            String numSection = rule;

            /**
             * 处理数据中的Inf,后面的工具类对于Inf不识别
             */
            if (numSection.contains("Inf") || numSection.contains("-Inf")) {
                numSection = numSection.replaceAll("Inf", "∞");
            }
            strWhereSection = numSection;
            String[] split = numSection.split(",");
            String splitLeft = split[0];
            String splitRight = split[1];
            sql = numBin_sqlDispose(field, splitLeft, splitRight);
        } else {
            /**
             * 处理字符串
             */
            sql = strBin_sqlDispose(field,section);
        }
        return sql;
    }

    public List<String> sectionPackage(String section,String field){
        List<String> listCondition = new ArrayList<>();
        String sqlCondition = "";

        String sqlSection = "";
        String and = " AND ";
        String groupByString = "";
        if (section != null){
            String[] splitSection = section.split("@");
            for (int j = 0;j<splitSection.length;j++){
                StringBuilder sqljoint = new StringBuilder(300);
                sqlSection = splitSection[j];
                sqljoint.append(sqlSection);

                sqlCondition = sqljoint.toString();
                System.out.println("----------sqlCondition-----------");
                System.out.println(sqlCondition);
                System.out.println("----------sqlCondition-----------");
                listCondition.add(sqlCondition);
            }
        } else {
            StringBuilder sqljoint = new StringBuilder(300);

            sqlCondition = sqljoint.toString();
            listCondition.add(sqlCondition);
            System.out.println("----------sqlCondition-----------");
            System.out.println(sqlCondition);
            System.out.println("----------sqlCondition-----------");
        }
        return listCondition;
    }

    public List<DashboardPage_DataPackageBean> sqlPackageingForView(List<String> ruleSectionList, String field, String condition, String tableName, String section_type){
        sFinallyList = new ArrayList<>();
        String and = " AND ";
        String creditability = "";
        /**
         * sql拼接分为两种
         *      一种是数字区间的，例如[35,37)。。。                         由于原始数据中对于每一个数字个数很多，因此需要一个区间来限定
         *      二种是文字类型的，例如delay in paying off in the past      直接比较数字类型
         */
        for (int i = 0;i<ruleSectionList.size();i++){

            /**
             * 处理where后区间
             */
            String strWhere = sqlPackage(ruleSectionList.get(i), field, condition, section_type);

            List<String> listCondition = sectionPackage(condition, field);
            for (int j = 0;j<listCondition.size();j++){
                // creditability_give_mark = 0 " + "@" + " creditability_give_mark = 1
                if (listCondition.get(j).contains("creditability_give_mark = 0")){
                    creditability = "0";
                } else {
                    creditability = "1";
                }

                StringBuilder sqljoint = new StringBuilder(300);
                if (field!=null){
                    sqljoint.append("SELECT "+field+",COUNT(*) AS count FROM "+tableName+" where 1=1 ");
                } else {
                    sqljoint.append("SELECT COUNT(*) AS count FROM "+tableName+" where 1=1 ");
                }
                String strCondition = listCondition.get(j);
                sqljoint.append(and);
                sqljoint.append(strWhere);
                sqljoint.append(and);
                sqljoint.append(strCondition);
                System.out.println(sqljoint.toString());

                DashboardPage_DataPackageBean bean = new DashboardPage_DataPackageBean(0,0l,sqljoint.toString(),0.0,strWhereSection,creditability);
                sFinallyList.add(bean);
            }

        }
        return sFinallyList;
    }

    /**
     * 处理str数据类型的sql
     * @param bin
     * @return
     */
    private String strBin_sqlDispose(String variable,String bin) {
        String sql = "";
        sql  = variable + " LIKE '%"+bin+"%' ";
        return sql;
    }

    public String numBin_sqlDispose(String variable,String splitLeft,String splitRight){
        String sql = "";
        String sqlTemporary = "";
        String sqlLeft = "";
        String sqlRight = "";
        /*
        左侧：  [-∞,26)，[35,37)，[37, ∞)，[26,28)，[28,35)
        */

        if (splitLeft.contains("∞")){
            if (splitRight.contains(")")){
                sqlTemporary = splitRight.replaceAll("\\)","").trim();
                sqlTemporary = " < " + sqlTemporary;
            }else if (splitLeft.contains("]")){
                sqlTemporary = splitRight.replaceAll("\\]","").trim();
                sqlTemporary = " <= " + sqlTemporary;
            }
            sql = variable + sqlTemporary;
        }else if (splitRight.contains("∞")){
            if (splitLeft.contains("(")){
                sqlTemporary = splitLeft.replaceAll("\\(","").trim();
                sqlTemporary = " > " + sqlTemporary;
            }else if (splitLeft.contains("[")){
                sqlTemporary = splitLeft.replaceAll("\\[","").trim();
                sqlTemporary = " >= " + sqlTemporary;
            }
            sql = variable + sqlTemporary;
        } else {
            if (splitLeft.contains("[")){
                sqlLeft = splitLeft.replaceAll("\\[","").trim();
                sqlLeft = " >= " + sqlLeft;
            } else {
                sqlLeft = splitLeft.replaceAll("\\(","").trim();
                sqlLeft = " > " + sqlLeft;
            }

            if (splitRight.contains("]")){
                sqlRight = splitRight.replaceAll("\\]","").trim();
                sqlRight = " <= " + sqlRight;
            } else {
                sqlRight = splitRight.replaceAll("\\)","").trim();
                sqlRight = " < " + sqlRight;
            }
            sql = variable +  sqlLeft + " AND "  + variable + sqlRight;
        }

        return sql;
    }

    public DashboardPage_DataPackageBean dataPackageBeans(DashboardPage_DataPackageBean bean,List<Object[]> resultList){
        Integer sum_point = 0;
        Long count = 0l;

        if (resultList.size() == 0){
            sum_point = 0;
            count = 0l;
            bean.setSum_point(sum_point);
            bean.setCount(count);
        }else {
            for (int i = 0;i<resultList.size();i++){
                sum_point = (Integer) resultList.get(i)[0];
                count = Long.valueOf((Long) resultList.get(i)[1]);
                bean.setSum_point(sum_point);
                bean.setCount(count);
            }
        }
        return bean;
    }


    public List<String> sectionPackageing(String section,List<Object[]> sectionResultList){
        //SELECT MAX(sum_point),MIN(sum_point),AVG(sum_point) FROM source_data_give_mark
        List<String> ruleSectionList = new ArrayList<>();
        for (int i = 0; i < sectionResultList.size(); i++) {
            /**
             * 向上取整用Math.ceil(double a)
             *
             * 向下取整用Math.floor(double a)
             */
            Object[] objects = sectionResultList.get(i);
            Double max = Double.parseDouble(objects[0].toString());
            Double maxNum = Math.ceil(max);
            Double min = Double.parseDouble(objects[1].toString());
            Double minNum = Math.floor(min);

            Double differenceValue = maxNum - minNum;

            Double intervalValue=differenceValue/Double.parseDouble(section);
            ruleSectionList = sectionStrDispose(section,maxNum, minNum, intervalValue);

        }
        return ruleSectionList;
    }

    public List<String> sectionStrDispose(String section,Double maxNum,Double minNum,Double intervalValue){
        List<String> strSection = new ArrayList<>();
        //[-Inf,220)@[220,283)@[283,345)@[345,408)@[408,470)@[470,532)@[532,595)@[595,657)@[657,720)@[720,Inf)
        if (maxNum == 0){
            return strSection;
        } else if (maxNum == minNum){
            String sectionStr1 = "[-Inf,"+maxNum+")";
            String sectionStr2 = "["+maxNum+",Inf)";
            strSection.add(sectionStr1);
            strSection.add(sectionStr2);
            return strSection;
        } else {
            Double left = 0.0;
            Double mid = 0.0;
            Double right = 0.0;
            String sectionStr = "";
            //累加的最小值
            Integer count = Integer.valueOf(section) - 2;
            for (int i = 0;i<=count;i++){
                if (i == count){
                    String sectionStrLeft = "[-Inf,"+Math.ceil(minNum)+")";
                    String sectionStrRight = "["+Math.ceil(left)+",Inf)";
                    strSection.add(0,sectionStrLeft);
                    strSection.add(strSection.size(),sectionStrRight);
                    break;
                } else {
                    if (i == 0){
                        left = minNum;
                        right = minNum + intervalValue;
                        System.out.println(left);
                        System.out.println(right);


                        sectionStr = "["+Math.ceil(left) + "," + Math.ceil(right) + ")";
                        strSection.add(sectionStr);
                        left = right;
                        right = right + intervalValue;
                    } else {
                        System.out.println(left);
                        System.out.println(right);

                        right = left + intervalValue;
                        sectionStr = "["+Math.ceil(left) + "," + Math.ceil(right) + ")";
                        strSection.add(sectionStr);
                        left = right;
                        right = right + intervalValue;
                    }
                }
            }
            return strSection;
        }
    }
}
