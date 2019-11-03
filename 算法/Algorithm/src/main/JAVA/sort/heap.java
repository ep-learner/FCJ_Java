package sort;

public class heap<T extends Comparable<T>> extends demo<T> {
    @Override
    public void sort(T[] a) {
        int N = a.length - 1;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);

        while (N>1){//因为没有index=0的元素，所以这里不能放等号
            swap(a,1,N--);//最大值和堆底交换，再把堆大小N减一，不再考虑那个排好序的元素
            sink(a,1,N);//刚刚把堆尾元素提上来了，所以得找到该元素的准确位置，恢复最大堆
        }
    }

    public void sink(T[] a,int k ,int n){

        int child_max;
        while(2*k+1<=n){
            //比较和子节点的大小
            child_max = less(a[2*k],a[2*k+1])?2*k+1:2*k;
            if(less(a[k],a[child_max])){
                swap(a,child_max,k);
                k = child_max;
            }
            else break;

        }

    }
}
