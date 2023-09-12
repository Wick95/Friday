package com.surge.Friday.algorithm.basis.sort.quickSort;

public class Exercise20230822 {
    public static void quickSort(int[] array, int left, int right) {
        // 数组分片长度小于1
        if (left > right) {
            return;
        }
        int temp, i, j;
        i = left;
        j = right;
        temp = array[i];
        while (i < j) {
            //先从右侧开始，比较并移动游标
            while (temp <= array[j] && i < j) {
                j--;
            }
            while (temp >= array[i] && i < j) {
                i++;
            }
            if (i < j) {
                int t = array[j];
                array[j] = array[i];
                array[i] = t;
            }
        }
        array[left] = array[i];
        array[i] = temp;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
