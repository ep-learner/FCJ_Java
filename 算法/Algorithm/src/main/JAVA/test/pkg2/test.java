package test.pkg2;
import test.pkg1.child;
public class test extends child {
    public void test(){
        test t1 = new test();
        t1.child();
    }
    public static void main(String[] args) {
        test test = new test();
        test.child();
    }
}
