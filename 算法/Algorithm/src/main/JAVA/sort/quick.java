package sort;

public class quick<T extends Comparable<T>> extends demo<T> {
    @Override
    public void sort(T[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(T[] a, int low, int high) {
        if(low>=high) return;
        int mid = partition(a,low,high);
        sort(a,low,mid-1);
        sort(a,mid+1,high);

    }

    public int partition(T[] a, int low, int high){
        /**
         * 类似选择排序，每次调整一个
         * 但是因为调到中间，所以得用递归
         */
        int i = low;
        int j = high + 1;
        while (true){
            //不能用j--，因为找到符合条件的j，这之后再改变j的值，逻辑出错，找到一个未必符合条件的值
            //查找范围是 low+1 --- high
            while(less(a[low],a[--j])) if(j<=low) break;
            while(less(a[++i],a[low])) if(i>=high) break;
            if(i>=j) break;
            swap(a,i,j);
        }
        swap(a,low,j);
        return j;

    }
}
