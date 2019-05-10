package com.nanrui.userscore.utils;
import com.nanrui.userscore.entities.SourceData;
import com.nanrui.userscore.entities.SourceData_GiveMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName DataSourceGiveMarkUtils
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/8 13:54
 * @Version 1.0
 **/
public class DataSourceGiveMarkUtils {

    Pattern p = Pattern.compile(".*\\d+.*");

    public List<SourceData_GiveMark> giveMarkToDataSourceUtil(Map<String,List<Object[]>> ruleMap, List<SourceData> sourceDataList) {

        List<SourceData_GiveMark> beanListFinally = new ArrayList<>();

        for (int i = 0;i<sourceDataList.size();i++){
            SourceData sourceData = sourceDataList.get(i);
            List<SourceData_GiveMark> beanList = disposeGiveMarkUtil(ruleMap,sourceData);
            beanListFinally.addAll(beanList);
        }
        return beanListFinally;
    }

    private List<SourceData_GiveMark> disposeGiveMarkUtil(Map<String,List<Object[]>> ruleMap,SourceData sourceData) {
        Integer sumPoint = 0;
        List<SourceData_GiveMark> beanList = new ArrayList<>();
        SourceData_GiveMark sourceData_giveMark = new SourceData_GiveMark();
        for (Map.Entry<String,List<Object[]>> listObject : ruleMap.entrySet()){
            Integer points = 0;
            String key = listObject.getKey();
            List<Object[]> value = listObject.getValue();
            switch (key){
                case "age" :
                    points = rule_matching(sourceData.getAge(),value,false);
                    sourceData_giveMark.setAge_giveMark(points);
                    break;
                case "credit_history" :
                    points = rule_matching(sourceData.getCredit_history(),value,false);
                    sourceData_giveMark.setCredit_history_giveMark(points);
                    break;
                case "duration_month" :
                    points = rule_matching(sourceData.getDuration_month(),value,false);
                    sourceData_giveMark.setDuration_month_giveMark(points);
                    break;
                case "employment_since" :
                    points = rule_matching(sourceData.getEmployment_since(),value,false);
                    sourceData_giveMark.setEmployment_since_giveMark(points);
                    break;
                case "housing" :
                    points = rule_matching(sourceData.getHousing(),value,false);
                    sourceData_giveMark.setHousing_giveMark(points);
                    break;
                case "savings_account_and_bonds" :
                    points = rule_matching(sourceData.getSavings_account_and_bonds(),value,false);
                    sourceData_giveMark.setSavings_account_and_bonds_giveMark(points);
                    break;
                case "installment_plans" :
                    points = rule_matching(sourceData.getInstallment_plans(),value,false);
                    sourceData_giveMark.setInstallment_plans_giveMark(points);
                    break;
                case "status_sxisting_checking_account" :
                    points = rule_matching(sourceData.getStatus_sxisting_checking_account(),value,false);
                    sourceData_giveMark.setStatus_sxisting_checking_account_giveMark(points);
                    break;
                case "installment_income" :
                    points = rule_matching(sourceData.getInstallment_income(),value,false);
                    sourceData_giveMark.setInstallment_income_giveMark(points);
                    break;
                case "credit_amount" :
                    points = rule_matching(sourceData.getCredit_amount(),value,false);
                    sourceData_giveMark.setCredit_amount_giveMark(points);
                    break;
                case "purpose" :
                    points = rule_matching(sourceData.getPurpose(),value,false);
                    sourceData_giveMark.setPurpose_giveMark(points);
                    break;
                case "other_debtors_or_guarantors" :
                    points = rule_matching(sourceData.getOther_debtors_or_guarantors(),value,false);
                    sourceData_giveMark.setOther_debtors_or_guarantors_giveMark(points);
                    break;
                case "personal_status_and_sex" :
                    points = rule_matching(sourceData.getPersonal_status_and_sex(),value,true);
                    sourceData_giveMark.setPersonal_status_and_sex_giveMark(points);
                    break;
            }
            sumPoint += points;
        }

        sumPoint = sumPoint + sourceData_giveMark.getBasicPoints();
        sourceData_giveMark.setCreditability_giveMark(Integer.valueOf(sourceData.getCreditability()));
        sourceData_giveMark.setIdentifying_giveMark(1);
        sourceData_giveMark.setSum_point(sumPoint);
        beanList.add(sourceData_giveMark);
        return beanList;
    }

    private Integer rule_matching(String label,List<Object[]> ruleList,boolean sex) {
        /**
         * SELECT DISTINCT bin,points,variable_second_label FROM rule_wide_table where variable LIKE ?1
         */
        Integer points = 0;
        String bin = "";
        IntervalUtil a = new IntervalUtil();
        for (int i = 0;i<ruleList.size();i++){
            Object[] objects = ruleList.get(i);
            bin = (String) objects[0];
            points = (Integer) objects[1];
            String variable_second_label = (String) objects[2];

            bin = bin.replaceAll("\\s*", "");
            label = label.replaceAll("\\s*", "");

            if (sex == true){
                bin = variable_second_label + ":" + bin;
            }
            if (label.contains("->")){
                if (label.contains(bin)){
                    break;
                }
            }else if (label.contains("/")){
                /**
                 * critical account/ other credits existing (not at this bank)
                 */
                if (label.contains(bin)){
                    break;
                }
            } else if (p.matcher(label).matches()){
                a.isInTheInterval(label, bin);
                break;
            } else {
                if (label.contains(bin)){
                    break;
                }
            }
        }

        return points;
    }

}
