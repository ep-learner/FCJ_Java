# 集合API
分为三大类：**MAP List  和Set**

## MAP（K，V）
map有两种hashmap和Treemap，但一般情况下用hashmap会比Treemap快，尽可能选择使用**hashmap**

### hashmap



**hashmap实现排序可以借助ArrayList实现；**

#### 增删改查

```java
String key = "key";
int value = 1;
HashMap<String, Integer> hm = new HashMap<String, Integer>();

hm.put(key,value);
hm.remove(key);
hm.get(key);
```



#### contains

```java
hm.containsKey("id1");
hm.containsValue(1);
```



#### 遍历

- **keySet()**

```java
for (String i: hm.keySet()) {
    System.out.println(i+""+hm.get(i));
}
```

- **entrySet()**

  **Entry**的API

  - **entry.getKey()；**

  - **entry.getValue()；**

  - **entry.setValue("new_value")；**

```java
for(Map.Entry<String, Integer> entry:hm.entrySet()){
    
    System.out.println(entry.getKey()+entry.getValue());
    
    if (entry.getKey().equals("id1")) entry.setValue(1008611);
}
```



#### 排序

借助ArrayList存储hashmap，然后使用集合API。

因为是Entry所以比较器得自己写，自己决定先比较key还是先比较value。

hashmap实现排序

```java
ArrayList<Map.Entry<String, Integer>> a = new ArrayList<>(hm.entrySet());

Collections.sort(a, new Comparator<Map.Entry<String, Integer>>() {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if(!o1.getValue().equals(o2.getValue())) return o1.compareTo(o2);
        return o1.getKey().compareTo(o2.getKey());
    }
});
```



备注：比较器的写法

integer的比较器

```java
public int compareTo(Integer anotherInteger) {
    return compare(this.value, anotherInteger.value);
}

public static int compare(int x, int y) {
    return (x < y) ? -1 : ((x == y) ? 0 : 1);
}
```





#### 其他

```java
merge(key,100,(old,p)->old+p);
//merge之前：key = 1
//merge之后：key = 101
```



## List

常用的有两种：

- ArrayList
- LinkedList



### [ArrayList]()

#### 增删改

```java
ArrayList<String> a = new ArrayList();
int index = 0;
a.add("value");
a.remove(index);//不加索引，默认最后一个
a.set(index,"value_new");
```


#### 查询

- 基于key：   get
- 基于value：indexof

```java
String value = a.get(0);
int    index = a.indexOf(value);
```



#### 遍历

- **ListIterator**

  ​	nextindex：如果需要获取到下一个对象的索引

  ```java
  ListIterator<String> iter = a.listIterator();
  while(iter.hasNext()){
      int next_index = iter.nextIndex();
      iter.add
      iter.next();
  }
  ```

  可以这么理解iter：

  - 最初**iter**指向索引**-0.5**
  - nextIndex的作用是向上取整，也就是拿到索引0，但是**不改变iter所指向**的位置
  - 注意先next再add或者remove。

- **iterator**

  iterator没有nextindex的功能，但是也可以查询到当前value的index来获取其前后位置索引

  ```java
  Iterator<String> iterator = a.iterator();
  while (iterator.hasNext()){
      String next = iterator.next();
      int i = a.indexOf(next);
      System.out.println(next+""+i);
  }
  ```

- **foreach**

  本质上，在编译的时候这种方法还是会被翻译成上面iterator模式遍历列表

  限制：用这种方法，**绝对不要**在里面调用**ArrayList**的**add和remove或者clear**方法

  iter的add/remove/clear都是没问题的，所以建议使用上述iterator的方式。（原因见**集合知识点剖析**）

  ```java
  for(String str:a){
      System.out.println(s);
  }
  ```

- **toArray**

  ```java
  String[] strs = new String[a.size()]
  a.toArray(strs);
  
  ```

#### 其他

```
a.contains("value");
```



#### 总结

- 如果没有修改需求就用foreach

- 有修改需求就用**iterator**

  nextIndex这种方法没有记忆的必要，获取索引又不复杂


### LinkedList

#### 总结

虽然比起ArrayList多了很多API，但是为了方便记忆只需要额外记住push这个API就好

```java
ll.push("value");//添加在链表头
```



备注：关于poll  pop和remove

poll()：队列结构，获取**队首**元素

pop()：栈结构，弹出**栈顶**元素

remove()：不带参数时候就是弹出链表第一个元素

本来pop和poll是针对两种数据结构的，但是对于LinkedList而言，上面三种的功能一样，因为对于LinkedList而言，队首是链表头，栈顶还是链表头（因为push是加在链表头的，那么链表头就是栈顶了）。





## Set

等价于不能添加重复元素的ArrayList