package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.*;

public class LeetCode_127 {

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            List queue = new LinkedList<Pair<String, Integer>>();
            queue.add(new Pair<String, Integer>(beginWord, 1));

            Set<String> visited = new HashSet<String>();
            visited.add(beginWord);

            while (!queue.isEmpty()) {
                Pair<String, Integer> pair = (Pair)((LinkedList) queue).removeFirst();
                String str = pair.getKey();
                int step = pair.getValue();

                for (int i = 0; i < wordList.size(); i++) {
//            for (String other : wordList) {
                    String other = wordList.get(i);
                    if (!visited.contains(other)) {
                        if (isNextTransformValue(str, other)) {
                            if (endWord.equals(other)) {
                                return step + 1;
                            }
                            queue.add(new Pair<String, Integer>(other, step + 1));
                            wordList.remove(other);
                            i --;
                        }
                    }
                }
            }

            return 0;
        }

        public boolean isNextTransformValue(String str, String other) {
            int flag = 0;
            int length = str.length();
            for (int i = 0; i <length; i++) {
                if (str.charAt(i) != other.charAt(i)) {
                    flag ++;
                }
            }

            if (flag == 1) {
                return true;
            }

            return false;
        }



    }

    public static void main(String[] args) {
        String beginWord = "a";
        String endWord = "c";
//        List<String> wordList = Arrays.asList("a", "b", "c");
        List<String> wordList = new ArrayList<String>();
        wordList.add("a");
        wordList.add("b");
        wordList.add("c");

        LeetCode_127 leetCode_127 = new LeetCode_127();
        LeetCode_127.Solution solution = leetCode_127.new Solution();

        System.out.println(solution.ladderLength(beginWord, endWord, wordList));
    }
}


