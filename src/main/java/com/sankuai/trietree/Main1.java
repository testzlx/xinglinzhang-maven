package com.sankuai.trietree;

import java.util.*;

/**
 *  https://leetcode-cn.com/problems/re-space-lcci/submissions/
 *
 */
public class Main1 {
    public static void main(String[] args) {
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";
        Main1 main1 = new Main1();
        System.out.println(main1.respace(dictionary,sentence));
        System.out.println(main1.respaceV2(dictionary,sentence));
    }
    public int respace(String[] dictionary, String sentence) {
        // 构建字典树
        Trie trie = new Trie();
        for (String word: dictionary) {
            trie.insert(word);
        }
        // 状态转移，dp[i] 表示字符串的前 i 个字符的最少未匹配数
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx: trie.search(sentence, i - 1)) {
                dp[i] = Math.min(dp[i], dp[idx]);
            }
        }
        return dp[n];
    }

    public int respaceV2(String[] dictionary, String sentence){
        Set<String> dic = new HashSet<>();
        // <最后一个字符，这样的单词长度有哪些>
        Map<Character, Set<Integer>> map = new HashMap<>();
        for(String str: dictionary){
            dic.add(str);
            int len = str.length();
            char c = str.charAt(len-1);
            Set<Integer> set = map.getOrDefault(c, new HashSet<>());
            set.add(len);
            map.put(c, set);
        }

        int n = sentence.length();
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1]+1;
            char c = sentence.charAt(i-1);
            if(map.containsKey(c)){
                Set<Integer> lens = map.get(c);
                // for(int l: lens){
                //     if(i>=l && dic.contains(sentence.substring(i-l,i))){
                //         dp[i] = Math.min(dp[i], dp[i-l]);
                //     }
                // }
                Iterator<Integer> it = lens.iterator();
                while(it.hasNext()){
                    int l = it.next();
                    if(i>=l && dic.contains(sentence.substring(i-l,i))){
                        dp[i] = Math.min(dp[i], dp[i-l]);
                    }
                }
            }
        }
        return dp[n];
    }

}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 将单词倒序插入字典树
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (cur.children[c] == null) {
                cur.children[c] = new TrieNode();
            }
            cur = cur.children[c];
        }
        cur.isWord = true;
    }

    // 找到 sentence 中以 endPos 为结尾的单词，返回这些单词的开头下标。
    public List<Integer> search(String sentence, int endPos) {
        List<Integer> indices = new ArrayList<>();
        TrieNode cur = root;
        for (int i = endPos; i >= 0; i--) {
            int c = sentence.charAt(i) - 'a';
            if (cur.children[c] == null) {
                break;
            }
            cur = cur.children[c];
            if (cur.isWord) {
                indices.add(i);
            }
        }
        return indices;
    }
}

class TrieNode {
    boolean isWord;
    TrieNode[] children = new TrieNode[26];

    public TrieNode() {}
}

