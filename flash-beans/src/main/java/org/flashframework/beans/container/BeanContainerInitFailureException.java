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
package org.flashframework.beans.container;

import org.flashframework.beans.BeanRuntimeException;

/**
 * Bean 容器初始化失败异常
 *
 * @author kay
 * @version v1.0
 */
public class BeanContainerInitFailureException extends BeanRuntimeException {

    private static final long serialVersionUID = 1;

    /**
     * 默认构造器
     */
    public BeanContainerInitFailureException() {
        super();
    }

    /**
     * 带有错误信息的构造器
     *
     * @param message 错误信息
     */
    public BeanContainerInitFailureException(String message) {
        super(message);
    }

    /**
     * 带有堆栈异常信息的构造器
     *
     * @param cause 堆栈异常信息
     */
    public BeanContainerInitFailureException(Throwable cause) {
        super(cause);
    }
}
