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
package org.flashframework.beans.util;

import org.flashframework.beans.BeanRuntimeException;

/**
 * Bean 新建对象失败异常
 *
 * @author kay
 * @version v1.0
 */
public class BeanCreateFailureException extends BeanRuntimeException {
    private static final long serialVersionUID = -1978739301158111034L;

    /**
     * 带有错误信息的构造器
     *
     * @param message 错误信息
     */
    public BeanCreateFailureException(String message) {
        super(message);
    }

    /**
     * 带有堆栈异常信息的构造器
     *
     * @param cause 堆栈异常信息
     */
    public BeanCreateFailureException(Throwable cause) {
        super(cause);
    }
}
