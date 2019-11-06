## MVC简介

这里简要介绍一下为什么需要SpringMVC。

前面没学框架之前，我们需要**在web.xml里面配置Servlet对应的URI**，每个Servlet都包括（名称，类全路径，拦截的URI），这样配置文件的规模会比较庞大和不可控。且不同Servlet的配置其实存在很大冗余。

### MVC工作机理

MVC的**作用**就是：1）接受请求URI；2）将URI转发至相应的Servlet。

它是通过一个名为DispatcherServlet的Servlet实现的

- 配置拦截路径：**"/"**，所有非静态资源

- 在每个类出进行注解@Controller;

  前面Spring也提过有四个注解的功能一模一样；对于MVC来说习惯用Controller来给类做注解

- @RequestMapping("/拦截路径")

### MVC三个成员的内涵

C：控制器，**实质是Servlet类**。

V：视图，包括静态html和动态jsp。直观上来说它是一个**空白模板**。

M：模型；或者说填充在**视图里面的数据**



​	