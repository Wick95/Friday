package com.surge.Friday.algorithm;

import com.surge.Friday.algorithm.datastructure.AbstractTreeClass;

/**
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.07.27
 */
public class TreeNode extends AbstractTreeClass {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}