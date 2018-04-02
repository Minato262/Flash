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
package org.flashframework.beans.factory;

import org.flashframework.beans.BeanRuntimeException;

/**
 * Bean Definition 冲突异常, 用于标示 Bean Definition 在内部解析中的抛出
 * 的异常
 *
 * @author kay
 * @version v1.0
 */
class BeanDefinitionConflictException extends BeanRuntimeException {

    /**
     * 默认的构造器
     */
    BeanDefinitionConflictException() {
        super();
    }

    /**
     * 带有错误信息的构造器
     *
     * @param message 错误信息
     */
    BeanDefinitionConflictException(String message) {
        super(message);
    }
}
