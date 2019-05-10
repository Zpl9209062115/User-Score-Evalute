package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.RuleBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DataDisposeUtils
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/8 17:19
 * @Version 1.0
 **/
public class DataDisposeUtils {

    List<RuleBean> listColon = new ArrayList<>();

    List<RuleBean> listOther = new ArrayList<>();

    Map<String,List<RuleBean>> mapFinally = new HashMap<>();

    public Map<String,List<RuleBean>> generative_rule(Map<String,Map<String,String>> csvMap){
        /**
         * 根据文件生成规则:把生成的mapRuleCsv放入处理工具类
         */
        GenerativeRuleUtils generativeRuleUtils = new GenerativeRuleUtils();

        /**
         * 处理带冒号和不带冒号的
         *      剩下的判别在对应的里面进行
         */
        for (Map.Entry<String, Map<String, String>> mapRule : csvMap.entrySet()){
            if (("colonMap").equals(mapRule.getKey())){
                listColon = generativeRuleUtils.colonMapDispose(mapRule.getValue());
            }  else {
                listOther = generativeRuleUtils.colonNotMapDispose(mapRule.getValue());
            }
        }
        mapFinally.put("listColon",listColon);
        mapFinally.put("listOther",listOther);
        return mapFinally;
    }

}
