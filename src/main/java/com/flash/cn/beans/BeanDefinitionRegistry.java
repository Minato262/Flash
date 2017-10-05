package com.flash.cn.beans;

import com.flash.cn.annotation.Autowired;
import com.flash.cn.annotation.Controller;
import com.flash.cn.annotation.Repository;
import com.flash.cn.annotation.Service;
import com.flash.cn.core.ClassPathResource;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Bean Definition 注册
 *
 * @author kay
 * @version v1.0
 */
public class BeanDefinitionRegistry implements BeanDefinition {

    /**
     * put 对象到容器中
     *
     * @param container 容器
     * @param key       容器关键字
     * @param name      新建对象路径
     * @param isCheck   是否需要检测 bean 是否冲突
     * @throw BeanCreateFailureException Bean 已经存在
     */
    private void put(Map<String, Object> container, String key, String name, boolean isCheck) {
        if (container.containsKey(key) && isCheck) {
            throw new BeanCreateFailureException("Bean 已经存在");
        }
        Object object = BeanReflect.newInstance(name);
        container.put(key, object);
    }

    /**
     * 遍历 Class，载入类注释
     *
     * @param container 容器
     * @throw BeanCreateFailureException Bean 初始化加载异常
     */
    private void loadRepository(Map<String, Object> container) {
        ClassPathResource resource = new ClassPathResource();
        List<Class<?>> list = resource.getClasses();
        for (Class clazz : list) {
            Repository annotation = (Repository) clazz.getAnnotation(Repository.class);
            if (annotation != null) {
                put(container, annotation.value(), clazz.getName(), true);
                continue;
            }
            Service annotation1 = (Service) clazz.getAnnotation(Service.class);
            if (annotation1 != null) {
                put(container, annotation1.value(), clazz.getName(), true);
                continue;
            }
            Controller annotation2 = (Controller) clazz.getAnnotation(Controller.class);
            if (annotation2 != null) {
                put(container, annotation2.value(), clazz.getName(), true);
            }
        }
    }

    /**
     * 根据容器 key，载入方法注释
     *
     * @param container 容器
     * @param key       容器中的key
     * @throw BeanCreateFailureException Bean 设置失败
     */
    private BeanDefinitionWrap loadAutowired(Map<String, Object> container, String key) {
        boolean hasAutowired = false;
        Object object = container.get(key);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                try {
                    field.set(object, container.get(field.getName()));
                }
                catch (IllegalAccessException e) {
                    throw new BeanCreateFailureException(e);
                }
                hasAutowired = true;
            }
        }
        return new BeanDefinitionWrap(hasAutowired, object);
    }

    /**
     * 遍历容器，载入方法注释
     *
     * @param container 容器
     * @throw BeanCreateFailureException Bean 设置失败
     */
    private void loadAutowired(Map<String, Object> container) {
        for (Map.Entry<String, Object> entry : container.entrySet()) {
            BeanDefinitionWrap warp = loadAutowired(container, entry.getKey());
            if (warp.isHasAutowired()) {
                container.put(entry.getKey(), warp.getObject());
            }
        }
    }

    /**
     * 注册 Bean.
     *
     * @param container 容器
     * @throw BeanCreateFailureException Bean 初始化加载异常
     */
    @Override
    public void registry(Map<String, Object> container) {
        loadRepository(container);
        loadAutowired(container);
    }

    /**
     * 注册 Bean.
     *
     * @param container 容器
     * @param key       容器中的关键字
     * @throw BeanCreateFailureException Bean 初始化加载异常
     */
    @Override
    public void registry(Map<String, Object> container, String key) {
        String name = container.get(key).getClass().getName();
        put(container, key, name, false);
        loadAutowired(container, key);
    }
}
