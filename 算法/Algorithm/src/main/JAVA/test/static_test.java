package test;

public class static_test extends  test{
    static  int static_age = 0;
    int non_static_age;

    public static void func2(int age){
        static_age = age;
    }
    public void func1(int age){
        non_static_age = age;
        super.i2 =age;
    }
    public static class static_inner{
        int a = 5;

        public void main(String[] args) {

        }
    }
    public class non_static_inner{
        public  void main(String[] args) {
            System.out.println(static_age);
            static_inner si = new static_inner();

        }
    }
    public static void main(String[] args) {
    }
}
