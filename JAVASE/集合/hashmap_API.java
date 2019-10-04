import java.util.*;

public class hashmap_API {

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("id1",1);
        hm.put("id2",2);
        hm.put("id3",3);
        System.out.println("初始hashmap："+hm);

        hm.remove("id3");
        
        hm.get("id1");

        hm.replace("id3",6);//假如没有这个key不会添加

        hm.containsKey("id1");

        hm.merge("id1",10086,(old,par)->(Integer)old +(Integer)par);
        System.out.println("merge 将id1对应的值1（old），应用函数加上par（10086）："+hm);

        /*
        * map需要key才能遍历，keySet获取key集合
        * 遍历keySet，操作map的value
         */
        for (String i: hm.keySet()) {
            System.out.println(i);
            System.out.println(hm.get(i));
        }
        /*
        * entry的API
        * getKey
        * getValue
        * */
        for(Map.Entry<String, Integer> i:hm.entrySet()){
            System.out.println("打印set的key："+i.getKey());
            System.out.println("打印set的value："+i.getValue());
            if (i.getKey().equals("id1")){
                i.setValue(1008611);
                System.out.println("打印修改过后的value"+i.getValue());
            }
        }

        hm.computeIfAbsent("java", (k)->((String)k).length());
        //这个方法可以构建val和key之间的关系；
        //当然也可以使用两个变量k，v循环put
        System.out.println("添加java键后的map"+hm);
    }
}
