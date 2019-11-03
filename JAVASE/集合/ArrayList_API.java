
import java.util.*;

public class ArrayList_API{

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("5");
        a.add("4");
        a.add("3");
        System.out.println(a);
        //a.remove(1);//a[1] == 4
        a.set(1,"123456");
        a.indexOf("1");
        System.out.println(a);

        ListIterator<String> ite = a.listIterator();
        while(ite.hasNext()){
            /*
            *通过迭代器获得index，再用ArrayList的API进行修改
            *只有next会影响迭代器的位置：
            *iter(next)：    -0.5  0.5  1.5
			
			*nextIndex获取迭代器向后取整的索引，但不改变迭代器的位置
            *nextIndex： 0    1    2
            */

            a.set(ite.nextIndex(),a.get(ite.nextIndex())+"_1");

            ite.next();
        }
        System.out.println(a);


    }

}
