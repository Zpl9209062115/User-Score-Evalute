package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.JpaJavaBean;

import java.util.List;

/**
 * @ClassName SqlPackageing
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/6 20:34
 * @Version 1.0
 **/
public class SqlPackageing {

    public List<JpaJavaBean> sqlPackageingForView(String variable,List<Object[]> objectList){/*

        *//**
         * 根据指标去获取每个指标、各个区间中的人数，进行统计
         *//*
        List<JpaJavaBean> jpaJavaBeanList = new ArrayList<>();
        JpaJavaBean jpaJavaBean = null;
        String sql = null;
        String groupByString = "";
        String sqlFinally = "";
        *//**
         * sql拼接分为两种
         *      一种是数字区间的，例如[35,37)。。。                         由于原始数据中对于每一个数字个数很多，因此需要一个区间来限定
         *      二种是文字类型的，例如delay in paying off in the past      直接比较数字类型
         *//*
        for (int i = 0;i<objectList.size();i++){
            StringBuilder sqljoint = new StringBuilder(300);
            sqljoint.append("SELECT "+variable+",COUNT(*) AS count FROM source_data where 1=1 AND ");
            jpaJavaBean = new JpaJavaBean();
            sql = "";
            groupByString = "";
            String bin = (String) objectList.get(i)[0];
            String section_type = (String) objectList.get(i)[1];

            if (section_type.equals("num")){
                *//**
                 * 处理数字区间
                 *//*
                String[] split = bin.split(",");
                String splitLeft = split[0];
                String splitRight = split[1];
                sql = numBin_sqlDispose(variable, splitLeft, splitRight);


                sqljoint.append(sql);
                groupByString = " GROUP BY "+ variable;
                sqljoint.append(groupByString);
                sqlFinally = sqljoint.toString();
                System.out.println("----------sqlFinally-----------");
                System.out.println(sqlFinally);
                System.out.println("----------sqlFinally-----------");

                jpaJavaBean.setSection(bin);
                jpaJavaBean.setSql_packageing(sqlFinally);
            } else {
                *//**
                 * 处理字符串
                 *//*
                sql = strBin_sqlDispose(variable,bin);

                sqljoint.append(sql);
                groupByString = " GROUP BY "+ variable;
                sqljoint.append(groupByString);
                sqlFinally = sqljoint.toString();
                System.out.println("----------sqlFinally-----------");
                System.out.println(sqlFinally);
                System.out.println("----------sqlFinally-----------");

                jpaJavaBean.setSection(bin);
                jpaJavaBean.setSql_packageing(sqlFinally);
            }
            jpaJavaBean.setVariableLabel(variable);
            jpaJavaBean.setSection_type(section_type);
            jpaJavaBeanList.add(jpaJavaBean);
        }*/
        return null;
    }

    /**
     * 处理str数据类型的sql
     * @param bin
     * @return
     *//*
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
        *//**
         * 左侧：  [-∞,26)，[35,37)，[37, ∞)，[26,28)，[28,35)
         *//*
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
    }*/

}
