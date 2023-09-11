package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.basis.sort.quickSort.QuickSort;

/**
 * <p>排序数组
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *  
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class Exercise912 {
    public int[] sortArray(int[] nums) {
        QuickSort.quickSort(nums, 0, nums.length - 1);
        return nums;
    }
}
