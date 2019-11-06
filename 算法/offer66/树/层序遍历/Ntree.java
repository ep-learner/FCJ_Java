/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
/*
基本思路：使用temp1和temp2数组逐层遍历树
每个节点都有一个子节点数组，还得使用迭代器处理之后放入temp和temp1/2
每一次遍历完一层把val数组（temp）添加到result中

特别注意temp需要清空，但是因为该引用还是指向原来的位置，如果调用clear会把result的内容也清空，所以重新new对象赋给temp
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();//结果
        List<Integer> temp = new ArrayList<>();        
        List<Node> temp1 = new ArrayList<>();
        List<Node> temp2 = new ArrayList<>();
        
        temp1.add(root);
        if(root==null){
            return new ArrayList<>();
        }
        temp.add(root.val);
        result.add(temp); 
        List<Node> l = null;
        Node n1 = null;
        Node n = null;
        int tag = 0;

        //循环取出temp1的节点,放入temp2,达到层序遍历的作用
        //这里不仅仅是层序遍历，同一层的还需要放进同一个list temp
        while(temp1.size()!=0 || temp2.size()!=0){
            temp = new ArrayList<>();
            while(temp1.size()!=0){
                
                n = temp1.remove(0);
                 
                //获得取出的节点n，遍历节点的子节加入temp2，并将相应的v加入temp
                if(n.children.size()!=0){
                    tag = 1;
                    Iterator ite = n.children.iterator();
                    
                    while(ite.hasNext()){
                        n1 = (Node)ite.next();
                        //System.out.println("temp1  "+n1.val);
                        temp2.add(n1);
                        temp.add(n1.val);
                    }
                }
            }
            if(tag == 1){
                result.add(temp);
                //System.out.println("res" + result);
                tag = 0;
                
            }
            temp = new ArrayList<>();
            while(temp2.size()!=0){
                
                n = temp2.remove(0);
                
                //获得取出的节点n，遍历节点的子节加入temp2，并将相应的v加入temp
                if(n.children.size()!=0){
                    tag = 1 ;
                    Iterator ite = n.children.iterator();
                    
                    while(ite.hasNext()){
                        n1 = (Node)ite.next();
                        
                        temp1.add(n1);
                        temp.add(n1.val);
                        
                    }
                }
            }
            if(tag == 1){
                result.add(temp);
                tag = 0; 
            }
        }
        return result;


    }
}
