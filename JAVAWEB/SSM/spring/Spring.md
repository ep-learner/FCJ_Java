# Spring

## 耦合和解耦

### 示例

- 驱动类加载1.0：

```java
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
```

这个类依赖于**其他jar包**即mysql，也就是对于这个程序**单独编译**会报错，这样该类的独立性就很差，无法独立运行。

- 驱动类加载2.0：

```java
Class.foeName("com.mysql.jdbc.Driver");
```

同样的功能，通过反射，依赖的仅是字符串，就算没有jar包，**编译**也不会出错，当然没有jar包的话还是运行不了的。

但是这种方式也有自己的问题，比如，我如果想换一个Oracle数据库怎么做？通过配置文件来配置。

- 驱动类加载3.0

通过配置文件来配置

### 总结

- **减少new**的使用，编译时不依赖
- 通过**配置文件**访问

## 工厂模式

- dao：复用的部分
- service：业务逻辑
- ui：WEB界面 调用service

- Bean工厂

  Bean：理解为一个可重用组件

### 多例模式

**Beanfactory.factory.java**

1、获取**Properties**对象（需要一个**运行时的URI**）

```java
//Step1、定义一个props对象
private static Properties props;
//Step2、对于props赋值
static {
    props = new Properties();
    try {
        //这里不能直接new inputstream，因为运行路径不好确定，所以获取factory类的运行路径
        //但是props想加载的不是这个类，所以通过类的加载器的资源路径找到配置文件
       props.load(factory.class.getClassLoader().getResourceAsStream("bean.properties"));
    } catch (Exception e) {
        throw new ExceptionInInitializerError("初始化失败！");
        //假如这里出错意味着配置文件出错，后面自然也运行不了，这个异常得抛出去终止程序
    }
}
```

核心在于:**通过类加载器访问资源路径**，获得真实的运行路径



2、**getBean**

功能：通过bean的名称来获取一个Object实例。

```java
public Object getBean(String beanname){
    Object bean =null;
    try {
        bean = Class.forName(props.getProperty(beanname)).newInstance();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return bean;
}
```

通过反射得到一个实例：

- String path = props.getProperty(beanname);
- bean = Class.forName(path).newInstance();



3、小结

还是Class.forName().newInstance或者实例，但是**参数**是借助**Properties对象**从**配置文件**中获得的。

- Step1  加载

  `props.load(config_URI);`

- Step2  获取实例

  `val = props.getProperty(key)`

  `bean = Class.forName(val).newInstance();`



### 单例模式

不算缺陷，但是这种方式只能是多例模式，每次都会new一个对象。如何优化？

- static在类加载的时候**只调用一次**

  实例化完了之后统统放入一个**容器**中，所有来访问的线程拿到的都是同一个实例

- 容器用**HashMap**来存

  因为之后得根据beanname把这个对象找出来的

Step1、容器

```java
//创建一个容器beans
HashMap<String, Object> beans = new HashMap<String, Object>();
```

Step2、获得配置文件的keyset的迭代器

```java
Iterator<Object> iter = props.keySet().iterator();
```

Step3、访问配置文件

```java
while (iter.hasNext()){
    //获得配置文件的(k，v)
    String key = (String)iter.next();
    String val = props.getProperty(key);
    //创建实例，并把实例放入Map集合
    Object obj = Class.forName(val).newInstance();
    beans.put(key,obj);
}
```

Step4、getBean

```java
public Object getBean(String beanname){    
    return beans.get(beanname);
}
```

上面完成了什么工作？

**读取整个配置文件，全部加载出来，并创建对象，并把key和该对象塞进去一个Map数组。**

虽然感觉有点浪费？？万一有些类不会被访问到呢？？非得把它实例化了。不过考虑到一般是写工具类，用到的概率比较大。



### 优势

现在大致能理解对象工厂的意思：

​	原先**UI依赖SERVICE对象**，**SERVICE依赖DAO对象**，彼此耦合。

​	现在**UI和SERVICE都仅依赖于Beanfactory**，由对象工厂访问配置文件，返回一个实例。

特别的，假如有多个UI，每个UI都依赖于多个SERVICE。而工厂模式，所有**UI和SERVICE都仅依赖于工厂**。

## IoC

IoC：解耦，类似前面提到的工厂类。

### 访问方式1 :ApplicationContext

Step1、添加Spring的**jar包**

