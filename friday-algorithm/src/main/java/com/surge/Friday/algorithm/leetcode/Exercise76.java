package com.surge.Friday.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>最小覆盖子串
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.27
 */
public class Exercise76 {
    public static String minWindow(String s, String t) {
        boolean right = false;
        Map<Character, Integer> dict = new HashMap<>();
        int distance = -1;

        for (int i = 0; i < t.length(); i++) {
            char temp = t.charAt(i);
            dict.put(temp, dict.getOrDefault(temp, 0) + 1);
        }
        int pointA = 0, pointB = 0;
        int pA = 0, pB = 0;
        while (pointA <= s.length() - 1) {
            //移动A指针
            char head = s.charAt(pointA);
            if (dict.containsKey(head)) {
                //对字典值-1
                dict.put(head, dict.get(head) - 1);
            }
            //判断是否覆盖
            while (checkMap(dict)) {
                //如果覆盖 移动B指针 对字典值+1 判断是否覆盖 取消移动
                char tail = s.charAt(pointB);
                if (dict.containsKey(tail) && dict.get(tail) < 0) {
                    pointB++;
                    dict.put(tail, dict.get(tail) + 1);
                } else if (!dict.containsKey(tail)) {
                    pointB++;
                } else if (dict.get(tail) >= 0) {
                    right = true;
                    if (distance == -1 || pointA - pointB < distance) {
                        distance = pointA - pointB;
                        pA = pointA;
                        pB = pointB;
                    }
                    if (distance == t.length()) {
                        break;
                    }
                    break;
                }
            }

            pointA++;
        }
        return right ? s.substring(pB, pA + 1) : "";
    }

    public static boolean checkMap(Map<Character, Integer> hashMap) {
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ABCACFERE", "AFR"));
        int i=0;
    }
}
