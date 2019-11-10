# 简介

介绍几种常见的IO方法

## File

相关内容见[fileDemo]()

### 文件创建

```java
File file = new File("D:/test/test1");
boolean bool = file.mkdirs();//新建多级目录
```



### 文件类型:目录/文件

- **file.isDirectory();**

```java
boolean ifdir = file.isDirectory();
System.out.println("是否是目录"+ifdir);

```

- **file.isFile();**

```java
boolean iffile = file.isFile();
System.out.println("是否是文件"+iffile);
```





### 文件路径

- 绝对路径    
- 文件名    
- 父文件名

```java
System.out.println("getAbsolutePath:"+file.getAbsolutePath());
System.out.println("getName:"+file.getName());
System.out.println("getParentFile"+file.getParentFile().getName());
```



### 子文件

特别的，关于目录下的子文件筛选

- **第一种：筛选File数组：**

```java
File[] files = file.listFiles(new FileFilter() {
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(".txt")||pathname.isDirectory();
    }
});
```

过滤器为一**匿名内部类**，实现**接口**`new FileFilter()`,重写`accept`方法

方法的返回值为一个boolean类型的值，即返回值为true则该file加入files数组。所以在return处写**当前文件的文件名或文件类型需要满足的条件**



- **第二种：筛选字符串数组**

  用途和前面那个是一样的，前面的file可以通过获取父文件，拿到目录名称，所以建议用第一种

```java
file.list(new FilenameFilter() {
    public boolean accept(File dir, String name) {
        System.out.println("dir是目录 test"+dir.getName());
        System.out.println("name是子文件的名称"+name);
        return true;
    }
});
```



## IO

### 1、[打印流]()

Scanner，用法类似于**iterator**

```java
InputStream is  = System.in;

Scanner scanner = new Scanner(is);
String next = scanner.next();
```



### 2、[文件流]()

- 读

```java
FileInputStream is = new FileInputStream("/a/b.txt");
byte[] b = new byte[1024];
int len = is.read(b);//len获取的是读取的输入流的长度
String str = new String(b, 0, len);
```

- 写

```java
FileOutputStream fos = new FileOutputStream("D:/output");
fos.write(bytes);//输入前面从输入流读取的内容
fos.write("输出到文件D:/output".getBytes());
```



### 3、[缓冲流]()

InputStreamReader和OutputStreamWriter核心都是调用IO流的read和write，因为前面文件流的读写委实不是很方便，包装成缓冲流，读写内容整合。

- 读

```java
FileInputStream fis = new FileInputStream("/input");
BufferedReader br = new BufferedReader(new InputStreamReader(fis));
String s ;
while ((s = br.readLine())!=null){
    System.out.println(s);
}
```

- 写

```java
FileOutputStream fos = new FileOutputStream("/output");
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
bw.write("输出这串内容到文件中");
```





## Properties

和前面不一样的是，这种方式加载的流会被处理成**键值对**，使用上更加方便。

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

**核心步骤**

- p.load(fis);
- String value = p.get("key");

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

