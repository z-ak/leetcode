package com.java.leetcode.StackandQueue_06;

public class LeetCode_104 {


//    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class Solution {
        public int maxDepth1(TreeNode root) {
            int result = 0;

            result = findDepth(root, result);

            return result;
        }

        public int findDepth(TreeNode node, int result) {
            if (node == null) {
                return result;
            } else {
                result ++;
                int result1 = findDepth(node.left, result);
                int result2 = findDepth(node.right, result);

                result = Math.max(result1, result2);
            }
            return result;
        }


        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}
