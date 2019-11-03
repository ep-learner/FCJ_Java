package OJ.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class baselayer {

    public void baselayer(TreeNode root){
        /**
         * 层序遍历
         * 双队列：A，B
         */
        /**
         *     10
         *   5   15
         *  7 8 6 20
         */
        if(root == null) return;
        ArrayList<TreeNode> A = new ArrayList<>();
        ArrayList<TreeNode> B = new ArrayList<>();
        A.add(root);
        while (!A.isEmpty() || !B.isEmpty()){
            while (!A.isEmpty()){
                TreeNode temp = A.remove(0);
                if(temp==null) continue;
                System.out.println(temp.val);
                B.add(temp.left);
                B.add(temp.right);
            }
            while (!B.isEmpty()){
                TreeNode temp = B.remove(0);
                if(temp==null) continue;
                System.out.println(temp.val);
                A.add(temp.left);
                A.add(temp.right);
            }
        }
    }
}
