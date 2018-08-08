package com.java.leetcode.StackandQueue_06;

import javafx.util.Pair;

import java.util.*;

public class LeetCode126 {

    class Solution {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Map<String, Integer> ladder = new HashMap<String, Integer>();
            for(int i = 0 ; i<wordList.size() ; i++){
                ladder.put(wordList.get(i), Integer.MAX_VALUE);
            }
            ladder.put(beginWord, 0);
            Queue<String> q = new LinkedList<String>();
            int minStep = Integer.MAX_VALUE;
            if(beginWord!=null){
                q.offer(beginWord);
                while(!q.isEmpty()){
                    String current = q.poll();
                    int step = ladder.get(current)+1;
                    if(step>minStep) break;
                    for (int i = 0; i < current.length(); i++){
                        StringBuilder sb = new StringBuilder(current);
                        for (char ch='a';  ch <= 'z'; ch++){
                            sb.setCharAt(i, ch);
                            String sbs = sb.toString();
                            if(ladder.containsKey(sbs)){
                                if(step>ladder.get(sbs)) continue;
                                else if(step<ladder.get(sbs)){
                                    q.add(sbs);
                                    ladder.put(sbs, step);
                                }
                                if(map.containsKey(current)){
                                    map.get(current).add(sbs);
                                }else{
                                    Set<String> list= new HashSet<String>();
                                    list.add(sbs);
                                    map.put(current,list);
                                }
                                if(sbs.equals(endWord)) minStep = step;
                            }
                        }
                    }
                }
            }

