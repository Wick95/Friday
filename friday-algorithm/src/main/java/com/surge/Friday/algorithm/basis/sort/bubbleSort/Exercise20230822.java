package com.surge.Friday.algorithm.basis.sort.bubbleSort;

public class Exercise20230822 {
    public static void bubbleSort(int[] array) {
        for(int i = 0; i< array.length; i++){
            for(int j= 0; j < array.length -i -1; j++){
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        bubbleSort(arr);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
