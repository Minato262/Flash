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

/**
 * Bean 容器相关模块
 *
 * <p>在 Java 的应用体系中, JavaBean 被看成是所有应用的的基石, 主要原因在于她的灵
 * 活轻便，以及能够将变化点封装成代码块，从而简化开发的特点。但用 JavaBean 作为基本
 * 的组件模型。却来自于常年编程经验的积累，她是用于代替最初的 EJB 的。
 *
 * <p>EJB 是 SUN 制定的一种组件模型，其作用是将可以重复使用的代码打包，从而达到简化
 * 开发的作用。在早期的 JDK 中，他是作为 JavaEE 标准的一部分，写入 JDK 中。事实上在
 * JDK6 的 javax.ejb 中仍然有其最新的改进版，但只是为 PC 控件提供少量支持。早年的
 * EJB 在 servlet，jsp 访问，JDBC，applet 等等方面 EJB 被评价为过于笨重。主要是
 * 因为 EJB 主要是为企业级应用而设计的，企业级应用中往往会伴随分布式，事务，高并发等
 * 场景，以至于对于中小型应用或者其他一些场景而言，EJB 就有些设计过度了。
 *
 * <p>于是，就有了 JavaBean 代替 EJB 的运用。事实上 EJB 就是基于 JavaBean 设计的
 * ，她们本身就有很多基本相同之处：都是用一组特性创建，以执行其特定任务的对象或组件。
 * 只是 JavaBean 可以从服务器的容器中获得其它特性。这也使得 JavaBean 相较于 EJB 更
 * 为灵活，更加轻便，她的行为可以根据特定任务以及所处环境的不同而有所不同。这也是她最
 * 大的优势。而她本身也成为了 JavaEE 的一个标准。虽然在 JDK 的 java.beans 中早就有
 * 许多相关支持了。
 *
 * <p>但简单的 JavaBean 直接构成应用，也是有问题的。因为从用户的角度去看 JavaBean，
 * 他与可重用应用程序的代码块，共同构建了组件，而一个组件和一个开关就可以构成一个小程序
 * 。但开发中，你必须分别创建对象，并且它们需要一起使用或者在不同的应用程序或情况下，不
 * 同的组件产生不同的组合来使用。
 *
 * <p>所有，这里就有了一个 Bean 的概念，作为对软件组件模型的补充，简单来说，就是在 Bean
 * 使用的过程中，她的一些属性对其他 Bean 是可见或者操作的，简单来说，Bean 就是从用户
 * 角度来看待组件。所有 JavaBean 可分为两种：一种是用于操作用户界面的，另一种则是用于
 * 操作事务等情况。
 *
 * <p>而为了更加简便的构成应用，就出现了一些特殊场景下，使用的专门对象。这也就是 POJO
 * 的由来。POJO 实质上是简单的实体类，private 私有变量作为属性，get 和 set 作为其访问
 * 接口。POJO 与 JavaBean 之间的主要区别在于 JavaBean 并没有严格的规范，从理论上讲，
 * 任何一个 Java 类都可以构成一个 Bean。而 POJO 用来代指那些没有遵从特定的Java对象模
 * 型,约定，框架等。理想地讲，一个POJO是一个不受任何限制的Java对象（除了Java语言规范）。
 *
 * @author kay
 * @version v1.0
 */
package org.flashframework.beans.container;