package com.surge.Friday.algorithm.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.05.14
 */
public class Exercise215 {
    /**
     * 堆排序 O nlogn
     *
     * @param nums 插入数组
     * @param k    第几大
     * @return 结果
     */
    public int findKthLargest(int[] nums, int k) {
        Integer[] heap = new Integer[nums.length];

        AtomicInteger capacity = new AtomicInteger(0);

        for (int insert_num : nums) {
            int position = capacity.getAndIncrement();
            while (position > 0) {
                int parent_position = (position - 1) / 2;
                if (insert_num <= heap[parent_position]) {
                    break;
                }
                heap[position] = heap[parent_position];
                position = parent_position;
            }
            heap[position] = insert_num;
        }


        for (int i = k - 1; i > 0; i--) {
            int position = 0;
            Integer end_value = heap[capacity.decrementAndGet()];

            int half = capacity.intValue() / 2;
            while (position < half) {
                int child_position = position * 2 + 1;
                int child_value = heap[child_position];

                if (child_position + 1 < capacity.intValue()
                        && child_value < heap[child_position + 1]) {
                    child_value = heap[child_position = child_position + 1];
                }
                if (end_value > child_value) {
                    break;
                }
                heap[position] = child_value;
                position = child_position;
            }
            heap[position] = end_value;

        }
        return heap[0];
    }

    public static void main(String[] args) {
        Exercise215 exercise215 = new Exercise215();
        System.out.println(exercise215.findKthLargest(new int[]{-1, 2, 0}, 3));
    }
}