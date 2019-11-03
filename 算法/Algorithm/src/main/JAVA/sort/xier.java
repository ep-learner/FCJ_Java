package sort;

public class xier<T extends Comparable<T>> extends demo<T> {
    //xier被调用的时候，main函数一旦运行，那么T的类型就知道了，假如是Integer，
    // 那么这么写是告诉编译器，demo里面需要的泛型的实际类型也是Integer，
    // 不然编译器可能把它猜成别的，比如Comparable
    @Override
    public void sort(T[] a) {
        int h = 1;
        int size = a.length;
        while(h<size/3) h=3*h+1;
        while (h>=1){
            //外循环仅遍历
            for (int i =h;i<size;i++){
                for (int j = i;j>=h && less(a[j],a[j-h]);j=j-h){
                    swap(a,j,j-h);
                }
            }
            h = h/3;
        }
    }
}
