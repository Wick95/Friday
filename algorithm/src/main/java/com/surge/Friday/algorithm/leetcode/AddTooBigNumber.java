package com.surge.Friday.algorithm.leetcode;

/**
 * <p>两个非常大的数字相加
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.21
 */
public class AddTooBigNumber {

    public static String twoStringAdd(String str1, String str2) {
        int length = Math.max(str1.length(), str2.length()) -1 ;
        int i = 0;


        String result = "";
        while (length >= 0) {
            int int1 = 0;
            int int2 = 0;
            if (length < str1.length()) {
                 int1 = Integer.parseInt(String.valueOf(str1.charAt(length)));

            }
            if (length < str2.length()) {
                 int2 = Integer.parseInt(String.valueOf(str2.charAt(length)));
            }
            int total = int1 + int2 + i;
            i = 0;
            if (total >= 10) {
                total = total % 10;
                i = 1;
            }
            result = total + result;
            length--;
        }
        if (i != 0) {
            result = i + result;
        }


        return result;
    }

    public static void main(String[] args) {

        System.out.println(twoStringAdd("123123123123123123","123123123123123123"));
    }
}
