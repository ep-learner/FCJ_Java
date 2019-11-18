package DP;

public class isSubsequence {
    public static boolean isSubsequence(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        int j = 0;
        int flag = 0;
        for(int i = 0; i<slen ;i++){
            char target = s.charAt(i);
            for(;j<tlen;j++){
                if(t.charAt(j)==target) {
                    j++;
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) return false;
            flag = 0;
        }
        return true;
    }


    public boolean solutionDp(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return false;
        if (sLen == 0) return true;
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];

        //s为空串，则dp始终为true
        for (int j = 0; j < tLen; j++) {
            dp[0][j] = true;
        }

        //给dp赋值
        // 假如当前位置的字符串（i和j）匹配上，说明dp[i][j]和前面dp[i-1][j-1]的结果是一致的；
        // 因为把0当成空串，那么索引和字符串长度之间有差别
        // 假如没匹配上就和dp[i][j-1]的结果是一致的
        //dp[i][j]的含义是：s.sub(0,i)是否是t.sub(0,j)的子序列
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                //dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1)?dp[i - 1][j - 1]:dp[i][j - 1];
            }
        }
        return dp[sLen][tLen];
    }



    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
