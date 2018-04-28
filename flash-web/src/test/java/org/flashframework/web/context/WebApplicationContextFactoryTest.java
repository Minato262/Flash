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
package org.flashframework.web.context;

import org.flashframework.beans.factory.BeanFactory;
import org.flashframework.web.Bean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link WebApplicationContextFactory} Test.
 *
 * @author kay
 * @version v1.0
 */
public class WebApplicationContextFactoryTest {

    private BeanFactory factory;

    @Before
    public void init() {
        factory = new WebApplicationContextFactory();
        System.out.println("容器启动!");
    }

    @Test
    public void test() {
        Bean bean = (Bean) factory.getBean("bean");
        System.out.println(bean);
        Assert.assertNotNull(bean);
    }
}