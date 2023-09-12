package com.surge.Friday.algorithm.leetcode.question215;

public class Exercise215B {
    static class Solution {
        public int capacity = 0;

        public int findKthLargest(int[] nums, int k) {

            int res = 0;

            int[] heap = new int[nums.length];
            // 构建大大顶堆
            for (int element : nums) {
                add(heap, element);
            }
            for (int i = 0; i < k; i++) {
                res = pop(heap);
            }

            return res;
        }

        public void add(int[] array, int element) {
            //容量 +1
            int newPosition = capacity++;

            //从下往上比较
            while (newPosition > 0) {
                int parentPosition = (newPosition -1) / 2;
                int parentValue = array[parentPosition];
                //分别比较父节点和新节点的值，如果父节点比新节点小，替换父节点的值
                if (element <= parentValue) {
                    break;
                }
                array[newPosition] = parentValue;
                newPosition = parentPosition;
            }
            //替换对应节点的值
            array[newPosition] = element;
        }

        public int pop(int[] array) {
            //获取当前堆的最后一个节点
            //容量-1
            int pos = --capacity;
            int tailValue = array[pos];
            //获取堆顶的值
            int newPosition = 0;
            int head = array[newPosition];
            // 获取最后一个父节点，从上往下比较
            int lastParentPosition = capacity / 2;

            //比较子节点和最后一个节点的值
            while (newPosition < lastParentPosition) {
                int parentPosition = newPosition * 2 + 1;
                int parentValue = array[parentPosition];
                if (parentPosition + 1 < capacity && array[parentPosition + 1] > array[parentPosition]) {
                    parentValue = array[parentPosition = parentPosition + 1];
                }
                if (tailValue > parentValue) {
                    break;
                }
                array[newPosition] = parentValue;
                newPosition = parentPosition;
            }
            //替换对应节点的值
            array[newPosition] = tailValue;

            return head;

        }

    }

    public static void main(String[] args) {
        int result = new Solution().findKthLargest(new int[]{0, -1, 2}, 2);
        System.out.println(result);
    }
}
