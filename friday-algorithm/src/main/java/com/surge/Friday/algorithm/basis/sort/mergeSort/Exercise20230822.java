package com.surge.Friday.algorithm.basis.sort.mergeSort;

import java.util.Arrays;

public class Exercise20230822 {
    public static int[] mergeSort(int[] array){
        //数组长度为1时，直接返回
        if (array.length <= 1){
            return array;
        }
        int[] result = new int[array.length];

        //寻找数组中点
        int middle = array.length /2;
        int[] leftResult = mergeSort(Arrays.copyOfRange(array, 0, middle));
        int[] rightResult = mergeSort(Arrays.copyOfRange(array, middle, array.length));

        //归并排序
        int l = 0, r = 0, i = 0;

        //分别比对左侧和右侧进行排序
        while (l <= leftResult.length - 1 && r <= rightResult.length - 1) {
            if (leftResult[l] <= rightResult[r]) {
                result[i] = leftResult[l];
                l++;
                i++;
            } else {
                result[i] = rightResult[r];
                r++;
                i++;
            }
        }
        //将剩余未比对部分直接组装
        for (;l <= leftResult.length - 1; l++) {
            result[i] = leftResult[l];
            i++;
        }
        for(;r<=rightResult.length -1; r++){
            result[i] = rightResult[r];
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = mergeSort(new int[]{4, 5, 7, 123, 456, 1, 2, 78});
        System.out.println(Arrays.toString(result));
    }
}
