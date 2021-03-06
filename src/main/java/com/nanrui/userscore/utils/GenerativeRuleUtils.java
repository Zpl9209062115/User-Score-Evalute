package com.nanrui.userscore.utils;


import com.nanrui.userscore.entities.RuleBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName GenerativeRule
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/16 15:11
 * @Version 1.0
 **/
public class GenerativeRuleUtils {

    String variable = "";
    String bin = "";
    String points = "";
    String variable_sex = "";
    int count = 0;

    List<RuleBean> ruleBeanList = new ArrayList<>();

    String sFinally = "";

    String num1 = null;
    String symbol1 = null;

    Map<Integer, String> mapCombination = new HashMap<>();


    RuleBean bean = null;

    Pattern p = Pattern.compile(".*\\d+.*");

    /**
     * 处理带冒号的
     *
     * @param colonMap
     * @return
     */
    public List<RuleBean> colonMapDispose(Map<String, String> colonMap) {
        for (Map.Entry<String, String> mapDispose : colonMap.entrySet()) {
            String[] splitVariable = mapDispose.getKey().split("@");
            variable = variableDispose(splitVariable[0]);
            bin = splitVariable[1];
            /**
             * 处理数据中的Inf,后面的工具类对于Inf不识别
             */
            if (bin.contains("Inf") || bin.contains("-Inf")) {
                bin = bin.replaceAll("Inf", "∞");
            }
            points = mapDispose.getValue();
            /**
             * 处理含':'的bin
             */
            if (null != bin && !"".equals(bin)) {
                String[] splitColon = bin.split("[:]");
                variable_sex = splitColon[0];
                ruleBeanList = specialCharBeanDispose(variable, splitColon[1], variable_sex, points);
            }

        }
        return ruleBeanList;
    }

    /**
     * 处理不带冒号的
     *
     * @param colonMap
     * @return
     */
    public List<RuleBean> colonNotMapDispose(Map<String, String> colonMap) {
        for (Map.Entry<String, String> mapDispose : colonMap.entrySet()) {
            String[] splitVariable = mapDispose.getKey().split("@");
            variable = variableDispose(splitVariable[0]);
            bin = splitVariable[1];

            /**
             * 处理数据中的Inf,后面的工具类对于Inf不识别
             */
            if (bin.contains("Inf") || bin.contains("-Inf")) {
                bin = bin.replaceAll("Inf", "∞");
            }
            points = mapDispose.getValue();
            /**
             * 处理不含':'的bin
             */
            if (null != bin && !"".equals(bin)) {
                ruleBeanList = specialCharBeanDispose(variable, bin, variable_sex, points);
            }

        }
        return ruleBeanList;
    }


    /**
     * utils处理
     * 处理特殊字符  %,%  /  ...
     */
    public List<RuleBean> specialCharBeanDispose(String variable, String splitColon, String variable_sex, String points) {
        if (splitColon.contains("%,%") || splitColon.contains("/")) {
            String[] splitColon_bin = splitColon.split("[%,%/]");
            for (int i = 0; i < splitColon_bin.length; i++) {
                if (!splitColon_bin[i].equals("")) {
                    if (symbolDisposeBoolean(splitColon_bin[i], null) && numDisposeBoolean(splitColon_bin[i])) {
                        String bin = utils(splitColon_bin[i]);
                        if (bin.contains("->")) {
                            String[] split = bin.split("->");
                            bean = new RuleBean(variable.trim(), variable_sex.trim(), split[0].trim(), Integer.valueOf(points.trim()), split[1].trim(), split[2].trim());
                        } else {
                            bean = new RuleBean(variable.trim(), variable_sex.trim(), bin, Integer.valueOf(points.trim()), "", "");
                        }
                    } else {
                        bean = new RuleBean(variable.trim(), variable_sex.trim(), splitColon_bin[i].trim(), Integer.valueOf(points.trim()), null, "str");
                    }
                    ruleBeanList.add(bean);
                }
            }
        } else {
            if (symbolDisposeBoolean(splitColon, null) && numDisposeBoolean(splitColon)) {
                String bin = utils(splitColon);
                if (bin.contains("->")) {
                    String[] split = bin.split("->");
                    bean = new RuleBean(variable.trim(), variable_sex.trim(), split[0].trim(), Integer.valueOf(points.trim()), split[1].trim(), split[2].trim());
                } else {
                    bean = new RuleBean(variable.trim(), variable_sex.trim(), bin.trim(), Integer.valueOf(points.trim()), "", "");
                }
            } else if (splitColon.contains("[") || splitColon.contains("(") || splitColon.contains("]") || splitColon.contains(")")) {
                bean = new RuleBean(variable.trim(), variable_sex.trim(), splitColon.trim(), Integer.valueOf(points.trim()), null, "num");
            } else {
                bean = new RuleBean(variable.trim(), variable_sex.trim(), splitColon.trim(), Integer.valueOf(points.trim()), null, "str");
            }
            ruleBeanList.add(bean);
        }
        return ruleBeanList;
    }

