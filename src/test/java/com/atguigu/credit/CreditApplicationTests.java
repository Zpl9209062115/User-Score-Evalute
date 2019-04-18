package com.atguigu.credit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditApplicationTests {

    @Test
    public void contextLoads() {
        String str = new String("status.of.existing.%,%checking.account@... < 0 DM%,%0 <= ... < 200 DM--->/-33");
        /*if (str.contains("%,%")){
            String[] split = str.split("%,%");
            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i].trim());
            }
        }*/
        String regEx = "/%,%[ _.`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        String[] strings = str.split("[%,%/]");
        boolean matches = str.matches(regEx);
        System.out.println(matches);
        for (int i = 0;i<strings.length;i++){
            System.out.println(strings[i]);
        }


    }

}
