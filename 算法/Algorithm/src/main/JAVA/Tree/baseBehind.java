package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class baseBehind {
    public  void baseBehind(TreeNode root){
        if(root==null) return;
        baseBehind(root.left);
        baseBehind(root.right);
        System.out.println(root.val);
    }
    public  ArrayList<Integer> baseBehind(TreeNode root,int n){
        Stack<TreeNode> s = new Stack<>();
        ArrayList<Integer> ali = new ArrayList<>();
        while (!s.isEmpty() || root !=null){
            while (root!=null){
                ali.add(root.val);
                s.push(root);
                root = root.right;
            }
            root = s.pop();
            root = root.left;
        }
        Collections.reverse(ali);
        return ali;
    }

}
