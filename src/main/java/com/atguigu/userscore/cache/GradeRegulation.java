package com.atguigu.userscore.cache;

import org.springframework.cache.annotation.CachePut;

import java.util.Map;

/**
 * @ClassName GradeRegulation
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/16 13:51
 * @Version 1.0
 **/

public class GradeRegulation {

    @CachePut
    public Map<String,Object> getRegulation(){
        return null;
    }

}
