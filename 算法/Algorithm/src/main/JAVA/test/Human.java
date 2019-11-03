package test;

/**
 *
 */
public class Human{
    int age;
    static  int age1;
    Human(){
    }
    public  Human newInstance(int age){
        System.out.println(age1);
        age1 = age;
        return this;
    }

    public static void main(String[] args) {
        Human human = new Human();
    }
}
