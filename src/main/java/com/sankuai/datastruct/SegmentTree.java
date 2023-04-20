package com.sankuai.datastruct;

//线段树 https://blog.csdn.net/johnny901114/article/details/80643017
public class SegmentTree<T> {
    private T data[];
    private T tree[];

    private Merger<T> merger;

    interface  Merger<T>{
        public T merge(T a, T b);
    }

    public SegmentTree(T[] arr,Merger merger){
        int size=  arr.length;
        data = (T[]) new Object[size];
        System.arraycopy(arr,0,data,0,size);
        tree = (T[]) new Object[size * 4];
        this.merger = merger;
        buildSegmentTree(0,0,size-1);

    }

    private void buildSegmentTree(int treeIndex, int start, int end) {
        if(start == end){
            tree[treeIndex] = data[start];
            return ;
        }
        int treeLeft = treeIndex *2+1,treeRight = treeLeft +1;
        int mid = start + (end - start) /2;
        buildSegmentTree(treeLeft,start,mid);
        buildSegmentTree(treeRight,mid+1,end);
        tree[treeIndex] = merger.merge(tree[treeLeft] , tree[treeRight]);
    }

    public  T query(int start,int end){
        return query(0,0,this.data.length-1,start,end);
    }

    private T query(int treeIndex, int treeLeft, int treeRight, int queryL, int queryR) {
        if(treeRight == queryR && treeLeft == queryL){
            return tree[treeIndex];
        }
        int mid = treeLeft + (treeRight - treeLeft)/2;
        int leftTreeIndex = treeIndex *2+1,rightTreeIndex = leftTreeIndex+1;
        if(queryR <=mid){
            return query(leftTreeIndex,treeLeft,mid,queryL,queryR);
        }
        if(queryL >= mid+1){
            return query(rightTreeIndex,mid+1,treeRight,queryL,queryR);
        }
        T left = query(leftTreeIndex, treeLeft, mid, queryL, mid);
        T right = query(rightTreeIndex, mid + 1, treeRight, mid + 1, queryR);
        return merger.merge(left, right);
    }


    public void update(int index, T e) {
        data[index] = e;
        update(0, 0, data.length - 1, index, e);
    }


    private void update(int treeIndex, int treeLeft, int treeRight, int index, T e) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = e;
            return;
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftChildIndex = treeIndex * 2+1;
        int rightChildIndex = leftChildIndex +1;

        if (index <= mid) {
            update(leftChildIndex, treeLeft, mid, index, e);
        } else if (index >= mid + 1) {
            update(rightChildIndex, mid + 1, treeRight, index, e);
        }

        //更改完叶子节点后，还需要对他的所有祖辈节点更新
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    public static void main(String[] args) {
        Integer arr[] = {2,6,1,5,8,3,4,1,2,5};
        SegmentTree<Integer> segmentTree = new SegmentTree(arr, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a+b;
            }
        });
        int result = segmentTree.query(1,4);
        System.out.println("hello world!!");
    }
}
