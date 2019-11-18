# css

## 语法：

- 选择器：处理的对象

- 声明：kv对，属性和属性值

  ```css
  selector {declaration1; declaration2; ... declarationN }
  
  /* 示例 */
  h1 {
      color:red; 
      font-size:14px;
  }
  
  /* 选择器分组 */
  h1,h2,h3,h4,h5,h6 {
    color: green;
    }
  ```

  

### 派生选择器

```css
/*对于嵌套的标签进行选择，注意区别，这种也称呼为派生选择器*/

li strong{/*对于列表元素且别标记为strong的元素应用样式*/
    color: red;
}

strong{/*对于所有strong元素应用*/
    color: red;
}
```



### id选择器

```css
/*id选择器 前面加#，表示该选择器为索引id*/
#id_red {color:red;}
<p id="id_red">这个段落是红色。</p>
```



id选择器和派生选择器的综合使用

```css
#id p{color:red;}

<div id="id">
	<p>这个段落是红色。</p>
</div>
```

实质是一个派生选择器，前面将一个id选择器作为限制条件，只有id为"id"的元素内的段落p才被应用样式



### 类选择器



```css
.center {text-align: center}/*前面添加“.”表示该选择器为类选择器*/
<h1 class="center">标题是居中的</h1>
<p class="center">段落是居中的</p>

p.center {text-align: center}/*限制类的主体标签*/
<h1 class="center">标题不是居中的</h1>
<p class="center">段落是居中的</p>
```



同样类选择器也可以被应用成派生选择器

```css
/*和前面id的用法一致*/
.center p{text-align: center}
<div class="center">
	<p>这个段落是居中的</p>
</div>
```

### [属性选择器](https://www.w3school.com.cn/css/css_syntax_attribute_selector.asp)

## 应用

### 外部样式表

- 组成
  - css样式表：**mystyle.css**
  - html应用

```html
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css" />
</head>
```

- 场景：全局应用某个样式表

### 内部样式表

- 直接在html的head标签内写css

  ```html
  <head>
  <style type="text/css">
    h {color: sienna;}
    p {margin-left: 20px;}
  </style>
  </head>
  ```

- 场景：对于某个html页面需要单独设置样式

### 内联样式

- 在某个标签内通过style属性，直接写css语句

  ```html
  <p style="color: sienna; margin-left: 20px">内联样式</p>
  ```

- 场景:练习的时候可能会用，实际应用不会挨个写样式的



### 优先级

同一个选择器（标签 比如h）同时出现在外部样式表，内部样式表中

应用时合并两个样式表，冲突的以内部样式表为主

## 样式

对于声明内常见的属性和属性值进行汇总

### 字体

- 字体族
- 字体样式：正常(normal)、斜体(italic)和倾斜(oblique)
- 字体大小
- 字体粗细：bold/normal

```css
body  {
     font-family: Verdana, sans-serif;
     font-style:normal;
     font-size:14pt;
     font-weight:bold;
     }
```

