# Lambda

## lambda实现了把"一块代码"赋值给某个变量；

[参考](https://mp.weixin.qq.com/s/LOpSzJ0P8JBaxvyl_QxXFA)

一块代码

```java
public void func(String S){
    System.out.println(S);
}
```

我们想实现：

```java
myInterface aBlockOfCode = public  void func(String S){
    System.out.println(S);
}
```

上面太复杂了，所以做了简化：

```markdown
首先public不需要；
返回值和参数类型，编译器是可以自己判断的；
函数名func更不需要了，既然是把这块代码赋值给aBlockOfCode，过渡用的函数名没什么意义；
只有一行，所以{}也不需要了；
```

最后剩下来的就是Lambda了：

```
myInterface aBlockOfCode = (S)->System.out.println(S);
```

补充

之前能把{}的前提是只有一行代码；

- 我们能简化的是那种，接口只有**一个函数**需要实现的接口，称之为函数式接口；

- JAVA有个注释是专门针对这个的：**@FuctionalInterface**





## Lambda实现匿名类

[参考](http://how2j.cn/k/lambda/lambda-lamdba-tutorials/697.html#nowhere)

筛选集合里面满足某个条件的成员：

```java
/**
常规方法：定义一个功能函数，main函数调用
匿名类：定义一个接口，匿名类实现该接口的筛选方法；调用该方法，实现筛选
Lambda：把匿名类简化之后的简介代码；1.8之后支持
*/
//匿名类
HeroChecker checker = new HeroChecker() {
    @Override
    public boolean test(Hero h) {
        return (h.hp>100 && h.damage<50);
    }
}
filter(heros,checker);

//Lambda
filter(heros,h->h.hp>100 && h.damage<50);

```



更一般的

```java
//实现ComparTo的Lambda
Comparator<Hero> c =(h1,h2)->h1.hp>=h2.hp?1:-1;
Collections.sort(heros,c);
或者
Collections.sort(heros,(h1,h2)->h1.hp>=h2.hp?1:-1);
//这么写是h1.CompareTo(h2)；如果h1.hp>h2.hp，那么返回1反之为-1；
//这样调用sort的结果是升序
```



[集合](https://blog.csdn.net/hui1setouxiang/article/details/88759384)

[回调]

Comparetor 和Comparable