package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.basis.dp.DynamicProgramming;

/**
 * <p>动态规划：给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.16
 */
public class Exercise300 {
    public static void main(String[] args) {
        DynamicProgramming.getLongestSubList(new int[]{1, 2});
    }
}