    /**
     * utils处理
     * 处理特殊字符  ...
     * 由于省略号均是和数字区间搭伴一起出现的，因此处理省略号就是处理数字区间
     */
    public String utils(String str1) {
        String unit = "";
        count = 0;
        List<Map<Integer, String>> listMapCombination = new ArrayList<>();
        List<String> ellipsisList = new ArrayList<>();
        int num = 0;
        if (str1.contains("...")) {
            String[] split1 = str1.split("[...]");
            for (int i = 0; i < split1.length; i++) {
                if (!split1[i].equals("")) {
                    count++;
                    ellipsisList.add(split1[i]);
                }
            }

            /**
             * 500 <=
             * < 1000 DM
             */
            String conmbinationString = "";
            for (int i = 0; i < ellipsisList.size(); i++) {
                /**
                 * list.size() = 2
                 */
                String[] split = ellipsisList.get(i).split(" ");
                for (int j = 0; j < split.length; j++) {
                    if (!"".equals(split[j])) {
                        boolean symbolBooleanResult = split[j].contains("<") || split[j].contains("<=") || split[j].contains(">") || split[j].contains(">=");
                        boolean numBooleanResult = p.matcher(split[j]).matches();
                        /**
                         * 判断分割后的字符串是否为数字
                         */
                        if (numBooleanResult) {
                            num1 = split[j];
                        }
                        /**
                         * 判断分割后的字符串是否为符号
                         */
                        if (symbolBooleanResult) {
                            symbol1 = split[j];
                        }

                        /**
                         * 单位的获取
                         */
                        if (!symbolBooleanResult && !numBooleanResult) {
                            unit = split[j];
                        }

                        /**
                         * 将分割后的数组和符号进行第一次组合
                         */
                        if (null != num1 && null != symbol1) {
                            if (symbolBooleanResult || numBooleanResult) {
                                conmbinationString = combinationNumAndSymbol_1(num1, symbol1, count);
                                num++;
                                mapCombination.put(num, conmbinationString);
                                listMapCombination.add(mapCombination);
                                num1 = null;
                                symbol1 = null;
                            }
                        }

                    }
                }
            }
            /**
             * 将分割后的数组和符号进行第二次组合
             */
            sFinally = combinationNumAndSymbol_2(listMapCombination);
            sFinally = sFinally + "->" + unit + "->" + "num";
        } else {
            sFinally = str1 + "->" + unit + "->" + "num";
        }

        return sFinally;
    }

    /**
     * 将处理后的数据进行补全，分为两种
     * 一、筛选条件中为半区间，因此需要补全，正无穷或负无穷
     * 二、筛选条件中为全区间，因此需要拼接，成为完整区间
     *
     * @param listMapCombination
     * @return
     */
    public String combinationNumAndSymbol_2(List<Map<Integer, String>> listMapCombination) {
        for (int i = 0; i < listMapCombination.size(); i++) {
            if (count % 2 == 0) {
                if (count != 1) {
                    /**
                     * 里面有两个元素，形成一个区间
                     */
                    sFinally = listMapCombination.get(i).get(i + 1) + "," + listMapCombination.get(i + 1).get(i + 2);
                    break;
                }
            } else {
                /**
                 * 里面有一个元素，需要添加另一部分，形成一个区间
                 */
                sFinally = halfSectionCompletion(listMapCombination);
                break;
            }
        }
        return sFinally;
    }

