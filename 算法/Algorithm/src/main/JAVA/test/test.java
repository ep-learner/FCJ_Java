package test;

public class test {
    /**
     * 构造函数和成员变量的关系
     */
    static int i1  = 1 ;
    protected int i2  = 2 ;
    test(){
        System.out.println("构造函数"+i1);
    }
    class inner{
        inner(){
            System.out.println("这是内部类构造函数");
        }
    }
    public  void test(int a){
        System.out.println("和类名相同的方法");
        static_test sta = new static_test();
        sta.main(new String[]{});

    }

    public static void main(String[] args) {

        static_test sta = new static_test();
        sta.main(new String[]{});

    }

}
