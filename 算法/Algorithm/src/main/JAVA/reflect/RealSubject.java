package reflect;

public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("我要把房子租出去");
    }
    @Override
    public void hello(String str) {
        System.out.println("hello"+str);
    }
}
