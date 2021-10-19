package com.surge.Friday.algorithm.leetcode.top100;

import java.util.List;

/**
 * <p> 2.两数相加
 *
 * @author Matt Liu
 * @version 1.0 created by 10.15, 2021
 */
public class Exercise2 {
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode setNext(ListNode next) {
            this.next = next;
            return this;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i1 = readLinkedListToInt(l1);

        int i2 = readLinkedListToInt(l2);


        return convertToLinkedList(i1 + i2);
    }

    public int readLinkedListToInt(ListNode node) {
        int res = 0;
        int quan = 1;
        ListNode current = node;
        while (current != null) {
            res += quan * current.val;
            quan *= 10;
            current = current.next;
        }
        return res;
    }


    public ListNode convertToLinkedList(int num) {
        ListNode listNode = new ListNode();
        ListNode temp = listNode;

        while (num  != 0) {
            listNode.next = new ListNode(num % 10);
            listNode =listNode.next ;
            num = num /10;
        }

        return temp.next;
    }

    public static void main(String[] args) {
        new Exercise2().addTwoNumbers(
                new ListNode(1).setNext(new ListNode(9).setNext(new ListNode(9))),
                new ListNode(9));

    }
}
