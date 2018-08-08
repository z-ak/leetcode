package com.java.leetcode.StackandQueue_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode_144 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    class Solution {
        class Command {
            String s;    // print, go
            TreeNode treeNode;

            public Command(String s, TreeNode treeNode) {
                this.s = s;
                this.treeNode = treeNode;
            }
        }

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            Stack<Command> stack = new Stack<Command>();
            if (root != null) {

                stack.push(new Command("go", root));

                while (stack.size() > 0) {
                    Command command = stack.pop();
                    if ("print".equals(command.s)) {
                        result.add(command.treeNode.val);
                    } else {
                        assert ("go".equals(command.s));
                        if (command.treeNode.right != null) {
                            stack.push(new Command("go", command.treeNode.right));
                        }
                        if (command.treeNode.left != null) {
                            stack.push(new Command("go", command.treeNode.left));
                        }
                        stack.push(new Command("print", command.treeNode));
                    }
                }

            }


            return result;
        }

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            Stack<Command> stack = new Stack<Command>();
            if (root != null) {
                stack.push(new Command("go", root));

                while (!stack.empty()) {
                    Command command = stack.pop();
                    if ("print".equals(command.s)) {
                        result.add(command.treeNode.val);
                    } else {
                        assert("go".equals(command.s));
                        if (command.treeNode.right != null) {
                            stack.push(new Command("go", command.treeNode.right));
                        }
                        stack.push(new Command("print", command.treeNode));
                        if (command.treeNode.left != null) {
                            stack.push(new Command("go", command.treeNode.left));
                        }
                    }
                }
            }

            return result;
        }

        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            Stack<Command> stack = new Stack<Command>();
            if (root != null) {
                stack.push(new Command("go", root));

                while (!stack.empty()) {
                    Command command = stack.pop();
                    if ("print".equals(command.s)) {
                        result.add(command.treeNode.val);
                    } else {
                        assert("go".equals(command.s));
                        stack.push(new Command("print", command.treeNode));
                        if (command.treeNode.right != null) {
                            stack.push(new Command("go", command.treeNode.right));
                        }
                        if (command.treeNode.left != null) {
                            stack.push(new Command("go", command.treeNode.left));
                        }
                    }
                }
            }

            return result;
        }
    }
}
