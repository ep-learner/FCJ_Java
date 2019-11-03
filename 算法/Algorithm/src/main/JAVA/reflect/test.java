package reflect;

import java.lang.reflect.Proxy;

public class test {
    public static void main(String[] args) throws InterruptedException {
        try {
            Subject s = new RealSubject();//真实对象
            DynamicProxy dp = new DynamicProxy(s);
            Subject obj = (Subject)Proxy.newProxyInstance(dp.getClass().getClassLoader(), s.getClass().getInterfaces(), dp);
            //强转之后obj自然有Subject的方法；但是为什么可以强转？
            //第二个参数提供了Subject的接口，生成的动态代理对象会去实现这个接口，也就是把这里面的方法rent hello都实现一遍，当然就可以强转了
            obj.rent();
            //这里会去访问DynamicProxy的invoke方法；
            //invoke方法里面通过反射去调用RealSubject的rent
            obj.hello("Proxy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}