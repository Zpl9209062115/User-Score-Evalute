package com.nanrui.userscore.utils;


import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @ClassName PropertiesLoadUtils
 * @Description TODO
 * @Author ZPL
 * @Date 2019/3/4 11:31
 * @Version 1.0
 **/
public class PropertiesReadUtils {

    private final Properties properties;

    public PropertiesReadUtils(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : new Double(defaultValue);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.
     */
    public Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return null;
    }

    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    private Properties loadProperties(String... resourcesPaths) {
        Properties props = new Properties();

        for (String location : resourcesPaths) {
            try {
                URL url = Thread.currentThread().getContextClassLoader().getResource(location);
                if (url != null) {
                    props.load(url.openStream());
                }
            } catch (IOException ex) {
                System.out.println("Could not load properties from path:" + location + ", " + ex.getMessage());
            }
        }
        return props;
    }

    public static void main(String[] args) {
        PropertiesReadUtils propertiesLoader = new PropertiesReadUtils("MailProperties.properties");

        System.out.println(propertiesLoader.getProperty("age.in.years"));
        System.out.println(propertiesLoader.getProperty("credit.history"));
        System.out.println(propertiesLoader.getProperty("duration.in.month"));
        System.out.println(propertiesLoader.getProperty("present.employment.since"));
        System.out.println(propertiesLoader.getProperty("housing"));
        System.out.println(propertiesLoader.getProperty("savings.account.and.bonds"));
        System.out.println(propertiesLoader.getProperty("other.installment.plans"));
        System.out.println(propertiesLoader.getProperty("status.of.existing.checking.account"));
        System.out.println(propertiesLoader.getProperty("installment.rate.in.percentage.of.disposable.income"));
        System.out.println(propertiesLoader.getProperty("credit.amount"));
        System.out.println(propertiesLoader.getProperty("purpose"));
        System.out.println(propertiesLoader.getProperty("other.debtors.or.guarantors"));
        System.out.println(propertiesLoader.getProperty("personal.status.and.sex.female"));
        System.out.println(propertiesLoader.getProperty("personal.status.and.sex"));
        System.out.println(propertiesLoader.getProperty("personal.status.and.sex.male"));


    }

}