package sort;

public abstract  class demo<T extends Comparable<T>>{
    /**
     * less swap
     * abstract: sort
     */
    T[] ts ;
    public abstract void sort(T[] a);
    public void swap(T[] A,int low,int high){
        T temp = A[low];
        A[low] = A[high];
        A[high] = temp;
    }

    /**
     * 功能：若a<b 则返回true
     * @param a
     * @param b
     * @return
     */
    public boolean less(T a,T b){
        return a.compareTo(b)<0;
    }

    /**
     * 把两个有序数组归并
     */
    public  void merge(T[] a,int low,int mid,int high){
        T[] temp = a.clone();
        int left =low;
        int right = mid+1;
        for (int i=low;i<=high;i++){
            temp[i] = a[i];
        }
        for (int i=low;i<=high;i++){
            if(left>=mid+1) a[i] = temp[right++];
            else if(right>high) a[i] = temp[left++];
            else if(less(a[left],a[right])) a[i] = temp[left++];
            else a[i] = temp[right++];
        }

    }

}
