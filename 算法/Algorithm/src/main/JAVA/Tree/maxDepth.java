package Tree;

public class maxDepth {
    public  void recur(TreeNode A,int layer){
        if(A==null) return;
        recur(A.left,layer+1);
        recur(A.right,layer+1);
    }
    public  int recur(TreeNode A){
        if(A==null) return 0;
        int left = recur(A.left);
        int right = recur(A.right);
        return Math.max(left,right) + 1;
    }

    public static void main(String[] args) {

    }
}
