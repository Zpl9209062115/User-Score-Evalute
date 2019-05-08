package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.LoanUser_GiveMark;

import java.util.*;

/**
 * @ClassName RuleSectionJudgeUtils
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/26 9:44
 * @Version 1.0
 **/
public class RuleSectionJudgeUtils {

    String AGE_VARIABLE = "age";
    String CREDITHISTORY_VARIABLE = "credit_history";
    String DURATIONMONTH_VARIABLE = "duration_month";
    String EMPLOYMENTSINCE = "employment_since";
    String HOUSING_VARIABLE = "housing";
    String SAVINGACCOUNTANDBONDS_VARIABLE = "savings_account_and_bonds";
    String INSTALLMENTPLANS_VARIABLE = "installment_plans";
    String STATUSSXISTINGCHECKINGACCOUNT_VARIABLE = "status_sxisting_checking_account";
    String INSTALLMENTINCOME_VARIABLE = "installment_income";
    String CREDITAMOUNT_VARIABLE = "credit_amount";
    String PURPOSE_VARIABLE = "purpose";
    String OTHERDEBTORSORGUARANTORS_VARIABLE = "other_debtors_or_guarantors";
    String SEX_VARIABLE = "personal_status_and_sex";

    int pointsSum = 0;

    IntervalUtil a = new IntervalUtil();

    List<LoanUser_GiveMark> listLabelSection = new ArrayList<>();

    LoanUser_GiveMark loanUser_giveMark_Finally = new LoanUser_GiveMark();

    Map<String,Map<String,Integer>> mapClassify = null;

    Map<String,Integer> mapVariableAndBinNew = new HashMap<>();

    public List<LoanUser_GiveMark> ruleSectionJudge(String loanUserName,String loanUserId,LoanUser loanUser, Map<String, Integer> mapRule) {
        sectionJudge_labelClassify(mapRule);
        if (0 != mapClassify.size()){
            Integer age_Point = numSectionJudge_binClassify(loanUser.getAge(),mapClassify,AGE_VARIABLE);
            Integer durationMonth_Point = numSectionJudge_binClassify(loanUser.getDurationMonth(),mapClassify,DURATIONMONTH_VARIABLE);
            Integer installmentIncome_Point = numSectionJudge_binClassify(loanUser.getInstallmentIncome(),mapClassify,INSTALLMENTINCOME_VARIABLE);
            Integer creditAmount_Point = numSectionJudge_binClassify(loanUser.getCreditAmount(),mapClassify,CREDITAMOUNT_VARIABLE);

            Integer housing_Point = stringSectionJudge_labelClassify(loanUser.getHousing(),mapClassify,HOUSING_VARIABLE);
            Integer installmentPlans_Point = stringSectionJudge_labelClassify(loanUser.getInstallmentPlans(),mapClassify,INSTALLMENTPLANS_VARIABLE);

            Integer purpose_Point = stringSectionJudge_labelClassify(loanUser.getPurpose(),mapClassify,PURPOSE_VARIABLE);
            Integer employmentSince_Point = stringSectionJudge_labelClassify(loanUser.getEmploymentSince(),mapClassify,EMPLOYMENTSINCE);
            Integer savingsAccountAndBonds_Point = stringSectionJudge_labelClassify(loanUser.getSavingsAccountAndBonds(),mapClassify,SAVINGACCOUNTANDBONDS_VARIABLE);
            Integer creditHistory_Point = stringSectionJudge_labelClassify(loanUser.getCreditHistory(),mapClassify,CREDITHISTORY_VARIABLE);
            Integer statusSxistingCheckingAccount_Point = stringSectionJudge_labelClassify(loanUser.getStatusSxistingCheckingAccount(),mapClassify,STATUSSXISTINGCHECKINGACCOUNT_VARIABLE);
            Integer otherDebtorsOrGuarantors_Point = stringSectionJudge_labelClassify(loanUser.getOtherDebtorsOrGuarantors(),mapClassify,OTHERDEBTORSORGUARANTORS_VARIABLE);
            pointsSum = Integer.valueOf(loanUser_giveMark_Finally.getBasicPoints()) + pointsSum;
            loanUser_giveMark_Finally = new LoanUser_GiveMark(loanUserName,age_Point,null,durationMonth_Point,installmentIncome_Point,employmentSince_Point,housing_Point,installmentPlans_Point,savingsAccountAndBonds_Point,creditHistory_Point,creditAmount_Point,statusSxistingCheckingAccount_Point,purpose_Point,otherDebtorsOrGuarantors_Point,pointsSum,451,loanUserId);
            listLabelSection.add(loanUser_giveMark_Finally);
        }
        return listLabelSection;
    }

    /**
     * 判断输入值是在什么数据区间内，只要针对前台
     * 年龄、社保缴纳时间、分期付款率占可支配收入的百分比、欠款金额   这四项输入值进行比对与判断
     * @param mapRule
     * @return
     */
    public void sectionJudge_labelClassify(Map<String, Integer> mapRule) {
        Integer points = 0;
        System.out.println("--------------------------------是数据项，需要对录入的值进行区间判断--------------------------------");
        for (Map.Entry<String, Integer> map : mapRule.entrySet()) {
            String mapKey = map.getKey();
            String[] split = mapKey.split("@");
            String variable = split[0];
            String bin = split[1];
            points = Integer.valueOf(map.getValue());

            boolean contains = false;

            if (null == mapClassify){
                contains = false;
            } else {
                Set<String> set = mapClassify.keySet();
                contains = set.contains(variable);
            }

            if (contains){
                Map<String, Integer> mapVariableAndBin = mapClassify.get(variable);
                mapVariableAndBin.put(bin, points);
            } else {
                if (null ==mapClassify){
                    mapClassify = new HashMap<>();
                }
                mapVariableAndBinNew = new HashMap<>();
                mapVariableAndBinNew.put(bin,points);
                mapClassify.put(variable, mapVariableAndBinNew);
            }
        }

    }

    public Integer numSectionJudge_binClassify(String loanData, Map<String, Map<String,Integer>> ruleMapBin, String lableValue) {
        Map<String, Integer> ruleBin = ruleMapBin.get(lableValue);
        boolean inTheInterval = false;
        Integer points = 0;
        for (Map.Entry<String, Integer> ruleBinMap : ruleBin.entrySet()) {
            points = Integer.valueOf(ruleBinMap.getValue());
            inTheInterval = a.isInTheInterval(loanData, ruleBinMap.getKey());
            if (inTheInterval) {
                /*loanUser_giveMark.set(lableValue, ruleBin.getValue());*/
                pointsSum = pointsSum + Integer.valueOf(ruleBinMap.getValue());
                System.out.println("数据项，需要进行匹配" + pointsSum);
                break;
            }
        }
        return points;
    }

    public Integer stringSectionJudge_labelClassify(String loanData, Map<String, Map<String,Integer>> ruleMapBin, String lableValue){
        Integer points = 0;
        Map<String, Integer> ruleBin = ruleMapBin.get(lableValue);
        for (Map.Entry<String,Integer> ruleBinMap : ruleBin.entrySet()){
            String mapKey = ruleBinMap.getKey();
            Integer mapValue = ruleBinMap.getValue();
            if (loanData.equals(mapKey)){
                points = mapValue;
                pointsSum = pointsSum + Integer.valueOf(ruleBinMap.getValue());
                System.out.println("数据项，需要进行匹配" + pointsSum);
                break;
            }
        }
        return points;
    }

}
