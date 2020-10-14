package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>给定一个有环链表，实现一个算法返回环路的开头节点。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.28
 */
public class Exercise0208 {
    public ListNode detectCycle(ListNode head) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ListNode slow = null;

        while (!checkCycle(hashMap)) {
            slow = slow == null ? head:slow.next;
            if (slow == null) {
                return null;
            }
            hashMap.put(slow.hashCode(), hashMap.getOrDefault(slow.hashCode(), 0) + 1);
        }


        return slow;
    }

    public boolean checkCycle(HashMap<Integer, Integer> hashMap) {
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > 1) {
                return true;
            }
        }
        return false;
    }
}
