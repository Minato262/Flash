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

import com.flash.cn.beans.BeanContainer;

/**
 * Application Context Factory.
 *
 * @author kay
 * @version v1.0
 */
public class ApplicationContextFactory implements ApplicationContext {

    /** Bean 容器 */
    private BeanContainer container = BeanContainer.getInstance();

    /**
     * 默认构造器
     */
    public ApplicationContextFactory() {
        //
    }

    /**
     * 获取 Bean
     *
     * @param name 想获取 Bean 的名称
     * @param <T>  获取容器中的 Bean 对象
     * @return bean 对象
     */
    @Override
    public <T> T getBean(String name) {
        return container.getValue(name);
    }
}
