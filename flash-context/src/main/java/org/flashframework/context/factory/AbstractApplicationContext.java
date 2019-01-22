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
package org.flashframework.context.factory;

import lombok.extern.slf4j.Slf4j;
import org.flashframework.beans.BeanRuntimeException;
import org.flashframework.beans.Registry;
import org.flashframework.beans.Resolution;
import org.flashframework.beans.container.BeanContainer;
import org.flashframework.beans.container.BeanContainerAware;
import org.flashframework.beans.container.BeanContainerInitFailureException;
import org.flashframework.beans.factory.BeanDefinitionRegistry;
import org.flashframework.beans.factory.BeanDefinitionResolution;
import org.flashframework.beans.handle.Handler;
import org.flashframework.beans.handle.HandlerChain;
import org.flashframework.beans.util.BeanCreateFailureException;
import org.flashframework.context.ApplicationContext;
import org.flashframework.core.io.ClassResourceLoader;
import org.flashframework.core.io.Resource;
import org.flashframework.core.logger.Configurator;

import java.security.InvalidParameterException;

/**
 * 应用上下环境抽象类
 * <p>用于初始化上下文，开启容器</p>
 *
 * @author kay
 * @version v1.0
 */
@Slf4j
abstract class AbstractApplicationContext implements ApplicationContext {

    /*
     * 概述：
     *
     *      这个抽象类的设计目的主要是：初始化 Bean 容器，设置上下文环境，为整个应用提供 IOC 支持。
     * 其中，初始化容器的过程最为重要。它包括 刷新容器，然后扫描，解析，注册 Bean Definition。
     *
     *      最后 ，载入类与方法注解。整个过程就初试化完成。
     */

    /**
     * Bean 容器
     */
    private BeanContainer container = BeanContainerAware.getInstance();

    private Handler handle = getHandle();

    private HandlerChain chain = HandlerChain.getInstance();

    /**
     * 默认构造器
     */
    AbstractApplicationContext() {
        if (container.isEmpty()) {
            // 初始化 配置
            Configurator.init();

            // 载入拦截器设置
            loadHandle();

            // 初始化
            init();
        }
    }

    private void loadHandle() {
        chain.setHandle(handle);
    }

    /**
     * BeanDefinition 工厂，用于载入 BeanDefinition 到 BeanDefinition 注册表中
     *
     * @return BeanDefinition 工厂
     */
    protected abstract Handler getHandle();

    /**
     * 初始化上下文环境及容器
     *
     * @throws BeanContainerInitFailureException 如果 Bean 容器初始化失败
     */
    private void init() {
        Resource resource = new ClassResourceLoader();
        Resolution resolution = new BeanDefinitionResolution(resource);
        try {
            log.info("container initiate start");
            Registry beanDefinition = new BeanDefinitionRegistry(resolution);
            // 刷新，扫描 解析 注册 Bean Definition，初始化 Bean 容器
            beanDefinition.refresh();
            log.info("container initiate end");
        }
        catch (BeanRuntimeException e) {
            // 清空 Bean Definition 注册表
            resolution.clear();
            // 清空 Bean 容器
            container.clear();
            log.error("container initiate error", e);
            throw new BeanContainerInitFailureException(e);
        }
    }

    /**
     * 获取 Bean 容器
     *
     * @return Bean 容器
     */
    protected BeanContainer getBeanContainer() {
        return container;
    }

    /**
     * 根据 Bean 名称，获取 Bean 实例
     *
     * @param name 想获取 Bean 的名称（一定不能为空）
     * @return 获取 bean 对象
     * @throws BeanContainerInitFailureException 如果 Bean容器初始化失败
     */
    @Override
    public Object getBean(String name) {
        throw new BeanContainerInitFailureException();
    }

    /**
     * 根据 Bean 的信息获取 Bean 实例对象
     *
     * @param clazz 想获取 Bean 的信息（一定不能为null）
     * @return 获取 bean 实例对象
     * @throws InvalidParameterException  如果对象信息为 null
     * @throws BeanCreateFailureException 如果对象新建失败
     */
    @Override
    public <T> T getBean(Class<T> clazz) {
        throw new BeanContainerInitFailureException();
    }
}
