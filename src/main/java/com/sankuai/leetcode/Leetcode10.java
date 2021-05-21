package com.sankuai.leetcode;

public class Leetcode10 {

    public static void main(String[] args) {
        Leetcode10 leetcode10 = new Leetcode10();
        System.out.println(leetcode10.isMatch("cab","ca.*b"));
    }
    private boolean isMatch(String s,String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (s.length() == 0){
            if (p.length() % 2 != 0){
                return false;
            }
            for(int i = 1; i < p.length(); i += 2){
                if(p.charAt(i) != '*'){
                    return false;
                }
            }
            return true;
        }
        //Initiate the dp array, we can set isMatch[0][j] and isMatch[0][j - 1]  to  1 when the even index of Column zero is '*'
        int[][] isMatch = new int[s.length() + 1][p.length() + 1];
        isMatch[0][0] = 1;
        for(int j = 2; j <= p.length() && p.charAt(j - 1) == '*'; j += 2){
            isMatch[0][j] = 1;
            isMatch[0][j - 1] = 1;
        }
        //then we start the two layer loop, there're totally four situation need to be discussed
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                if(p.charAt(j - 1) == '.'){
                    isMatch[i][j] = isMatch[i - 1][j - 1];
                }else if(p.charAt(j - 1) == '*'){
				    /*
					In this situation, if any previous elements of column j equals 1, isMatch[i][j] will be 1.
					Which means isMatch[i][j] = isMatch[i][j - 2] ｜ isMatch[i - 1][j - 2] ｜ isMatch[i - 2][j - 2] |...|isMatch[0][j - 2].
					We can notice that the" isMatch[i - 1][j - 2] ｜ isMatch[i - 2][j - 2] |...|isMatch[0][j - 2] " part euqals isMatch[i - 1][j]
					So  isMatch[i][j] =  isMatch[i][j - 2] ｜isMatch[i - 1][j]
					*/
                    if(p.charAt(j - 2) == '.'){
                        if(isMatch[i][j - 2] == 1 || isMatch[i - 1][j] == 1){
                            isMatch[i][j] = 1;
                        }
                    }else{
                        //the logic is similar to the previous one
                        if(isMatch[i][j - 2] == 1 || (isMatch[i - 1][j] == 1 && s.charAt(i - 1) == p.charAt(j - 2))){
                            isMatch[i][j] = 1;
                        }
                    }
                }else{
                    isMatch[i][j] = s.charAt(i - 1) == p.charAt(j - 1) ? isMatch[i - 1][j - 1] : 0;
                }
            }
        }
        return isMatch[s.length()][p.length()] == 1;
    }
}
