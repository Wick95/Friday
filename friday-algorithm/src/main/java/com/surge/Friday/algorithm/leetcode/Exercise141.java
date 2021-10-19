package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;

/**
 * <p>判断链表有环
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.28
 */
public class Exercise141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
