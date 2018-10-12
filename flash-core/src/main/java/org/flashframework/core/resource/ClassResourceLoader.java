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
import org.flashframework.core.util.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 根据路径，获取 Class 资源
 *
 * @author kay
 * @version v1.0
 */
public class ClassResourceLoader extends AbstractClassResource implements Resource {

    private static final Logger log = LoggerFactory.getLogger(ClassResourceLoader.class);

    /** 文件常量——名称 */
    private static final String FILE_NAME = "file";

    /** 文件常量——后缀 */
    private static final String FILE_CLASS = FILE_DOT + "class";

    /**
     * 扫描并获取到 Class 资源列表，暂不支持扫描 jar 包
     *
     * @param urlElements url 元素
     * @param packageName 相对路径包名（不能为空）
     * @return Class 资源列表
     */
    @Override
    protected List<Class<?>> getClasses(Enumeration<URL> urlElements, String packageName) {
        log.info("{}, load class start", packageName);
        List<Class<?>> classes = new ArrayList<>();
        while (urlElements.hasMoreElements()) {
            URL url = urlElements.nextElement();
            String protocol = url.getProtocol();

            // 暂时不支持扫描 jar 包
            if (FILE_NAME.equals(protocol)) {
                String filePath = Decoder.decode(url.getFile());
                loadClasses(packageName, filePath, classes);
            }
        }
        log.info("{}, load class end", packageName);
        return classes;
    }

    /**
     * 递归扫描，全部 Class 的资源列表
     *
     * @param packageName 相对路径包名称
     * @param packagePath 相对包路径
     * @param classes     Class 资源清单
     */
    private void loadClasses(String packageName, String packagePath, List<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles(file -> file.isDirectory() || file.getName().endsWith(FILE_CLASS));
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                loadClasses(packageName + FILE_DOT + file.getName(), file.getAbsolutePath(), classes);
                continue;
            }

            final String className = file.getName().substring(0, (file.getName().length() - FILE_CLASS.length()));
            final String name = packageName + FILE_DOT + className;
            Class<?> clazz = loadClass(name);
            classes.add(clazz);

            if(log.isDebugEnabled()) {
                log.debug("load Class，name={}", name);
            }
        }
    }

    /**
     * 根据类的名称，载入类的资源
     *
     * @param name 类的名称
     * @return 载入的 Class
     * @throws ClassResourceLoadFailureException 如果根据资源名称载入没有找到对应的类，则抛出异常
     */
    private Class<?> loadClass(String name) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(name);
        }
        catch (ClassNotFoundException e) {
            throw new ClassResourceLoadFailureException();
        }
    }
}
