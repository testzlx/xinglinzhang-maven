package com.sankuai.tree;



import java.util.*;

public class Leetcode_cn_95 {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val,ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    //leetcode_cn_199
    List<Integer> res_199 = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
         dfs(root,0);
         return res_199;

    }

    private void dfs(TreeNode root, int depth) {
        if(root!=null){
            if(res_199.size() == depth){
                res_199.add(root.val);
            }
            depth++;
            dfs(root.right,depth);
            dfs(root.left,depth);
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if(n ==0){
            return new ArrayList<>();
        }
        return generateTrees(1,n);

    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if(start > end){
            res.add(null);
            return res;
        }
        for(int i = start;i <= end;i++){
            List<TreeNode> subLeftTree = generateTrees(start,i-1);
            List<TreeNode> subRightTree = generateTrees(i+1,end);
            for(TreeNode left : subLeftTree){
                for(TreeNode right : subRightTree){
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    res.add(node);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n= 3;
        Leetcode_cn_95 leetcode_cn_95 = new Leetcode_cn_95();
        List<TreeNode> res = leetcode_cn_95.generateTrees(n);
        System.out.println(res.size());
        System.out.println("-------------------------------------------");
        int[] arr = {1,2,3,4,5,6};
        TreeNode root = leetcode_cn_95.sortedListToBST(arr,0,arr.length-1);
        System.out.println(root.val);

    }

    //leetcode_cn_109 用有序数组[把链表转为数组]转为高度差不多的二叉搜索树
    public TreeNode sortedListToBST(int[] arr,int left,int right) {
        if(left > right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode treeNode = new TreeNode(arr[mid]);
        treeNode.left = sortedListToBST(arr,left,mid-1);
        treeNode.right = sortedListToBST(arr,mid+1,right);
        return treeNode;
    }

    //450 删除二叉搜索树中的节点 //todo 考虑情况很多，没有写完
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        TreeNode cur = null, parent = null, tmp = root;
        while(true){
            if(key == tmp.val){
                cur = tmp;
                break;
            }else if (key > tmp.val){
                parent = tmp;
                tmp = tmp.right;
            }else {
                parent = tmp;
                tmp = tmp.left;
            }
        }
        if (cur == root){

        } else if (cur.left == null && cur.right == null){
                    if (parent.left == cur){
                        parent.left = null;
                    }else {
                        parent.right = null;
                    }
        }
        return root;

    }

    ////450 删除二叉搜索树中的节点
    public TreeNode deleteNodeV2(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if (key>root.val){
            root.right = deleteNodeV2(root.right,key);
        }else if (key < root.val){
            root.left = deleteNodeV2(root.left,key);
        }else {
            if (root.left == null || root.right == null){
                root = root.left== null?root.right:root.left;
            }else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                root.val = cur.val;
                root.right = deleteNodeV2(root.right, cur.val);
            }
        }
        return root;
    }

    //leetcode_113
    List<List<Integer>> paths = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        Deque<Integer> stack = new ArrayDeque<>();
        midWalk(root,targetSum,stack);
        return paths;
    }

    private void midWalk(TreeNode root, int targetSum, Deque<Integer> stack) {
        if (root!=null) {
            stack.addLast(root.val);
            if (root.left == null && root.right == null){
                if(isSum(targetSum,stack)) {
                    paths.add(new ArrayList<>(stack));
                }
            }
            midWalk(root.left,targetSum,stack);
            midWalk(root.right,targetSum,stack);
            stack.pollLast();
        }
    }

    private boolean isSum(int targetSum, Deque<Integer> stack) {
        int sum =0;
        for(int ele:stack){
            sum+=ele;
        }
        return sum == targetSum;
    }


    //树每一层次的节点组成链表  https://leetcode.cn/problems/list-of-depth-lcci/
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> res = new ArrayList<>();
        if (tree == null) {
            return new ListNode[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while(!queue.isEmpty()){
            Queue<TreeNode> tmpQueue = new LinkedList<>();
            ListNode head = new ListNode(-1,null);
            ListNode p = head;
            while(!queue.isEmpty()){
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    tmpQueue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    tmpQueue.add(treeNode.right);
                }
                ListNode newNode  = new ListNode(treeNode.val,null);
                p.next = newNode;
                p = newNode;
            }
            res.add(head.next);
            queue = tmpQueue;
        }
        return res.toArray(new ListNode[0]);
    }

    //  二叉树剪枝
    // https://leetcode.cn/problems/pOCWxh/
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null){
            return null;
        }
        return root;

    }


}
