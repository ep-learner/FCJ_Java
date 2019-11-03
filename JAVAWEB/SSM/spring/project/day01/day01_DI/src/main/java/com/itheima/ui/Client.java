package com.itheima.ui;


import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Client {
    /**
     * 获取IoC核心容器对象，根据id获得对象
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService)ac.getBean("accountService");

        System.out.println(as);


//        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("bean.xml"));
//        IAccountDao ad2 = factory.getBean("accountDao",IAccountDao.class);
//        System.out.println(ad2);

    }
}