            generatePath(beginWord, endWord, new ArrayList<String>());
            return result;
        }
        public void generatePath(String currentWord, String endWord, List<String> current){
            current.add(currentWord);
            if(currentWord.equals(endWord)){
                result.add(new ArrayList<String>(current));
            }else{
                Set<String> set = map.get(currentWord);
                if(set!=null){
                    for(String s : set){
                        generatePath(s, endWord,current);
                    }
                }
            }
            current.remove(current.size()-1);
        }
    }

    class Solution2 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Map<String, Integer> distance = new HashMap<>();
            Set<String> dict = new HashSet<>(wordList);
            Map<String, Set<String>> map = new HashMap<>();
            List<List<String>> result = new ArrayList<>();
            List<String> path = new ArrayList<>();

            bfs(beginWord, endWord, dict, distance, map);
            dfs(beginWord, endWord, map, distance, result, path);
            return result;
        }

        private void bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> distance, Map<String, Set<String>> map) {
            for (String s : dict) {
                map.put(s, new HashSet<>());
            }
            Queue<String> begin = new ArrayDeque<>();
            begin.offer(beginWord);
            map.put(beginWord, new HashSet<>());
            distance.put(beginWord, 1);
            while (!begin.isEmpty()) {
                boolean found = false;
                int size = begin.size();
                for (int j = 0; j < size; j++) {
                    String s = begin.poll();
                    int curDistance = distance.get(s);
                    for (int i = 0; i < s.length(); i++) {
                        char[] arr = s.toCharArray();
                        char cur = arr[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != cur) {
                                arr[i] = c;
                            }
                            String newStr = new String(arr);
                            if (dict.contains(newStr)) {
                                map.get(s).add(newStr);
                                if (!distance.containsKey(newStr)) {
                                    distance.put(newStr, curDistance + 1);
                                    if (endWord.equals(newStr)) {
                                        found = true;
                                    } else {
                                        begin.offer(newStr);
                                    }
                                }
                            }
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        private void dfs(String cur, String endWord, Map<String, Set<String>> map, Map<String, Integer> distance, List<List<String>> result, List<String> path) {
            path.add(cur);
            if (endWord.equals(cur)) {
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                return;
            }
            int curDistance = distance.get(cur);
            for (String next : map.get(cur)) {
                if (curDistance + 1 == distance.get(next)) {
                    dfs(next, endWord, map, distance, result, path);
                }
            }
            path.remove(path.size() - 1);
        }
    }

    class Solution1 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            List<List<String>> result = new ArrayList<List<String>>();

            if (!wordList.contains(endWord)) {
                return result;
            }

            Integer minStep = 0;

            Queue queue = new LinkedList<Pair<String, Integer>>();
            queue.add(new Pair<String, Integer>(beginWord, 1));

            List<String> root = new ArrayList<String>();
            root.add(beginWord);
            result.add(root);

            while (!queue.isEmpty()) {
                Pair<String, Integer> pair = (Pair<String, Integer>)queue.poll();

                String str = pair.getKey();
                Integer step = pair.getValue();

                if (!minStep.equals(0) && step > minStep) {
                    break;
                }

//                List<String> wordListCopy = (ArrayList<String>)((ArrayList<String>)wordList).clone();
                for (int i = 0; i < wordList.size(); i++) {
                    String word = wordList.get(i);
                    if (isNextTransformValue(str, word)) {

                        List<List<String>> targetResult= getTargetResultList(result, str, step);
                        for (List<String> inner : targetResult) {
                            if (inner != null) {
                                if (inner.size() == step) {
                                    inner.add(word);
                                } else {
                                    List<String> otherInner = (ArrayList<String>) ((ArrayList<String>) inner).clone();
                                    otherInner.set(step, word);
                                    result.add(otherInner);
                                }
                            }
                        }


                        if (word.equals(endWord)) {
                            if (minStep.equals(0)) {
                                minStep = step + 1;
                            }
                            minStep = Math.min(minStep, step + 1);
                            break;
                        }

                        queue.add(new Pair<String, Integer>(word, step + 1));
//                        wordListCopy.remove(word);
//                        i --;
                    }
                }
            }

            result = new ArrayList<List<String>>(new HashSet<List<String>>(result));
            if (result.size() > 0) {
//                Integer minStep = 0;
//                for (int i = 0; i < result.size(); i++) {
//                    List<String> inner = result.get(i);
//                    if (inner.get(inner.size() - 1).equals(endWord)) {
//                        if (minStep.equals(0)) {
//                            minStep = inner.size();
//                        }
//                        minStep = Math.min(minStep, inner.size());
//                    }
//                }

                for (int i = 0; i < result.size(); i++) {
                    List<String> inner = result.get(i);
                    if (inner.size() != minStep || !inner.get(inner.size() - 1).equals(endWord)) {
                        result.remove(inner);
                        i --;
                    }
                }
            }



            return result;

        }

        public List<List<String>> getTargetResultList(List<List<String>>result, String str, Integer step) {
            List<List<String>> targetResult = new ArrayList<List<String>>();
            for (List<String> inner : result) {
                if (inner.size() >= step && inner.get(step - 1).equals(str)) {
                    targetResult.add(inner);
                }
            }

            return targetResult;
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
//        String beginWord = "a";
//        String endWord = "c";
//        List<String> wordList = new ArrayList<String>();
//        wordList.add("a");
//        wordList.add("b");
//        wordList.add("c");

//        String beginWord = "hit";
//        String endWord = "cog";
//        List<String> wordList = new ArrayList<String>();
//        wordList.add("hot");
//        wordList.add("dot");
//        wordList.add("dog");
//        wordList.add("lot");
//        wordList.add("log");
//        wordList.add("cog");


//        String beginWord = "hot";
//        String endWord = "dog";
//        List<String> wordList = new ArrayList<String>();
//        wordList.add("hot");
//        wordList.add("cog");
//        wordList.add("dog");
//        wordList.add("tot");
//        wordList.add("hog");
//        wordList.add("hop");
//        wordList.add("pot");
//        wordList.add("dot");



//        "hit"
//        "cog"
//                ["hot","hit","cog","dot","dog"]

//        String beginWord = "hit";
//        String endWord = "cog";
//        List<String> wordList = new ArrayList<String>();
//        wordList.add("hot");
//        wordList.add("hit");
//        wordList.add("cog");
//        wordList.add("dot");
//        wordList.add("dog");



//        "red"
//        "tax"
//                ["ted","tex","red","tax","tad","den","rex","pee"]

//        String beginWord = "red";
//        String endWord = "tax";
//        List<String> wordList = new ArrayList<String>();
//        wordList.add("ted");
//        wordList.add("tex");
//        wordList.add("red");
//        wordList.add("tax");
//        wordList.add("tad");
//        wordList.add("den");
//        wordList.add("rex");
//        wordList.add("pee");


//        "hot"
//        "dog"
//                ["hot","cog","dog","tot","hog","hop","pot","dot"]

        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("cog");
        wordList.add("dog");
        wordList.add("tot");
        wordList.add("hog");
        wordList.add("hop");
        wordList.add("pot");
        wordList.add("dot");


        LeetCode126 leetCode126 = new LeetCode126();
        LeetCode126.Solution solution = leetCode126.new Solution();

        System.out.println(solution.findLadders(beginWord, endWord, wordList));
    }
}
