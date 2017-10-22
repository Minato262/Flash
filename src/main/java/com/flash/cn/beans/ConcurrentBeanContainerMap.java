package com.flash.cn.beans;

import com.flash.cn.util.Assert;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kay
 * @version v1.0
 */
public class ConcurrentBeanContainerMap extends ConcurrentHashMap<String, Object> implements BeanContainer {

    /**
     * 根据 key 获取容器中的对象
     *
     * @param key 容器关键字(一定不能为空)
     * @param <T> 弱类型转换成强类型
     * @return 返回容器中的对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        Assert.isNotEmpty(key);
        return (T) super.get(key);
    }

    /**
     * 根据关键字存放对象进入容器
     *
     * @param key    容器的关键字
     * @param object 放入容器的关键字
     * @return 放入容器的对象
     */
    @Override
    public Object put(String key, Object object) {
        Assert.isNotEmpty(key);
        return super.put(key, object);
    }
}
