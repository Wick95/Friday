package com.surge.Friday.algorithm.basis.sort;

/**
 * <p> 冒泡排序：两两比较，每次最大值放在最后，外层i控制内层比较长度（n,n-1,n-2)，然后进行n次。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class BubbleSort {

    public static int[] SORT(int[] nums) {
        for (int i = 0; i < nums.length; i++) {//0,1,2,3,
            for (int j = 0; j < nums.length - i - 1; j++) {//0,1,2  //0,1 //0
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

}
