import java.util.*;

public class Treemap_APi {

    public static void main(String[] args) {
        TreeMap<String, Integer> t = new TreeMap<>();
        t.put("id1",8);
        t.put("id2",5);
        t.put("id3",9);
        t.remove("id1");
        t.get("id2");
        /*
        使用ArrayList进行排序，用匿名内部类定义排序规则，比如按val或者key排序
        排序之后map的顺序不会改变，但是可以通过arrayList访问排序之后的结果
         */
        ArrayList<Map.Entry<String, Integer>> a = new ArrayList<>(t.entrySet());

        Collections.sort(a, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        System.out.println(a.get(0).getValue());//打印最小值

        for (Map.Entry<String, Integer> i:t.entrySet()) {
            System.out.println(i.getKey());
            System.out.println(i.getValue());
            if (i.getKey().equals("id3"))
            i.setValue(123456);

        }
    }
}
