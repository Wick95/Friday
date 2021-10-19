package com.surge.Friday.algorithm.basis.sort;

import com.surge.Friday.algorithm.ListNode;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * <p>归并排序
 * <p>
 * 可以参见Array中的归并实现，{@link java.util.Arrays#sort(Object[], Comparator)}
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.06.09
 */
public class MergeSort {

    public static ListNode sortList(ListNode head) {
        //0.如果List为空或者只有一个，直接返回。
        if (head == null || head.next == null) {
            return head;
        }
        //1.确定List中点
        ListNode slow = head, fast = head.next, left, right;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.切割链表
        ListNode rightHead = slow.next;
        slow.next = null;
        //3.递归归并排序
        left = sortList(head);
        right = sortList(rightHead);

        //4.归并排序
        ListNode tail = new ListNode(0), result = tail;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left == null ? right : left;
        return result.next;
    }

    public static void main(String[] args) {
        double a = 0.1;
        Double d = a ;
        System.out.println(d.toString());
        BigDecimal b = new BigDecimal(a);
        System.out.println(a);
        System.out.println(b);
    }
}












