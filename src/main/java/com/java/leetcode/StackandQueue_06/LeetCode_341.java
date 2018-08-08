package com.java.leetcode.StackandQueue_06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class LeetCode_341 {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        class Command {
            String s;    // print, go
            NestedInteger nestedInteger;

            public Command(String s, NestedInteger nestedInteger) {
                this.s = s;
                this.nestedInteger = nestedInteger;
            }
        }

        public List<Integer> list = new ArrayList<Integer>();

        public Integer currentIndex = null;

        public boolean iteratored = false;

        public NestedIterator(List<NestedInteger> nestedList) {

            if (nestedList != null) {
                Stack<Command> stack = new Stack<Command>();
                addToStack(nestedList, stack);

                while (!stack.empty()) {
                    Command command = stack.pop();
                    if ("print".equals(command.s)) {
                        list.add(command.nestedInteger.getInteger());
                    } else {
                        assert("go".equals(command.s));
                        List<NestedInteger> childNestedInteger = command.nestedInteger.getList();
                        addToStack(childNestedInteger, stack);
                    }
                }
            }
        }

        @Override
        public Integer next() {
            return currentIndex != null ? list.get(currentIndex) : null;
        }

        @Override
        public boolean hasNext() {
            if (list.size() > 0) {
                if (currentIndex == null) {
                    if (!iteratored) {
                        currentIndex = 0;
                        iteratored = true;
                    }
                } else {
                    if (currentIndex != list.size() -1) {
                        currentIndex ++;
                    } else {
                        currentIndex = null;
                    }
                }

                if (currentIndex != null) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        }

        private void addToStack(List<NestedInteger> nestedList, Stack<Command> stack) {
            for (int i = nestedList.size() -1; i >= 0; i--) {
                NestedInteger nestedInteger = nestedList.get(i);
                if (nestedInteger.isInteger()) {
                    stack.push(new Command("print", nestedInteger));
                } else {
                    stack.push(new Command("go", nestedInteger));
                }
            }
        }

    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
