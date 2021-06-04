package com.sankuai.queue;

import java.util.*;

public class Leetcode_zh_199 {
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int height;

        TreeNode(int value, int height) {
            this.val = value;
            this.height = height;
        }

        TreeNode(int value) {
            this.val = value;
        }
    }

    public static void main(String[] args) {
        Leetcode_zh_199 main = new Leetcode_zh_199();
        int[] arr = {1, 2, -1, 5, -1, -1, 3, 4, -1, -1, -1};
        TreeNode root = main.createTree(arr, 0);
        System.out.println(root.val);
        System.out.println(main.rightSideViewV2(root));
        System.out.println(main.rightSideView(root));
    }

    int index = 0;

    public TreeNode createTree(int[] arr, int height) {
        TreeNode node = null;
        int value = arr[index++];
        if (value != -1) {
            node = new TreeNode(value, height);
            node.left = createTree(arr, height + 1);
            node.right = createTree(arr, height + 1);
        }
        return node;
    }

    // 自己的做法, 深度遍历
    public List<Integer> rightSideViewV2(TreeNode root) {
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        preOrder(root, map);
        List<Integer> ret = new LinkedList<>();
        for (Deque<Integer> tmpQueue : map.values()) {
            ret.add(tmpQueue.peekLast());
        }
        return ret;

    }

    // 自己的做法, 广度遍历
    public List<Integer> rightSideView(TreeNode root) {
        resetHeight(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        root.height = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Deque<Integer> deque = map.getOrDefault(node.height, new LinkedList<>());
            deque.add(node.val);
            map.put(node.height, deque);
            if (node.left != null) {
                node.left.height = node.height + 1;
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.height = node.height + 1;
                queue.add(node.right);
            }
        }
        List<Integer> ret = new LinkedList<>();
        for (Deque<Integer> tmpQueue : map.values()) {
            ret.add(tmpQueue.peekLast());
        }
        return ret;
    }


    //--------------------------------官方解答------------------------------------

        public List<Integer> rightSideViewV3(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                        res.add(node.val);
                    }
                }
            }
            return res;
        }



    private void resetHeight(TreeNode node) {
        if (node != null) {
            resetHeight(node.left);
            node.height = 0;
            resetHeight(node.right);
        }
    }

    private void preOrder(TreeNode node, Map<Integer, Deque<Integer>> map) {
        if (node != null) {
            preOrder(node.left, map);
            Deque<Integer> queue = map.getOrDefault(node.height, new LinkedList<>());
            queue.add(node.val);
            map.put(node.height, queue);
            preOrder(node.right, map);
        }
    }
}
