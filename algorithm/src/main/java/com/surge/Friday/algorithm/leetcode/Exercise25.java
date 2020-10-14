package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;

/**
 * <p>给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.24
 */
public class Exercise25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null){
            return null;
        }
        ListNode virtual = new ListNode();
        virtual.next = head;
        ListNode temp = virtual;

        while (virtual != null) {
            int i = k-1, j = 0;
            // 1.需要数组来存储反转经过的节点
            ListNode[] array = new ListNode[k];
            // 2.进行装载
            ListNode node = virtual;
            while (i >= 0) {
                if (node.next == null) {
                    return temp.next;
                }
                array[i] = node.next;
                node = node.next;
                i--;
            }
            ListNode tail = node.next;

            //3.进行重新排序
            while (j < k) {
                virtual.next = array[j];
                virtual = virtual.next;
                j++;
            }
            virtual.next = tail;
            virtual = tail;
        }
        return temp.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        reverseKGroup(head,2);

    }
}
