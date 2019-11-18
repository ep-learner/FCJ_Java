# 目的
这里主要介绍一些JAVA的基础内容，包括String（StringBuffer），JAVA的IO，以及JAVA核心技术设计到的点，整理之后会上传到这里。

# 内部类

- 内部类（inner）可以访问外部（outer）类，外部类需要通过创建一个内部类对象才能访问内部类；
- 内部类的修饰符限制了对**外部类**成员的访问权限
  - 静态内部类
    - 只能访问**外部类的静态成员**
  - 非静态内部类
    - 可以访问**外部类**的**所有成员**

# 静态修饰符

- 可以用类名.方法名来调用，**也可以通过实例.方法名来调!!!**
- 静态方法没法**直接**访问非静态成员
- 非静态方法却可以直接访问静态/非静态成员

# this/super

this和super都是指代一个实例，访问权限也仅仅是非静态的对象

# 反射

1、通过**字符串**获取**字节码**

```java
Class<?> c = Class.forName("reflect.people");
```

2、获取构建函数

```java
Constructor<?> con = c.getConstructor(String.class);
```

3、创建对象

```java
people obj = (people)con.newInstance("构造函数的初始化参数");
```

4、获取方法

method.invoke()

```java
Method eat = c.getDeclaredMethod("eat", String.class);
eat.invoke(obj,"meat");//使用obj对象，调用方法"eat"，方法的参数为"meat"
```

5、获取域;通过**变量名称**获取

```java
Field name = c.getDeclaredField("name");
```

# 动态代理

借助一个**InvocationHandler接口**；和**类Proxy的newProxyInstance方法**完成动态代理。



**Step1、准备**

接口A 

实现接口A的方法B，B就是被代理对象所属的类。

```
interface A

class B  implements A
```



**Step2、动态代理类**

```java
class C  implements InvocationHandler{
    @Override inovke(){

    }
}
```

在invoke可以写:

- 你希望调用的基类的**哪些方法**，

- 在调用这些方法之**前**你需要**加**些什么

- 处理之后你又想加些什么



**Step3、主函数**（调用动态代理类）

```java
objB = new B();//被代理对象
objC = new C(objB);//代理类的实例

objproxy = (objB)Proxy.newProxyInstance(objC.Loader,objB.Interface,objC);
//第一个参数：getClassLoader     经纪人
//第二个参数：getInterfaces	   被代理对象的接口
//第三个参数：InvocationHandler  经纪人对被代理对象的改进，继承了这个接口

objproxy.B_func1();//直接调用B的方法名称得到增强过后的结果
```



总结：

- 类B是被代理对象，它的方法有缺陷我们需要给他的方法增加一些功能

- 代理类C1，C2

  类C1是类B的代理类（经纪人1），它觉得类B的某个方法需要增加内容C1

  类C2是类B的代理类（经纪人2），它觉得C1不行，类B的某个方法就应该增加内容C2

- 调用的时候，我不要你觉得，我要我觉得！！

  通过Proxy.newProxyInstance决定使用哪个代理类（经纪人）的修改，创建一个最终的实例。这个实例使用起来和直接使用类B的实例一模一样，不过会采用某个代理类给他添加的内容。

# String



```java
String str = "testforStringDemo";
//一般的需求是把字符串当成字符数组使用，更多的API参考onenote笔记
char[] chars = str.toCharArray();
char c = str.charAt(0);
```



# 多线程





# 预期补充内容

API 相关

- [ ] StringBuffer
- [x] 反射
- [x] 代理
- [ ] 线程

文档

- [ ] 参数传递，引用
- [ ] 数组拷贝
- [ ] String.clear的误区
- [ ] 关于Protect的理解



