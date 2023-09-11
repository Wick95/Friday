package com.surge.Friday.algorithm.basis.sort.quickSort;

/**
 * <p>快速排序：首先选取哨兵元素，然后对集合进行排序，排序后哨兵左边的元素全部小于它，右边全部大于它，再递归进行排序
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int i, j, temp, t;
        i = low;
        j = high;
        //1.获取哨兵元素
        temp = arr[low];
        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t; }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}