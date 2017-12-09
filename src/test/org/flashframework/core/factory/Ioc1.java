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
package org.flashframework.core.factory;

import org.flashframework.beans.annotation.Autowired;
import org.flashframework.beans.annotation.Scope;
import org.flashframework.beans.annotation.Service;
import org.flashframework.beans.container.BeanContainerMode;

/**
 * @author kay
 * @version v1.0
 */
@Service("ioc1")
@Scope(BeanContainerMode.PROTOTYPE)
public class Ioc1 {

    @Autowired
    private Father father;

    @Autowired
    private People people;

    @Autowired
    private User user;

    @Autowired
    private Ioc ioc;

    public Father getFather() {
        return father;
    }

    public People getPeople() {
        return people;
    }

    public User getUser() {
        return user;
    }

    public Ioc getIoc() {
        return ioc;
    }
}
