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

import org.flashframework.beans.util.BeanCreateFailureException;

import java.security.InvalidParameterException;

/**
 * Bean 工厂接口，IOC 中最核心的接口
 *
 * @author kay
 * @version v1.0
 */
public interface BeanFactory {

    /**
     * 根据 Bean 的名称获取 Bean 实例对象
     *
     * @param name 想获取 Bean 的名称（一定不能为空）
     * @return 获取 bean 实例对象
     */
    Object getBean(String name);

    /**
     * 根据 Bean 的信息获取 Bean 实例对象
     *
     * @param clazz 想获取 Bean 的信息（一定不能为null）
     * @return 获取 bean 实例对象
     * @throws InvalidParameterException  如果对象信息为null
     * @throws BeanCreateFailureException 如果对象新建失败
     */
    <T> Object getBean(Class<T> clazz);
}
