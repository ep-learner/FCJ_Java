class Solution:
    def Permutation(self, ss):
        # write code here
        if ss == "": return  []
        result = []
        s = []
        ans = []
        for j in ss:
            s.append(j)
        for i in range(len(s)):
            tag = 1#判断之前是否出现过元素s[i]如果出现过就不需要置换了
            for j in range(i):
                if s[i] == s[j]:
                    tag = 0
                    break
            if tag == 1 :
                s[0],s[i] = s[i],s[0]
                temp = self.Permutation("".join(s[1:]))
                if temp == []: #最底层返回值是[]，而for k in [] 会导致跳过该循环，没有进行ans 的赋值，ans恒等于[]
                    return [s[0]]
                for k in temp:
                    a = "".join([s[0],k])
                    ans.append(a)
        return ans
