package com.java.leetcode.StackandQueue_06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_199 {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new LinkedList<Integer>();

            Queue<TreeNode> queue = new LinkedList<TreeNode>();

            if (root == null) {
                return result;
            }

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();

                while (size-- > 0) {
                    TreeNode treeNode = queue.remove();

                    if (size == 0) {
                        result.add(treeNode.val);
                    }

                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }

            }

            return result;
        }
    }
}
