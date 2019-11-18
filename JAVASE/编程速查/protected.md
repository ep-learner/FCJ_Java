# protected

https://blog.csdn.net/blacktal/article/details/81198579

## 1、同包内的类可访问

此时protected等价于public

## 2、子类内部可访问

- 首先必须在子类内部
- 其次主调必须是子类，而不能是父类

父类

```java
package test.pkg1;

public class child {
    protected int i = 0;
    protected void  child(){
        System.out.println("protected");
    }
}

```



不同包下的子类

```java
package test.pkg2;
import test.pkg1.child;
public class test extends child {
    public void test(){
        /*下述方式会报错*/
        //child c1 = new child();
        //c1.child();
    }
    public static void main(String[] args) {
        test test = new test();
        test.child();
    }
}

```



# 其他修饰符

|                | public | protected | default | private |
| -------------- | ------ | --------- | ------- | ------- |
| 同一class      | 1      | 1         | 1       | 1       |
| 同包下的子类   | 1      | 1         | 1       |         |
| 同包下非子类   | 1      | 1         | 1       |         |
| 不同包下的子类 | 1      | 1         |         |         |
| 不同包下非子类 | 1      |           |         |         |

 

- default 在不同包下访问权限等同private

- protected  运行子类和同包下的类使用。