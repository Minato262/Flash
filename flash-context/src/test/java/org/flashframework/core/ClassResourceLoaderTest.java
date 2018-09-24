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
package org.flashframework.core;

import org.flashframework.context.factory.ApplicationContextFactory;
import org.flashframework.beans.factory.BeanFactory;
import org.flashframework.core.factory.Bean;
import org.flashframework.core.resource.ClassResourceLoader;
import org.flashframework.core.util.Assert;
import org.junit.Test;

import java.util.List;

/**
 * {@link ClassResourceLoader} Test.
 *
 * @author kay
 * @version v1.0
 */
public class ClassResourceLoaderTest extends BeforeTest {

    @Test
    public void test() {
        ClassResourceLoader classPathResource = new ClassResourceLoader();
        List<Class<?>> classes = classPathResource.getClasses();
        BeanFactory factory = new ApplicationContextFactory();
        for (Class<?> clazz : classes) {
            Bean people = (Bean) factory.getBean(clazz.getName());
        }
    }
}
