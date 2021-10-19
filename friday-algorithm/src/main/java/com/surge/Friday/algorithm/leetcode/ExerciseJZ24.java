package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;

/**
 * <p>剑指offer 24题
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.28
 */
public class ExerciseJZ24 {
    public ListNode reverseList(ListNode head) {

        if(head == null){
            return null;
        }
        ListNode pointA = head, pointB = head.next;
        ListNode prev = null;
        while (pointB != null) {
            ListNode temp = pointB.next;
            pointB.next = pointA;
            pointA.next = prev;
            prev = pointA;
            pointA = pointB;
            pointB = temp;
        }
        return pointA;
    }
    /* ArrayList<Integer> arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        ListNode listNode = new ListNode();
        ListNode temp = listNode;
        for (int i = arrayList.size() - 1; i >= 0; i--) {

            temp.next = new ListNode(arrayList.get(i));
            temp = temp.next;
        }
        return listNode.next;*/
}
