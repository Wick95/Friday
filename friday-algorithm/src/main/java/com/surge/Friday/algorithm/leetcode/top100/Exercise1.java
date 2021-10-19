package com.surge.Friday.algorithm.leetcode.top100;

/**
 * <p> 两数之和
 *
 * @author Matt Liu
 * @version 1.0 created by 10.14, 2021
 */
public class Exercise1 {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (i != j &&target == first + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
