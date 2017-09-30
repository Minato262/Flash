package com.flash.cn.core;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author kay
 * @version v1.0
 */
public class ClassLoader {

    /**
     * 根据来源获取，目标 URL 资源
     *
     * @param name 资源名称
     * @return URL 元素资源
     * @throw ClassPathResourceException
     */
    public Enumeration<URL> getEnumeration(String name) {
        try {
            return Thread.currentThread().getContextClassLoader().getResources(name);
        }
        catch (IOException e) {
            throw new ClassPathResourceException(e);
        }
    }

    /**
     * 载入 Class 类
     *
     * @param name 资源名称
     * @return 载入的 Class 类
     * @throw ClassPathResourceException
     */
    public Class<?> loadClass(String name) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(name);
        }
        catch (ClassNotFoundException e) {
            throw new ClassPathResourceException(e);
        }
    }
}
