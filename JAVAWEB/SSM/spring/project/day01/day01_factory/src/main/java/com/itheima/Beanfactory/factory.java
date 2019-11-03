package com.itheima.Beanfactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class factory {
    //定义一个props对象
    private static Properties props;
    private static HashMap<String, Object> beans;
    //对于props赋值
//    static {
//        props = new Properties();
//        try {
//            props.load(factory.class.getClassLoader().getResourceAsStream("bean.properties"));
//        } catch (Exception e) {
//            throw new ExceptionInInitializerError("初始化失败！");
//        }
//    }
    static {
        props = new Properties();
        beans = new HashMap<String, Object>();
        try {
            props.load(factory.class.getClassLoader().getResourceAsStream("bean.properties"));

            Iterator<Object> iter = props.keySet().iterator();
            while (iter.hasNext()){
                String key = (String)iter.next();
                String val = props.getProperty(key);
                Object obj = Class.forName(val).newInstance();
                beans.put(key,obj);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化失败！");
        }
    }
    public Object getBean(String beanname){
        return beans.get(beanname);
    }
    //根据bean名称获取对象
//    public Object getBean(String beanname){
//        Object bean =null;
//        try {
//            bean = Class.forName(props.getProperty(beanname)).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }
}
