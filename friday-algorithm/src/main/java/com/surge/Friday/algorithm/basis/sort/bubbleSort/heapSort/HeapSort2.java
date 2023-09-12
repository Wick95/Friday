package com.surge.Friday.algorithm.basis.sort.bubbleSort.heapSort;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>推排序
 * <p>
 * 可以参见优先队列的实现： {@link java.util.PriorityQueue#offer(Object)}
 * 复杂度分析：
 * <p>
 * 时间复杂度：O(nlogn)。
 * 初始化建堆的时间复杂度为 O(n)，建完堆以后需要进行 n−1 次调整，一次调整（即 maxHeapify） 的时间复杂度为 O(\log n)O(logn)，那么 n-1 次调整即需要 O(n\log n)O(nlogn) 的时间复杂度。因此，总时间复杂度为 O(n+n\log n)=O(n\log n)O(n+nlogn)=O(nlogn)。
 * 空间复杂度：O(1)。
 * 只需要常数的空间存放若干变量。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.05.28
 */
public class HeapSort2 {

    public static Integer capacity = 0;

    public static int[] heapSort(int[] array) {
        int[] heap = new int[array.length];
        int[] result = new int[array.length];
        //1.构建小顶堆
        for (int j : array) {
            addIn(heap, j);
        }
        //2.依次弹出
        for (int i = 0; i < array.length; i++) {
            result[i] = pop(heap);
        }

        return result;
    }

    public static void addIn(int[] array, int i) {
        //容量+1
        int newPosition = capacity++;

        //找到最后一个父节点
        while (newPosition > 0) {
            int lastParentPosition = (newPosition - 1) / 2;
            int lastParentValue = array[lastParentPosition];
            if (i >= lastParentValue) {
                break;
            }
            array[newPosition] = lastParentValue;
            newPosition = lastParentPosition;
        }
        array[newPosition] = i;
    }

    public static int pop(int[] array) {
        int position = 0;

        int result = array[position];

        //容量 -1
        int tailPosition = capacity - 1;
        int tailValue = array[tailPosition];

        //找到最后一个父节点的位置
        int lastParentPosition = (capacity - 1) / 2;

        while (position < lastParentPosition) {
            //线判断子节点之间大小关系
            int childPosition = position * 2 + 1;
            int childValue = array[childPosition];
            if (childPosition + 1 <= capacity - 1 && array[childPosition + 1] < array[childPosition]) {
                childValue = array[childPosition + 1];
                childPosition = childPosition + 1;
            }
            if (tailValue <= childValue) {
                break;
            }
            array[position] = childValue;
            position = childPosition;
        }
        array[position] = tailValue;

        capacity--;

        return result;

    }

    public static void main(String[] args) {
        int[] result = heapSort(new int[]{4, 5, 7, 123, 456, 1, 2, 78});
        System.out.println(Arrays.toString(result));
    }
}
