# AJAX

## 简介

AJAX的目的是在**不重新载入**整个页面的基础上，从**服务器**获取数据**部分更新**网页

AJAX = 异步JS + XML

## XHR

XMLHttpRequest

AJAX是从服务器取数据来进行页面的局部更新，所以需要一个XMLHttpRequest对象。

### 创建XHR对象

```javascript
var xmlhttp=new XMLHttpRequest();
```

### 和服务器交互

- 规定请求类型

```javascript
open(method,url,async)
```

| method | 含义                         |
| ------ | ---------------------------- |
| method | 请求类型：GET 或 POST        |
| url    | 文件在服务器上的位置         |
| async  | true（异步）或 false（同步） |

- 发送请求

  将请求发送到服务器。

```html
send(String);
```

*string*：仅用于 POST 请求

### 响应

通过XHR对象的两个成员变量(只是返回值的类型不同)获得响应的结果

| 属性         | 描述                       |
| ------------ | -------------------------- |
| responseText | 获得字符串形式的响应数据。 |
| responseXML  | 获得 XML 形式的响应数据。  |

- Text类型响应

```html
var result = xmlhttp.responseText;
document.getElementById("myDiv").innerHTML=result;
```

- XML类型响应

  需要对xml对象进行解析

```html
var xmlDoc=xmlhttp.responseXML;

x=xmlDoc.getElementsByTagName("title");
x[i].childNodes[0].nodeValue;
```

### [readystate](https://www.w3school.com.cn/ajax/ajax_xmlhttprequest_onreadystatechange.asp)待续





```html
<script type="text/javascript">
    function loadXMLDoc()
    {
        var xmlhttp;
        //创建一个XMLHttpRequest对象，并完成赋值
        if (window.XMLHttpRequest){xmlhttp=new XMLHttpRequest();}
        else{xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}


        xmlhttp.onreadystatechange=function(){
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET","/ajax/demo_get.asp",true);
        xmlhttp.send();
    }
</script>

<button type="button" onclick="loadXMLDoc()">请求数据</button>
<div id="myDiv"></div>
```

