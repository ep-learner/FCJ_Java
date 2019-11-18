package Tree.test;

import Tree.base.TreeNode;
import Tree.isValidBST;

public class Main_Test {
    public TreeNode prepared_data(){
        /**
         *     3
         *   1   5
         *  0 2 4 6
         *     3
         */
        TreeNode t0 = new TreeNode(3);
        TreeNode t10 = new TreeNode(1);
        TreeNode t11 = new TreeNode(5);
        TreeNode t20 = new TreeNode(0);
        TreeNode t21 = new TreeNode(2);
        TreeNode t22 = new TreeNode(4);
        TreeNode t23 = new TreeNode(6);
        TreeNode t33 = new TreeNode(3);
        t0.left =t10;
        t0.right =t11;
        t10.right =t21;
        t10.left =t20;
        t11.right =t23;
        t11.left =t22;
        t21.right = t33;
        return t0;
    }
    public static void main(String[] args) {
        TreeNode t0 = new Main_Test().prepared_data();
        /**
         * 层序遍历
         * baselayer b = new baselayer();
         * b.baselayer(t0);
         *
         */
        /**
         * 测试镜像二叉树
         * 迭代
         * boolean is = new isSymmetric().isSymmetric(t0);
         * System.out.println(is);
         *
         * 递归
         * System.out.println(new isSymmetric().helper2(t0,t0));
         */
//        System.out.println(new isValidBST().isValidBST(t0));

    }
}
