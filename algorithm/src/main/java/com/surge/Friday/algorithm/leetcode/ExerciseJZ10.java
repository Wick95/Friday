package com.surge.Friday.algorithm.leetcode;

/**
 * <p>剑指offer10
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.28
 */
public class ExerciseJZ10 {
    public static int numWays(int n) {
        return init(n)[n];
    }

    public static int[] init(int n) {
        int[] array = new int[n + 1];
        array[0] =1;
        array[1] =1;
        array[2] =2;

        for (int i = 3; i <= n; i++) {

            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(numWays(4));
        System.out.println(1 << 30);
        System.out.println(Integer.MAX_VALUE);
    }
}
