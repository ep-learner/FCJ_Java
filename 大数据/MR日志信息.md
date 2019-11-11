## Map和Reduce的日志信息

一旦程序进入Mapper和Reducer的部分，这个时候，任务是分发到datanode的机器里面跑，那里的JVM的输出就不会再返回到task的JVM了，所以在Mapper和Reducer里面的System.out输出是无法看到的

以下三个方法可以

- 用log4j来生成logger；

- 用apache common里面的LogFactory生成logger；

- 用MultipleOutput来自己写log输出

  这个就不介绍了，相当于自己在Reduce那里输出想看到的日志内容



[参考链接](https://blog.csdn.net/infovisthinker/article/details/45370089)

step1、配置yarn-site.xml

  <property>
    <name>yarn.log-aggregation-enable</name>
    <value>true</value>
    <description>Configuration to enable or disable log aggregation</description>
  </property>

step2、命令行启动

```java
mapred --daemon start historyserver
```

step3、启动datanode  namenode 和yarn





比如：job_1573331477785_0001

```sh
yarn logs -applicationId application_1573319208225_0004 > logs.txt
```

