package com.surge.Friday.algorithm.basis.sort.insertionSort;

import java.util.Arrays;

public class Exercise20230822 {
    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int insertion = array[i];
            int j = i - 1;
            while ( j >= 0 &&  array[j] > insertion) {
                array[j + 1] = array[j];
                j--;
            }
            array[j +1] = insertion;
        }
    }

    public static void main(String[] args) {
        int[] result = new int[]{4, 5, 7, 123, 456, 1, 2, 78};
        insertionSort(result);
        System.out.println(Arrays.toString(result));
    }
}
