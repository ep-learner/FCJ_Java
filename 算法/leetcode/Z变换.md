# [Z变换](https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode/)
## 思路
1）最简单的方式是建立一个矩阵，往里面放数组，再遍历读取出来[代码](https://leetcode-cn.com/submissions/detail/22859833/)

2）第二种方式通过List里面存放StringBuffer实现：

每个StringBuffer存一行的内容；之后将按序SB拼接起来
```
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();

        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
        //建立List，每个元素是一个StringBuffer，只要找到char在哪一行，属于哪个StringBuffer
        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;//碰到边界转向
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);//遍历List里的SB，SB拼接起来就是答案
        return ret.toString();
    }
}
```
