package com.surge.Friday.algorithm.leetcode;

/**
 * <p>请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.26
 */
public class Exercise739 {
    public static int[] dailyTemperatures(int[] T) {
        if (T.length == 0){
            return new int[0];
        }
        int[] R = new int[T.length];
        Stack buttom = null;

        for (int i = 0;i<T.length;i++){
            //将温度压入栈
            if (buttom == null) {
                buttom = new Stack(T[i], i);
            }else {

            }
        }

        return R;
    }
    static class Stack{
        public int val;
        public int order;
        public Stack next;

        public Stack(int val, int order) {
            this.val = val;
            this.order = order;
        }
    }
}
