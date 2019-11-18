简介

介绍Mysql和JDBC的常见操作。

## MySQL

### 一、数据库

数据库的创建（create），删除（DROP），切换/使用（USE）

```mysql
CREATE DATABASE dbname;
DROP DATABASE dbname;
SHOW DATABASES;#查看数据库
USE dbname;
```

### 二、数据表

#### 1、创建

需要指定**主键**；千万注意这里是反引号，不是引号！

```mysql
CREATE TABLE IF NOT EXISTS `table1`(
	`id` INT UNSIGNED AUTO_INCREMENT,#id自增
	`name` CHAR(10) NOT NULL,#不允许该列为空
    `age` int default 0 comment '年龄'
	PRIMARY KEY (`id`)
)DEFAULT CHARSET = utf8;
```

- 需指定主键
- comment用于指定列的别名

#### 2、删除

别忘记**关键词table**

```mysql
drop table table1;
```

[注意](https://blog.csdn.net/lhhxw/article/details/79477612)：

标识符（表名，字段名）加**反引号**的作用是防止与SQL的关键词名称重复了；

- **反引号不是单引号**
- 在不考虑和关键词重复的情况下，**可以不加**反引号

格式：

- **列名在前，类型在后**

### 三、数据

#### 插入

```mysql
INSERT INTO  
	table1(name)  #指定域名（列名），列名可以不带反引号；
	VALUES("name1");#对应列的取值
```

####    查询

```mysql
select id,name from table1 where id = 1;
select * from table1 where id like "%1";
select * from table1 where binary  id = 1;
```

- **模糊匹配**；该值**类型为INT**也可以。
- binary 是用于区分大小写的关键词

#### 更新

```mysql
UPDATE table1 
	set name = "changed" 
	where id = 1;
```

- **慎用**，如果不加限制条件意味着对于整个表进行修改，这是灾难性的
- 后续可以看一下[误操作的恢复](https://www.cnblogs.com/yingdiblog/p/7602865.html)

#### 删除

```mysql
DELETE 
	FROM table1 
	WHERE id=3;   
```

#### 排序

```mysql
SELECT * FROM tb  ORDER BY id ASC; #默认 由上到下依次递增
SELECT * FROM tb  ORDER BY id DESC;
```



## JDBC

通过JAVA驱动访问数据库

### Step0

安装jar包，通过pom.xml：

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.6</version>
    </dependency>
</dependencies>
```

### Step1、注册驱动

```java
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
```

### Step2、获取连接(URI,user_name,passwd)

```java
Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root", "admin");
```

### Step3、获取预处理对象和结果集

```java
ResultSet rs = c.prepareStatement("select * from table1 where id = 1").executeQuery();
```

- Step3.1、PreparedStatement **ps** = c.prepareStatement("SQL语句");
- Step3.2、ps.execute(); 或者ps.executeQuery();

### Step4、遍历结果集

```java
while (rs.next()){    
    System.out.println(rs.getInt(0));
}
```

### Step5、资源关闭

```java
rs.close();
ps.close();
c.close();
```

- 先开的后关，后开的先关

