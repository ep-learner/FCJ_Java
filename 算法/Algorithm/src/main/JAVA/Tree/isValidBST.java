package Tree;

import Tree.base.TreeNode;

public class isValidBST {
    public boolean isValidBST(TreeNode root) {

        /**
         * 验证是否为二叉搜索树
         */

        if(root==null) return true;
        return helper(root,null,null);
    }
    public boolean helper(TreeNode root, Integer lower, Integer upper){
        if(root==null) return true;
        int val =root.val;
        if(lower!=null&&val<=lower) return false;
        if(upper!=null&&val>=lower) return false;
        if(!helper(root.left,val,upper)) return false;
        if(!helper(root.right,lower,val)) return false;
        return true;


    }
}
