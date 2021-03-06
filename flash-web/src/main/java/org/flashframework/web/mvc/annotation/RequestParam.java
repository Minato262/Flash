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
package org.flashframework.web.mvc.annotation;

import java.lang.annotation.*;

/**
 * {@code RequestParam} 注解配合 {@code RequestMapping} 注解一起使用，可以将请求
 * 的参数同处理方法的参数绑定在一起。
 *
 * @author kay
 * @version v2.0
 * @see RequestMapping
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestParam {

    /**
     * 设置请求参数
     *
     * @return 请求参数 默认为空
     */
    String value() default "";

    /**
     * 是否是必要请求参数
     *
     * @return 是否是请求参数
     */
    boolean request() default false;
}
