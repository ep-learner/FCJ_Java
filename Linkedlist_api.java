
import java.util.*;

public class Linkedlist_api{

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.set(0,123465);
        ll.get(0);

        
        /*
        处理链尾
         */
        ll.add(3); //添加在链尾
        System.out.println("删除链尾元素"+ ll.remove(ll.size()-1));

        /*
        * push pop peek 都是对于链表头进行操作
        * */
        System.out.println("peek对应linklist的索引为0的值"+ll.indexOf(ll.peek()));
        System.out.println("pop同样是索引为0的值"+ll.pop());
        ll.push(362); //"将元素加载linkedlist的首部"
        System.out.println("删除队尾元素"+ ll.remove(ll.size()-1));
        
        Iterator<Integer> ite = ll.iterator();
        while(ite.hasNext()){
            System.out.println("打印链表内容"+ite.next().toString());
        }


    }

}
