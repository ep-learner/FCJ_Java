# README

[TOC]

# 简介

这里主要介绍J2EE中应用到的JSP Servlet Filter，会尽可能详尽的记录我所了解到的一些知识点，包括部署和应用上的快速入门。

# 一、Servlet

目的：

1、解释Servlet的原理

2、Servlet的get post service init destory等函数

3、request 和response的API

## 1、Servlet是怎么被调用的

- **客户端通过HTTP请求访问资源**，这个请求不是直接发给Servlet，而是交由Web服务器（比如Tomcat），Tomcat会通过Servlet容器对Servlet进行操作；
- Servlet容器查找是否存在相应的Servlet，没有则从**磁盘中加载**出来放到**Servlet容器**中；
- Tomcat把HTTP**请求解析**成ServletRequest交由Servlet**处理**；
- Servlet执行doget/dopost，返回一个response给Tomcat；
- Tomcat获取Response，解析成**HTTP响应**，发送给浏览器。
- ![1570175838798](C:\Users\49143\AppData\Roaming\Typora\typora-user-images\1570175838798.png)
- 参考文献： https://blog.csdn.net/yw_1207/article/details/78706701

## 2、Servlet的生命周期

**Servlet的init，service和destory都是通过容器调用的，从而控制Servlet的生命周期。**

- **（1）加载和实例化**

  当Servlet容器启动或客户端发送一个请求时，Servlet容器会查找内存中是否存在该Servlet实例，若存在，则直接读取该实例响应请求；如果不存在，就创建一个Servlet**实例**。

- **（2） 初始化** 

  实例化后，Servlet容器将调用Servlet的init()方法进行初始化（一些准备工作或资源预加载工作）。**init在service之前**

- **（3）服务**

  初始化后，Servlet处于能响应请求的**就绪状态**。当接收到客户端请求时，调用service()的方法处理客户端请求，HttpServlet的service()方法会根据不同的请求转调不同的doGet/Post()方法。

- **（4）销毁**

  当Servlet容器关闭时，Servlet实例也随时销毁。其间，Servlet容器会调用Servlet 的destroy()方法去判断该Servlet是否应当被释放（或回收资源）。

## 3、重定向和转发

### 1、客户端跳转(重定向)：

`response.sendRedirect("fail.html");`

调用响应(response) 的API实现客户端跳转；让客户端把url修改为fail.html，发起请求req2，tomcat再响应req2；因为request被修改了，那么之前保存的一些变量会被丢弃，即**`request.setAtrribute`方式无法保存该变量**。

### 2、服务端跳转（转发）

`request.getRequestDispatcher("success.html").forward(request, response);`

最直接的不同，这是调用**request**的API，而不是response，服务器端自行修改返回给用户的页面，这时候可以通过`request.setAtrribute`传参。

### 3、两者的应用场合

重定向的两个应用场景：1）避免重复调用；2）重定向到外部网站；

第一个应用，比如需要添加数据项，假如使用服务端跳转，那么添加完之后刷新页面，因为url没变，会重新发起添加请求，出现重复添加现象。反之，添加完成，url改变成其他资源地址，比如某个jsp，刷新之后就不再是原来的添加页面，避免了重复调用Servlet。

第二个应用：服务器端跳转只能访问本WEB应用程序的资源，如果需要访问其他WEB应用程序，则只能使用重定向。

转发的应用场景：

因为转发不需要客户端重新发起一个新的请求，那么首先数据不会丢失，再者速度也比重定向要快；除了上述两种情况，一般会使用转发。



参考：

