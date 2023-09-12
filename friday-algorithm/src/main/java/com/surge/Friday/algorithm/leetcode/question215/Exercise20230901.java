package com.surge.Friday.algorithm.leetcode.question215;

public class Exercise20230901 {
    int capacity = 0;

    public int findKthLargest(int[] nums, int k) {

        int res = 0;
        int[] heap = new int[nums.length];

        //构建大顶堆
        for (int element : nums) {
            add(heap, element);
        }
        //弹出K次，得到最K的值
        for (int i = 0; i < k; i++) {
            res = pop(heap);
        }

        return res;
    }

    public void add(int[] heap, int element) {
        //获取末尾位置，容量加一
        int newPosition = capacity++;
        //与父节点比较，直到根节点
        while (newPosition > 0) {
            int parentPosition = (newPosition - 1) / 2;
            int parentValue = heap[parentPosition];
            if (element <= parentValue) {
                break;
            }
            //如果比父节点大，替换两者的值
            heap[newPosition] = parentValue;
            newPosition = parentPosition;
        }
        //替换最终位置的值
        heap[newPosition] = element;
    }

    public int pop(int[] heap) {
        //获取跟节点
        int position = 0;
        int head = heap[position];
        //获取尾部节点，容量-1
        int tailPos = --capacity;
        int tailValue = heap[tailPos];

        int lastParentPos = capacity / 2;

        while (position < lastParentPos) {
            int childPos = position * 2 + 1;
            int childValue = heap[childPos];
            //和子节点比较
            if (childPos + 1 < capacity && heap[childPos + 1] > childValue) {
                childValue = heap[childPos = childPos + 1];
            }
            if (tailValue >= childValue) {
                break;
            }
            heap[position] = childValue;
            position = childPos;
        }
        heap[position] = tailValue;
        return head;
    }
}
