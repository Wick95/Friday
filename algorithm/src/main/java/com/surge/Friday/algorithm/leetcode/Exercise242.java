package com.surge.Friday.algorithm.leetcode;

/**
 * <p>字母异位词
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.24
 */
public class Exercise242 {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.length() == 0) {
            return false;
        }
        int[] array = new int[24];

        for (int i = 0 ; i<s.length();i++) {
            char ele = s.charAt(i);
            array[((int)ele)-97] +=1;
        }
        for (int i = 0; i < t.length(); i++) {
            char ele = t.charAt(i);
            array[((int)ele)-97] -=1;
        }

        for (int ele : array){
            if (ele != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("carcars","ccaarrs"));
    }
}
