package com.flash.cn.core;

import com.flash.cn.BeforeTest;
import com.flash.cn.annotation.Autowired;
import com.flash.cn.beans.BeanContainer;
import com.flash.cn.core.factory.Ioc;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * {@link BeanContainer} Test.
 *
 * @author kay
 * @version v1.0
 */
public class BeanContainerTest extends BeforeTest {

    @Test
    public void test() {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            BeanContainer container = BeanContainer.getInstance();
            System.out.println(container.getValue("ioc"));
        }
    }

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        BeanContainer container = BeanContainer.getInstance();
        Ioc ioc = container.getValue("ioc");
        System.out.println(ioc);
        System.out.println(ioc.getFather());
        System.out.println(ioc.getPeople());
        System.out.println(ioc.getUser());
    }

    @Test
    public void test2() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource();
        List<Class<?>> classes = classPathResource.getClasses();
        for (Class<?> clazz : classes) {
            Field[] fields = Class.forName(clazz.getName()).getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Autowired hasAnnotation = field.getAnnotation(Autowired.class);
                if (hasAnnotation != null) {
                    Object obj = Class.forName(clazz.getName()).newInstance();
                    System.out.println(field.getName());
                    System.out.println(clazz);
                    System.out.println(field.get(obj));
                    field.set(obj, factory.getBean(field.getName()));
                    System.out.println(field.get(obj));
                }
            }
        }
    }
}


