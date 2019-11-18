# javascript

## 作用

- 改变html标签内容

  ```html
  <p>标签内容</p>
  ```

- 改变html标签属性

  ```html
  <img src="标签属性" />
  ```

## 应用

- JS代码写在head或者body中均可,此时写在标签script内

  ```html
  <html>
      <body> 
          <p id="demo">一个段落</p>
          <button type="button" onclick="myFunction()">修改段落内容</button>
          <script>
              function myFunction() {
                  document.getElementById("demo").innerHTML = "段落被更改。";
              }
          </script>
      </body>
  </html>
  ```



- 外部脚本

  本地的js通过相对地址访问

  ```html
  <script src="/js/myScript1.js"></script>
  ```

  

- 外部引用

  通过完整的URI来访问

  ```html
  <script src="https://www.w3school.com.cn/js/myScript1.js"></script>
  ```

  

## 输出

### 输出到标签内容

修改`innerHTML`属性

```html
<script>document.getElementById("demo").innerHTML = 5 + 6;</script>
<p id="demo">标签内容被前面脚本的输出覆盖</p>
```

### 输出在html界面

`document.write()`

- 打印到html界面
- **仅用于调试**，因为如果html已经加载再调用会清空html内容，如示例二

```html
/*示例一*/
<script>document.write(5 + 6);</script>

/*示例二*/
<button onclick="document.write(5 + 6)">点击该按钮会清空现有的html界面</button>
```

### 输出弹窗

```html
<script>window.alert("hello ");</script>
```

### 输出到控制台

```html
<script>console.log(5 + 6);</script>
```

## 语法

- 和java语法差不太多，后面侧重介绍和java的区别

### 代码示例

- 变量统一声明为var

```html
<script type="text/javascript">
    var a, b, c;
    a = 5; b = 6; c = a + b;
    document.getElementById("demo1").innerHTML = c;
</script>
```



### 代码块（函数）

- 有关键词function
- 没有返回值类型

```html
<html>
<body>
	<p id = "demo">段落内容被修改为6（2*3）</p>
	<script type="text/javascript">
		var x = myFunction(2,3);
		document.getElementById("demo").innerHTML = x;
		function myFunction(a,b) {
			return a * b;
		}
	</script>
</body>

</html>
```



### 对象

- 通过**多个key :value**的形式定义对象
  - key是对象的成员标识
  - value可以是字符串，int或者**函数**

```html
<script>
// 创建对象：
var person = {
    firstName: "Bill",
    lastName : "Gates",
    id       : 12345,
    fullName : function() {
       return this.firstName + " " + this.lastName;
    }
};

// 显示对象中的数据：
document.getElementById("demo").innerHTML = person.fullName;
</script>
```

### 数组

- 定义

  ```javascript
  var points = [];
  var fruits = ["Banana", "Orange"];
  ```

  

- API

  - 长度（数组的成员变量 length ）

    ```javascript
    var len = fruits.length;
    ```

  - 数组元素获取(和java一样)

    ```javascript
    var mem = fruits[i] ;
    ```

  - 添加元素：除了类似java的添加之外，还有push的API

    ```javascript
    fruits[3] = "hahaha";//输出结果：["Banana", "Orange", "undefined", "hahaha"]
    fruits.push("Lemon");//输出结果：["Banana", "Orange", "undefined", "hahaha","Lemon"]
    ```

demo：

```html
<script>
    var fruits,len,i,text;
    fruits = ["Banana", "Orange"];
    fruits[3] = "hahaha";
    fruits.push("Lemon");
    len = fruits.length;
    text = "<ol>";
    for (i = 0; i < len; i++) {
        text += "<li>" + fruits[i] + "</li>";
    }
    text += "</ol>";
</script>
```

#### 数组和对象的区别

- 在 JavaScript 中，**数组**使用**数字索引**。

- 在 JavaScript 中，**对象**使用**命名索引**。

**数组可以看成是特殊的对象**，它有一些特殊的成员变量长度，添加元素等等。。

- length，push()（对象会丧失这些API）
- 用数字当成索引（你要自定义一个对象，其成员变量的key为数字那么效果相似）

### 事件

事件类型

![1573906289069](pic\1573906289069.png)

- 最常用的就是onclick，当button被点击时生效

  ```html
  <button onclick="document.getElementById('demo').innerHTML=Date()">时间是？</button>
  ```



### [待续](https://www.w3school.com.cn/js/js_arrays.asp)