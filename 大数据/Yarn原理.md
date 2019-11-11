# Yarn提交

## 相关基础介绍

### 提交模板

```java
//1、配置
Configuration conf = new Configuration();
// conf.set(XX,XX)

//2、job
Job job = Job.getInstance(conf);
//job.setXX    各种class

//3、提交：
job.submit();
//其实我们一般看到的是这句话，里面还是调用了submit方法
boolean res = job.waitForCompletion(true);
```

### 运行关键组件

- MRAppMaster
- yarnchild    (map task)
- yarnchild    (reduce task)
- 提交job的客户端（client）

## 启动过程

- 1、向ResourceManager请求一个容器（需要提供容器的资源参数，CPU和Memory）

  

- 2、返回一个**jobid**以及一个用于提交程序资源的**路径**（比如`/temp/root/yarn/**/staging`）

  前面向RM提交请求，提交的参数是容器的大小信息。JAVA代码还没提交，这显然没法运行，所以返回给客户端一个jobid，告诉你申请成功，你要把java代码放到我指定的某个资源路径下

  

- 3、客户端扫描输入的文件目录

  - 首先扫描文件得到一系列FileSplit组成的ArrayList

  - 然后将这个ArrayList序列化成`job.split`

  - 最后`job.split`放到把到前述的资源路径下。

    `/temp/root/yarn/**/staging/jobid/job.split`

    

- 4、job的相关参数，其实就是conf的那些参数，而job创建时基于这些参数，所以需要上传相关参数

  - 生成job.xml（包含参数信息）

    `/temp/root/yarn/**/staging/jobid/job.xml`

    

- 5、把jar包拷贝到目录下

  - `/temp/root/yarn/**/staging/jobid/job.jar`

  以上，jar，xml，spilt已经准备完毕

  

- 6、通知RM，资源上传完毕。

  

- 7、RM分配map task给某一个NodeManager，NM查询前面的资源路径，为这个任务**创建一个容器**



- 8、Client知道自己的任务容器已经启动，发送命令行启动MRAppMaster。

  

- 9、NM向RM发出请求，请求更多的容器（NM做的和Client一样）
- 10、同理，Reduce任务

