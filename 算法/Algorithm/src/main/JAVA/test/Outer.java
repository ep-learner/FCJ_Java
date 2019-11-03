package test;
/**
 * 内部类和外部类成员变量如何访问
 * 静态内部类的访问限制(不能直接访问)
 */
public class Outer {
    static  int a1 = 0;
    int a = 0;
    public void o_func(){
        inner inner = new inner();
    }
    static class inner{
        public void  i_func(){

            System.out.println(a1);
        }
    }
}
