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
package org.flashframework.core.resource;

import org.flashframework.core.Resource;
import org.flashframework.core.properties.FlashProperties;
import org.flashframework.core.logger.Configurator;
import org.flashframework.core.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * Class 资源载入类
 *
 * @author kay
 * @version v1.0
 */
public abstract class AbstractClassResource implements Resource {

    /** 根据配置获取配置的包名 */
    private static final String FLASH_PACKAGE_NAME = FlashProperties.INSTANCE_FLASH.load("packageName");

    /** 文件常用字节——斜杠 */
    private static final char FILE_SLASH = '/';

    /** 文件常用字节——点 */
    static final char FILE_DOT = '.';

    /**
     * 获取所有当前包内 Class 类的列表
     *
     * @return 扫描的 Class 类列表
     * @throws ClassResourceRuntimeException 如果没有在配置项中配置包路径
     */
    @Override
    public List<Class<?>> getClasses() {
        String packageDirName = FLASH_PACKAGE_NAME.replace(FILE_DOT, FILE_SLASH);
        if (StringUtils.isEmpty(packageDirName)) {
            throw new ClassResourceRuntimeException("No package path is configured in the configuration item!");
        }

        Configurator.init();

        Enumeration<URL> dirs = getEnumeration(packageDirName);
        return getClasses(dirs, FLASH_PACKAGE_NAME);
    }

    /**
     * 根据来源获取，目标 URL 资源
     *
     * @param name 资源名称
     * @return URL 元素资源
     * @throws ClassResourceRuntimeException 如果 I/O 出现异常
     */
    private Enumeration<URL> getEnumeration(String name) {
        try {
            return Thread.currentThread().getContextClassLoader().getResources(name);
        }
        catch (IOException e) {
            throw new ClassResourceRuntimeException(e);
        }
    }

    /**
     * 根据 URL元素和包名，获取所有当前包内 Class 类的列表
     *
     * @param urlElements url 元素
     * @param packageName 包名
     * @return Class 类清单
     */
    protected abstract List<Class<?>> getClasses(Enumeration<URL> urlElements, String packageName);

}
