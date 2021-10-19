package com.surge.Friday.algorithm.leetcode;

/**
 * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ :用亦或的方式，任何数字和0亦或都等于本身，任何数字亦或本身结果为0（还原）
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.05.14
 */
public class Exercise136 {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
