# JQuery

一个JavaScript函数库

## 应用

### 1、导包

在head标签内添加js脚本的引用

```html
<head>
<script type="text/javascript" src="jquery.js"></script>
</head>
```

如果电脑没有下JQuery那么用google或者microsoft的(用完整uri代替相对路径)

```html
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>

<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery
/jquery-1.4.min.js"></script>
```

### 2、基本框架

- ready
- click
- $(seletor).action()

```html
<script>
    $(document).ready(function(){//html加载完成时触发——页面显示一个button
        $("button").click(function(){//点击时触发
            $("p").hide();//触发时对于选择器筛选出来的标签对象执行hide方法
        });
    });
</script>
```



## 基础语法

基本组成：**$(selector).action()**

可以理解为 JQuery(selector).action()



### 1、$：表示应用JQuery

如果其他的一些库比如Prototype可使用这个标识符，那么通过下述方式解决冲突

```html
<script>
$.noConflict();//noConflict方法，后续使用jQuery而不是$来标识，这样就不存在冲突
    
jQuery(document).ready(function(){
  jQuery("button").click(function(){
    jQuery("p").text("jQuery 仍在运行！");
  });
});
</script>
```



### 2、选择器：selector

类似于css的选择器

- 筛选特定元素
- 筛选特定属性
- 获得当前的调用对象this



- 元素选择器：筛选特定元素

  ```html
  <script>
      $("p").hide()       //选取 <p> 元素。
      $("p.intro").hide() //选取所有 class="intro" 的 <p> 元素。
      $("p#demo").hide()  //选取所有 id="demo" 的 <p> 元素
  </script>
  ```

- 属性选择器：筛选特定属性

  下述表示筛选出带有属性herf（链接）的标签；进一步可以筛选属性的取值

  ```html
  <script>
      $("[href]").hide()          //选取所有带有 href 属性的元素。
      $("[href='#']").hide()      //选取所有带有 href 值等于 "#" 的元素。
      $("[href!='#']").hide()     //选取所有带有 href 值不等于 "#" 的元素。
      $("[href$='.jpg']").hide()  //选取所有 href 值以 ".jpg" 结尾的元素。
  </script>
  ```

  

- $(this)：当前元素    

  ```html
  <script>
      $(document).ready(function(){
          $("button").click(function(){
              $(this).hide();//隐藏的就是当前元素button
          });
      });
  </script>
  ```



### 3、事件方法

#### 基础方法

ready和click

| 事件函数                    | 函数绑定位置                                       |
| --------------------------- | -------------------------------------------------- |
| $(document).ready(function) | 将函数绑定到文档的就绪事件（当**文档完成加载**时） |
| $(selector).click(function) | 触发或将函数绑定到被选元素的**点击事件**           |

#### css方法

```html
<script>
    $("p").css("background-color","red");
</script>
```

#### 效果/方法

##### hide/show

