# MVC

# 配置

## web.xml

MVC的实质就是**一个Servlet来充当控制器**，转发其他请求，所以在web.xml只需要配一个Servlet。



### 配置分发Servlet

dispatchServlet（**控制器**）的uri

- 指定springmvc.xml（mvc相关配置文件）路径
- 进行初始化：指定扫描路径
- url-pattern**为  / 而不是/***；它只需要拦截地址形式路径，我们不希望拦截静态资源；而JSP类型在配置文件中会进行映射（视图解析，即加前缀和后缀）



### 编码

这个不影响整体架构，但是一般项目会有中文，所以通过一个拦截器统一修改编码是比较合适的方式

用filter实现 避免每个Servlet都写一个设置编码为UTF-8



## springmvc.xml

mvc要能够实现转发请求的功能，得知道请求该转发到哪些Servlet。

- 扫描注解，注解会告诉mvc该Servlet所拦截的url

- 视图解析

  为了简化Servlet的写法，比如想跳转到succeed.jsp就不需要全路径，只需要返回succeed字符串，mvc自动解析成完整的url路径/WEB-INF/pages/succeed.jsp

- 过滤静态资源：不拦截静态资源比如html，不然整个界面什么都显示不出来，往哪填账号密码

- 打开注解支持

  

### 注解扫描

```xml
<context:component-scan base-package="**cn.edu.javatest**">
```

### 视图解析

```xml
<bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
</bean>

```

### 过滤静态资源

```xml
<mvc:resources location="/css/" mapping="/css/**"/>
<mvc:resources location="/images/" mapping="/images/**"/>
<mvc:resources location="/js/" mapping="/js/**"/>
```

### 开启MVC注解支持

```xml
<mvc:annotation-driven/>
```



## Servlet

这里说的是在需要mvc管理的Servlet中添加注解，注明拦截路径

- @Controller                                    类注解 Spring的注解（有四个功能一样的注解，在mvc里用@Controller）
- @RequestMapping("/login")        方法注解,参数为路径 这是mvc的注解



# 路径

## RequestMapping

### 修饰对象

- 放在**类**上，路径表示其一级目录   @Requestmapping("/myweb") 

- 放在**方法**上，路径表示二级目录    @Requestmapping("/login")   

  访问方式：/项目名称/myweb/login
  
  

### 参数

#### 路径

可选关键词：1）path  2） value  3）**缺省**关键词直接放入路径参数

通配符：

- `/project/*.a`               匹配**任意长度**字符
- `/project/p?ttern`       匹配**单个**字符
- `/**/example`                 匹配任意长度**路径**



#### 限制访问类型

method = {Requestmethod.POST}

限制访问的类型为POST



#### 参数限制

- params = {"username"}

  要求uri必须有参数username 

  如/myweb/login?username=1

- params = {"username！=1"}

  要求有参数，**且参数不等于1**





# 参数传递

传递的参数类型包括以下几种：

- 基本类型和String
- JavaBean
- 集合

注意：数据绑定要求**请求参数**名和**方法中参数名**相同，或者使用`@RequestParam`为方法参数起别名.

## URI传参

这种情况下是能**直接从uri传参数**，只是传递的值比较少，一般会通过**表单**传

细分的话有两种：

### 同名参数

输入路径类似：

```jsp
<!--index.jsp-->
<a href="account/findAccount?accountId=10&accountName=zhangsan">查询账户</a>
```

```java
//Servlet
@RequestMapping("/findAccount")
public String findAccount(Integer accountId, String accountName) {
    //accountId = 10
    //accountName = "zhangsan"
}
```

### PathVaribale

和前面那种方式的区别在于，参数没有指定变量名，而是url路径的一部分，用起来比较麻烦，不如前一种

示例：

当访问路径：`http://localhost:8080/myProject/account/findAccount/10`

相当于request调用了方法**findAccount(10)**

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



## 类型转换【待续】

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





# 总结

## 作用

- 代替了web.xml里面路径和servlet的绑定，统一由mvc提供的dispatchSevlet管理
- 后续servlet里面涉及的页面跳转等等也需要由mvc管理

## 实现

- mvc需要知道不同的Servlet拦截哪些url
  - RequestMapping注解提供拦截的url信息
  - springmvc.xml配置注解的扫描路径（注解要生效，必须人为扫描到包含注解的类）
- url有时候会携带请求参数，这些参数是Servlet需要获取并处理的，这时候涉及到参数绑定
  - String和基本数据类型的参数
  - bean对象参数
  - 集合参数

