package com.surge.Friday.algorithm.leetcode;

import com.surge.Friday.algorithm.TreeNode;

import java.util.ArrayList;

/**
 * <p> 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.27
 */
public class Exercise230 {
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        inOrderTraversal(root, arrayList);
        return arrayList.get(k - 1);
    }

    public static ArrayList<Integer> inOrderTraversal(TreeNode root, ArrayList<Integer> arrayList) {
        if (root == null) {
            return arrayList;
        }
        inOrderTraversal(root.left, arrayList);
        arrayList.add(root.val);
        inOrderTraversal(root.right, arrayList);
        return arrayList;
    }




    /*  public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    while (true) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();
      if (--k == 0) return root.val;
      root = root.right;
    }
  }*/
}
