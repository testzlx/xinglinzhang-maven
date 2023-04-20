package com.sankuai.random;


import java.util.Random;

//蓄水池抽样算法
public class Leetcode_cn_382 {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    ListNode head;
    Random random;

    public Leetcode_cn_382(ListNode head){
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int count = 0;
        int returnVal =0 ;
        ListNode node = this.head;
        while (node != null){
            ++count;
            int rdm = this.random.nextInt(count);
            if (rdm ==0){
                returnVal = node.val;
            }
            node = node.next;
        }
        return returnVal;
    }




    public static void main(String[] args) {

    }
}
