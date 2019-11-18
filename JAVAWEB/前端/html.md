# html

## 框架

- head设置
- 文章标题——h1
- 段落——p
- 链接——href
- 图像——src
- 表格——table

```html
<!DOCTYPE html>
<html>
    
 <!--head：网页配置，不显示在页面中-->   
<head>
    <style type="text/css">
        h1 {color: red}
        p {color: blue}
    </style><!--内嵌样式表-->
	<title>网页名称</title>
</head>
    
<body>
    <h1>文章标题</h1>
    
    <p>段落</p>
	
    <a href="http://www.baidu.com">链接</a>

    <img src="/pic/11.png" alt="/pic/11.png" width="300" height="120" />

</body>

</html>
```

## 标签公共属性

### id

- 配合CSS样式表（id=选择器id）

  ```html
  #id {color:red;}
  
  <p id="id">这个段落是红色。</p>
  ```

  

- 配合JS应用

  ```html
  <p id="demo">JavaScript 能够改变 HTML 内容。</p>
  
  <button type="button" onclick='document.getElementById("demo").innerHTML = "Hello JavaScript!"'>点击我！</button>
  ```

### style

- 写该标签的样式

  ```html
  <p style="color: sienna; margin-left: 20px">内联样式</p>
  ```

  

## [标签](https://www.w3school.com.cn/tags/tag_u.asp)

### title/h1

```html
<!DOCTYPE html>
<html>
<head>
	<title>网页名称</title>
</head>
    
<body>
    <h1>文章标题</h1>
</body>
</html>
```

显示效果：

![1573623213898](pic\1573623076799.png)

### 图像

```html
<img src="/i/eg_w3school.gif" alt="/eg_w3school.gif" width="300" height="120" />
```



- src:图像URI路径
- alt：图像路径，如果图像显示不出来，那么在页面上看到的就是alt的内容

### 文本



```html
<b> 黑体</b><br>
<strong>加粗</strong><br>
<big>加大</big><br>
<em>强调</em><br>
<sub>下标</sub><sup>上标</sup><br>
<i>斜体</i><br>
```



```html
<!--预格式文本-->
<pre>
它保留了      空格
和换行。
</pre>
```

### table

- table
  - 行：tr
    - 表头：th
    - 数据：td

```html
<table border="1">
	<tr>  <th>th</th>  <td>td1</td>  <td>td2</td>  </tr>

	<tr>  <th>th1</th>  <th>th2</th>  <td>td3</td>  </tr>
</table>
```



![1573623890106](C:\Users\49143\AppData\Roaming\Typora\typora-user-images\1573623890106.png)

### div

结合CSS样式表决定div的格式

```html
<!--分区division，可以对于每个分区选择样式-->
<div style="color:#00FF00">
    <h3>This is a header</h3>
    <p>This is a paragraph.</p>
</div>
```



### ul/li

无序列表，也就是md文档的'—   XX'

```html
<ul>
  <li>牛奶</li>
  <li>咖啡</li>
</ul>
```

结果：

![1573869551248](pic\1573869551248.png)

### ol/li

和前面相对应的有序列表

```html
<ol>
  <li>牛奶</li>
  <li>咖啡</li>
</ol>
```

结果

1、牛奶

2、咖啡