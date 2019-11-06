## 功能
1）解析SQL语句；把SQL的查询，分组等操作解析成内部的mapreduce任务，组装成mr_job

2）job通过yarn集群提交

## 安装：以下介绍mysql和hive的安装
前提：yarn和hdfs正常启动
```
[root@master ~]# hdfs --daemon start namenode
[root@master ~]# hdfs --daemon start datanode
[root@master ~]# start-yarn.sh
```
http://192.168.171.11:9870
http://192.168.171.11:8088

上述两个网址能打开就是正常启动

1、安装mysql
```
  1.1、解压获取rpm包：
  tar -xvf MySQL-5.6.26-1.linux_glibc2.5.x86_64.rpm-bundle.tar
  1.2、rpm安装mysql：
  rpm -ivh MySQL-server-5.6.26-1.linux_glibc2.5.x86_64.rpm 
  rpm -ivh MySQL-client-5.6.26-1.linux_glibc2.5.x86_64.rpm

  1.3、启动mysql的服务端：
  [root@mylove ~]# service mysql start
  Starting MySQL. SUCCESS!

  1.4、修改root的初始密码：
  初始密码：cat /root/.mysql_secret 
  修改密码：/usr/bin/mysql_secure_installation  

  1.5、授权
  mysql -uroot -p
  mysql>grant all privileges on *.* to 'root'@'%' identified by 'root的密码' with grant option;
  mysql>flush privileges;
```

2、安装hive
```
2.1、上传hive安装包；注意版本和hadoop版本匹配

2.2、解压：
[root@master /]# cd /home/spark/apps
[root@master apps]# tar -zxvf apache-hive-3.1.1-bin.tar.gz
[root@master apps]# mv apache-hive-3.1.1-bin hive-3.1.1

2.3、添加连接包
把*mysql-connector-java-5.1.39.jar*放入hive/lib

2.4、配置文件：
1）hive-site.xml
[root@master apps]# cd hive-3.1.1
[root@master hive-3.1.1]# vi conf/hive-site.xml

<configuration>
<property>
<name>javax.jdo.option.ConnectionURL</name>
<value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
<description>JDBC connect string for a JDBC metastore</description>
</property>

<property>
<name>javax.jdo.option.ConnectionDriverName</name>
<value>com.mysql.jdbc.Driver</value>
<description>Driver class name for a JDBC metastore</description>
</property>

<property>
<name>javax.jdo.option.ConnectionUserName</name>
<value>root</value>
<description>username to use against metastore database</description>
</property>

<property>
<name>javax.jdo.option.ConnectionPassword</name>
<value>your_passwd</value>
<description>password to use against metastore database</description>
</property>
</configuration>

2）etc/profile
[root@master hive-3.1.1]# vi /etc/profile

export HIVE_HOME=/home/spark/apps/hive-3.1.1
export HIVE_CONF_DIR=$HIVE_HOME/conf
export PATH=$PATH:$HIVE_HOME/bin

```
