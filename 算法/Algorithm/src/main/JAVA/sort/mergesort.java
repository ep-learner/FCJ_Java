package sort;

public class mergesort<T extends Comparable<T>> extends demo<T> {
    @Override
    public void sort(T[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(T[] a, int low, int high) {
        if(low>=high) return;
        int mid = (low+high)/2;
        sort(a,low,mid);
        sort(a,mid+1,high);
        merge(a,low,mid,high);
    }
    public boolean less(T a,T b){
        return a.compareTo(b)<0;
    }

}
