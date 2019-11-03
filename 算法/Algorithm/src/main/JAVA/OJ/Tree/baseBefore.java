package OJ.Tree;

import java.util.Stack;

public class baseBefore {
    /**
     * 递归时遍历
     */
    public void baseBefore(TreeNode root){
        if(root == null) return;
        System.out.println(root.val);
        baseBefore(root.left);
        baseBefore(root.right);
    }

    /**
     * 非递归
     */
    public void baseBefore(TreeNode root,int s1){
        Stack<TreeNode> s = new Stack<>();
//        //Step1、目的是遍历，那就让root先沿一个方向访问至底
//        while(root!=null){
//            root = root.left;
//        }
//
//        //到达底部之后怎么退回到上一节点？stack就是帮你保留前面路径上的节点信息的
//        //Step2、首先我们得在前面访问过程中把节点加载stack里面,对于Step1做了改进
//        while(root!=null){
//            s.push(root);
//            root = root.left;
//        }
//        //Step3、循环退出时，root = null；而stack的栈顶元素恰好是最后一个非空的元素，所以把该值赋予root变量
//        root = s.pop();
//
//        //Step4、但这个节点在stack里面，自然是已经访问过的，再有既然左边走不通，走右边
//        root = root.right;

        //Step5、重复步骤2,3,4，换种说法，给2,3,4套一个外循环
        while (root!=null || !s.isEmpty()){
            while(root!=null){
                System.out.println(root.val);
                s.push(root);
                root = root.left;
            }
            //Step3、循环退出时，root = null；而stack的栈顶元素恰好是最后一个非空的元素，所以把该值赋予root变量
            root = s.pop();

            //Step4、但这个节点在stack里面，自然是已经访问过的，再有既然左边走不通，走右边
            root = root.right;
        }
    }

}
