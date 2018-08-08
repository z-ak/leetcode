package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.*;

public class LeetCode_279 {

    class Solution {
        public int numSquares(int n) {
            assert(n >=0);
            List queue = new LinkedList<Pair>();

            queue.add(new Pair<Integer, Integer>(n, 0));

            Map visited = new HashMap();
            visited.put(n, true);

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pair = (Pair)((LinkedList) queue).removeFirst();
                int num = pair.getKey();
                int step = pair.getValue();


                for (int i = 1; ; i++) {
                    int a = num - i * i;
                    if (a == 0) {
                        return step + 1;
                    }
                    if (a < 0) {
                        break;
                    }
                    if (!visited.containsKey(a)) {
                        queue.add(new Pair<Integer, Integer>(a, step + 1));
                        visited.put(a, true);
                    }
                }
            }

            return -1;
        }

    }

    public static void main(String[] args) {
        LeetCode_279 leetCode_279 = new LeetCode_279();
        LeetCode_279.Solution solution = leetCode_279.new Solution();
        System.out.println(new Date());
        System.out.println(solution.numSquares(12));
        System.out.println(new Date());
    }
}



