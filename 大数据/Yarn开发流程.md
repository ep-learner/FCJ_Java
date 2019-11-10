# Yarn开发流程

## 本地调试

为了保证和虚拟机端测试效果一致，且减少后续更换输入/输出路径的过程，这里在windows端仍然使用**HDFS**而不再使用本地文件系统。

### Step1、准备工作

Step1、启动虚拟机，提交测试文件到HDFS

#### HDFS启动

```
//Step1、主节点启动namenode和datanode
hdfs --daemon start namenode
hdfs --daemon start datanode

//Step2、从节点启动datanode
hdfs --daemon start datanode
```

#### Yarn集群启动

```sh
#Step1、查看从节点信息：域名+别名
cat /etc/hosts

#Step2、主节点——配置workers节点
cd $HADOOP_HOME/etc/hadoop
vi workers
#添加如下信息
localhost
slave1
slave2

#Step3、启动集群（主节点的资源管理器和所有workers的节点管理器）
#前面的配置是为了通过脚本启动所有从节点不需要一台台启动
start-yarn.sh

#备注：考虑到一般测试用虚拟机只有2到3台，手动启动也没问题
#从节点的启动命令
yarn --daemon start nodemanager
yarn --daemon start resourcemanager
```

#### 浏览器监控

HDFS的端口号：master:9870（或者192.168.XXX.11:9870）

Yarn的端口号：master:8088（或者192.168.XXX.11:9870）

备注1、如果出错的话，手动**查看具体进程端口号**

```sh
jps
netstat -nltp | grep 1610 
#1610更换成namenode，resourcemanager的进程号  分别获得对应的端口号
```

备注2、设置主机端的hosts文件，使得主机端能识别虚拟机的别名

比如，在HDFS页面下载文件，跳转时会通过节点别名访问，windows如果没有配置hosts网页就打不开

再比如，如果没配置这个，HDFS的web页面就不能通过别名访问

路径：（主机hosts文件）

```
C:\WINDOWS\system32\drivers\etc
```

添加如下内容

```sh
域名  别名
192.168.XXX.XXX master
```

#### 提交文件到HDFS

我们既然想操作HDFS的文件，那么首先还是得把文件上传到HDFS。

把文件传到虚拟机的`/home/spark`下，文件名为`NBCorpus`

```sh
hadoop fs -mkdir -p /BAYES    #注意这里不是-r
hadoop fs -mv NBCorpus /BAYES/NBCorpus
```

其他此处会用到的hdfs命令（rm  cat）

```sh
hadoop fs -cat /BAYES/NBCorpus/result  #查看结果文件
hadoop fs -rm -r -f /BAYES/NBCorpus/output   #MR经常有路径已存在，需要删除原路径
```

至此准备工作完成

### Step2、本地调试

这里指的是在本地（WINDOWS）的IDEA，**操作HDFS**，并且**提交任务**到Yarn集群

#### 1、编写提交程序

```java
public static void main(String[] args)  {
	Configuration conf = new Configuration();
    //configuration
    System.setProperty("HADOOP_USER_NAME", "root"); //用户身份更改为root
    conf.set("fs.defaultFS", "hdfs://master:9000");
    conf.set("mapreduce.framework.name", "yarn");
    conf.set("yarn.resourcemanager.hostname", "master");
    conf.set("mapreduce.app-submission.cross-platform","true");
    
    //job 的jar包
    Job job = Job.getInstance(conf);
    job.setJarByClass(Test_MR.class);

}
```



#### 2、添加配置文件

在/myproject/resource中添加两个配置文件

`mapred-site.xml`

`yarn-site.xml`

备注1：因为有的机子可能会报如下所示错误：

`AM Container for appattempt_XXXX exited with exitCode: 1`

` Could not find or load main class org.apache.hadoop.mapreduce.v2.app.MRAppMaster`

那么就把这两文件放进IDEA的resource内，重新打包。

备注2：这个其实用conf.set把这两个文件的内容写出来也是可以的，只是两个配置文件内容有点多，后面linux提交也不需要，所以还是建议直接放到资源根目录



