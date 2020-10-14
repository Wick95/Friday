package com.surge.Friday.algorithm.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.07
 */
public class Exercise202008010 {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
    }

    public static int fib(int n) {

        int a = 0, b = 1, next;
        for (int i = 0; i < n; i++) {
            next = (a + b);
            a = b;
            b = next;
        }
        return a;
    }

    public static int findNotRepeat(int[] arrays) {
        if (arrays.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arrays.length; i++) {
            if (set.add(arrays[i])) {
                hashMap.put(arrays[i], arrays[i]);
            } else {
                hashMap.remove(arrays[i]);
            }
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            result = entry.getKey();
        }
        return result;
    }

}
