package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;

/**
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.24
 */
public class Exercise24 {
    public ListNode swapPairs(ListNode head1) {
        ListNode head = new ListNode();
        head.next = head1;
        ListNode temp = head;
        while (head.next != null && head.next.next != null) {
            ListNode pointB = head.next;
            ListNode pointC = head.next.next;
            ListNode pointD = head.next.next.next;
            head.next = pointC;
            pointC.next = pointB;
            pointB.next = pointD;
            head = head.next.next;
        }

        return temp.next;
    }
}
