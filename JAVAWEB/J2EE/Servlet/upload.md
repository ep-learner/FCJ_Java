# README
## 简介
文件上传借助了fileitem的API，下面简述fileitem的处理流程


1、factory：(解析器工厂)

1)设置内存缓冲区大小：**setSizeThreshold**

`factory.setSizeThreshold(1024 * 1024);`
    
2)设置临时文件目录（上传文件过大时，将文件缓存至指定目录）：**Repository（new file）**

factory.setRepository(new File("D:\\my_dir\\"));


2、servlet-file-upload：(文件上传的配置)

1)设置编码格式，设置单个文件大小：

`upload.setFileSizeMax(1024*1024);upload.setHeaderEncoding("UTF-8");`

2)解析req对象，得到fileitem列表：

```
List<FileItem> items = upload.parseRequest(req);

这里得到一个fileitem的List，接着就能使用fileitem的API进行文件的处理
```


3、fileitem

1）判断item类型，决定处理方式：

```
item.isFormField()

判断item是否为二进制文本
```

2）获取文件输入流：

`InputStream is = item.getInputStream();`

后续就是文件的读写了

