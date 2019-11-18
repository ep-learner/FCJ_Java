# 目的

记录JAVA的基本特性（代理，IO，多线程，泛型，反射等等），记录常见集合API的应用和编程中碰到的一些常见的内容

- 基本特性
  - IO
  - Lambda
  - 多线程
  - 代理
  - 反射
  - 泛型
  - 构造函数
  - 类（静态，内部类）
- 集合
  - map
  
    ```java
    hm.put(key,value);
    hm.remove(key);
    hm.get(key);
    for(Map.Entry<String, Integer> entry:hm.entrySet()){
    	entry.getKey();
        entry.getValue();
        entry.setValue("new_value");
    }
    ```
  
    
  
  - list
  
    ```java
    a.add("value");
    a.remove(index);//不加索引，默认最后一个
    a.set(index,"value_new");
    String value = a.get(0);
    int    index = a.indexOf(value);
    ```
  
    如果遍历过程需要修改集合元素，注意使用iterator，且注意顺序
  
    - 先用next再用add或者remove
    - 不在内部使用**集合**的add/remove，只能使用**iter**的add/remove
  
  - set
- 编程速查
  - 变量初始化
  - 取整
  - protected
  - 
- 