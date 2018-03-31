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
package org.flashframework.beans;

import org.flashframework.beans.annotation.Controller;
import org.flashframework.beans.annotation.Repository;
import org.flashframework.beans.annotation.Resource;
import org.flashframework.beans.annotation.Service;
import org.flashframework.beans.factory.BeanDefinitionFactory;
import org.flashframework.util.Assert;

/**
 * BeanDefinition 默认工厂
 *
 * @author kay
 * @version v2.0
 */
public class DefaultDefinitionFactory extends BeanDefinitionFactory {

    /**
     * 根据 Class，载入类注解信息
     *
     * @param clazz class 信息
     * @throws IllegalArgumentException 如果字符串为null
     */
    @Override
    public void load(Class clazz) {
        Assert.isNotNull(clazz);
        Repository annotation = (Repository) clazz.getAnnotation(Repository.class);
        if (annotation != null) {
            put(annotation.value(), clazz);
            return;
        }
        Service annotation1 = (Service) clazz.getAnnotation(Service.class);
        if (annotation1 != null) {
            put(annotation1.value(), clazz);
            return;
        }
        Controller annotation2 = (Controller) clazz.getAnnotation(Controller.class);
        if (annotation2 != null) {
            put(clazz);
            return;
        }

        Resource annotation3 = (Resource) clazz.getAnnotation(Resource.class);
        if (annotation3 != null) {
            put(annotation3.value(), clazz);
        }
    }
}
