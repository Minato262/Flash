/*
 * Copyright 2017-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flash.cn.context;

import com.flash.cn.beans.*;
import com.flash.cn.core.ClassPathResource;
import com.flash.cn.core.Resource;
import com.flash.cn.util.Assert;

/**
 * 应用上下环境抽象类
 *
 * @author kay
 * @version v1.0
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    /**
     * Bean 容器
     */
    private BeanContainer container = BeanContainer.getInstance();

    /**
     * 默认构造器
     */
    public AbstractApplicationContext() {
        init();
    }

    /**
     * 初始化上下文环境
     */
    private void init() {
        Resource resource = new ClassPathResource();
        BeanDefinitionTable beanDefinitionTable = new BeanDefinitionResolution(resource);
        try {
            beanDefinitionTable = new BeanDefinitionResolution(resource);
            BeanDefinition beanDefinition = new BeanDefinitionRegistry(beanDefinitionTable, container);
            beanDefinition.refresh();  // 扫描 解析 注册 Bean Definition，初始化 Bean 容器
        }
        catch (BeanException e) {
            beanDefinitionTable.clear();  // 清空 Bean Definition 注册表
            container.clear();  // Bean 容器
        }
    }

    /**
     * 检测 Bean 是否为 Class 类
     *
     * @param name Bean 名称
     * @return 返回 Bean 是否为 Class 类
     */
    private boolean checkBean(String name) {
        return container.get(name) instanceof Class;
    }

    /**
     * 根据 Bean 名称，获取 Bean 实例
     *
     * @param name 想获取 Bean 的名称
     * @param <T>  获取容器中的 Bean 对象
     * @return bean 对象
     */
    @SuppressWarnings("unchecked")
    private <T> T loadAutowired(String name) {
        Class clazz = container.get(name);
        return (T) BeanReflect.getInstance().loadAutowired(clazz);
    }

    /**
     * 根据 Bean 名称，获取 Bean 实例
     *
     * @param name 想获取 Bean 的名称
     * @param <T>  获取容器中的 Bean 对象
     * @return bean 对象
     */
    @SuppressWarnings("unchecked")
    private <T> T getInstance(String name) {
        return container.get(name);
    }

    /**
     * 根据 Bean 名称，获取 Bean 实例
     *
     * @param name 想获取 Bean 的名称
     * @param <T>  获取容器中的 Bean 对象
     * @return bean 对象
     */
    @SuppressWarnings("unchecked")
    protected <T> T get(String name) {
        Assert.isNotEmpty(name);
        return (T) (checkBean(name) ? loadAutowired(name) : getInstance(name));
    }
}
