package Tree;

import Tree.base.TreeNode;

import java.util.ArrayList;

/**
 * 是否为对称二叉树
 */
 class isSymmetric {
    public boolean isSymmetric(TreeNode root){
        if(root==null) return true;
        ArrayList<TreeNode> arr1 = new ArrayList<>();
        ArrayList<TreeNode> arr2 = new ArrayList<>();
        arr1.add(root);
        while (!arr1.isEmpty() || !arr2.isEmpty()){
            while (!arr1.isEmpty()){
                TreeNode temp = arr1.remove(0);
                if(temp==null) continue;
                arr2.add(temp.left);
                arr2.add(temp.right);
            }
            if(!arr2.isEmpty()&&!helper(arr2)) return false;
            while (!arr2.isEmpty()){
                TreeNode temp = arr2.remove(0);
                if(temp==null) continue;
                arr1.add(temp.left);
                arr1.add(temp.right);
            }
            if(!arr1.isEmpty()&&!helper(arr1)) return false;
        }
        return true;

    }
    public boolean helper(ArrayList<TreeNode> a){
        /**
         * 比较arraylist是否为对称
         */
        if(a.size()==0 || a.size()==1) return  true;

        int left = 0;
        int right = a.size()-1;
        while (left<right){
            TreeNode l = a.get(left);
            TreeNode r = a.get(right);
            if(l==null && r==null) {
                left++;
                right--;
                continue;
            }
            if(l==null||r==null) return false;
            if(a.get(left).val != a.get(right).val){
                return false;
            }
            left++;
            right--;
        }
        return  true;
    }
    /**
     * 该问题难点是难以定位节点对应的镜像节点：
     * 这个问题的子问题不是左节点和右节点相同，因为除了root外，其他节点的子节点都不是对应的镜像节点
     *
     * 要写递归就用root的拷贝和root比较，这样逻辑就能走得通了
     *
     */
    public boolean helper2(TreeNode left ,TreeNode right){
        if(left==null&&right==null) return true;
        if(left==null||right==null) return false;
        return left.val==right.val&&helper2(left.left,right.right)&&helper2(left.right,right.left);
    }


}
