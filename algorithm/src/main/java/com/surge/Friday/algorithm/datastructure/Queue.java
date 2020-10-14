package com.surge.Friday.algorithm.datastructure;

import com.surge.Friday.algorithm.ListNode;

/**
 * <p>Queue
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class Queue extends AbstractLinearClass {

    public ListNode head;
    public ListNode tail;
    public int length;

    public Queue() {
    }

    public Queue(ListNode head) {
        this.head = head;
        this.tail = head;

    }

    public void add(ListNode listNode) {
        if (head == null || tail == null) {
            head = listNode;
        } else {
            ListNode prev = tail;
            prev.next = listNode;
            listNode.prev =  prev;
        }
        tail = listNode;
    }

    public void remove() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        }
    }

}