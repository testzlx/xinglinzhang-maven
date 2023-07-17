package com.sankuai.string;

public class Offer67 {

    public static void main(String[] args) {
        String s = "   +0 123";
        System.out.println(strToInt(s));


    }
    public static int strToInt(String str) {
        boolean isNegative = false,findHeader = false;
        long ret = 0;
        for(char ch:str.toCharArray()){
            if (ch ==' '){
                if(findHeader){
                    break;
                }else {
                    continue;
                }
            }else if (  ch - '0' <= 9 && ch - '0' >= 0){
                findHeader = true;
                ret = ret * 10 + (ch - '0');
                if(ret > Integer.MAX_VALUE){
                    return isNegative? Integer.MIN_VALUE: Integer.MAX_VALUE;
                }
            }else {
                if (!findHeader && (ch == '+' || ch == '-') ){
                    findHeader = true;
                    if (ch == '-'){
                        isNegative = true;
                    }
                }else {
                    break;
                }
            }
        }
        return isNegative? (int)(-1*ret): (int)ret;
    }
}
