/*
 * Copyright 2017-2019 the original author or authors.
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

import lombok.extern.slf4j.Slf4j;
import org.flashframework.beans.BeanNotFindException;
import org.flashframework.core.Aware;
import org.flashframework.core.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean Definition 注册表辅助类，单例模式。
 *
 * @author kay
 * @version v1.0
 */
@Slf4j
public final class BeanDefinitionTableAware extends ConcurrentHashMap<String, Class> implements BeanDefinitionTable, Aware {
    private static final long serialVersionUID = 4882416802842440014L;

    /*
     * 概述：
     */

    /**
     * Bean Definition 注册表
     */
    private static BeanDefinitionTable table = new BeanDefinitionTableAware();

    /**
     * 获取 Bean Definition 注册表对象，单例模式下获取的是静态对象，原型模式下新建对象
     *
     * @return Bean Definition 注册表接口
     */
    public static BeanDefinitionTable getInstance() {
        return table;
    }

    /**
     * 默认构造器
     */
    private BeanDefinitionTableAware() {
        //
    }

    /**
     * 将扫描出的对象信息，放入注册表
     *
     * @param key   关键字
     * @param value 包含 Class 信息的对象值
     * @return 成功放入容器的 Class 信息
     * @throws BeanNotFindException            如果 Bean 没有发现
     * @throws BeanDefinitionConflictException 如果 Bean Definition 相冲突
     */
    @Override
    public Class put(String key, Class value) {
        if (StringUtils.isEmpty(key)) {
            throw new BeanNotFindException("The key of bean must be not empty!");
        }
        if (super.containsKey(key)) {
            throw new BeanDefinitionConflictException(key + ", Bean Definition already exists");
        }

        if (log.isDebugEnabled()) {
            log.debug("Bean Definition table put, key:{}, value:{}", key, value);
        }
        return super.put(key, value);
    }
}
