package com.surge.Friday.algorithm.basis.sort.quickSort;

public class Exercise20230823 {
    public static void quickSort(int[] array,int left,int right){
        if (left > right) {
            return;
        }
        // 1.确定哨兵
        int l = left,r = right,temp = array[l];
        // 2.分别从左右两端进行比较
        while (l < r) {
            while (array[r] >= temp && l < r){
                r--;
            }
            while (array[l] <= temp && l< r){
                l++;
            }
            int t = array[l];
            array[l] = array[r];
            array[r] = t;
        }
        array[left] = array[l];
        array[l] = temp;

        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
