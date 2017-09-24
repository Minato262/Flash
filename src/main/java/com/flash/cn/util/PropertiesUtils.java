package com.flash.cn.util;

import java.io.*;
import java.util.Properties;

/**
 * 配置工具类。
 *
 * @author kay
 * @version v1.0
 */
public class PropertiesUtils {

    /**
     * 载入配置，获取 Properties 配置值
     *
     * @param propertyPath 配置路径
     * @param propertyName 配置名称
     * @return 获取 Properties 配置值
     * @throws IOException I/O 异常
     */
    private static String loadProperties(String propertyPath, String propertyName) throws IOException {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = Object.class.getResourceAsStream(propertyPath);
            prop.load(in);
            for (String key : prop.stringPropertyNames()) {
                if (propertyName.equals(key)) {
                    return prop.getProperty(key);
                }
            }
            return "";
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 载入配置，获取 Properties 配置值
     *
     * @param propertyPath 配置路径
     * @param propertyName 配置名称
     * @return 获取 Properties 配置值
     */
    public static String load(String propertyPath, String propertyName) {
        try {
            return loadProperties(propertyPath, propertyName);
        }
        catch (IOException e) {
            return "";
        }
    }
}
