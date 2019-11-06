#二叉搜索树后续遍历，seq[-1]为root.val，其大小介于左子树和右子树的值之间
#找到一个val>seq[-1],将原seq切分为两部分，递归判断左右子树是否为后续遍历
#边界条件：初始为[] 返回False

class Solution:
    def VerifySquenceOfBST(self, sequence):
        # write code here
        if sequence == []: return False
        #if len(sequence)<=2: return True

        count = -1
        tag = True
        for i,val in enumerate(sequence):
            if(sequence[-1]<val):
                count = i
                break

        for j in sequence[count:-1]:
            if j<sequence[-1] : 
                return False
        
        #设置条件跳过部分递归，因为seq=[]返回False仅限于首次循环
        #递归体出现[]证明前述没有返回False，跳过该次递归
        if sequence[0:count] != []:
            tag = self.VerifySquenceOfBST(sequence[0:count])
            if tag and sequence[count:-1] != []:
                tag = self.VerifySquenceOfBST(sequence[count:-1])
        return tag
