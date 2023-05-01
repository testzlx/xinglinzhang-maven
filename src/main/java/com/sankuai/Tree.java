package com.sankuai;


import com.sankuai.datastruct.Queue;

import java.util.*;


/**
 * http://www.tuicool.com/articles/YjyuQ3J
 */
public class Tree {
    private Node root;
    private TreeNode treeNode;
    private Node threadedPre;// 线索二叉树按照某种顺序的前一节点
    private char[] queue = new char[100]; // 祖宗和孩子之间的路径
    private Node parentNode; // 求两个孩子的最近父母
    private Node reverseNode = null;

    Map<Character, TreeNode> treeNodeHashmap = new HashMap<Character, TreeNode>();
    private final char[] chs = {'a', 'b', 'x', 'd', 'x', 'x', 'c', 'e', 'x', 'x', 'f', 'g', 'x', 'x', 'x'};
    // 非递归树的节点
    /**
     * 非递归树的节点
     */
    private final char[] chss = {'1', '2', '3', '4', '5', '6', '7', '8'};
    // private final char[] chs = {'a','x','x'};
    private int index = 0;

    public Tree() {
        root = null;
    }

    /**
     * 树的叶子节点数
     */
    private int count = 0;
    /**
     * 树的高度
     */
    private int height = 0;

    class Node {
        char value;
        int leftTag, rightTag; // 建立线索二叉树的标志
        Node left;
        Node right;
        int height;  //应用于AVL树

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    /**
     *  判断树是否有效二叉搜索树
     *
     * @param root
     * @return
     */
    private boolean isValidBST(Node root) {
        int min = Integer.MIN_VALUE,max = Integer.MAX_VALUE;
        return helper(root,min,max);
    }

    // 这种写法错误，只是比较了当前节点及其自节点的大小关系，没有比较整体
    private boolean error_isValidBST(Node root) {
        if (root == null){
            return true;
        }
        boolean leftFlag = true,rightFlag = true;
        if (root.left == null ||root.left.value < root.value) {
            leftFlag = error_isValidBST(root.left);
        }else {
            return false;
        }
        if (root.right == null ||root.right.value > root.value) {
            rightFlag = error_isValidBST(root.right);
        }else {
            return false;
        }
        return leftFlag && rightFlag;
    }


    int preValue = Integer.MIN_VALUE;
    private boolean isValidBSTV2(Node root) {
        if (root != null) {
            boolean l = isValidBSTV2(root.left);
            if (root.value < preValue) {
                return false;
            }
            preValue = root.value;
            boolean r = isValidBSTV2(root.right);
            return l && r;
        }
        return true;
    }



    private boolean helper(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.value < min || root.value > max) {
            return false;
        }
        return helper(root.left,min,root.value) && helper(root.right,root.value,max);
    }


    //---------------------------------------------------------------------------------------

    //二叉平衡树
    //-------------------------------------------------------------------
    private int height(Node node) {
        if (node ==null) {
            return 0;
        }
        return node.height;
    }

    private Node llrate(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        node.height = Math.max(height(node.left),height(node.right)) +1;
        tmp.height = Math.max(height(tmp.left),height(tmp.right)) +1;
        return tmp;
    }

    private Node rrrate(Node node) {
        Node tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        node.height = Math.max(height(node.left),height(node.right)) +1;
        tmp.height = Math.max(height(tmp.left),height(tmp.right)) +1;
        return tmp;
    }

    private Node lrrate(Node node) {
        node.left = rrrate(node.left);
        return llrate(node);
    }

    private Node rlrate(Node node) {
        node.right = llrate(node.right);
        return rrrate(node);
    }

    private void insert(char val) {
        root = insert(root,val);
    }

    private Node insert(Node node,char val) {
        if(node == null) {
            return  new Node(val);
        }
        if (val > node.value) {
            node.right = insert(node.right,val);
            if(height(node.right) - height(node.left) ==2) {
                if(val > node.right.value) {
                    node = rrrate(node);
                } else {
                    node = rlrate(node);
                }
            }
        } else if(val < node.value) {
            node.left = insert(node.left,val);
            if(height(node.left) - height(node.right) ==2) {
                if(val < node.left.value) {
                    node = llrate(node);
                } else {
                    node = lrrate(node);
                }
            }
        } else {
            System.out.printf(val + " existed!");
        }
        node.height = Math.max(height(node.left),height(node.right)) +1;
        return node;
    }

