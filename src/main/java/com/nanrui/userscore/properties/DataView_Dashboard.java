package com.nanrui.userscore.properties;


import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName dashboard
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/8 17:30
 * @Version 1.0
 **/
public class DataView_Dashboard {

    public static Map<String, String> commonMap = new HashMap<String, String>();

    public static void main(String[] args) {
        init("test");
    }

    public static void init(String name) {
        try {
            Properties properties = new Properties();

            InputStream in = DataView_Dashboard.class.getClassLoader().getResourceAsStream(name + ".properties");

            properties.load(in);

            for (String keyName : properties.stringPropertyNames()) {
                String value = properties.getProperty(keyName);
                System.out.println(keyName + " " + value);
                commonMap.put(keyName, value);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
