package OJ.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class s {
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        return helper(root,1,2);
    }
    public boolean helper(TreeNode root,int limit,int target){
        if(root==null) return true;
        /**
         * 该节点是父节点的左节点
         */
        if(target==0){
            if(root.right!=null){
                if(root.right.val>=limit || root.right.val<=root.val) return false;

            }
            if(root.left!=null&&root.left.val>=root.val) return false;
        }
        /**
         * 该节点是父节点的右节点
         */
        if(target==1){
            if(root.right!=null&&root.right.val<=root.val)return false;
            if(root.left!=null){
                if(root.left.val<=limit || root.left.val>=root.val) return false;
            }
        }
        if(target==2){
            if(root.right!=null&&root.right.val<=root.val)return false;
            if(root.left!=null&&root.left.val>=root.val) return false;

        }

        return helper(root.left,root.val,0)&&helper(root.right,root.val,1);
    }
}
