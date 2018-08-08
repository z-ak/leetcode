package com.java.leetcode.StackandQueue_06;

import java.util.Stack;

public class ValidParentheses_01 {
    public static void main(String[] args) {

        if (isValid("()")) {
            System.out.println("() is valid.");
        } else {
            System.out.println("() is invalid.");
        }

        if (isValid("()[]{}")) {
            System.out.println("()[]{} is valid.");
        } else {
            System.out.println("()[]{} is invalid.");
        }

        if (isValid("(]")) {
            System.out.println("(] is valid.");
        } else {
            System.out.println("(] is invalid.");
        }

        if (isValid("([)]")) {
            System.out.println("([)] is valid.");
        } else {
            System.out.println("([)] is invalid.");
        }
    }

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i< s.length(); i ++ ) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0) {
                    return false;
                }

                char c = stack.pop();

                char match;
                if(s.charAt(i) == ')') {
                    match = '(';
                } else if (s.charAt(i) == ']') {
                    match = '[';
                } else {
                    assert(s.charAt(i) == '}');
                    match = '{';
                }

                if (c != match) {
                    return false;
                }
            }
        }

        if (stack.size() != 0) {
            return false;
        }

        return true;
    }
}
