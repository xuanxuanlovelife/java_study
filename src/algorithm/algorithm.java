package algorithm;

import java.util.Arrays;
import java.util.HashMap;

public class algorithm {

    /**
     * N个鸡蛋从M楼层摔（2个鸡蛋从100层摔）
     * @param egg int
     * @param num int
     * @return int
     */
    public static int countMinSetp(int egg,int num){
        if(egg < 1 || num < 1) return 0;
        int[][] f = new int[egg+1][num+1];//代表egg个鸡蛋，从num楼层扔下来所需的最小的次数
        for(int i=1;i<=egg; i++){
            for(int j=1; j<=num; j++)
                f[i][j] = j;//初始化，最坏的步数
        }

        for(int n=2; n<=egg; n++){
            for(int m=1; m<=num; m++){
                for(int k=1; k<m; k++){
                    //这里的DP的递推公式为f[n][m] = 1+max(f[n-1][k-1],f[n][m-k]) k属于[1,m-1]
                    //从1-m层中随机抽出来一层k
                    //如果第一个鸡蛋在k层碎了，那么我们将测试1~k-1层，就可以找出来，也即1+f[1][k-1]
                    //如果第一个鸡蛋在k层没有碎，那么我们将测试k+1~m也即m-k层，
                    //      这里也是重点！！！！
                    //      现在我们手里有2个鸡蛋，要测试m-k层，那么我想问，此时和你手里有2个鸡蛋要测试1~m-k层有什么区别？
                    //      没有区别！是的在已知k层不碎的情况下，测试k+1~m层的方法和测试1~m-k没区别，所以可以写成 1+f[n][m-k] 其中1表示为 在k层的那一次测试
                    f[n][m] = Math.min(f[n][m],1+Math.max(f[n-1][k-1],f[n][m-k]));
                }
            }
        }
        return f[egg][num];
    }



    public static void main(String[] args){
//        Hanoi(3,'a','b','c');
//        System.out.print(minEditDistance("zhanghua","zhanghau"));
//        int arr[] = {1,-1,2,-3,4,-5,6,-7};
//        /* 计算LIS长度 */
//        System.out.println(getLongestIncreasingSubsequence(arr));
        System.out.println(countMinSetp(2,100));    //result:14
    }


    static int[] MaxV=new int[30];   /* 存储长度i+1（len）的子序列最大元素的最小值 */

    /**
     * 最长递增子系列
     * @param arr int[]
     * @return int
     */
    static int getLongestIncreasingSubsequence(int[] arr){
        MaxV[0] = arr[0];
        int len = arr.length;
        int index = 1;
        for(int i=1;i<len;i++){
            if(arr[i] > arr[index-1] ){
                arr[index++] = arr[i];
            }else{
                int position = Arrays.binarySearch(MaxV,arr[i]);
                position = position == -1 ? 0:position;
                System.out.println(position);
                MaxV[position] = arr[i];
            }
        }
        return index;
    }


    private int max = 0;
    private String res = "";
    /**
     * 最长回文串
     * @param s String
     * @return String
     */
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0) return s;
        for(int i=0;i<s.length()-1;i++){
            checkPalindromeExpand(s,i,i);
            checkPalindromeExpand(s,i,i+1);
        }
        return res;
    }

    public void checkPalindromeExpand(String s, int low, int high){
        while(low >=0 && high < s.length()){
            if(s.charAt(low) == s.charAt(high)){
                if(high-low+1 > max){
                    max = high-low+1;
                    res = s.substring(low,high+1);
                }
                low--;
                high++;
            }else{
                return;
            }
        }
    }


    /**
     * 最长公共子序列
     * @param s1 String
     * @param s2 String
     * @return int
     */

    public static int longestCommonSequence(String s1,String s2){
        if(s1 == null || s1.length() == 0) return 0;
        if(s2 == null || s2.length() == 0) return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 最长公共子串
     * @param s1 String
     * @param s2 String
     * @return int
     */
    public static int longestCommonSubstring(String s1,String s2){
        if(s1 == null || s1.length() == 0) return 0;
        if(s2 == null || s2.length() == 0) return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = 0;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 最短编辑距离
     * @param s1 String
     * @param s2 String
     * @return int
     */
    public static int minEditDistance(String s1,String s2){
        if(s1 == null || s1.length() == 0) return 0;
        if(s2 == null || s2.length() == 0) return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=1;j<=n;j++){
            dp[0][j] = j;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                int add = dp[i-1][j] + 1;
                int delete = dp[i][j-1] + 1;
                int replace = dp[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1);
                dp[i][j] = Math.min(replace,Math.min(add,delete));
            }
        }
        return dp[m][n];
    }

    /**
     * 汉诺塔
     * @param n int
     * @param a char
     * @param b char
     * @param c char
     */
    public static void Hanoi(int n,char a,char b,char c) {
        if(n == 1){
            move(a,c);
        }else{
            Hanoi(n-1,a,c,b);
            move(a,c);
            Hanoi(n-1,b,a,c);
        }

    }

    public static void move(char a,char b){
        System.out.println(a+"->"+b);
    }
}
