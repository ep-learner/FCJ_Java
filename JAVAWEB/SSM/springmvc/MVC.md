# MVC基础配置

## web.xml

- 配置dispatchServlet（控制器）的uri

  - 在其中指定配置文件（springmvc.xml）路径，进行初始化：指定扫描路径

  - url-pattern**为  / 而不是/***；它只需要拦截地址形式路径，我们不希望拦截静态资源；而JSP类型在配置文件中会进行映射（视图解析，加前缀和后缀）

  MVC的实质就是**一个Servlet来充当控制器**，转发请求

- 编码（中文编码问题）

  用filter实现 避免每个Servlet都写一个设置编码为UTF-8

## springmvc.xml

- 注解扫描

  ```xml
  <context:component-scan base-package="**cn.edu.javatest**">
  ```

- 视图解析

  ```xml
  <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/WEB-INF/pages/"/>
          <property name="suffix" value=".jsp"/>
  </bean>
  
  ```

- 过滤静态资源

  ```xml
  <mvc:resources location="/css/" mapping="/css/**"/>
  <mvc:resources location="/images/" mapping="/images/**"/>
  <mvc:resources location="/js/" mapping="/js/**"/>
  ```

- 开启MVC注解支持

  ```xml
  <mvc:annotation-driven/>
  ```

## Servlet

- @Controller                                 类注解 这是Spring的注解
- @RequestMapping("/login")        方法注解,参数为路径 这是mvc的注解

# 注解

## RequestMapping

修饰对象

- 放在类上，路径表示其一级目录   @Requestmapping("/myweb") 

- 放在方法上，路径表示二级目录    @Requestmapping("/login")   

  访问方式：/项目名称/myweb/login

参数

- 路径

  可选关键词：1）path  2） value  3）**缺省**关键词直接放入路径参数

  通配符：

  - `/project/*.a`               匹配**任意长度**字符
  - `/project/p?ttern`       匹配**单个**字符
  - `/**/example`                 匹配任意长度**路径**

  

- 限制访问类型

  method = {Requestmethod.POST}

  限制访问的类型为POST

  

- 参数限制

  - params = {"username"}

    要求uri必须有参数username 

    如/myweb/login?username=1

  - params = {"username！=1"}

    要求有参数，**且参数不等于1**

## PathVaribale

其作用还是**Servlet从uri中获取参数**

```java
@Controller
@RequestMapping(path = "/account")
public class HelloController {
    @RequestMapping("/findAccount/{id}")
    public void findAccount(@PathVariable(name = "id") Integer accountId){
        //这里拿到的accountId根据uri给的参数决定
    }
}
```



当访问路径：`http://localhost:8080/myProject/account/findAccount/10`

相当于request调用了方法**findAccount(10)**

# 参数绑定

- 基本类型和String
- JavaBean
- 集合



数据绑定要求**请求参数**名和**方法中参数名**相同，或者使用`@RequestParam`为方法参数起别名.

## URI传参

这种情况下是能**直接从uri传参数**，只是传递的值比较少，一般会通过**表单**传

1、index.jsp

```jsp
<a href="account/findAccount?accountId=10&accountName=zhangsan">查询账户</a>
```

2、Servlet

```java
@RequestMapping("/findAccount")
public String findAccount(Integer accountId, String accountName) {
    //accountId = 10
    //accountName = "zhangsan"
}
```

## JavaBean

1、index.jsp

- 只需要映射到主类，子类User通过主类的成员变量  **user.field** 的方式访问

```jsp
<form action="account/saveAccount" method="post">
    <label>用户名</label><input type="text" name="user.uname"><br/>
    <label>年龄</label><input type="text" name="age"><br/>
    
    <input type="submit" value="保存">
</form>
```

2、JavaBean

```java
public class Account implements Serializable {
    private User user;
    private Integer age;
}
public class User implements Serializable{
    private String uname;
}
```

3、Servlet

```java
@RequestMapping("/saveAccount")
public String saveAccount(Account account){
```

## Servlet API传参

request

response

session

这些参数都能把参数传进来，不过作用域不太一样

## 类型转换

因为传进来的都是String，如果成员变量的类型比如Date，需要一个实现**Convert<String,T>**接口 的实现类。

# MAV对象

ModelAndView

```java
// 向model中存入属性attribute_user
// 可以认为model是一个map 
// key = "attribute_user"   value<T>
mv.addObject("attribute_user", new User("张三", "123"));

// 设置返回视图,视图解析器将"success"解析为视图URL /WEB-INF/pages/succeess.jsp
//这里不会跳转，但是函数出口return mv 会访问到URL
mv.setViewName("success");

return mv;
```

# 转发和重定向

## 转发

前面配置的视图解析器的作用：

**客户端访问**的是"/project/index",而**服务端实际响应**给客户端的是/project/前缀+index +后缀

这显然是一种转发（服务端跳转）



另一种转发方式：

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testForward")
    public String testForward() {
        // 格式：  forward:完整路径
        // return "forward:success"	错误
        //会将请求转发到 /项目名/user/success
        return "forward:/WEB-INF/pages/success.jsp";
    }
}
```

如果写成： return "forward:success"	

会报404错误，因为这个返回值的意思是将请求转发到 /project/user/success

既不是html也不是jsp，当然访问不了。

另外类前面有前缀“ user ”，所以返回值的路径不需要写/user



## 重定向

```java
return "redirect:/index.jsp";
```

实际映射位置：/project/user/testForward/index.jsp

# 响应JSON数据

