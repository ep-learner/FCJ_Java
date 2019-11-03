package sort;

public class quicksort<T extends Comparable<T>> extends demo<T> {
    @Override
    public void sort(T[] a) {
        sort(a,0,a.length-1);
    }
    public void sort(T[] a,int low,int high) {
        //par
        if(low>=high) return;
        int mid = par(a,low,high);
        sort(a,low,mid-1);
        sort(a,mid+1,high);
    }
    public int par(T[] a,int low,int high){
        //
        int left = low;
        int right = high + 1;
        //右指针
        while (true){
            while (less(a[low],a[--right])) if (right<=low) break;
            while (less(a[++left],a[low])) if (left>=high) break;
            if(left>=right) break;
            swap(a,left,right);
        }
        swap(a,low,right);
        return right;
    }
}
