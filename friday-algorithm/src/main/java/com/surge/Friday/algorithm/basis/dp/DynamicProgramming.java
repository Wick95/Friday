package com.surge.Friday.algorithm.basis.dp;

/**
 * <p>动态规划
 * <p>
 * 　最长上升子序列（LIS）问题：给定长度为n的序列a，从a中抽取出一个子序列，这个子序列需要单调递增。问最长的上升子序列（LIS）的长度。
 * 　　e.g. 1,5,3,4,6,9,7,8的LIS为1,3,4,6,7,8，长度为6。
 * <p>
 * max {f(x)} = max{f(p) + 1} 其中：x,p为数组下标，x>p,a[x]>a[p]
 * f(0) = 1;
 * f(1) = 2;  = f(0) +1;
 * f(3) = 2;
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.16
 */
public class DynamicProgramming {

    public static int getLongestSubList(int[] nums) {

        int length = 1;
        int[] f = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int p = i - 1; p >= 0; p--) {
                if (nums[p] < nums[i]) {
                    f[i] = Math.max(f[p] + 1, f[i]);
                }
            }
        }
        for (int num : f) {
            length = Math.max(num, length);
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(getLongestSubList(new int[]{1, 5, 3, 4, 6, 9, 7, 8}));
    }

}
