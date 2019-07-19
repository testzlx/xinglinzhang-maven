package com.visualbusiness.my_test;
/**
 *
 * @author zhanglinxing
 *二叉平衡树
 */
public class AvlTree {
    private Node root;
    class Node{
        int height;
        Node left,right;
        char value;
        private Node(char value) {
            this.value = value;
            this.height = 0;
        }
    }
    private int heigt(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }
    private int maxHeight(Node node1,Node node2){
        return heigt(node1) > heigt(node2) ? heigt(node1) : heigt(node2);
    }
    private Node llRotate(Node root){
        Node leftNode = root.left;
        root.left = leftNode.right;
        leftNode.right = root;
        root.height = maxHeight(root.left, root.right) +1;
        leftNode.height = maxHeight(leftNode.left, leftNode.right) +1;
        return leftNode;
    }
    private Node rrRotate(Node root){
        Node rightNode = root.right;
        root.right = rightNode.left;
        rightNode.left = root;
        root.height = maxHeight(root.left, root.right) +1;
        rightNode.height = maxHeight(rightNode.left, rightNode.right) +1;
        return rightNode;
    }
    private Node lrRotate(Node root){
        root.left = rrRotate(root.left);
        return llRotate(root);
    }
    private Node rlRotate(Node root){
        root.right = llRotate(root.right);
        return rrRotate(root);
    }

    private Node getMaxNode(Node root){
        while(root.right != null){
            root  = root.right;
        }
        return root;
    }
    private Node getMinNode(Node root){
        while(root.left != null){
            root  = root.left;
        }
        return root;

    }
    private Node insert(Node root,char value){
        if(root == null){
            root  = new Node(value);
            return root;
        }
        if(value < root.value){
            root.left = insert(root.left, value);
            if(heigt(root.left) - heigt(root.right) == 2){
                if(value < root.left.value){
                    root = llRotate(root);
                }else{
                    root = lrRotate(root);
                }
            }
        }else if (value > root.value) {
            root.right = insert(root.right, value);
            if(heigt(root.right) - heigt(root.left) == 2){
                if(value > root.right.value){
                    root = rrRotate(root);
                }else{
                    root = rlRotate(root);
                }
            }
        }else{
            System.out.println("节点值 "+value+" 已经存在");
            return root;
        }
        root.height = maxHeight(root.left, root.right) +1;
        return root;
    }
    private Node remove(Node root,Node delete){
        if(root == null || delete == null){
            return null;
        }
        if(delete.value < root.value){
            root.left = remove(root.left, delete);
            if(heigt(root.right) - heigt(root.left) == 2 ){
                Node tmp = root.right;
                if(heigt(tmp.left) < heigt(tmp.right)){
                    root = rrRotate(root);
                }else{
                    root = rlRotate(root);
                }
            }
        }else if (delete.value > root.value) {
            root.right = remove(root.right, delete);
            if(heigt(root.left) - heigt(root.right) == 2 ){
                Node tmp = root.left;
                if(heigt(tmp.left) > heigt(tmp.right)){
                    root = llRotate(root);
                }else{
                    root = lrRotate(root);
                }
            }
        }else{
            if(root.left != null && root.right != null){
                if(heigt(root.left) > heigt(root.right)){
                    Node maxNode = getMaxNode(root.left);
                    root.value = maxNode.value;
                    root.left = remove(root.left, maxNode);
                }else{
                    Node minNode = getMinNode(root.right);
                    root.value = minNode.value;
                    root.right = remove(root.right, minNode);
                }
            }else{
                root = root.left == null? root.right:root.left;
            }
        }
        if(root != null)
            root.height = maxHeight(root.left, root.right)+1;
        return root;
    }
    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        avlTree.root = avlTree.insert(avlTree.root, 'j');
        avlTree.root = avlTree.insert(avlTree.root, 'y');
        avlTree.root = avlTree.insert(avlTree.root, 'p');
        avlTree.root = avlTree.insert(avlTree.root, 'r');
        avlTree.root = avlTree.insert(avlTree.root, 'a');
        avlTree.root = avlTree.insert(avlTree.root, 'c');
        avlTree.root = avlTree.insert(avlTree.root, 'g');
        avlTree.root = avlTree.insert(avlTree.root, 'i');
        avlTree.root = avlTree.insert(avlTree.root, 'd');
        avlTree.root = avlTree.insert(avlTree.root, 'e');
        avlTree.root = avlTree.insert(avlTree.root, 't');
        avlTree.root = avlTree.insert(avlTree.root, 'r');
        avlTree.root = avlTree.remove(avlTree.root, avlTree.new Node('g'));
        avlTree.root = avlTree.remove(avlTree.root, avlTree.new Node('p'));
        System.out.println("OK!!!");
    }

}