    //删除很复杂，没看懂
    private void remove(char ch) {
        Node z;
        if((z = search(root,ch)) != null) {
             root = remove(root,z);
        }
    }

    private Node search(Node node,char ch) {
        if(node == null) {
            return null;
        }
        while(node != null) {
            if (ch == node.value) {
                return node;
            } else if (node.value > ch) {
                node = node.left;
            }else {
                node = node.right;
            }
        }
        return null;
    }

    private Node remove(Node node, Node z) {
        if (node == null || z == null) {
            return null;
        }
        if (z.value > node.value) {
            node.right = remove(node.right,z);
            if (height(node.left) - height(node.right) ==2) {
                Node leftNode = node.left;
                if (height(leftNode.left) > height(leftNode.right)) {
                    node = llrate(node);
                } else {
                    node = lrrate(node);
                }
            }
        } else if (z.value < node.value) {
            node.left = remove(node.left,z);
            if (height(node.right) -height(node.left) ==2) {
                Node rightNode = node.right;
                if (height(rightNode.right) > height(rightNode.left)) {
                    node = rrrate(node);
                }else  {
                    node = rlrate(node);
                }
            }
        } else {
            if (node.left != null && node.right != null) {
               if (height(node.left) > height(node.right)) {
                   Node max = maxNode(node.left);
                   node.value = max.value;
                   node.left = remove(node.left,max);
               } else {
                   Node min = minNode(node.right);
                   node.value = min.value;
                   node.right = remove(node.right,min);
               }
            } else {
                node = node.left != null ? node.left : node.right;
            }

        }
        if(node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
        return node;
    }

    private Node maxNode(Node node) {
        Node pre = node;
        while (node != null) {
            pre = node;
            node = node.right;
        }
        return pre;
    }

    private Node minNode(Node node) {
        Node pre = node;
        while (node != null) {
            pre = node;
            node = node.left;
        }
        return pre;
    }

    //-------------------------------------------------------------------------

    /*
     * class Linknode{ char value; Linknode next; public Linknode(char ch){
     * value = ch; next = null; } }
     */
    Node createTree() {
        Node root = null;
        char value = chs[index++];
        if (value != 'x') {
            root = new Node(value);
            root.left = createTree();
            root.right = createTree();
        }
        return root;
    }

    //递归创建树，但是没返回结果
    void createTreeWithOutRet(Node root) {
        //思考了下还是不行，操作操作指针，java是引用传递；
    }

    /**
     * 非递归创建二叉树
     */
    void createTree2() {
        Queue<Node> queue = new Queue<Node>();
        for (int i = 0; i < chss.length; i++) {
            Node tmp = new Node(chss[i]);
            queue.linkPush(tmp);
            if (root == null) {
                root = tmp;
            } else {
                if (i % 2 == 1) {
                    Node parent = queue.linkTop();
                    parent.left = tmp;
                } else {
                    Node parent = queue.linkPull();
                    parent.right = tmp;
                }
            }
        }
    }

    //leetcode  https://leetcode.com/problems/maximum-binary-tree/
    public Node constructMaximumBinaryTree(char[] nums) {
        return construct(null, nums, 0, nums.length - 1);

    }

    public Node construct(Node root, char[] nums, int l, int r) {
        if (l <= r) {
            int max_i = max(nums, l, r);
            if (root == null) {
                root = new Node(nums[max_i]);
            }
            root.left = construct(root.left, nums, l, max_i - 1);
            root.right = construct(root.right, nums, max_i + 1, r);
        }
        return root;
    }


    public int max(char[] nums, int l, int r) {
        int max_i = l;
        for (int i = l; i <= r; i++) {
            if (nums[max_i] < nums[i])
                max_i = i;
        }
        return max_i;
    }


    /*********************************
     *
     * 递归
     */
    /**
     * 先序遍历
     *
     * @param root
     */
    void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.print(root.value);
            preOrder(root.right);
        }
    }

    /**
     *  中序遍历
     * @param
     */
    void midOrder(Node root) {
        if (root != null) {
            System.out.print(root.value);
            midOrder(root.left);
            midOrder(root.right);
        }
    }

    int nodeSum(Node root) {
        if (root != null) {
            return root.value + nodeSum(root.left) + nodeSum(root.right);
        }
        return 0;
    }

    /**
     *  后序遍历
     * @param
     */
    void afterOrder(Node root) {
        if (root != null) {
            afterOrder(root.left);
            afterOrder(root.right);
            System.out.print(root.value);
        }
    }

    /*****************************************************************************/
    /**  左右中
     * 先序 非递归遍历
     */

    void midOrder1(Node root) {
        int index = -1;
        Node[] arr = new Node[10];
        Node node = root;
        while (index != -1 || node != null) {
            while (node != null) {
                arr[++index] = node;
                node = node.left;
            }
            node = arr[index--];
            System.out.print(node.value);
            node = node.right;
        }
    }

    /**
     *  中序遍历 中左右
     *
     * @param
     */
    void preOrder1(Node root) {
        int index = -1;
        Node[] arr = new Node[10];
        Node node = root;
        while (index != -1 || node != null) {
            while (node != null) {
                System.out.print(node.value);
                arr[++index] = node;
                node = node.left;
            }
            node = arr[index--];
            node = node.right;
        }
    }

    /**
     *  后序遍历 左右中
     *
     * @param
     */
    void afterOrder1(Node root) {
        // 标示，
        int[] tags = new int[100];
        int index = -1;
        Node[] arr = new Node[10];
        Node node = root;
        while (node != null) {
            arr[++index] = node;
            node = node.left;
        }
        while (index != -1) {
            node = arr[index];
            if (node.right == null || tags[index] != 0) {
                //tags[index] = 0; 这部我理解是多余的，时间20190331。
                System.out.print(node.value);
                if (index > 0) {
                    node = arr[--index];
                } else {
                    return;
                }
            } else {
                tags[index] = 1;
                node = node.right;
                while (node != null) {
                    arr[++index] = node;
                    node = node.left;
                }
            }
        }
    }

    /**
     * 后序遍历另一种非递归，保留前置节点，左右中
     */
    void afterOrder2(Node root) {

        int index = -1;
        Node[] arr = new Node[10];
        Node node = root;
        Node pre = null;
        if (root == null) {
            return;
        }
        arr[++index] = node;
        while (index != -1) {
            node = arr[index];
            if ((node.right == null && node.left == null) || (pre != null && (pre == node.left || pre == node.right))) {
                System.out.print(node.value);
                pre = node;
                if (index > 0) {
                    node = arr[--index];
                } else {
                    return;
                }
            } else {
                if (node.right != null) {
                    arr[++index] = node.right;
                }
                if (node.left != null) {
                    arr[++index] = node.left;
                }
            }
        }

    }

    /**
     * 从树的前序和中序逆向构造出树
     *
     * @param args
     */
    private Node BPI(char[] pre, char[] mid, int i, int j, int k, int l) {
        Node root = new Node(pre[i]);
        int m = k;
        while (pre[i] != mid[m]) {
            m++;
        }
        if (m != k) {
            root.left = BPI(pre, mid, i + 1, i + m - k, k, m - 1);
        }
        if (m != l) {
            root.right = BPI(pre, mid, i + m - k + 1, j, m + 1, l);
        }
        return root;
    }

    /**
     * 树的叶子节点数
     *
     * @param args
     */
    private void leafCount(Node root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                count++;
            }
            leafCount(root.left);
            leafCount(root.right);
        }
    }

    /**
     * 二叉树的高度
     *
     * @param args
     */
    private void treeHeight(Node node, int tmpHeight) {
        if (node != null) {
            tmpHeight++;
            if (tmpHeight > height) {
                height = tmpHeight;
            }
            treeHeight(node.left, tmpHeight);
            treeHeight(node.right, tmpHeight);
        }
    }

    //leetcode_cn_104  树最大深度
    public  int  maxDepth(Node node) {
        if (node != null) {
            return 0;
        }
        return 1+Math.max(maxDepth(node.left),maxDepth(node.right));

    }
    //leetcode_cn_111 树最小深度
    public  int  minDepth(Node node) {
        if (node == null) {
            return 0;
        }
        // null节点不参与比较
        if (node.left == null && node.right != null) {
            return 1 + minDepth(node.right);
        }
        // null节点不参与比较
        if (node.right == null && node.left != null) {
            return 1 + minDepth(node.left);
        }
        return 1+Math.min(minDepth(node.left),minDepth(node.right));

    }

    /**
     * 一般树的高度
     *
     * @param args
     */
    private void treeHeight1(Node node, int tmpHeight) {
        if (node != null) {
            tmpHeight++;
            if (tmpHeight > height) {
                height = tmpHeight;
            }
            treeHeight1(node.left, tmpHeight);
            treeHeight1(node.right, tmpHeight - 1);
        }
    }

    /**
     * 创建一般树，孩子兄弟表示法，Node left表示最左孩子，right表示右邻兄弟
     */
    private void createTree3() {
        Queue<Node> queue = new Queue<Node>();
        System.out.println("请输入根节点");
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        Node root = new Node(tmp.toCharArray()[0]);
        this.root = root;
        queue.linkPush(root);
        while (!queue.isLinkEmpty()) {
            // 结束用#,子孩子为空用?
            Node value = queue.linkPull();
            System.out.println("请输入节点 " + value.value + " 的子孩子 ");
            String children = sc.next();
            if (children.equals("#")) {
                sc.close();
                return;
            } else if (children.equals("?")) {
                continue;
            }
            char[] chars = children.toCharArray();
            Node tmpNode = null;
            for (int i = 0; i < chars.length; i++) {
                Node child = new Node(chars[i]);
                queue.linkPush(child);
                if (value.left == null) {
                    value.left = tmpNode = child;
                } else {
                    tmpNode.right = child;
                    tmpNode = tmpNode.right;
                }
            }

        }
        sc.close();
    }

    /*
     * 输入输出
     */
    private void testInputOutput() {
        /*
         * String string = "abc"; char ch = '中'; char[] chars =
         * string.toCharArray(); Charset cs = Charset.forName ("UTF-8");
         * CharBuffer cb = CharBuffer.allocate (chars.length); cb.put (chars);
         * cb.flip (); ByteBuffer bb = cs.encode (cb); byte bytes[] =bb.array();
         * // byte b = chars[0];
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("输入第一个boolean值(true/false):");
        if (sc.nextBoolean()) {
            System.out.println("输入布尔：真的");
        } else {
            System.out.println("输入布尔：假的");
        }

        System.out.println("输入第一个数字:");
        System.out.println("输入数字：" + sc.nextInt());
        System.out.println("输入字节：" + sc.nextByte());
        System.out.println("输入字节：" + sc.nextByte());
        sc.close();

    }

    // ---------------------普通树的建立

    class TreeNode {
        char element;
        int childrenCount;
        TreeNode[] children;

        public TreeNode(char ele) {
            element = ele;
            treeNodeHashmap.put(ele, this);
        }

        public void pushTreeNode(String string) throws Exception {
            char parentChar = string.toCharArray()[0];
            char[] childrenChar = string.substring(1).toCharArray();
            TreeNode parent = null;
            if (treeNodeHashmap.containsKey(parentChar)) {
                parent = treeNodeHashmap.get(parentChar);
            } else {
                throw new Exception("树建立失败");
            }
            parent.children = new TreeNode[childrenChar.length];
            int index = 0;
            for (char child : childrenChar) {
                TreeNode childTreeNode = new TreeNode(child);
                treeNodeHashmap.put(child, childTreeNode);
                parent.children[index++] = childTreeNode;
            }
        }
    }

    public TreeNode createTree4() throws Exception {
        TreeNode root = null;
        System.out.println("请输入节点");
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        while (tmp.indexOf("#") == -1) {
            if (root == null) {
                root = new TreeNode(tmp.toCharArray()[0]);
            } else {
                root.pushTreeNode(tmp);
            }
            System.out.println("请输入节点");
            tmp = sc.nextLine();
        }
        sc.close();
        return root;
    }

    /**
     * 树转换为二叉树
     */
    public Node convertBitree(TreeNode root) {
        Node node = null;
        node = new Node(root.element);
        if (root.children != null && root.children.length > 0) {
            node.left = convertBitree(root.children[0]);
            Node tmp = node.left;
            for (int j = 1; j < root.children.length; j++) {
                tmp.right = convertBitree(root.children[j]);
                tmp = tmp.right;
            }
        }
        return node;
    }

    /**
     * 二叉树转化为树
     *
     * @param root
     * @throws Exception
     */
    public void convertTree(Node root) throws Exception {
        if (root != null) {
            // System.out.println("开始遍历节点 "+root.value);
            if (treeNode == null) {
                treeNode = new TreeNode(root.value);
            }
            Node tmp = root.left;
            if (tmp != null) {
                StringBuffer children = new StringBuffer().append(root.value).append(tmp.value);
                while (tmp.right != null) {
                    children.append(tmp.right.value);
                    tmp = tmp.right;
                }
                treeNode.pushTreeNode(children.toString());
            }

            convertTree(root.left);
            convertTree(root.right);
        }
    }

    /**
     * 求二叉树的两节点（祖宗和孩子）的路径
     *
     * @param parent
     * @param child
     * @param queue
     * @return
     */
    private boolean getNodePath(Node parent, Node child, char[] queue) {
        boolean found = false;
        queue[index++] = parent.value;
        if (parent.value == child.value)
            return true;
        if (!found && parent.left != null) {
            found = getNodePath(parent.left, child, queue);
        }
        if (!found && parent.right != null) {
            found = getNodePath(parent.right, child, queue);
        }
        if (!found) {
            --index;
        }
        return found;
    }

    /**
     * 二叉树逆向
     *
     * @param root
     */
    private void reverseRoot(Node root) {
        if (root != null) {
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            reverseRoot(root.left);
            reverseRoot(root.right);
        }
    }

    //二叉树的层次打印
    private List<List<Character>> levelPrint(Node root){
        if(root== null){
            return new ArrayList<>();
        }
        List<List<Character>> ret = new ArrayList<List<Character>>();
        java.util.Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Character> eles = new LinkedList<Character>();
            List<Node> tmp = new LinkedList<Node>();
            while (!queue.isEmpty()){
                Node node = queue.poll();
                eles.add(node.value);
                tmp.add(node);
            }
            ret.add(eles);
            for (Node node:tmp){
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return ret;

    }

    private List<Character> largestValues = new ArrayList<Character>();
    //二叉树每层最大值
    private List<Character> largestValues(Node root){
        dfs(root,0);
        return largestValues;
    }

    private void dfs(Node root,int depth){
            if (root != null) {
                if (largestValues.size() <= depth) {
                    largestValues.add(Character.MIN_VALUE);
                }
                Character tmp = largestValues.get(depth);
                Character max_ch = tmp > root.value ? tmp : root.value;
                largestValues.set(depth,max_ch);
                dfs(root.left,depth+1);
                dfs(root.right,depth+1);
            }
    }

    private Map<Node,Integer> depthMap= new HashMap<>();
    //leetcode  1123
    private Node lcaDeepestLeaves(Node root){
        depth(root);
        return dfsLeaves(root);
    }

    private Node dfsLeaves(Node root){
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == right){
            return root;
        }else  if(left > right){
            return dfsLeaves(root.left);
        }else {
            return dfsLeaves(root.right);
        }

    }

    private int depth(Node root){
        if (root == null){
            return 0;
        }
        if(depthMap.containsKey(root)){
            return depthMap.get(root);
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        int parentDepth = Math.max(leftDepth,rightDepth) +1;
        depthMap.put(root,parentDepth);
        return parentDepth;
    }




    //判断两个树是否相等
    public boolean isTheSame(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.value == node2.value) {
            boolean leftEqual = isTheSame(node1.left, node2.left);
            boolean rightEqual = isTheSame(node1.right, node2.right);
            return leftEqual && rightEqual;
        } else {
            return false;
        }
    }


    //leetcode_101
    public boolean isSymmetric(Node root) {
        if (root == null){
            return true;
        }
        return cmp(root.left,root.right);
    }

    private boolean cmp(Node leftNode, Node rightNode) {
        if (leftNode == null && rightNode == null){
            return true;
        }
        if (leftNode == null || rightNode == null || leftNode.value != rightNode.value){
            return false;
        }
        return cmp(leftNode.left,rightNode.right) && cmp(leftNode.right,rightNode.left);
    }


    /**
     * 二叉树逆向
     *
     * @param root
     */
    private void reverseRoot2(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new Queue<Node>();
        queue.linkPush(root);
        while (!queue.isLinkEmpty()) {
            Node tmp = queue.linkPull();
            if (tmp.left != null) {
                queue.linkPush(tmp.left);
            }
            if (tmp.right != null) {
                queue.linkPush(tmp.right);
            }
            Node nodeTmp = tmp.left;
            tmp.left = tmp.right;
            tmp.right = nodeTmp;
        }
    }

    /**
     * 寻找二叉树任意两节点的最近祖先
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public int findParent(Node root, Node node1, Node node2) {
        if (parentNode != null)
            return 0;
        int sum = 0;
        if (root == null)
            return 0;
        if (root.value == node1.value)
            sum += 1;
        if (root.value == node2.value)
            sum += 1;
        sum += findParent(root.left, node1, node2);
        sum += findParent(root.right, node1, node2);
        if (sum == 2 && parentNode == null) {
            parentNode = root;
            return 0;
        }
        return sum;
    }

    public Node findParentV2(Node root,Node node1,Node node2) {
        if (root == null || root.value == node1.value || root.value ==node2.value) {
            return root;
        }
        Node left = findParentV2(root.left,node1,node2);
        Node right = findParentV2(root.right,node1,node2);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right:left;
    }

    char[] longestPath;
    char[] tmpPath = new char[30];
    int longestDepth = 0;

    //寻找二叉树的最长路径(大致验证了下，应该是ok的)
    private void longPath(Node node) {
        if (node != null) {
            tmpPath[index++] = node.value;
            if (node.left == null && node.right == null) {
                if (index > longestDepth) {
                    longestDepth = index;
                    longestPath = tmpPath;
                }
            }
            longPath(node.left);
            longPath(node.right);
            index--;
        }
    }

    /**
     * 把普通二叉树转化为线索二叉树
     *
     * @param root
     */
    public void intoThreaded(Node root) {
        if (root != null) {
            intoThreaded(root.left);
            if (root.left == null) {
                root.leftTag = 1;
            }
            if (root.right == null) {
                root.rightTag = 1;
            }
            if (threadedPre != null) {
                if (threadedPre.rightTag == 1) {
                    threadedPre.right = root;
                }
                if (root.leftTag == 1) {
                    root.left = threadedPre;
                }
            }
            threadedPre = root;
            intoThreaded(root.right);
        }
    }

    /**
     * 二叉排序树的插入
     *
     */
    private void sortInsert(char value) {
        Node tmp = new Node(value), pre, curent;
        if (root == null) {
            root = tmp;
            return;
        }
        curent = pre = root;
        while (curent != null) {
            pre = curent;
            if (curent.value > value) {
                curent = curent.left;
            } else if (curent.value < value) {
                curent = curent.right;
            } else {
                return;
            }
        }
        if (pre.value > value) {
            pre.left = tmp;
        } else {
            pre.right = tmp;
        }
    }

    /**
     * 创建二叉排序树
     */
    private void createBST() {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        while (tmp.indexOf("#") == -1) {
            sortInsert(tmp.toCharArray()[0]);
            tmp = sc.nextLine();
        }
        sc.close();
    }

    /**
     * 删除二叉排序树其中一节点
     *
     */
    private void sortDelete(char value) {
        Node parent = root, curent = root;
        boolean find = false;
        while (curent != null) {
            if (curent.value == value) {
                find = true;
                break;
            }
            parent = curent;
            if (curent.value > value) {
                curent = curent.left;
            } else {
                curent = curent.right;
            }

        }
        if (!find) {
            System.out.println("the value: " + value + " does not exist");
            return;
        }
        if (curent.left == null) {
            if (curent == root) {
                root = curent.right;
                return;
            }
            if (parent.left == curent) {
                parent.left = curent.right;
            } else {
                parent.right = curent.right;
            }
        } else {
            Node f = curent, t = curent.left;
            while (t.right != null) {
                f = t;
                t = t.right;
            }
            if (f == curent) {
                f.left = t.left;
            } else {
                f.right = t.left;
            }
            curent.value = t.value;
        }
    }

    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        /*
        //二叉平衡树相关操作
        tree.insert('3');
        tree.insert('2');
        tree.insert('1');
        tree.insert('4');
        tree.insert('5');
        tree.insert('7');
        tree.insert('9');
        tree.insert('0');
        tree.insert('8');
        System.out.println(tree.root.value);
        tree.remove('2');
        tree.remove('0');
        tree.remove('3');
        System.out.println(tree.root.value);
        */

        /*
        char[] chars = {'d', 'c', 'b', 'g', 'a', 'f'};
        Node node = tree.constructMaximumBinaryTree(chars);
        System.out.println("node:" + node);
         */

        tree.root = tree.createTree();
        List<List<Character>> ret = tree.levelPrint(tree.root);
        /*
        tree.longPath(tree.root);
        System.out.println(tree.longestPath);
        */
        // System.out.println(tree.root.left.right.value);

        /**
         * 递归
         *
         * tree.preOrder(tree.root); System.out.println();
         * tree.midOrder(tree.root); System.out.println();
         * tree.afterOrder(tree.root);
         **/

        /**
         * 非递归
         *
         * tree.preOrder1(tree.root); System.out.println();
         * tree.midOrder1(tree.root); System.out.println();
         * tree.afterOrder1(tree.root); System.out.println();
         * tree.afterOrder2(tree.root); System.out.println();
         * /*************************************
         *
         * reverse a bitree
         */
        /*
         * char[] pre ={'a','b','d','c','e','f','g'}; char[]
         * mid={'b','d','a','e','c','g','f'}; tree.root = tree.BPI(pre, mid, 0,
         * 6, 0, 6); tree.preOrder1(tree.root); tree.leafCount(tree.root);
         * System.out.println(); System.out.println(tree.count);
         * tree.treeHeight(tree.root, 0); System.out.println(tree.height);
         */
        // tree.test(tree);
        // System.out.println(tree.root.value);
        // tree.root = tree.createTree();
        // System.out.println(tree.root.left.right);
        /*
         * 创建树（孩子兄弟表示法）
         */
        // tree.createTree3();
        // tree.treeHeight1(tree.root,0);
        // System.out.println(tree.height);
        /**************************/
        /*
         * 创建树，孩子表示法
         */

        // tree.treeNode = tree.createTree4();
        // tree.root = tree.convertBitree(tree.treeNode);
        // Node node1 = tree.new Node('e');
        // Node node2 = tree.new Node('h');
        // tree.findParent(tree.root, node1, node2);
        // System.out.println(tree.parentNode.value);
        // tree.getNodePath(tree.parentNode, node2, tree.queue);
        // System.out.println("-------------------------------");
        // tree.print(tree.queue);
        // tree.preOrder(tree.root);
        // System.out.println();
        // tree.intoThreaded(tree.root);
        // System.out.println(tree.root.value);
        /*
         * tree.preOrder(tree.root); tree.treeNode = null;
         * tree.treeNodeHashmap.clear(); tree.convertTree(tree.root);
         */
        // System.out.println(tree.treeNode.element);
        // tree.testInputOutput();
        // ------------------------
        /*
         * tree.createTree2(); tree.preOrder(tree.root);
         * System.out.println("-----------"); tree.reverseRoot2(tree.root);
         * tree.preOrder(tree.root);
         */
        /*
        tree.createBST();
        tree.preOrder(tree.root);
        System.out.println("\n请输入要删除的节点值");
        tree.sortDelete('d');
        tree.preOrder(tree.root);
        */

    }

    private void print(char[] queue2) {
        int tmp = 0;
        while (tmp <= index) {
            System.out.println(queue2[tmp++]);
        }

    }

}
