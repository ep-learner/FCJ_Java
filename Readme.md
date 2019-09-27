# 目的
介绍JAVA使用过程中一些常用的集合API，或者一些容易遗漏的点

## MAP
一般情况下用hashmap会比Treemap快，尽可能选择使用hashmap

hashmap实现排序可以借助ArrayList实现；

常用的api：
```
put(k,v)
get(k)
replace(k,v)//相当于set方法
remove(k)
merge(k,para,(old,par)->old+par)
```

遍历方法：
```
keySet
entrySet
```

## List
常用的是ArrayList和LinkedList
```
add(i,v)
remove(i)
set(i,v)
get(i)
indexof(v)
iterator()
listIterator()
```
```
//LinkedList特有的方法
pop()
peek()
push(val)
```

## Queue



