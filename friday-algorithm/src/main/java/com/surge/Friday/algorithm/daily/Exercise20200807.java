package com.surge.Friday.algorithm.daily;

/**
 * <p>这一周股市价格为[2,6,1,4,8]，求哪一天买入哪一天卖出，可获得最大收益，最大收益为多少
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.07
 */
public class Exercise20200807 {
    public static void main(String[] args) {
        System.out.println(String.valueOf(calculation(new int[]{2, 6, 1, 4, 8})));
    }

    public static int[] calculation(int[] arrays) {
        int[][] dict = new int[arrays.length][2];
        int start = 0, end = 0;
        int max = 0;
        if (arrays.length == 0) {
            return new int[2];
        }
        for (int i = 0; i < arrays.length; i++) {
            if (i == 0 || arrays[i] < arrays[i - 1]) {
                dict[i][0] = 0;
                dict[i][1] = 0;
            } else if (arrays[i] >= arrays[i - 1]) {
                dict[i][0] = dict[i - 1][0] + 1;
                dict[i][1] = arrays[i] - arrays[i - dict[i][0]];
            }
        }
        for (int i = 0; i < arrays.length; i++) {
            if (dict[i][1] > max) {
                max = dict[i][1];
                start = arrays[i - dict[i][0]];
                end = arrays[i];
            }
        }
        int[] result = new int[2];
        result[0] = start;
        result[1] = end;


        return result;
    }
}
