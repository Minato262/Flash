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
package org.flashframework.aop;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author kay
 * @version v1.0
 */
public class AnnotationTest {

    @Test
    public void test() {
        Annotation[] annotations = BeanTest.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println();

        Field[] fields = BeanTest.class.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations1 = field.getAnnotations();
            for (Annotation annotation : annotations1) {
                System.out.println(annotation);
            }
        }

        Method[] methods = BeanTest.class.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations1 = method.getAnnotations();
            for (Annotation annotation : annotations1) {
                System.out.println(annotation);
            }
        }
        System.out.println();
    }
}
