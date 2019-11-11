
import java.util.*;

public class hashmap_sort{

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("id1", 5);
        hm.put("id2", 5);
        hm.put("id3", 5);
        System.out.println("初始hashmap：" + hm);

        ArrayList<Map.Entry<String, Integer>> a = new ArrayList<>(hm.entrySet());

        Collections.sort(a, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(!o1.getValue().equals(o2.getValue()))
                {
                    System.out.println("value不相等,比较value:"+o1.getValue()+"  "+o2.getValue());
                    return o1.getValue()-o2.getValue();
                }
                System.out.println("value相等，比较key:"+o1.getKey()+"  "+o2.getKey());
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        System.out.println(a.get(0).getKey());//打印最小值

    }

}
