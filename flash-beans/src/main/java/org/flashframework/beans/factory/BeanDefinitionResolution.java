/*
 * Copyright 2017-2018 the original author or authors.
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
package org.flashframework.beans.factory;

import org.flashframework.beans.Resolution;
import org.flashframework.core.Resource;
import org.flashframework.util.Assert;

import java.util.List;

/**
 * Bean Definition 的解析
 *
 * @author kay
 * @version v1.0
 */
public class BeanDefinitionResolution extends BeanDefinitionTableContext implements Resolution {

    /** 资源解析接口 */
    private Resource resource;

    /** BeanDefinition 载入接口 */
    private BeanDefinitionFactory load;

    /**
     * 带有资源解析的 Bean Definition 解析的构造器
     *
     * @param resource 资源解析接口（不能为null）
     * @param load BeanDefinition 载入接口（不能为null）
     * @throws IllegalArgumentException 如果资源解析接口为null
     */
    public BeanDefinitionResolution(Resource resource, BeanDefinitionFactory load) {
        Assert.isNotNull(load);
        Assert.isNotNull(resource);
        this.load = load;
        this.resource = resource;
    }

    /**
     * 遍历 Class，载入类注释并将对象放入容器的 value 中
     *
     * @throws BeanDefinitionConflictException 如果 Bean Definition 已经存在
     */
    private void loadRepository() {
        List<Class<?>> list = resource.getClasses();
        for (Class clazz : list) {
            load.load(clazz);
        }
    }

    /**
     * 默认注册 Bean，注解标记的 bean 默认为单例模式，容器初始化时会一次性载入所
     * 有 Bean
     *
     * @throws BeanDefinitionConflictException 如果 Bean Definition 对象已经存在
     */
    @Override
    public void load() {
        clear();   // 清理和初始化注册表
        loadRepository();   // 载入类注释
    }

    /**
     * 清理注册表
     */
    @Override
    public void clear() {
        super.clear();
    }
}
