# 简介

介绍几种IO方法



## read

1、多字节读取文件流

简化版本：

读取文件流，但读取完之后没有把内容保存下来

```java
FileInputStream is = new FileInputStream("Path");
is.read(new byte[1024]);
```

完整读取：

```java
FileInputStream is = new FileInputStream("Path");
byte[] b = new byte[1024];
//len获取的是读取的输入流的长度
//读取的内容会被写入字节数组
int len = is.read(b);
String str = new String(b, 0, len);
```

2、任意类型/长度输入流的读取

```java
FileInputStream fis = new FileInputStream("Path");
BufferedReader br = new BufferedReader(new InputStreamReader(fis));
String s = br.readLine();
```

## write

1、多字节流写出

```java
FileOutputStream fos = new FileOutputStream("out");//写出的文件路径
fos.write("写出去的内容".getBytes());
```

2、任意类型/长度输出流

这时候就不只是能写出去字节数组了。

```java
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out")));
bw.write("任意内容");
```

## Properties

### 1、读取文件，通过变量名获取参数值

文本：22233.txt

```json
name:123
class:456
```

通过**Properties**获取变量值：

```java
Properties p = new Properties();
p.load(new FileInputStream("d:/22233.txt"));
//从输入流中获取一个Properties的list；
//就是从配置文件中获得一个list:[key=val_name,val = value;]
String name = (String)p.get("name");
System.out.println(name);
```

### 2、释义

properties继承了哈希表

```java
public class Properties extends Hashtable<Object,Object>
```

### 3、常用方法

1、list(PrintStream out) 

将此属性列表打印到指定的输出流。此方法对于调试很有用。

```java
Properties p = new Properties();
p.load(new FileInputStream("d:/22233.txt"));
p.list(System.out)  //把配置文件读取到的内容输出到输出流，System.out，也就是输出到控制台
```

