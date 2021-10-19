package com.surge.Friday.algorithm.leetcode;

/**
 * <p>最长公共子序列：
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * <p>
 * max{f(x)} = max{f(y) +1 },其中：y为公共子序列，其中indexOf(y)<inedexOf(x) && != -1;
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.16
 */
public class Exercise1143 {
    public static int getLongestCommonSubList(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char s1 = text1.charAt(i), s2 = text2.charAt(j);
                if (s1 == s2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(getLongestCommonSubList("adfdasfasfdsaferrertcb", "dsfdsfadsfsdfasfadfsdf"));
        System.out.println(Solution.longestPalindrome("cabad"));
    }
}

class Solution {

    public static String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;

                if (l == 0) {
                    dp[i][j] = 1;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = (b ? 1 : 0);
                    } else {
                        dp[i][j] = (b && dp[i + 1][j - 1] == 1) ? 1 : 0;
                    }
                }
                if (dp[i][j] == 1 && l + 1 > ans.length()) {
                    ans = s.substring(i, i+l + 1);
                }
            }
        }
        return ans;
    }

    class binSearch {
        public int search(int[] nums, int target) {
            int pivot, left = 0, right = nums.length - 1;
            while (left <= right) {
                pivot = left + (right - left) / 2;
                if (nums[pivot] == target) return pivot;
                if (target < nums[pivot]) right = pivot - 1;
                else left = pivot + 1;
            }
            return -1;
        }
    }


}
