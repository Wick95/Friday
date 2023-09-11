package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.ListNode;
import com.surge.Friday.algorithm.basis.sort.mergeSort.MergeSort;

/**
 * <p>在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.06.09
 */
public class Exercise148 {
    public static void main(String[] args) {
        MergeSort.sortList(new ListNode(1));
    }
}
