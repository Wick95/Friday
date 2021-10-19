package com.surge.Friday.algorithm.basis.sort;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>推排序
 * <p>
 * 可以参见优先队列的实现： {@link java.util.PriorityQueue#offer(Object)}
 * 复杂度分析：
 * <p>
 * 时间复杂度：O(n\log n)O(nlogn)。初始化建堆的时间复杂度为 O(n)O(n)，建完堆以后需要进行 n-1n−1 次调整，一次调整（即 maxHeapify） 的时间复杂度为 O(\log n)O(logn)，那么 n-1n−1 次调整即需要 O(n\log n)O(nlogn) 的时间复杂度。因此，总时间复杂度为 O(n+n\log n)=O(n\log n)O(n+nlogn)=O(nlogn)。
 * 空间复杂度：O(1)O(1)。只需要常数的空间存放若干变量。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.05.28
 */
public class HeapSort {

    public Integer[] small_top_heap = new Integer[1];

    public AtomicInteger heap_capacity = new AtomicInteger(0);

    public void addIn(int i) {

        //1.用数组实现堆，每次加入元素获取当前下标
        int position = heap_capacity.getAndIncrement();
        //2.如果超过数组大小，进行扩容
        if (heap_capacity.intValue() > small_top_heap.length) {
            small_top_heap = Arrays.copyOf(small_top_heap, small_top_heap.length + 10);
        }
        //位置不是栈顶，循环判断，直到找到位置
        while (position > 0) {
            int parent_position = (position - 1) / 2;
            int parent_value = small_top_heap[parent_position];
            if (i >= parent_value) {
                break;
            }
            small_top_heap[position] = parent_value;
            position = parent_position;
        }

        small_top_heap[position] = i;
    }

    public int pop(int i) {
        //需要提前弹出几次
        for (int times = i - 1; times > 0; times--) {
            //1.从栈顶开始弹出
            int position = 0;

            int tail_position = heap_capacity.decrementAndGet();
            int tail_value = small_top_heap[tail_position];

            int half = heap_capacity.intValue() / 2;
            while (position < half) {
                int child_position = position * 2 + 1;
                int child_value = small_top_heap[child_position];

                //先判断子节点之间大小关系
                if (child_position + 1 <= heap_capacity.intValue() && small_top_heap[child_position + 1] < small_top_heap[child_position]) {
                    child_value = small_top_heap[child_position = child_position + 1];
                }
                //再判断尾部节点和子节点之间的关系
                if (child_value > tail_value) {
                    break;
                }
                small_top_heap[position] = child_value;
                position = child_position;
            }
            small_top_heap[position] = tail_value;
        }
        //弹出栈顶元素
        return small_top_heap[0];
    }

    public static void main(String[] args) {
//        HeapSort hs = new HeapSort();
//        hs.addIn(1);
//        hs.addIn(-5);
//        hs.addIn(20);
//        hs.addIn(4);
//        System.out.println(Arrays.toString(hs.small_top_heap));
        int i = 0 ;
        System.out.println(++i);

    }
}
