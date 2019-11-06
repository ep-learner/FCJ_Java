# -*- coding:utf-8 -*-
# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

# 在访问node.next.next之前必须对node.next判空，否则编译都过不去

class Solution:
    # 返回 RandomListNode
    def Clone(self, head):
        # write code here
        if head == None: return head
        #if head.next == None: return head
        
        #1、复制链表，添加在原链表后面
        h = head
        
        while head:
            new_node = RandomListNode(head.label)
            temp = head.next
            head.next = new_node
            new_node.next = temp
            head = temp

        
        #2、指定复制链表的random指针
        head =h
        while head :
            if head.random:
                head.next.random = head.random.next
            head = head.next.next
        
        #3、拆分链表
        head =h
        root = h.next
        r1 = root
        while head:
            head.next = r1.next
            if head.next:
                r1.next = head.next.next
            r1 = r1.next
            head = head.next
        
        return root