pom.xml

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.0.2.RELEASE</version>
</dependency>
```

Step2、写**配置文件**

类似于前面写的那个`bean.properties`,这里同在resources中添加一个bean.xml；

因为使用了Spring，需要提供框架的一些信息；[官网链接](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html)

**bean.xml:**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    
	<!--把对象的创建交给spring管理-->
    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
    <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
</beans>
```

- 核心就是两个bean对象，通过id和类的全限定名访问

  和前面**单例模式**一样，Spring会帮你创建一个**容器**（Map），你可以**通过id**访问对应实例

Step3、**访问**示例

```java
ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

IAccountService as = (IAccountService)ac.getBean("accountService");
IAccountDao     ad = ac.getBean("accountDao", IAccountDao.class);
```

- ClassPathXmlApplicationContext 加载配置文件
- getBean有两种方式获取实例：（第一种好记一点）
  - 1）通过**key**获得Object之后**强转**
  - 2）通过**key和类的字节码**访问

### 访问方式2:BeanFactory

```java
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("bean.xml"));
IAccountDao ad2 = factory.getBean("accountDao",IAccountDao.class);
System.out.println(ad2);
```

### 区别：

加载时机：

- ApplicationContext：**读取配置文件**的时候会把全部对象加载出来
- BeanFactory：当你**调用**BeanFactory去获取一个对象的时候才会生成一个对象

因为上述区别：

- ApplicationContext 适合于单例模式，只创建一次还能保证线程安全
- BeanFactory 适合于多例模式

**实际应用场景ApplicationContext 用的更多，因为它其实能根据情况决定创建时机**

## bean管理细节

### 一、创建方式

决定使用哪个构造函数是通过配置文件来决定的

#### 方法1：默认构造函数

```xml
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
```

局限性：

- 如果类中重写了构造函数，而**没有无参构造**，配置文件会**报错**
- 通过这种方式，我们没得选，**只能调用**无参构造函数

可以看到就算不报错，实际场景也不可能只是调用无参构造函数，所以有下面一种方法

#### 方法2：使用普通工厂方法

我们没法直接修改源码的情况下，通过工厂方法获取目标对象

Step1、可以在定义一个工厂方法得到**工厂对象**

Step2、调用**工厂对象**的**方法**获取**目标对象**

```java
public class InstanceFactory {
    public IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
```



Step3、修改配置文件

- 获取**工厂对象**

  第一句话得到一个工厂对象，该对象可以通过"InstanceFactory"访问

- 调用对象的方法获取**目标对象**

  通过`factory-bean`和`factory-method`获取该方法的返回值

```xml
<bean id="InstanceFactory" class="com.itheima.factory.InstanceFactory"></bean>
<bean id="accountService" factory-bean="InstanceFactory" factory-method="getAccountService"></bean>
```

缺点：不够简洁？？

#### 方法3：静态工厂方法

Step1、工厂类把**方法**类型改为**static**

Step2、修改配置文件

```xml
<bean id="accountService" class="com.itheima.factory.InstanceFactory" factory-method="getAccountService"></bean>
```

### 二、作用范围

**bean标签的scope属性**

```xml
<bean id="accountService" class="com.itheima.factory.InstanceFactory" scope="prototype"
      factory-method="getAccountService"></bean>
```

- **singleton**：单例的（默认值）

- **prototype**：多例的

- request：作用于web应用的请求范围

- session：作用于web应用的会话范围

- **global-session**：作用于**集群环境**的会话范围，当不是集群环境时，它就是session

  服务器1 2 3 都能访问到global-session；但是没法访问到其他服务器的session

### 三、生命周期

```java
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
    scope="prototype" init-method="init" destroy-method="destroy"></bean>
```

- init-method关键词对应对象的初始化方法  init
- destroy-method关键词对应对象的销毁方法  destroy



- 单例对象：
  - 出生：当容器创建时对象出生（访问配置文件）

  ```java
  ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
  ```

  - 活着：只要容器还在，对象一直活着

  - 死亡：容器销毁，对象消亡

    ApplicationContext接口没有close方法，而实现类有这个方法，所以ac的类型稍微注意一下

    对象的销毁一般不需要你管。

    ```java
    ac.close()
    ```

  - 总结：**单例对象的生命周期和容器相同**

- 多例对象

  - 出生：当我们使用对象时spring框架为我们创建

  - 活着：对象只要是在使用过程中就一直活着

  - 死亡：当对象长时间不用，且没有别的对象引用时，由Java的**垃圾回收器**回收

    也就是`ac.close()`也不能把对象销毁，它是依赖于垃圾回收器的，而**close命令关的是容器**。

