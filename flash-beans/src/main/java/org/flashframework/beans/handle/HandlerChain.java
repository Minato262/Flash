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
package org.flashframework.beans.handle;

import org.flashframework.core.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kay
 * @version v2.0
 */
public class HandlerChain {

    private List<Handler> list = new ArrayList<>();

    private static final HandlerChain chain = new HandlerChain();

    public static HandlerChain getInstance() {
        return chain;
    }

    public void setHandle(Handler handle) {
        Assert.isNotNull(handle);
        list.add(handle);
    }

    public void load(Class clazz) {
        Assert.isNotNull(clazz);
        for (Handler handle : list) {
            handle.load(clazz);
        }
    }
}