#### 3、打包

- 工具栏找到view（视图）

- 第一项就是Tool Windows（工具窗口），找到里面的Maven Projects（maven工程的视图）

- 点击

  ![Maven Projects  mapreduce24  >  >  Lifecycle  clean  validate  compile  test  package  verify  install  site  deploy  Plugins  Dependencies ](file:///C:/Users/49143/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png)

- 一般会生成一个target文件夹，里面有项目的jar包`hadoop_test-1.0-SNAPSHOT.jar`

  如果没有在Project Structure里面特别配置过的话.....

#### 4、最后一步

告诉Yarn集群，你刚刚打包的文件在哪。



在前面的配置文件中添加这么一句：

`job.setJar("target/hadoop_test-1.0-SNAPSHOT.jar");`

添加的位置：

```java
Job job = Job.getInstance(conf);
//添加的位置
job.setJar("target/hadoop_test-1.0-SNAPSHOT.jar");
job.setJarByClass(Test_MR.class);
```



这是由于Yarn工作机制决定的。Yarn会新建Job对象，提交你的Map和Reduce程序，但是它此时会找不到字节码(XX.class)，因为你的程序虽然打成jar包了，但是它确实不知道这个jar包在哪，所以得告诉yarn你打包的位置。

不理解也没关系，添加完这句话，你就可以在IDEA上调试了



## 集群部署

调试完了，放到集群里面跑！！



### Step1、编写提交程序

和本地调试不同，得把一些内容注释掉：

（其实我的机子上不注释也没有问题，但是正规一点还是把它们注释掉吧）

```java
    //System.setProperty("HADOOP_USER_NAME", "root"); 
    conf.set("fs.defaultFS", "hdfs://master:9000");
    conf.set("mapreduce.framework.name", "yarn");
    conf.set("yarn.resourcemanager.hostname", "master");
    //conf.set("mapreduce.app-submission.cross-platform","true");
```

### Step2、重新打jar包，把包放到linux的某个目录下

某个目录 ： 

```sh
/home/spark/hadoop_test-1.0-SNAPSHOT.jar  ##放到这里
```

### Step3、提交

示例：

**hadoop     jar     jar包     jar包里面的主类**

```sh
hadoop jar  modelPrior-1.0-SNAPSHOT.jar     bayes.modelPrior

hadoop jar  modelPosterior-1.0-SNAPSHOT.jar  bayes.Train_MR

hadoop jar  hadoop_test-1.0-SNAPSHOT.jar   bayes.Test_MR

hadoop jar  hadoop_test-1.0-SNAPSHOT.jar   bayes.Evaluation_MR
```

严格来讲一般出错比较多的是windows里面跑yarn，各种踩坑，提交到linux跑就没那么多问题了。如果前面本地调试没问题，这里一般不会出错。





# 课程作业

## 启动

hdfs --daemon start namenode
hdfs --daemon start datanode

start-yarn.sh

## 删除结果文件

hadoop fs -rm  -r /BAYES/NBCorpus/classWordNum   /BAYES/NBCorpus/prior   /BAYES/NBCorpus/train /BAYES/NBCorpus/valid   /BAYES/NBCorpus/classNum-r-00000     /BAYES/NBCorpus/WORD_DITIONARY_SIZE

hadoop fs -rm  -r /BAYES/NBCorpus/file_ct_cp

## 作业的几个问题

WEB监控![1573304828935](C:\Users\49143\AppData\Roaming\Typora\typora-user-images\1573304828935.png)



运行截图：

running截图



最后的输出结果

输出日志



map

每一个文件对应一个map，reducer是人为设置的



# 补充

## 环境配置

在cd $HADOOP_HOME/etc/hadoop/hadoopp-env.sh中

添加classpath



## sequencefile

前面出现问题是因为一个文件对应一个mapper这个计算量就很大了，并且浪费内存



## 多reduce

String能不能是正则表达式



## bean

自定义类型

