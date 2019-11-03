package sort;

public class insert<T extends Comparable<T>> extends demo<T> {


    public void sort(T[] a, int low, int high) {
        for (int i=1;i<=high;i++){
            /**
             * 每一次完成把a[i]放入正确位置，并把前面比a[i]大的依次后移
             */

            for (int j=i; j>=1 && less(a[j],a[j-1]); j--){
                swap(a,j,j-1);
            }
        }
    }
    @Override
    public void sort(T[] a) {
        sort(a,0,a.length -1);
    }
}
