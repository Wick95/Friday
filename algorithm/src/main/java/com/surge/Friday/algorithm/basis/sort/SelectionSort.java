package com.surge.Friday.algorithm.basis.sort;

/**
 * <p>选择排序：每次从无序区选出极限值，并与无序区第一个元素交换。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class SelectionSort {
    public static int[] SORT(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            //1.记录无序集第一个元素
            int swap = nums[i];
            //2.找到无序集中的极限值
            for (int j = i + 1; j < nums.length ; j++) {
                if (swap > nums[j]) {
                    int temp = nums[j];
                    nums[j] = swap;
                    swap = temp;
                }
            }
            //3.替换无序集中第一个元素
            nums[i] = swap;
        }
        return  nums;
    }

    public static void main(String[] args) {
        int[] result = SORT(new int[]{4, 5, 7, 123, 456, 1, 2, 78});
        System.out.println(result);

    }
}
