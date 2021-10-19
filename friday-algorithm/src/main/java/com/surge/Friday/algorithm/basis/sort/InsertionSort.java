package com.surge.Friday.algorithm.basis.sort;

/**
 * <p>插入排序：对于选中元素，依次和有序序列比较，如果找到位置，进行插入
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class InsertionSort {

    public static int[] SORT(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int insertion = nums[i];
            int j = i -1;
            while (j >= 0 && insertion < nums[j] ) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j + 1] = insertion;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] result = SORT(new int[]{4, 5, 7, 123, 456, 1, 2, 78});
        System.out.println(result);

    }
}
