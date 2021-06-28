package com.sankuai.trietree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    class TrieNode{
        TrieNode[] childs;
        boolean isWord;
        public TrieNode(){
            childs = new TrieNode[26];
        }
    }
    // leetcode  https://leetcode-cn.com/problems/multi-search-lcci/
    public int[][] multiSearch(String big, String[] smalls) {
        TrieNode root = new TrieNode();
        Map<String,List<Integer>> map = new HashMap<>();
        //构建字典树
        for (String small : smalls) {
            map.put(small,new ArrayList<>());
            insert(root,small);
        }
        //寻找所有单词在big中的起始位置，并存放在map中
        for(int i=0;i<big.length();i++){
            search(root, i, big,map);
        }
        //输出结果
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            ans[i] = map.get(smalls[i]).stream().mapToInt(Integer::valueOf).toArray();
        }
        return ans;
    }

    //构建后缀树
    public void insert(TrieNode root,String word){
        TrieNode node = root;
        for (int i = word.length()-1; i >=0; i--) {
            int idx = word.charAt(i)-'a';
            if(node.childs[idx]==null){
                node.childs[idx] = new TrieNode();
            }
            node = node.childs[idx];
        }
        node.isWord = true; //表示单词的结尾
    }

    //寻找以endPos结尾的所有单词的起始位置
    public void search(TrieNode root,int endPos,String sentence,Map<String,List<Integer>> map){
        TrieNode node = root;
        StringBuilder builder = new StringBuilder(); //单词作为key
        for(int i=endPos;i>=0;i--){
            int idx = sentence.charAt(i)-'a';
            if(node.childs[idx]==null){
                break;
            }
            //由于字典树存的是后缀，故要倒着插入
            builder.insert(0,sentence.charAt(i));
            node = node.childs[idx];//递归寻找
            //找到某个单词，就把起始位置添加到map中
            if(node.isWord){
                map.get(builder.toString()).add(i);
            }
        }
    }

}
