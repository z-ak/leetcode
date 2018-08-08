package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.*;

public class LeetCode_102 {

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();

            LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();

            if (root == null) {
                return result;
            }

            queue.add(new Pair<TreeNode, Integer>(root, 0));
            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> pair = queue.removeFirst();

                Integer level = pair.getValue();
                if (level == result.size()) {
                    result.add(new ArrayList<Integer>());
                }
                result.get(level).add(pair.getKey().val);

                if (pair.getKey().left != null) {
                    queue.add(new Pair<TreeNode, Integer>(pair.getKey().left, level + 1));
                }

                if (pair.getKey().right != null) {
                    queue.add(new Pair<TreeNode, Integer>(pair.getKey().right, level + 1));
                }
            }

            return result;
        }
    }
}
