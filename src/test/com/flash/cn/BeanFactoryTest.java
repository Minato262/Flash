package com.flash.cn;

import com.flash.cn.beans.ApplicationContextFactory;
import com.flash.cn.beans.BeanFactory;
import com.flash.cn.core.factory.People;
import org.junit.Test;

/**
 * {@link ApplicationContextFactory} test.
 *
 * @author kay
 * @version v1.0
 */
public class BeanFactoryTest extends BeforeTest {

    @Test
    public void test() {
        People people = factory.getBean("2");
        assert people != null;
        System.out.println(people);
        System.out.println(people.getAge());
        System.out.println(people.getName());

        people.setAge(1);
        people.setName("name");

        People people1 = factory.getBean("2");
        assert people1 != null;
        System.out.println(people1);
        System.out.println(people1.getAge());
        System.out.println(people1.getName());
    }

    @Test
    public void test1() {
        BeanFactory factory = new ApplicationContextFactory();
        People people = factory.getBean("2");
        System.out.println(people);
        System.out.println(people.getAge());
        System.out.println(people.getName());

        people.setAge(2);

        BeanFactory factory1 = new ApplicationContextFactory();
        People people1 = factory1.getBean("2");
        System.out.println(people1);
        System.out.println(people1.getAge());
        System.out.println(people1.getName());
    }
}
