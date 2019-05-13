package com.nanrui.userscore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName UserScoreTest
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/19 14:56
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserScoreTest {

    String right = ",Inf)";
    String left = "[-Inf,";
    String sFinally = "";

    String num1 = null;
    String symbol1 = null;

    List<Map<Integer,String>> listMapCombination = new ArrayList<>();
    Map<Integer,String> mapCombination = new HashMap<>();

    @Test
    public void contextLoads() {

        /*for (int i = 0;i<10;i++){
            if (i==2){
                break;
            }
            System.out.println(i);
        }*/

        /*String utils = "";*/

       /* String str1 = "... < 0 DM";
        String[] split1 = str1.split("[...]");
        System.out.println(split1.length);
        System.out.println("===============================");
        for (int i = 0; i < split1.length; i++) {
            if (!split1[i].equals("")) {
                count ++;
                list.add(split1[i]);
            }
        }
        utils = utils(list,count);*/


        /*String str2 = "0 <= ... < 200 DM";
        String[] split2 = str2.split("[...]");
        for (int i = 0; i < split2.length; i++) {
            if (!split2[i].equals("")) {
                count ++;
                list.add(split2[i]);
            }
        }
        utils = utils(list,count);*/

        /*String str3 = "500 <= ... < 1000 DM";
        String[] split3 = str3.split("[...]");
        System.out.println(split3.length);
        System.out.println("===============================");
        for (int i = 0; i < split3.length; i++) {
            if (!split3[i].equals("")) {
                count ++;
                list.add(split3[i]);
            }
        }
        utils = utils(list,count);*/

        /*String abc1 = "百度科技(123)公司1";
        abc1 = abc1.replaceAll("\\(|\\)", "");
        System.out.println(abc1);*/
    }

    public String utils(List<String> list,int count) {
        Pattern p = Pattern.compile(".*\\d+.*");
        int num = 0;
        /**
         * 500 <=
         * < 1000 DM
         */
        String conmbinationString = "";
        for (int i =0;i<list.size();i++){
            /**
             * list.size() = 2
             */
            String[] split = list.get(i).split(" ");
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
                     * 将分割后的数组和符号进行第一次组合
                     */
                    if (null != num1 && null != symbol1){
                        if (symbolBooleanResult || numBooleanResult){
                            conmbinationString = combinationNumAndSymbol_1(num1,symbol1,list.size());
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

        System.out.println(sFinally);
        return sFinally;
    }


    public String combinationNumAndSymbol_2 (List<Map<Integer,String>> listMapCombination){
        for (int i = 0;i<listMapCombination.size();i++){
            if (listMapCombination.size()%2==0){
                if (i%2==0){
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
     * 半个区间数据的补全
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

    public String combinationNumAndSymbol_1 (String num1,String symbol1,int sum){
        String combinationString = "";

        if (1 == sum){
            combinationString = criterionHalfSection(num1,symbol1,sum);
        } else {
            combinationString = criterionFullSection(num1,symbol1,sum);
        }
        return combinationString;
    }

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


}
