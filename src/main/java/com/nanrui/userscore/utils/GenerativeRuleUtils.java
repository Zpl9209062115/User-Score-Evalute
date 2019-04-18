package com.nanrui.userscore.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GenerativeRule
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/16 15:11
 * @Version 1.0
 **/
public class GenerativeRuleUtils {

    String keyMap = "";
    String valueMap = "";
    String variable = "";
    String bin = "";
    String points = "";
    String binAndVariable = "";
    public static HashMap<String,String> mapCache = new HashMap<>();
    public static HashMap<String,String> mapFinally = new HashMap<>();

    public Map<String,String> binDispose1(Map<String,String> mapRule){
        Map<String,String> map1 = new HashMap<>();
        for (Map.Entry<String,String> map : mapRule.entrySet()){
            String[] splitKey = map.getKey().split("@");
            variable= splitKey[0];
            bin= splitKey[1];
            valueMap = map.getValue();
            System.out.println(map.getKey() + "   " + map.getValue());
            /**
             * 过滤bin
             */
            if (null != bin){
                if (bin.contains(":")){
                    mapCache.put(variable + "@" + bin,valueMap);
                } else if (bin.contains("%,%") || bin.contains("/")){
                    String[] strings = bin.split("[%,%/]");

                    for (int i = 0;i<strings.length;i++){
                        keyMap = variable + "@" + strings[i];
                        map1.put(keyMap,valueMap);
                    }
                }else {
                    map1.put(map.getKey(),map.getValue());
                }
            }

        }
        for (Map.Entry<String,String> mAfter : map1.entrySet()){
            System.out.println(mAfter.getKey() + "   " + mAfter.getValue());
        }
        return map1;
    }

    public Map<String,String> binDispose2(Map<String,String> mapCache){
        Map<String,String> map2 = new HashMap<>();
        String sex = "";
        for (Map.Entry<String,String> map : mapCache.entrySet()){
            String[] splitKey = map.getKey().split("@");
            variable= splitKey[0];
            bin= splitKey[1];
            valueMap = map.getValue();
            System.out.println(map.getKey() + "   " + map.getValue());
            /**
             * 过滤bin
             */
            if (null != bin){
                if (bin.contains(":")){
                    String[] strings = bin.split("[:]");
                    sex = strings[0];
                    if (strings[1].contains("%,%") || strings[1].contains("/")){
                        String[] split = strings[1].split("[%,%/]");
                        for (int i = 0;i<split.length;i++){
                            keyMap = variable + "@" + sex + ":" + split[i];
                            map2.put(keyMap,valueMap);
                        }
                    }

                   /* for (int i = 0;i<strings.length;i++){
                        sex = strings[0];
                        keyMap = variable + "@" + sex + ":" + strings[i];
                        map2.put(keyMap,valueMap);
                    }*/
                } else {
                    map2.put(map.getKey(),map.getValue());
                }
            }
        }

        for (Map.Entry<String,String> mAfter : map2.entrySet()){
            System.out.println(mAfter.getKey() + "   " + mAfter.getValue());
        }

        return map2;
    }

    public Map<String,String> GenerativeRule(Map<String,String> mapRule){
        /**
         * 过滤数据中的分隔符，筛选出评分标准(条件)
         */
        Map<String,String> map1 = binDispose1(mapRule);
        Map<String, String> map2 = binDispose2(mapCache);
        mapFinally.putAll(map1);
        mapFinally.putAll(map2);
        return mapFinally;
    }


}
