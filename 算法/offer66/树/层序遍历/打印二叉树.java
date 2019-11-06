import java.util.ArrayList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        TreeNode temp = root;
        if(temp == null) return null;
        ArrayList<TreeNode> a = new ArrayList();
        ArrayList<Integer> b = new ArrayList();
        a.add(temp);
        b.add(temp.val);
        int count = 0;
        while(temp != null && a.size() > 0){
            if(temp.left!=null){
                a.add(temp.left);
                b.add(temp.left.val);
            }
            if(temp.right!=null){
                a.add(temp.right);
                b.add(temp.right.val);
            }
            System.out.println(temp.val);
            a.remove(0);
            System.out.println(a.size());
            //if(a.size() != 0)
            temp = a.get(0);
        }
        return b;
    }
}
