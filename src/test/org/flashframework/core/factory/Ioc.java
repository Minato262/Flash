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

import org.flashframework.annotation.Autowired;
import org.flashframework.annotation.Repository;
import org.flashframework.annotation.Scope;

/**
 * @author kay
 * @version v1.0
 */
@Repository("ioc")
@Scope
public class Ioc {

    @Autowired
    private Father father;

    @Autowired
    private People people;

    @Autowired
    private User user;

    public Father getFather() {
        return father;
    }

    public People getPeople() {
        return people;
    }

    public User getUser() {
        return user;
    }
}
