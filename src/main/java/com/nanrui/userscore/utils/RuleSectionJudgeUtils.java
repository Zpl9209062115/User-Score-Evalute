package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.LoanUser_GiveMark;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @ClassName RuleSectionJudgeUtils
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/26 9:44
 * @Version 1.0
 **/
public class RuleSectionJudgeUtils {

    String AGE_VARIABLE = "age";
    String CREDITHISTORY_VARIABLE = "creditHistory";
    String DURATIONMONTH_VARIABLE = "durationMonth";
    String EMPLOYMENTSINCE = "employmentSince";
    String HOUSING_VARIABLE = "housing";
    String SAVINGACCOUNTANDBONDS_VARIABLE = "savingsAccountAndBonds";
    String INSTALLMENTPLANS_VARIABLE = "installmentPlans";
    String STATUSSXISTINGCHECKINGACCOUNT_VARIABLE = "statusSxistingCheckingAccount";
    String INSTALLMENTINCOME_VARIABLE = "installmentIncome";
    String CREDITAMOUNT_VARIABLE = "creditAmount";
    String PURPOSE_VARIABLE = "purpose";
    String OTHERDEBTORSORGUARANTORS_VARIABLE = "otherDebtorsOrGuarantors";
    String SEX_VARIABLE = "sex";



    int pointsSum = 0;

    IntervalUtil a = new IntervalUtil();

    List<LoanUser_GiveMark> listLabelSection = new ArrayList<>();

    LoanUser_GiveMark loanUser_giveMark_Finally = new LoanUser_GiveMark();

    Map<String,Map<String,String>> mapClassify = null;

    Map<String,String> mapVariableAndBinNew = new HashMap<>();

    public List<LoanUser_GiveMark> ruleSectionJudge(String loanUserName,String loanUserId,LoanUser loanUser, Map<String, String> mapRule) {
        sectionJudge_labelClassify(mapRule);
        if (0 != mapClassify.size()){
            String age_Point = numSectionJudge_binClassify(loanUser.getAge(),mapClassify,AGE_VARIABLE);
            String durationMonth_Point = numSectionJudge_binClassify(loanUser.getDurationMonth(),mapClassify,DURATIONMONTH_VARIABLE);
            String installmentIncome_Point = numSectionJudge_binClassify(loanUser.getInstallmentIncome(),mapClassify,INSTALLMENTINCOME_VARIABLE);
            String creditAmount_Point = numSectionJudge_binClassify(loanUser.getCreditAmount(),mapClassify,CREDITAMOUNT_VARIABLE);

            String housing_Point = stringSectionJudge_labelClassify(loanUser.getHousing(),mapClassify,HOUSING_VARIABLE);
            String installmentPlans_Point = stringSectionJudge_labelClassify(loanUser.getInstallmentPlans(),mapClassify,INSTALLMENTPLANS_VARIABLE);

            String purpose_Point = stringSectionJudge_labelClassify(loanUser.getPurpose(),mapClassify,PURPOSE_VARIABLE);
            String employmentSince_Point = stringSectionJudge_labelClassify(loanUser.getEmploymentSince(),mapClassify,EMPLOYMENTSINCE);
            String savingsAccountAndBonds_Point = stringSectionJudge_labelClassify(loanUser.getSavingsAccountAndBonds(),mapClassify,SAVINGACCOUNTANDBONDS_VARIABLE);
            String creditHistory_Point = stringSectionJudge_labelClassify(loanUser.getCreditHistory(),mapClassify,CREDITHISTORY_VARIABLE);
            String statusSxistingCheckingAccount_Point = stringSectionJudge_labelClassify(loanUser.getStatusSxistingCheckingAccount(),mapClassify,STATUSSXISTINGCHECKINGACCOUNT_VARIABLE);
            String otherDebtorsOrGuarantors_Point = stringSectionJudge_labelClassify(loanUser.getOtherDebtorsOrGuarantors(),mapClassify,OTHERDEBTORSORGUARANTORS_VARIABLE);
            pointsSum = Integer.valueOf(loanUser_giveMark_Finally.getBasicPoints()) + pointsSum;
            loanUser_giveMark_Finally = new LoanUser_GiveMark(
                    loanUserName,
                    age_Point,
                    null,
                    durationMonth_Point,
                    installmentIncome_Point,
                    employmentSince_Point,
                    housing_Point,
                    installmentPlans_Point,
                    savingsAccountAndBonds_Point,
                    creditHistory_Point,
                    creditAmount_Point,
                    statusSxistingCheckingAccount_Point,
                    purpose_Point,
                    otherDebtorsOrGuarantors_Point,
                    String.valueOf(pointsSum),
                    loanUserId);
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
    public void sectionJudge_labelClassify(Map<String, String> mapRule) {
        String points = "";
        System.out.println("--------------------------------是数据项，需要对录入的值进行区间判断--------------------------------");
        for (Map.Entry<String, String> map : mapRule.entrySet()) {
            String mapKey = map.getKey();
            String[] split = mapKey.split("@");
            String variable = split[0];
            String bin = split[1];
            points = map.getValue();

            boolean contains = false;

            if (null == mapClassify){
                contains = false;
            } else {
                Set<String> set = mapClassify.keySet();
                contains = set.contains(variable);
            }

            if (contains){
                Map<String, String> mapVariableAndBin = mapClassify.get(variable);
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

    public String numSectionJudge_binClassify(String loanData, Map<String, Map<String,String>> ruleMapBin, String lableValue) {
        Map<String, String> ruleBin = ruleMapBin.get(lableValue);
        boolean inTheInterval = false;
        String points = "";
        for (Map.Entry<String, String> ruleBinMap : ruleBin.entrySet()) {
            points = ruleBinMap.getValue();
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

    public String stringSectionJudge_labelClassify(String loanData, Map<String, Map<String,String>> ruleMapBin, String lableValue){
        String points = "";
        Map<String, String> ruleBin = ruleMapBin.get(lableValue);
        for (Map.Entry<String,String> ruleBinMap : ruleBin.entrySet()){
            String mapKey = ruleBinMap.getKey();
            String mapValue = ruleBinMap.getValue();
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
