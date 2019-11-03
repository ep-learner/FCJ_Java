package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private Object sub;
    public DynamicProxy(Object sub){
        this.sub = sub;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法之前，我先添加一些功能");
        method.invoke(sub,args);//注意我们想调用的是真实对象sub的方法method。
        System.out.println("调用方法之后，我继续添加一些功能");
        return null;
    }
}