    /**
     * 数据的一次处理-
     * 数据与符号的拼接，分为完整区间与半区间两种
     *
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String combinationNumAndSymbol_1(String num1, String symbol1, int sum) {
        String combinationString = "";

        if (1 == sum) {
            combinationString = criterionHalfSection(num1, symbol1, sum);
        } else {
            combinationString = criterionFullSection(num1, symbol1, sum);
        }
        return combinationString;
    }

    /**
     * 完整区间补全规则
     *
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String criterionHalfSection(String num1, String symbol1, int sum) {
        String combinationString = "";
        if (null != num1 && null != symbol1) {
            switch (symbol1) {
                /**
                 *  <  或者  <=  得到的都是右面，确定的都是右面的区间
                 */
                case "<":
                    combinationString = num1 + ")";
                    break;
                case "<=":
                    combinationString = num1 + "]";
                    break;
                /**
                 *  >  或者  >=  得到的都是左面，确定的都是左面的区间
                 */
                case ">":
                    combinationString = "(" + num1;
                    break;
                case ">=":
                    combinationString = "[" + num1;
                    break;
            }
        }
        return combinationString;
    }

    /**
     * 半个数据的数据拼接，对于存在的一半的数据符号的拼接
     *
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String criterionFullSection(String num1, String symbol1, int sum) {
        String combinationString = "";
        if (null != num1 && null != symbol1) {
            switch (symbol1) {
                /**
                 *  <  或者  <=  得到的都是右面，确定的都是右面的区间
                 */
                //小于
                case "<":
                    combinationString = num1 + ")";
                    break;

                //大于等于
                case "<=":
                    combinationString = "[" + num1;
                    break;
                /**
                 *  >  或者  >=  得到的都是左面，确定的都是左面的区间
                 */
                //大于
                case ">":
                    combinationString = "(" + num1;
                    break;
                //小于等于
                case ">=":
                    combinationString = num1 + "]";
                    break;
            }
        }
        return combinationString;
    }

    /**
     * 半个数据的数据拼接，对于不存在的另一个区间的数据拼接-半个区间数据的补全规则
     *
     * @param halfList
     * @return
     */
    public String halfSectionCompletion(List<Map<Integer, String>> halfList) {
        String str = "";
        for (int i = 0; i < halfList.size(); i++) {
            String halfSection = halfList.get(i).get(i + 1);
            if (null != halfSection) {
                if (halfSection.contains("(")) {
                    str = halfSection + "," + "∞)";
                }
                if (halfSection.contains("[")) {
                    str = halfSection + "," + "∞)";
                }
                if (halfSection.contains(")")) {
                    str = "[-∞" + "," + halfSection;
                }
                if (halfSection.contains("]")) {
                    str = "[-∞" + "," + halfSection;
                }
            }
        }
        return str;
    }

    public String variableDispose(String variable) {
        String variableTrim = variable.trim();
        String variableDisposeStr = "";
        switch (variableTrim) {
            case "age.in.years":
                variableDisposeStr = "age";
                break;
            case "credit.history":
                variableDisposeStr = "credit_history";
                break;
            case "duration.in.month":
                variableDisposeStr = "duration_month";
                break;
            case "present.employment.since":
                variableDisposeStr = "employment_since";
                break;
            case "housing":
                variableDisposeStr = "housing";
                break;
            case "savings.account.and.bonds":
                variableDisposeStr = "savings_account_and_bonds";
                break;
            case "other.installment.plans":
                variableDisposeStr = "installment_plans";
                break;
            case "status.of.existing.checking.account":
                variableDisposeStr = "status_sxisting_checking_account";
                break;
            case "installment.rate.in.percentage.of.disposable.income":
                variableDisposeStr = "installment_income";
                break;
            case "credit.amount":
                variableDisposeStr = "credit_amount";
                break;
            case "purpose":
                variableDisposeStr = "purpose";
                break;
            case "other.debtors.or.guarantors":
                variableDisposeStr = "other_debtors_or_guarantors";
                break;
            case "personal.status.and.sex.female":
                variableDisposeStr = "personal_status_and_sex";
                break;
            case "personal.status.and.sex":
                variableDisposeStr = "personal_status_and_sex";
                break;
            case "personal.status.and.sex.male":
                variableDisposeStr = "personal_status_and_sex";
                break;

        }
        return variableDisposeStr;
    }

    public boolean symbolDisposeBoolean(String string, List<String> normList) {
        boolean symbolBooleanResult = false;
        if (normList == null) {
            symbolBooleanResult = string.contains("<") || string.contains("<=") || string.contains(">") || string.contains(">=");
        } else {
            for (int i = 0; i < normList.size(); i++) {
                symbolBooleanResult = string.contains(normList.get(i));
                if (symbolBooleanResult == true) {
                    break;
                }
            }
        }

        return symbolBooleanResult;
    }

    public boolean numDisposeBoolean(String string) {
        boolean numBooleanResult = p.matcher(string).matches();
        return numBooleanResult;
    }

}
