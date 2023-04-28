package com.sankuai.string;

/**
 *   https://www.cnblogs.com/zzuuoo666/p/9028287.html
 */
public class KMP {

    private int[] getNext(String s){
        int[] next = new int[s.length()];
        next[0] =-1;
        int j=0,k=-1;
        while (j<s.length() -1){
            if(k ==-1 || s.charAt(j) == s.charAt(k)){
                j++;k++;next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next;
    }

    public int kmpSearch(String txt,String pattern){
        int[] next = getNext(pattern);
        int i =0,j= 0;
        while(i< txt.length() && j < pattern.length()){
            if(j == -1 || txt.charAt(i) == pattern.charAt(j)){
                i++;j++;
            }else{
                j = next[j];
            }
        }
        if(j == pattern.length()){
            return  i- j;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String t = "abab",s = "abcabce";
        System.out.println(kmp.kmpSearch(s,t));
    }
}


