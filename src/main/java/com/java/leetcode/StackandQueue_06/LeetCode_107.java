package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_107 {
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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();

            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

            if (root == null) {
                return result;
            }

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();

                List<Integer> _list = new ArrayList<Integer>();
                while (size-- > 0) {
                    TreeNode treeNode = queue.poll();
                    _list.add(treeNode.val);

                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }

                result.addFirst(_list);
            }

            return result;
        }

        public List<List<Integer>> levelOrderBottom1(TreeNode root) {
            LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();

            LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();

            if (root == null) {
                return result;
            }

            queue.add(new Pair<TreeNode, Integer>(root, 0));
            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> pair = queue.removeFirst();

                Integer level = pair.getValue();
                if (level == result.size()) {
                    result.addFirst(new ArrayList<Integer>());
                }
                result.get(result.size() - 1 - level).add(pair.getKey().val);

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
