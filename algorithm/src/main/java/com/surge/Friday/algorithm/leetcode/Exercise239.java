package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;
import com.surge.Friday.algorithm.datastructure.Queue;

import java.util.Arrays;

/**
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.27
 */
public class Exercise239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {

        int[] result = new int[nums.length];
        //1.构建长度为K的窗口
        Queue queue = null;

        //2.移动窗口
        for (int i = 0; i < nums.length; i++) {
            //2.1 将新元素押入队尾，并将旧元素去除
            if (queue != null && queue.head.order <= i-k) {
                queue.remove();
            }
            while (true) {
                if (queue == null || queue.head == null) {
                    queue = new Queue(new ListNode(nums[i], i));
                    break;
                } else if (nums[i] <= queue.tail.val) {
                    ListNode temp = new ListNode(nums[i], i);
                    queue.add(temp);
                    break;
                } else if (nums[i] > queue.tail.val) {
                    if (queue.tail.prev == null) {
                        queue.head = null;
                    }
                    queue.tail = queue.tail.prev;
                }
            }
            //3.读取队首
            result[i] = queue.head.val;
        }
        int z = nums.length > k ? k-1 :nums.length-1;
        //4.返回结构
        return Arrays.copyOfRange(result,z,result.length);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3)));
    }
}