## DI

### 方法1、标签：

**带参构造函数** + 标签（**constructor-arg**）

```xml
<!-- 
String name;
Interger age;
Date birthday;
-->
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
    <constructor-arg name="name" value="泰斯特"></constructor-arg>
    <constructor-arg name="age" value="18"></constructor-arg>
    <constructor-arg name="birthday" ref="now"></constructor-arg>
</bean>

<!-- 配置一个日期对象 -->
<bean id="now" class="java.util.Date"></bean>
```

应用场景：

- value：用于提供基本类型和String类型的数据
  - String和基本数据类型及其包装类可以通过value配置
  - Date不行，因为字符串是没法强转成Date的，这不符合JAVA的规范。所以先获取一个日期对象`now`，在用ref给`accountService`的 `birthday`参数赋值

- ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象



缺点：必须给**每一个bean指定所有参数**，哪怕你用不到这个对象。

### 方法2、set方法

标签(**property**) + set方法

```xml
<bean id="accountService2" class="com.itheima.service.impl.AccountServiceImpl2">
    <property name="name" value="TEST" ></property>
    <property name="age" value="21"></property>
    <property name="birthday" ref="now"></property>
</bean>
<bean id="now" class="java.util.Date"></bean>
```



### 方法3、集合

```xml
<bean id="accountService3" class="com.itheima.service.impl.AccountServiceImpl3">
    <property name="myStrs">
        <set>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </set>
    </property>
    <property name="myProps">
        <map>
            <entry key="testA" value="aaa"></entry>
            <entry key="testB">
                <value>BBB</value>
            </entry>
        </map>
    </property>
</bean>
```

- **list set array**的形式是一样的，这三种不区分

- **map props** 的形式是一样的，这两种不区分
- 挑两个好记的 **map 和 set**，其他的通过这两种方式注入就可以了

## AOP【待续】



## 总结

SPRING的核心：IoC和DI和AOP

### IoC

目的是解耦，由Spring管理对象集合，类之间的耦合性减弱



- 1、配置文件：创建一个对象，对象的名称（引用）为"key"

  ```xml
  <bean id="key" class="com.edu.iterfaceImpl" factory-method="static_func_getObj"
        scope="prototype"></bean>
  ```

  

  上述配置文件的结果就是**调用iterfaceImpl**的**static_func_getObj方法**获取一个**iterfaceImpl对象**

  ```java
  package com.edu
  class  iterfaceImpl{
  	public iterfaceImpl static_func_getObj(){
  		return new iterfaceImpl();
  	}
  }
  ```

  | id             | 对象的标识              |
  | -------------- | ----------------------- |
  | class          | 该对象是哪个类new出来的 |
  | factory-method | 获取该对象的方法是什么  |
  | scope          | 单例/多例               |

  

- 2、应用：

  ```java
  ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
  iterfaceImpl instance = ac.getBean("key",iterfaceImpl.class);
  ```



### DI

目的：IoC通过配置文件获取的是一个**空对象**，DI需要给该对象赋予初值
- 1、构造函数法**constructor-argname**（变量名，变量值）

  - value：可以使基本数据类型或者String的变量

    `<constructor-argname="name" value="TEST" ></property>`

  - ref：其他对象

    `<constructor-argname="birthday" ref="now"></property>`

- 2、set方法**property**   这种方式更常用，一般会通过setter赋值而不是所有值都放在构造函数内

`<property name="name" value="TEST" ></property>`

`<property name="birthday" ref="now"></property>`



第一种方式可以理解为通过构造函数对于对象进行初始化

第二种方式可以理解为通过set方法对于变量的成员赋初值

​	

- 3、对于集合的赋值（以set方法为例）

有两种集合：

​	其一为set集合，由多个value组成

​	其二为map集合，由多个entry组成，每个entry包含一个kv对

```xml
<bean id="key" class="com.edu.implement" factory-method="static_forobj" scope="singleton">
	<property name="set">
        <set>
        	<value>AAA</value>
            <value>BBB</value>
        </set>
    </property>
	<property name="map">
        <map>
            <entry key="m_key" value="m_value"></entry>
        </map>
    </property>
</bean>
```



![1571640014389](C:\Users\locy\AppData\Roaming\Typora\typora-user-images\1571640014389.png)

### AOP

