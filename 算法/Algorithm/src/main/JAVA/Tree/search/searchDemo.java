package Tree.search;

import Tree.base.TreeNode;

public class searchDemo {

    public TreeNode findMin(TreeNode root){
        while (root.left!=null){
            root = root.left;
        }
        return root;
    }

    public TreeNode findMax(TreeNode root){
        TreeNode current = root;
        //从头到尾对于内存空间的值没有进行修改，仅仅访问，所以不需要多定义一个变量
        while (root.right !=null){
            root = root.right;
        }
        return root;
    }


}
