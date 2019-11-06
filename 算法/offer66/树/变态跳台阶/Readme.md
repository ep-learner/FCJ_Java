不难得出结论：f(n) = f(n-1) + f(n-2) + ***+1

第一思路是递归外面套一个for循环，但是累加值依赖于返回值：
  1、如果使用内部类，count作为参数每次返回结果都是1
  ```
  def helper(num,count)
    if num == 1: return 1
    ***
    count += helper(num,count)
    
    等价于
    a = helper(num,count)
    count +=a
    ***  
    这样每次传进去的count值始终为0，最后返回的count其实只是计算了递归次数  
    ***
  ```
  ```
  # -*- coding:utf-8 -*-
  class Solution:
    def jumpFloorII(self, number):
        # write code here
        count = 0
        def helper(num):
            nonlocal count
            if num == 1: return 1
            i = 1
            while(num - i > 0):
                count += helper(num-i)
                i += 1
            return count+1

        return helper(number)
  ```
对该无穷级数进行化简，除num=1或num=2返回num，其他返回2*f(n-1)
```
# -*- coding:utf-8 -*-
class Solution:
    def jumpFloorII(self, number):
        # write code here
        if number <3 : 
            return number
        else:
            return 2*self.jumpFloorII(number-1)
```

