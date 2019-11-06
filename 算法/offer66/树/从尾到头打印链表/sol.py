# -*- coding:utf-8 -*-
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # 返回从尾部到头部的列表值序列，例如[1,2,3]
    def printListFromTailToHead(self, listNode):
        # write code here
        result = []
        #使用内部类是为了在类外定义一个局部变量result
        #如果定义一个__init__，则可以不使用内部类
        def dfs(l):
            if l == None: return 
            dfs(l.next)
            result.append(l.val)
            return 
        dfs(listNode)
        return result
