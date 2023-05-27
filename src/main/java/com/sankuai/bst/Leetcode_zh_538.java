package com.sankuai.bst;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  把二叉搜索树转换为累加树
 */
public class Leetcode_zh_538 {
    int sum = 0;
    Node treeRoot = null;

    class Node {
        int val;
        Node left;
        Node right;
        Node(int value) {
            this.val = value;
        }
    }
    public static void main(String[] args) {
        Leetcode_zh_538  main= new Leetcode_zh_538();
        int[] arr = {4,1,0,-1,-1,2,-1,3,-1,-1,6,5,-1,-1,7,-1,8,-1,-1};
        main.treeRoot = main.createTree(arr);
        Node resultNode = main.convertBST(main.treeRoot);
        System.out.println(resultNode.val);
        int arr1[] = {4,1,6,0,2,5,7,3,8};
        main.treeRoot = null;
        main.createBSTree(arr1);
        Node node = main.balanceBST(main.treeRoot);
        System.out.println(node.val);
        main.test_1373();
    }

    private void test_1373() {

        int[] arr = {4,3,1,-1,-1,2,-1,-1,-1};
        index = 0;
        treeRoot = createTree(arr);
        int val = maxSumBST(treeRoot);
        System.out.println(val);
    }

    public Node convertBST(Node root) {
        if (root != null) {
            convertBST(root.right);
            sum+= root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
    int index = 0;
    private Node createTree(int[] arr) {
        Node node = null;
        int tmp = arr[index++];
        if (tmp != -1) {
            node = new Node(tmp);
            node.left = createTree(arr);
            node.right = createTree(arr);
        }
        return node;
    }

    private void createBSTree(int arr[]) {
        for (int value : arr) {
            insert(value);
        }
    }

    private void insert(int value) {
        Node node = new Node(value);
        if (treeRoot== null) {
            treeRoot = node;
            return;
        }
        Node parent = null,curr = treeRoot;
        while(curr!= null) {
            parent = curr;
            if(value > curr.val) {
                curr = curr.right;
            }else if(value < curr.val) {
                curr = curr.left;
            }else {
                System.out.println(value +" exists!");
                return;
            }
        }
        if (value > parent.val) {
            parent.right = node;
        } else {
            parent.left = node;
        }
    }

    private void delete(int value) {
        Node parent = null,curr = treeRoot;
        while(curr!= null) {
            if (curr.val == value) {
                break;
            }
            parent = curr;
            if(value > curr.val) {
                curr = curr.right;
            }else {
                curr = curr.left;
            }
        }
        if (curr == null) {
            System.out.println(value +" not found!");
            return;
        }else {
            if (curr.left == null) {
                if (curr == treeRoot) {
                    treeRoot = curr.right;
                    return;
                }
                if (parent.left == curr) {
                    parent.left  = curr.right;
                }else {
                    parent.right = curr.right;
                }
            } else {
                //
                Node tmp = curr.left, f = curr;
                while(tmp.right != null) {
                    f = tmp;
                    tmp = tmp.right;
                }
                if (f == curr) {
                    f.left = tmp.left;
                } else {
                    f.right = tmp.left;
                }
                curr.val = tmp.val;
            }
        }
    }

    /**
     *  排序二叉树变成平衡二叉树 leetcode-1382
     *
     * @param root
     * @return
     */
    public Node balanceBST(Node root) {
        List<Integer> list = new ArrayList<>();
        midOrder(treeRoot,list);
        treeRoot =  buildBalance(list,0,list.size()-1);
        return treeRoot;
    }

    private Node buildBalance(List<Integer> list, int left, int right) {
        int mid = (left + right) /2;
        Node node = new Node(list.get(mid));
        if (left <= mid -1) {
            node.left = buildBalance(list,0,mid-1);
        }
        if (right >= mid+1) {
            node.right = buildBalance(list,mid+1,right);
        }
        return node;
    }

    private void midOrder(Node node, List<Integer> list) {
        if (node != null) {
            midOrder(node.left,list);
            list.add(node.val);
            midOrder(node.right,list);
        }
    }

    int pre = Integer.MIN_VALUE;
    private boolean isBST(Node node) {
        if (node != null){
            boolean l = isBST(node.left);
            if (node.val < pre) {
                return false;
            }
            pre = node.val;
            boolean r = isBST(node.right);
            return l && r;
        }
        return true;
    }

    //leetcode_cn_98
    private boolean isBSTV2(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        return min < root.val && root.val < max && isBSTV2(root.left, min, root.val) && isBSTV2(root.right, root.val, max);
    }

    /**
     *  leetcode-1373. 二叉搜索子树的最大键值和
     *
     * @param root
     * @return
     */
    public int maxSumBST(Node root) {
        int[] res = {0};
        maxSumBST(root,res);
        return res[0];

    }

    private void maxSumBST(Node root, int[] res) {
        if (root!=null) {
            if (isBSTV2(root,Integer.MIN_VALUE,Integer.MAX_VALUE)) {
                sumNodeValue(res,root);
            }
            maxSumBST(root.left, res);
            maxSumBST(root.right, res);
        }
    }

    Map<Node,Integer> hmap = new HashMap<>();
    private int sumNodeValue(int[] res,Node node) {
        if (node == null) {
            hmap.put(node,0);
            return 0;
        }
        // 二叉搜索树节点和的最大值，一定在子节点中会出现
        if (hmap.containsKey(node)) {
            return hmap.get(node);
        }
        int sum = node.val + sumNodeValue(res, node.left) + sumNodeValue(res, node.right);
        hmap.put(node,sum);
        // 这里记录结果的最大值
        res[0] = Math.max(res[0], sum);
        return sum;
    }
}
