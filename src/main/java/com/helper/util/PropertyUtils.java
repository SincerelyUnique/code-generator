
package com.helper.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * <p>
 * <code>PropertyUtils</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/6 12:49
 */
public class PropertyUtils {

    public static final String SYSTEM_PATH = "config/system.properties";

    public static final String GENERATOR_PATH = "config/generator.properties";

    /**
     * 缓存配置文件里的值集合
     */
    private static HashMap<String, HashMap<String, String>> valueMap = new HashMap<String, HashMap<String, String>>();

    /**
     * 缓存配置文件的值
     * @param propName 配置文件名称
     */
    private static void cacheProperties(String propName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propName);
            if (properties != null) {
                HashMap<String, String> map = new HashMap<String, String>();
                Enumeration<Object> keys = properties.keys();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    map.put(key, properties.getProperty(key));
                }
                valueMap.put(propName, map);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 从缓存中获取值 如果没有则重新读取文件
     */
    public static String getValue(String key, String propName) {
        if (!valueMap.containsKey(propName)) {
            cacheProperties(propName);
        }

        HashMap<String, String> map = valueMap.get(propName);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    /**
     * 测试
     * @param args args
     */
    public static void main(String[] args) {
        String str = getValue("server.ip",SYSTEM_PATH);
        System.out.println(str);
    }
}
