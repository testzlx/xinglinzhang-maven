package com.visualbusiness.my_test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int result = main.transfer("-23e3e");
        System.out.println(result);

    }

    private int transfer(String str) {
        if (str == null || str.trim().equals("")){
            return 0;
        }
        if(!checkValid(str)) {
            throw  new IllegalArgumentException("illegal argument");
        }
        char[] array = str.toCharArray();
        char firstChar = array[0];
        boolean negative = firstChar == '-';
        int position = str.indexOf('e');
        if ( position == -1 ) {
            return negative ? -Integer.parseInt(str) : Integer.parseInt(str);
        }else {
            int before = Integer.parseInt(str.substring(0,position));
            int after  = Integer.parseInt(str.substring(position+1));
           // return negative ? -getNum(before,after) : getNum(before,after) ;
            return getNum(before,after) ;
        }

        /*
        int length = array.length,sum = 0;
        for (int i = 0; i< array.length;i++){
            if(i==0 && (array[i] == '-' ||array[i] == '+')) {
                length -=1;
                if(array[i] == '-') {
                    negative = true;
                }
                continue;
            }
            char ch = array[i];
            if(ch != 'e') {
                int j = ch - '0';
                sum += getNum(j,length-1-i);
            } else {
                if (onlyOne) {
                    throw  new IllegalArgumentException("illegal argument");
                }
                onlyOne = true;
                String

            }

        }
        return negative? (0-sum) : sum;
         */
    }

    private boolean checkValid(String str) {
        char[] array = str.toCharArray();
        char firstChar = array[0];
        List<Character> list = initLegalChar();
        list.add('+');
        list.add('-');
        if(!list.contains(firstChar)) {
            return false;
        }
        List<Character> tmp = initLegalChar();
        int eNum = 0;
        for(int i = 1;i<array.length;i++) {
            if(!tmp.contains(array[i])) {
                return false;
            }
            if(array[i] == 'e') {
                eNum++;
            }
        }
        return eNum <=1 ?true : false;
    }

    private int getNum(int base, int count) {
        int multiple = 1;
        for(int i=0;i<count;i++) {
            multiple *=10;
        }
        return base * multiple;
    }

    private List<Character> initLegalChar() {
        List<Character> list = new ArrayList<>();
        list.add('e');
        list.add('0');
        list.add('1');
        list.add('2');
        list.add('3');
        list.add('4');
        list.add('5');
        list.add('6');
        list.add('7');
        list.add('8');
        list.add('9');
        return list;
    }
}
