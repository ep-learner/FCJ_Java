package sort;

public class test {
    public static void main(String[] args) {
        Integer[] arr = {123, 5, 3, 9, 6,8};
        insert s1 = new insert();
        xier<Integer> s2 = new xier<>();
        mergesort<Integer> s3 = new mergesort<>();
        quick<Integer> s4 = new quick<>();
        quicksort<Integer> s5 = new quicksort<>();
        heap<Integer> s6 = new heap<>();
        s6.sort(arr);

        for (int i =1;i<arr.length;i++){

            System.out.println(arr[i]);
        }

    }
}
