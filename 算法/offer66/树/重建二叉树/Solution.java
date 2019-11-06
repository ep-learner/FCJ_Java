/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.Arrays;
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0)
            return null;
        TreeNode T = new TreeNode(pre[0]);
        int count = 0;

        for (int i=0;i<in.length;i++) 
        {
            if (in[i] == pre[0]) {
                count = i;
                System.out.println(count);
                break;
            }
        };
        T.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, count + 1), Arrays.copyOfRange(in, 0, count));
        T.right = reConstructBinaryTree(Arrays.copyOfRange(pre, count+1,pre.length), Arrays.copyOfRange(in, count+1, in.length));
        return  T;
    }
}
