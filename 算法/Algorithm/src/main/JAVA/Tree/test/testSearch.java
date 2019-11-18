package Tree.test;

import Tree.base.TreeNode;
import Tree.search.searchDemo;

public class testSearch {

    public static void main(String[] args) {
        TreeNode t0 = new Main_Test().prepared_data();

        new searchDemo().findMin(t0);
        while (t0.left!=null){
            System.out.println(t0.val);
            t0 = t0.left;
        }
    }
}
