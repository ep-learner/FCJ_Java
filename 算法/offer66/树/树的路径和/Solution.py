class Solution:
    # 返回二维列表，内部每个列表表示找到的路径
    def FindPath(self, root, expectNumber):
        # write code here
        result = []
        path = []
        def helper(root,count):
            if not root : return
            count += root.val
            path.append(root.val)
            if root.left==None and root.right==None:
                if count == expectNumber:
                    result.append(path[:])
                    #3、把list作为元素加入其它list中  不能形如
                    #result.append(path)
            else:
                helper(root.left,count)
                helper(root.right,count)
            
            path.pop()
            #1、每次退回上一节点需要pop掉path里的root，为什么count不需要减？
            #当前的count在出了这层函数之后生命周期结束，返回上一层，变量名仍然是count，但还没有加上叶子节点val的值。
            #path = [] 在类外定义，作用域不一样，每一次返回需要pop list顶层的值
            
            #2、注意path.pop的位置，为了减少递归次数，碰到叶子节点不再递归，循环不会进入else，不能写成下述形式
            #else:
            #    helper(root.left,count)
            #    helper(root.right,count)
            #    path.pop()
            
        helper(root,0)
        return result