[重定向和转发的区别](https://blog.csdn.net/liubin5620/article/details/79922692)

## 4、部署

1、继承了Servlet类的XXServlet实现类

2、显示界面：如HTML和JSP

3、URI的映射（web.xml）

这里会设置XXServlet具体拦截哪些URI，即设置ServletPath。

## 5、Request和Response

### 5.1、Request

Http请求的内容如下图所示

![1570178211308](C:\Users\49143\AppData\Roaming\Typora\typora-user-images\1570178211308.png)

编写测试用源码 test.java；

查看request的资源获取结果：主要是1）各个路径的实际含义，2）参数获取的方式

#### 1）路径资源

##### 1、web应用的根路径"/"

`request.RealPath("/")=C:\Users\49143\Desktop\servlet_test\web\`

这个“/”表示的是web应用在机子上的真实路径；web资源jsp html png都是在这个目录下面

##### 2、URI和URL

Servlet里面的URI是相对路径，即 **”/” + “访问的资源路径”**

Servlet里面的URL比起URI**多了网址和端口号**。

URI = **contextpath + servletpath + pageinfo**

```markdown
特别的，假如存在html调用Servlet的情况，那么URI由html的参数决定
举个栗子
index.html：<form action="/my_uri" method="get">
getRequestURI= /my_uri；

这里html会去访问/my_uri路径；web.xml配置了对/my_uri的拦截，让这个请求转发到XXServlet进行处理。即客户端访问的网址由/index.html变成了/my_uri。
```

##### 3、ServletPath和PageInfo

- 1、ServletPath取决于**web.xml**。

- - 1）web.xml设置了拦截的路径（如下），**ServletPath=/my_uri**；

```
<url-pattern>/my_uri</url-pattern>
```

- - 2）假如应用了正则表达式（如/abc/*），那么**ServletPath=/abc**；此时可以认为这个ServletPath是符合正则表达式的一切路径。

```
<url-pattern>/abc/*</url-pattern>
```

- 2、PageInfo是对于ServletPath的补充，**PageInfo为ServletPath下面的具体页面路径**

##### 4、ContextPath

这个可以在`edit configuration`里面的`deployment`里面设置；一般我们会设置成`/`.



##### 5、总结

前面可能不是很直观，这里举个实际例子：

web.xml的配置：<url-pattern>/abc/*</url-pattern>

浏览器网址：http://localhost:8080/context_path/abc/page

```markdown
结果：
getContextPath = /context_path （这里配置了context地址，这里只是为了显式表示，一般还是配置成/）

getServletPath = /abc   （这是在web.xml配置的  /abc/*）

getPageInfo    = /page   （这个就是web.xml里配置的/abc/*后面的/*部分的实际地址）

getRequestURI  = /context_path/abc/page

getRequestURL  = http://localhost:8080/context_path/abc/page

```

#### 2）参数资源

##### 不含页面跳转时的参数获取：

```java
request.getParameter("name")
//通过变量名称获取String
request.getParameterValues("hobits")
/*
通过变量名称获取String[]
相应的在html的form里面得获取到多个变量值，如：
A<input type="checkbox" name="hobits" value="A">
B<input type="checkbox" name="hobits" value="B">
*/
request.getParameterMap()
//这里不需要设置参数，可以获取request的所有参数，但是一般情况下的需求不是获取全部参数
```

##### 转发时的参数获取：

服务端跳转，把request都传过去了，那么原来的参数自然还是能获取的。

```java

request.setAttribute("name","req");//传递变量
request.getRequestDispatcher("/servlet1").forward(request,response);//服务端跳转

String str = (String)request.getAttribute("name");//获取变量
//注意Attribute传递的是对象，需要进行强制转换。
//这里就不再限制与传递字符串了

```

##### 重定向时的参数获取：

这时候request就不能使用了，可以选择的传参方式还剩下**session**和**servletcontext**。

```java
request.getSession().setAttribute("name","session");
Object o = request.getSession().getAttribute("name");

request.getServletContext().setAttribute("name","ServletContext");
Object o = request. getServletContext().getAttribute("name");
```

### 5.2、Response

```
response.setContentType("text/html; charset=UTF-8");
//发送到浏览器的内容为utf-8，且浏览器显示也是utf-8；
//唯一注意的是它得在getwriter之前，如果先getwriter，那么这里的设置就没用了

response.getWriter().println("输出到页面");
```

## 6、Html提交

这里主要介绍**GET和POST**的区别

get和post本质上都是TCP链接，是HTTP协议发送请求的两种方式。

主要区别：

- get方式会把参数放在URL中，而Post会把参数放入request body中；
- GET不是很安全，毕竟参数都在URL上摆出来了；
- GET只接受ASCII码，POST无限制；
- GET长度被限制了，URL不能大于64K。
- Post需要产生两个TCP，而GET只需要产生一个；Post相对慢一些，但数据完整性更能保证；
- GET的参数会被浏览器Cache。

## 7、request，session和servletcontext

作用范围：request<session<servletcontext

1、request：除了可以通过服务端跳转给到下一个页面以外数据都会丢失，用完即扔。不会占用大量内存。

 

2、session：是一个局部变量。（你没看错就是局部变量，相对于ServletContext这种而言它就是局部的）每个用户都有自己的一个session，浏览器关闭或者超时（30min）才会丢失，但是重定向之后仍然能够访问到，所以一般用session保存已经足够了。

 

3、servletcontext：是一个全局变量；别被误导，**这不是某个Servlet的上下文**，而是**tomcat的上下文**，即所有Servlet的上下文。所以数据保存在这里几乎不会丢失，除非tomcat关闭或者重启。

# 二、Filter





 





