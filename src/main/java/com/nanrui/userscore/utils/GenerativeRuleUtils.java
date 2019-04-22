package com.nanrui.userscore.utils;


import com.nanrui.userscore.entities.RuleBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
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
    String unit = "";
    String variable_sex = "";
    int count = 0;

    List<RuleBean> ruleBeanList = new ArrayList<>();

    String sFinally = "";

    String num1 = null;
    String symbol1 = null;

    Map<Integer,String> mapCombination = new HashMap<>();


    RuleBean bean = null;

    Pattern p = Pattern.compile(".*\\d+.*");

    /**
     * 处理带冒号的
     * @param colonMap
     * @return
     */
    public List<RuleBean> colonMapDispose(Map<String,String> colonMap){
        for (Map.Entry<String,String> mapDispose : colonMap.entrySet()){
            String[] splitVariable = mapDispose.getKey().split("@");
            variable = splitVariable[0];
            bin = splitVariable[1];
            points = mapDispose.getValue();
            /**
             * 处理含':'的bin
             */
            if (null != bin && !"".equals(bin)){
                String[] splitColon = bin.split("[:]");
                variable_sex = splitColon[0];
                ruleBeanList = specialCharBeanDispose(variable,splitColon[1],variable_sex,points);
            }

        }
        return ruleBeanList;
    }

    /**
     * 处理不带冒号的
     * @param colonMap
     * @return
     */
    public List<RuleBean> colonNotMapDispose(Map<String,String> colonMap){
        for (Map.Entry<String,String> mapDispose : colonMap.entrySet()){
            String[] splitVariable = mapDispose.getKey().split("@");
            variable = splitVariable[0];
            bin = splitVariable[1];
            points = mapDispose.getValue();
            /**
             * 处理不含':'的bin
             */
            if (null != bin && !"".equals(bin)){
                ruleBeanList = specialCharBeanDispose(variable,bin,null,points);
            }

        }
        return ruleBeanList;
    }


    /**
     * utils处理
     *      处理特殊字符  %,%  /  ...
     */
    public List<RuleBean> specialCharBeanDispose(String variable,String splitColon,String variable_sex,String points){

        if (splitColon.contains("%,%") || splitColon.contains("/")){
            String[] splitColon_bin = splitColon.split("[%,%/]");
            for (int i = 0;i<splitColon_bin.length;i++){
                if (!splitColon_bin[i].equals("")){
                    if (null == variable_sex){
                        if (symbolDisposeBoolean(splitColon_bin[i]) && numDisposeBoolean(splitColon_bin[i])){
                            String bin = utils(splitColon_bin[i]);
                            String[] split = bin.split("->");
                            bean = new RuleBean(variable,split[0],points,split[1]);
                        } else{
                            bean = new RuleBean(variable,splitColon_bin[i],points);
                        }
                    } else {
                        if (symbolDisposeBoolean(splitColon_bin[i]) && numDisposeBoolean(splitColon_bin[i])){
                            String bin = utils(splitColon_bin[i]);
                            String[] split = bin.split("->");
                            bean = new RuleBean(variable,split[0],points,split[1]);
                        } else{
                            bean = new RuleBean(variable+"."+variable_sex,splitColon_bin[i],points);
                        }
                    }
                    ruleBeanList.add(bean);
                }

            }

        } else {
            if (symbolDisposeBoolean(splitColon) && numDisposeBoolean(splitColon)){
                String bin = utils(splitColon);
                String[] split = bin.split("->");
                bean = new RuleBean(variable,split[0],points,split[1]);
            } else {
                bean = new RuleBean(variable,splitColon,points);
            }
            ruleBeanList.add(bean);
        }
        return ruleBeanList;
    }

    /**
     * utils处理
     *      处理特殊字符  ...
     *      由于省略号均是和数字区间搭伴一起出现的，因此处理省略号就是处理数字区间
     */
    public String utils(String str1) {
        String unit = "";
        count = 0;
        List<Map<Integer,String>> listMapCombination = new ArrayList<>();
        List<String> ellipsisList = new ArrayList<>();
        int num = 0;
        String[] split1 = str1.split("[...]");
        for (int i = 0; i < split1.length; i++) {
            if (!split1[i].equals("")) {
                count ++;
                ellipsisList.add(split1[i]);
            }
        }

        /**
         * 500 <=
         * < 1000 DM
         */
        String conmbinationString = "";
        for (int i =0;i<ellipsisList.size();i++){
            /**
             * list.size() = 2
             */
            String[] split = ellipsisList.get(i).split(" ");
            for (int j = 0;j<split.length;j++){
                if(!"".equals(split[j])){
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
                    if (symbolBooleanResult){
                        symbol1 = split[j];
                    }

                    /**
                     * 单位的获取
                     */
                    if (!symbolBooleanResult && !numBooleanResult){
                        unit = split[j];
                    }

                    /**
                     * 将分割后的数组和符号进行第一次组合
                     */
                    if (null != num1 && null != symbol1){
                        if (symbolBooleanResult || numBooleanResult){
                            conmbinationString = combinationNumAndSymbol_1(num1,symbol1,count);
                            num ++;
                            mapCombination.put(num,conmbinationString);
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

        System.out.println(sFinally + "->" + unit);
        return sFinally + "->" +unit;
    }

    /**
     * 将处理后的数据进行补全，分为两种
     *      一、筛选条件中为半区间，因此需要补全，正无穷或负无穷
     *      二、筛选条件中为全区间，因此需要拼接，成为完整区间
     * @param listMapCombination
     * @return
     */
    public String combinationNumAndSymbol_2 (List<Map<Integer,String>> listMapCombination){
        for (int i = 0;i<listMapCombination.size();i++){
            if (count%2==0){
                if (count!=1){
                    /**
                     * 里面有两个元素，形成一个区间
                     */
                    sFinally = listMapCombination.get(i).get(i + 1) + "," + listMapCombination.get(i + 1).get(i + 2);
                    break;
                }
            }else {
                /**
                 * 里面有一个元素，需要添加另一部分，形成一个区间
                 */
                sFinally =halfSectionCompletion(listMapCombination);
                break;
            }
        }
        return sFinally;
    }

    /**
     * 数据的一次处理-
     *          数据与符号的拼接，分为完整区间与半区间两种
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String combinationNumAndSymbol_1 (String num1,String symbol1,int sum){
        String combinationString = "";

        if (1 == sum){
            combinationString = criterionHalfSection(num1,symbol1,sum);
        } else {
            combinationString = criterionFullSection(num1,symbol1,sum);
        }
        return combinationString;
    }

    /**
     *
     * 完整区间补全规则
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String criterionHalfSection(String num1,String symbol1,int sum){
        String combinationString = "";
        if (null != num1 && null != symbol1){
            switch (symbol1){
                /**
                 *  <  或者  <=  得到的都是右面，确定的都是右面的区间
                 */
                case "<":
                    combinationString = num1 + ")";
                    break;
                case "<=":
                    combinationString = num1  + "]";
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
     * @param num1
     * @param symbol1
     * @param sum
     * @return
     */
    public String criterionFullSection(String num1,String symbol1,int sum){
        String combinationString = "";
        if (null != num1 && null != symbol1){
            switch (symbol1){
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
     * @param halfList
     * @return
     */
    public String halfSectionCompletion(List<Map<Integer,String>> halfList){
        String str = "";
        for (int i = 0;i<halfList.size();i++){
            String halfSection = halfList.get(i).get(i + 1);
            if (null != halfSection){
                if (halfSection.contains("(")){
                    str = halfSection + "," + "Inf)";
                }
                if (halfSection.contains("[")){
                    str = halfSection + "," + "Inf)";
                }
                if (halfSection.contains(")")){
                    str = "[-Inf" + "," + halfSection;
                }
                if (halfSection.contains("]")){
                    str = "[-Inf" + "," + halfSection;
                }
            }
        }
        return str;
    }

    public boolean symbolDisposeBoolean(String string){
        boolean symbolBooleanResult = string.contains("<") || string.contains("<=") || string.contains(">") || string.contains(">=");
        return symbolBooleanResult;
    }

    public boolean numDisposeBoolean(String string){
        boolean numBooleanResult = p.matcher(string).matches();
        return numBooleanResult;
    }

}
