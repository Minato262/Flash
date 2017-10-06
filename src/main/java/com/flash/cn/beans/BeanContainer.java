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
package com.flash.cn.beans;

import com.flash.cn.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 核心容器
 * <p>
 * 容器是用来管理对象的生命周期的。在框架中，定义好的对象的名称，如何产生对象（单例模式或者原型
 * 模式），对象与对象之间的关系，使用容器来存储她们，是一种直接有效的方式。当容器启动后，所有的
 * 对象都可以直接取用，不需要进行硬编码，也不需要重新确立对象与对象之间的关系。
 * </p>
 *
 * @author kay
 * @version v1.0
 */
public final class BeanContainer {

    /*
     * 概况
     *
     * 在 Java 的框架体系中, JavaBean 被看成是所有应用的的基石, 主要原因在于她的灵活轻便，以及
     * 能够将变化点封装成代码块，从而简化大型开发的特点。但用 JavaBean 作为基本的组件模型。却是
     * 来自于常年编程经验的积累，她最初是用于代替的 EJB 的。
     *
     * EJB 是 SUN 制定的一种组件模型，其作用是将可以重复使用的代码打包，从而达到简化开发的作用。
     * 在早期的 JDK 中，他是作为 JavaEE 标准的一部分，写入 JDK 的。事实上在 JDK6 的 javax
     * .ejb 中仍然有其最新的改进版，她为 PC 控件提供支持。但在 servlet，jsp 访问，JDBC，applet
     * 等等方面 EJB 被评价为过于笨重。主要是因为 EJB 是为企业级应用而设计的，企业级应用往往会伴
     * 随分布式，事务，高并发等等场景，以至于对于中小型应用或者其他一些场景而言，EJB 就有些设计过
     * 度了。
     *
     * 于是，就有了 JavaBean 代替 EJB 的运用。事实上 EJB 就是基于 JavaBean 设计的，她们本身就
     * 有很多基本相同之处：都是用一组特性创建，以执行其特定任务的对象或组件。只是 JavaBean 可以从
     * 服务器容器中获得其它特性。这也使得 JavaBean 相较于 EJB 更为灵活，更加轻便，她的行为可以根
     * 据特定任务以及所处环境的不同而有所不同。这也是她最大的优势。而她本身也成为 JavaEE 的一个标
     * 准。虽然早就在 JDK 的 java.beans 已经有了许多相关支持了。
     *
     * 但简单的 JavaBean 直接构成应用，也是有问题的。因为从用户的角度去看 JavaBean，他与可重用
     * 应用程序的代码块，共同构建了组件，而一个组件和一个开关就可以构成一个小程序。但开发中，你必须
     * 分别创建对象，并且它们需要一起使用或者在不同的应用程序或情况下，不同的组件产生不同的组合来使
     * 用。
     *
     * 所有，这里就有了一个 Bean 的概念，作为对软件组件模型的补充，简单来说，就是在 Bean 使用的过
     * 程中，她的一些属性对其他 Bean 是可见或者操作的，简单来说，Bean 就是从用户角度来看待组件。
     * 理论上讲，任何一个Java 类或者多个类都可以构成一个 Bean。
     *
     * 所以，从严格意义来讲，现在的 Java 的框架体系，很多都是对 Bean 的编程，而不是简单的对软件组件。
     *
     */

    /* ------------------------------ 常量区  ——————------------------------- */

    /** 容器模式（根据配置获取容器模式，单例或者原型模式）*/
    private static final BeanContainerMode CONTAINER_MODES = BeanContainerMode.getContainerModes();

    /* ------------------------------ 静态区  ——————------------------------- */

    /** Bean 容器中的 map，Bean 资源主要存放在这个 map 中 */
    private static Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    /** Bean 容器的静态对象，用于存储有注解的类的相关信息 */
    private static BeanContainer instance = new BeanContainer();

    /**
     * 获取 Bean 核心容器对象，单例模式下获取的是静态对象，原型模式下新建对象
     *
     * @return Bean 容器对象
     */
    public static BeanContainer getInstance() {
        if (CONTAINER_MODES.isSingleton()) {
            synchronized (BeanContainer.class) {
                return instance;
            }
        }
        return new BeanContainer();
    }

    /* ------------------------------ 方法区  ——————------------------------- */

    /** Bean Definition 注册 */
    private BeanDefinition registry = new BeanDefinitionRegistry();

    /**
     * 默认构造器，初始化容器
     */
    private BeanContainer() {
        if (container.isEmpty()) {
            registry.registry(container);     // 注册容器中的所有 Bean 实例
        }
    }

    /**
     * 根据 key 获取容器中的对象
     *
     * @param key 容器关键字(一定不能为空)
     * @param <T> 弱类型转换成强类型
     * @return 返回容器中的对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key) {
        Assert.isNotEmpty(key);
        if (CONTAINER_MODES.isSingleton()) {
            registry.registry(container, key);    // 注册容器中载入新的 Bean 实例
        }
        return (T) container.get(key);
    }
}
