package com.java.leetcode.StackandQueue_06;

public class LeetCode_111 {


//    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class Solution {
        public int minDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (!(root.left == null && root.right == null) && !(root.left != null && root.right != null)) {
                if (root.left != null) {
                    return minDepth(root.left) + 1;
                }
                if (root.right != null) {
                    return minDepth(root.right) + 1;
                }
            }

            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (root.left == null || root.right == null) {
                return minDepth(root.left) + minDepth(root.right) + 1;
            }

            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }


    }
}
