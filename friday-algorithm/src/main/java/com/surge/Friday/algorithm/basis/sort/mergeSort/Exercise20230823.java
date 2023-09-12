package com.surge.Friday.algorithm.basis.sort.mergeSort;

import java.util.Arrays;

public class Exercise20230823 {
    public static int[] mergeSort(int[] array) {
        // 如果数组长度为1，直接返回
        if (array.length <= 1) {
            return array;
        }
        int[] result = new int[array.length];

        // 1.确定数组中点
        int middle = array.length / 2;
        // 2.递归处理
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, middle));
        int[] right = mergeSort(Arrays.copyOfRange(array, middle, array.length));

        int l = 0, r = 0, i = 0;

        // 3.分别比较左右两边
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                result[i] = left[l];
                i++;
                l++;
            } else {
                result[i] = right[r];
                i++;
                r++;
            }
        }
        // 4.将剩余数据追加到结果集上
        for (; l < left.length; l++) {
            result[i] = left[l];
            i++;
        }
        for (; r < right.length; r++) {
            result[i] = right[r];
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = mergeSort(new int[]{4, 5, 7, 123, 456, 1, 2, 78});
        System.out.println(Arrays.toString(result));
    }
}
