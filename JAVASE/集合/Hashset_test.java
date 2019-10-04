
import java.util.*;

public class Hashset_test{

    public static void main(String[] args) {
        HashSet<String> s = new HashSet<>();
        s.add("id1");
        s.add("id1");
        s.add("id2");
        System.out.println(s);//和ArrayList的区别在于它会自动去重没法添加重复的内容
        
        s.remove("id2");
        
        Iterator<String> iter = s.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println(s);

    }

}
