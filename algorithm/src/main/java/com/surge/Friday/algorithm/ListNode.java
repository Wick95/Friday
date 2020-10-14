package com.surge.Friday.algorithm;

import com.surge.Friday.algorithm.datastructure.AbstractLinearClass;

/**
 * <p>ListNode
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.14
 */
public class ListNode extends AbstractLinearClass {
    public int val;
    public int order;
    public ListNode prev;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, int order) {
        this.val = val;
        this.order = order;
    }
}