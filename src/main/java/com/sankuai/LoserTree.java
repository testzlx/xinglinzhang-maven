package com.sankuai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//败者树
public class LoserTree {

    private int[] tree;
    private int size;
    private int[] leaves;

    private int MAXINTEGER = Integer.MAX_VALUE;

    public LoserTree(int [] leaves){
        init(leaves);
    }

    public LoserTree(){

    }

    public  void init(int [] leaves){
        this.leaves = leaves;
        this.tree = new int[leaves.length];
        this.size = leaves.length;
        for(int i= 0;i<size;i++){
            tree[i] = -1;
        }
        for(int i = size-1;i>=0;i--){
            adjust(i);
        }
    }

    private void adjust(int i) {
        int parent = (i+this.size) /2;
        while(parent > 0){
            if(i >=0&&(tree[parent] == -1 || leaves[i] > leaves[tree[parent]])){
               // System.out.println("i="+i+" tree[parent]:"+tree[parent]);
                int tmp = i;
                i = tree[parent];
                tree[parent] = tmp;
            }
            parent = parent /2;
        }
        tree[0] = i;
    }

    public int getWinter(){
        return tree[0];
    }

    public void add(int index,int value){
        leaves[index] = value;
        adjust(index);
    }
   //k路归并算法
    public void kMerge(int[][] allSegments,int size){
        List<Integer> result = new ArrayList<Integer>();
        //记录每段当前的下标
         Map<Integer,Integer> indexMap = new HashMap<Integer,Integer>();
        int kArr[] = new int[allSegments.length];
        //取每个段第一个元素
        for(int i =0 ;i<allSegments.length;i++){
            kArr[i] = allSegments[i][0];
            indexMap.put(i,0);
        }
        init(kArr);
        while(!mapValueAllGT5(indexMap,size)) {
            int segmentNum = getWinter();
            int index = indexMap.get(segmentNum);
            System.out.println(allSegments[segmentNum][index]);
            result.add(allSegments[segmentNum][index]);
            indexMap.put(segmentNum, ++index);
            if(indexMap.get(segmentNum) <=size) {
                add(segmentNum, allSegments[segmentNum][indexMap.get(segmentNum)]);
            }else{
                add(segmentNum, MAXINTEGER);
            }
           // adjust(segmentNum);
        }
    }

    private boolean mapValueAllGT5(Map<Integer,Integer> map,int size){
        for(Integer value : map.values()){
            if(value <= size){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*
        int leaves[] = {10,9,20,6,12};
        LoserTree loserTree = new LoserTree(leaves);
        */
        int segment1[] = {1,6,11,16,45,78,90,99};
        int segment2[] = {2,7,12,17,46,79,91,102};
        int segment3[] = {3,8,14,18,47,80,93,104};
        int segment4[] = {4,9,14,19,48,81,94,108};
        int segment5[] = {5,10,15,20,49,82,83,110};
        int all[][] = {segment1,segment2,segment3,segment4,segment5};
        LoserTree loserTree = new LoserTree();
        loserTree.kMerge(all,all[0].length-1);
        System.out.println(loserTree);
    }
}
