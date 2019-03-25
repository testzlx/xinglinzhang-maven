package com.sankuai;

public class KMP {

    private int[] getNext(String s){
        int[] next = new int[s.length() ];
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

    public int kmpSearch(String s,String t){
        int[] next = getNext(t);
        int i =0,j= 0;
        while(i< s.length() && j < t.length()){
            if(j == -1 || s.charAt(i) == t.charAt(j)){
                i++;j++;
            }else{
                j = next[j];
            }
        }
        if(j == t.length()){
            return  i- j;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String t = "aba",s = "abbaba";
        System.out.println(kmp.kmpSearch(s,t));
    }
}


