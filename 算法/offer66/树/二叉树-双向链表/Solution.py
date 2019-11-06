#中序遍历，加入数组，然后遍历数组，改变每个节点的left和right
#为避免处理边界，每次处理1）当前节点的right 和2）下一节点的left
# -*- coding:utf-8 -*-
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def Convert(self, pRootOfTree):
        # write code here
        if not pRootOfTree: return 
        self.res = []
        self.dfs(pRootOfTree)
        for i,val in enumerate(self.res[1:-1]):
            self.res[i].right = self.res[i+1]
            self.res[i+1].left = self.res[i]
        return self.res[0]
#
#解法二：和前面的思路一致但是需要考虑边界处理，
class Solution:
    def Convert(self, pRootOfTree):
        # write code here
        if not pRootOfTree: return 
        if not pRootOfTree.left and not pRootOfTree.right: return pRootOfTree
        self.res = []
        self.dfs(pRootOfTree)
        
        #边界处理 这里要求至少两个node，所以前面把单一node的情况return一个node
        self.res[0].right =  self.res[1]
        self.res[-1].left =self.res[-2]
        
        for i,val in enumerate(self.res[:-1]):
            if i>0: #注意判断否则越界 
                self.res[i].left = self.res[i-1]
                self.res[i].right = self.res[i+1]
        return self.res[0]
            
    def dfs(self,root):
        if not root: return
        self.dfs(root.left)
        self.res.append(root)
        self.dfs(root.right)
