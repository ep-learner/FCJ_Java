# -*- coding:utf-8 -*-
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
# 1、构建root节点
# 2、左子树的前序+中序作为输入，返回左子树根节点；同理右子树节点可以获得
# 3、根据2 递归赋值root.left和root.right；
class Solution:
    # 返回构造的TreeNode根节点
    def reConstructBinaryTree(self, pre, tin):
        # write code here
        if(len(pre) == 0): return None 
        root = TreeNode(pre[0])
        ##根据值找索引 ：ind = list.index(val)
        ind = tin.index(pre[0])
        root.left = self.reConstructBinaryTree(pre[1:ind+1],tin[0:ind])
        root.right = self.reConstructBinaryTree(pre[ind+1:],tin[ind+1:])
        return root
