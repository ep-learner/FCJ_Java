# SSM整合

## 环境

### 项目搭建

- 1、new project

![1573979717395](/pic/1573979717395.png)



- 2、从模板中选择webapp，创建maven项目

![1573979763871](/pic/1573979763871.png)



- 3、项目的信息，groupid和Artifactid可以随便填

![1573979791413](C:\Users\49143\Desktop\JAVA\java\FCJ_JavaSE\JAVAWEB\SSM\pic\1573979791413.png)

- 4、添加maven的属性值

  正常创建的时候，创建该demo的速度会很慢，**添加键值对**：

  key  = archetypeCatalog

  value = internal

  ![1573979918713](C:\Users\49143\Desktop\JAVA\java\FCJ_JavaSE\JAVAWEB\SSM\pic\1573979918713.png)



以上，一个maven项目就建立好了！！



### 项目数据

```sql
create database ssm;
use ssm;
create table account (
  id int primary key auto_increment,
  name varchar(50),
  money double
);
```

## 骨架

### domain

Account.class

| 属性  | 类型    |
| ----- | ------- |
| id    | Integer |
| name  | String  |
| money | Double  |

### dao

因为使用的mybatis所以不需要写实现类，只需要接口

IAccountdao.interface

```java
public interface IAccountdao {
    public List<Account> findAll();//查询所有
    public void saveAccount(Account account);//保存账户信息
}
```

### service

一个接口，一个实现类

- 接口

  ```java
  public interface IAccountService {
      public List<Account> findAll();
      public void saveAccount(Account account);
  }
  ```

- 实现类

  ```java
  package com.fcj.service.Impl;
  
  @Service("accountService")
  public class AccountService implements IAccountService {
      @Override
      public List<Account> findAll() {
          System.out.println("查询所有");
          return null;
      }
  
      @Override
      public void saveAccount(Account account) {
          System.out.println("保存账户");
      }
  }
  ```

### 资源文件

建立一个resource文件存放框架所需要的资源文件还包括log4j的日志资源等等。



![1573981594741](/pic/1573981594741.png)



#### spring配置

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/aop
	http://www.springframework.org/schema/aop/spring-aop.xsd
	http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--开启注解的扫描，希望处理service和dao，controller不需要Spring框架去处理-->
    <context:component-scan base-package="com.gx" >
        <!--配置哪些注解不扫描-->
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller" />
    </context:component-scan>
    
</beans>
```

#### mvc配置

springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    
    <!--开启注解扫描，只扫描Controller注解-->
    <context:component-scan base-package="com.fcj">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--配置的视图解析器对象-->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!--过滤静态资源-->
<!--    <mvc:resources location="/css" mapping="/css/**"/>-->
<!--    <mvc:resources location="/images/" mapping="/images/**"/>-->
<!--    <mvc:resources location="/js/" mapping="/js/**"/>-->
    <!--开启SpringMVC注解的支持-->
    <mvc:annotation-driven/>
</beans>

```



## 测试

### Spring

#### 前置条件

- 完成service和domain的编写
- 完成了applicationContext.xml的编写

#### junit测试：

```java
package com.fcj.test;
public class testSpring {
    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountService instance = (AccountService) ac.getBean("accountService");
        instance.findAll();
    }
}
```

#### 总结

目的：Spring实质只是对于注解的类创建一个对象，我们能通过Spring的上下文，访问Spring容器，获得Spring所管理的实例。

所以测试的时候我们测试的是：该实例能否正确被取出来，调用所属类的方法。



### SpringMVC

#### 前置条件

- 编写web.xml

  - dispatchServlet的映射
  - 编码

- 编写springmvc.xml

  - 

- 编写jsp

  ```html
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <body>
  <a href="account/findAll">测试SpringMVC查询</a>
  </body>
  </html>
  ```

  上述链接会访问到：   项目路径/account/findAll

  所以相应的service也得拦截到这个路径

- 编写controller

  ```java
  package com.fcj.controller;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  @Controller
  @RequestMapping("/account")
  public class AccountController {
      @RequestMapping("/findAll")
      public String findAll(){
          System.out.println("countroller find all....");
          return "list";
      }
  }
  ```

- 编写WEB-INF/pages/list.jsp

  毕竟需要考虑到mvc跳转之后的字符串处理是否正确，前面是默认添加/pages前缀和.jsp后缀

  ```html
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Title</title>
  </head>
  <body>
      <h2>查询所有的账户</h2>
  </body>
  </html>
  ```

### 整合spring+mvc







## 其余知识点

### [classpath](https://blog.csdn.net/u011095110/article/details/76152952)

类路径，编译之后的结果文件为/target/classes；如下图所示，原资源根目录内的文件也在classes里面，所以通过classpath:applicationContext.xml能直接定位到配置文件

![1573983221625](/pic/1573983221625.png)



## 参考

[IDEA+SSM整合](https://blog.csdn.net/qq_44543508/article/details/100192558#commentBox)



