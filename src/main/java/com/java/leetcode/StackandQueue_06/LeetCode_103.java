package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_103 {
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();

            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

            if (root == null) {
                return result;
            }

            int level = 0;
            queue.add(root);
            while (!queue.isEmpty()) {
                boolean flag = level % 2 == 0;
                int size = queue.size();

                List<Integer> _list = new ArrayList<Integer>();
                while (size-- > 0) {
                    // 根据奇偶行判断之字顺序
                    TreeNode treeNode = queue.removeFirst();
                    if (flag) {
                        _list.add(treeNode.val);
                    } else {
                        _list.add(0, treeNode.val);
                    }

                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }

                result.add(_list);

                level++;
            }

            return result;
        }

        public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
            LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();

            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

            if (root == null) {
                return result;
            }

            int level = 0;
            queue.add(root);
            while (!queue.isEmpty()) {
                boolean flag = level % 2 == 0;
                int size = queue.size();

                List<Integer> _list = new ArrayList<Integer>();
                LinkedList<TreeNode> _queue = new LinkedList<TreeNode>();
                _queue = (LinkedList<TreeNode>)queue.clone();
                while (size-- > 0) {
                    // 根据奇偶行判断之字顺序
                    TreeNode treeNode;
                    if (flag) {
                        treeNode = _queue.poll();
                    } else {
                        treeNode = _queue.removeLast();
                    }
                    _list.add(treeNode.val);

                    // 保持顺序的从队列中取元素
                    TreeNode queueTreeNode = queue.poll();
                    if (queueTreeNode.left != null) {
                        queue.add(queueTreeNode.left);
                    }
                    if (queueTreeNode.right != null) {
                        queue.add(queueTreeNode.right);
                    }
                }

                result.add(_list);

                level++;
            }

            return result;
        }
    }
}
