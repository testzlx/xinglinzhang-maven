package com.sankuai;

import java.util.Scanner;

public class Graph {
	private final static String DELEMETER = ",";
	private Node root;
	private Vexnode[] linkRoot;
	private int[] visits;//深度或广度遍历使用是否已遍历数组
	private int[] ga;//判断所选择的边是否在同一向量里
	/**
	 * kruskal所用数据结构,也用作prime算法
	 * @author zhanglinxing
	 *
	 */
	class KruskalNode{
		int from,end;//边的起点，终点
		int weight; //边的权重
		int sign; //0为待选择，1为已选择 2为构成闭环
		public KruskalNode(int from,int end,int weight){
			this.from = from;
			this.end = end;
			this.weight = weight;
			this.sign = 0;
		}
	}

	/*
	 *  邻接表
	 * 
	 */
	class Edgenode {
		int index;
		Edgenode next;

		public Edgenode(int index, Edgenode next) {
			this.index = index;
			this.next = next;
		}
	}

	class Vexnode {
		char value;
		Edgenode link;

		public Vexnode(char value) {
			this.value = value;
			this.link = null;
		}
	}

	/**
	 * 邻接表表示法
	 * 
	 */
	private void createGraph2(int vexCount, int arcCount) {
		linkRoot = new Vexnode[vexCount];
		visits = new int[vexCount];
		System.out.println("准备创建 " + vexCount + " 个点的图,边的条数为 " + arcCount);
		Scanner sc = new Scanner(System.in);
		int i;
		for (i = 0; i < vexCount; i++) {
			String tmp = sc.nextLine();
			char ch = tmp.toCharArray()[0];
			linkRoot[i] = new Vexnode(ch);
			// System.out.println("ch : "+ch);
		}
		for (i = 0; i < arcCount; i++) {
			String tmp = sc.nextLine();
			String[] ss = tmp.split(DELEMETER);
			Edgenode tmpEdgenode = new Edgenode(Integer.parseInt(ss[1]), linkRoot[Integer.parseInt(ss[0])].link);
			linkRoot[Integer.parseInt(ss[0])].link = tmpEdgenode;
			tmpEdgenode = new Edgenode(Integer.parseInt(ss[0]), linkRoot[Integer.parseInt(ss[1])].link);
			linkRoot[Integer.parseInt(ss[1])].link = tmpEdgenode;
		}
		sc.close();
	}

	/*
	 * 邻接矩阵
	 */
	class Node {
		char[] vextype;
		int[][] arcs;

		public Node(int count) {
			vextype = new char[count];
			arcs = new int[count][count];
		}
	}

	/**
	 * 邻接矩阵
	 * 
	 * @param vexCount
	 *            点的个数
	 * @param arcCount
	 *            边的个数
	 *@param arcCount
	 * 	 *        true 有向图，false：无向图
	 */
	private void createGraph(int vexCount, int arcCount,boolean flag) {
		root = new Node(vexCount);
		visits = new int[vexCount];
		System.out.println("准备创建 " + vexCount + " 个点的图,边的条数为 " + arcCount);
		Scanner sc = new Scanner(System.in);
		int i;
		System.out.println("开始输入点信息 " );
		for (i = 0; i < vexCount; i++) {
			String tmp = sc.nextLine();
			char ch = tmp.toCharArray()[0];
			root.vextype[i] = ch;
			// System.out.println("ch : "+ch);
		}
		System.out.println("开始输入边信息 " );
		for (i = 0; i < arcCount; i++) {
			String tmp = sc.nextLine();
			String[] ss = tmp.split(DELEMETER);
			root.arcs[Integer.parseInt(ss[0])][Integer.parseInt(ss[1])] = Integer.parseInt(ss[2]);
			// 有向图，无向图
			if(!flag) {
				root.arcs[Integer.parseInt(ss[1])][Integer.parseInt(ss[0])] = Integer.parseInt(ss[2]);
			}
		}
		sc.close();
	}

	/**
	 * 深度遍历，邻接矩阵
	 * 
	 * @param i
	 */
	private void DFSA(int i) {
		visits[i] = 1;
		System.out.print(root.vextype[i] + " ");
		for (int j = 0; j < root.vextype.length; j++) {
			if (root.arcs[i][j] != 0 && visits[j] == 0) {
				DFSA(j);
			}
		}
	}

	/**
	 * 广度遍历 邻接矩阵
	 */
	private void BFSA(int i) {
		Queue<Integer> queue = new Queue<Integer>();
		int j;
		queue.linkPush(i);
		visits[i] = 1;
		while (!queue.isLinkEmpty()) {
			int tmp = queue.linkPull();
			System.out.print(root.vextype[tmp]);
			for (j = 0; j < root.vextype.length; j++) {
				if (root.arcs[tmp][j] != 0 && visits[j] == 0) {
					queue.linkPush(j);
					visits[j] = 1;
				}
			}
		}
	}

	/**
	 * 深度遍历，邻接表
	 * 
	 * @param i
	 */
	private void DFSL(int i) {
		visits[i] = 1;
		System.out.print(linkRoot[i].value + " ");
		Edgenode edgenode = linkRoot[i].link;
		while (edgenode != null) {
			if (visits[edgenode.index] == 0) {
				DFSL(edgenode.index);
			}
			edgenode = edgenode.next;

		}
	}

	/**
	 * 广度遍历，邻接表
	 * 
	 * @param i
	 */
	private void BFSL(int i) {
		Queue<Integer> queue = new Queue<Integer>();
		queue.linkPush(i);
		visits[i] = 1;
		while (!queue.isLinkEmpty()) {
			int tmp = queue.linkPull();
			System.out.print(linkRoot[tmp].value + " ");
			Edgenode edgenode = linkRoot[tmp].link;
			while (edgenode != null) {
				if (visits[edgenode.index] == 0) {
					queue.linkPush(edgenode.index);
					visits[edgenode.index] = 1;
				}
				edgenode = edgenode.next;
			}
		}
	}
	
	/**
	 *图的节点个数 
	 */
	private int getVexCount(){
		return visits.length;
	}
	/**
	 * 把没连通的边的距离设定为无穷远,默认值为0
	 * 
	 */
	private void preHandle(){
		int vexCount = getVexCount();
		for(int i=0;i<vexCount;i++)
			for(int j=0;j<vexCount;j++)
				if(root.arcs[i][j]==0)
					root.arcs[i][j] = 11111;
	}
	/**
	 * 
	 * prime 最小生成树算法
	 */
	private void prime(int i){
		int vexCount = getVexCount(),j;
		KruskalNode swap;
		KruskalNode[] arrs = new KruskalNode[vexCount-1];
		//把没连通的边的距离设定为无穷远 11111；
		preHandle();
		for(j = 0 ;j<vexCount-1;j++){
			arrs[j] = new KruskalNode(i, j, -1);
			if(j >= i ){
				arrs[j].end = j+1;
				arrs[j].weight = root.arcs[i][j+1];
			}else{
				arrs[j].end = j;
				arrs[j].weight = root.arcs[i][j];
			}
			arrs[j].sign = 1;//此处为了兼容print
		}
		for(j = 0 ;j < vexCount-1;j++){
			int min = 10000,m = -1;
			for(int k = j ;k<vexCount-1;k++){
				if(arrs[k].weight < min){
					min = arrs[k].weight;
					m = k;
				}
			}
			swap = arrs[j];
			arrs[j] = arrs[m];
			arrs[m] = swap;
			int e = arrs[j].end;
			for(int n = j+1;n<vexCount -1;n++){
				if(arrs[n].weight > root.arcs[e][arrs[n].end]){
					arrs[n].weight = root.arcs[e][arrs[n].end];
					arrs[n].from = e;
				}
			}
		}		
		print(arrs);
	}
/**
 * 克鲁斯卡尔最小生成树算法
 * 此算法只与图的边数有关，适合求边稀疏性图
 * @param vexCount 图 点的个数
 * @param arcCount 图 边的个数
 */	
	private void kruskal(int vexCount,int arcCount){
		int i;
		ga = new int[vexCount];
		KruskalNode[] T = new KruskalNode[arcCount];
		for( i = 0 ;i< vexCount;i++){
			ga[i] = i;
		}
		System.out.println("准备创建图,边的条数为 " + arcCount);
		Scanner sc = new Scanner(System.in);
		for (i = 0; i < arcCount; i++) {
			String tmp = sc.nextLine();
			String[] ss = tmp.split(DELEMETER);
			T[i] = new KruskalNode(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
		}
		int k = 0;
		while(k < vexCount - 1){
			int min = 10000,fromIndex = -1,endIndex = -1,cur = -1;;
			for(int j=0;j<arcCount;j++){
				if(T[j].sign == 0 && T[j].weight < min){
					cur = j;
					fromIndex = T[j].from;
					endIndex = T[j].end;
					min = T[j].weight;
				}
			}
			System.out.println("fromIndex: "+fromIndex+" endIndex: "+endIndex+"  k为 "+k+" weight: "+min);
			if(ga[fromIndex] == ga[endIndex]){
				T[cur].sign = 2;
			}else{
				k++;
				T[cur].sign = 1;
				int tmp = ga[endIndex];
				for(int t = 0 ;t< vexCount;t++){
					if(ga[t] ==tmp){
						ga[t] = ga[fromIndex];					
						}
				}
			}
		}
		sc.close();
		print(T);
	}
	private void print(KruskalNode[] arrs){
		for(KruskalNode kruskalNode : arrs){
			if(kruskalNode.sign ==1){
				System.out.println
				(kruskalNode.from+"--"+kruskalNode.end+" : "+kruskalNode.weight);
			}
		}
	}
	/**
	 * 寻找某一节点到其它节点的最短路径，突然不想写了；坚持写下去20190405
	 * @param v
	 */
	private void dijkstra(int v){
		final float MAX = 10000f;
		float[][] dist = {
				{0f,6f,9f,8f,MAX,MAX},
				{18f,0f,7f,MAX,MAX,10f},
				{9f,MAX,0f,15f,MAX,MAX},
				{MAX,MAX,12f,0f,MAX,MAX},
				{MAX,MAX,4f,MAX,0f,MAX},
				{24f,5f,MAX,25f,MAX,0f}
				};
		int n = dist[0].length,k = 100; //n为节点个数
		float D[] =new float[n],min; //节点v到D数组下标节点的距离
		int p[] = new int[n];
		int s[] = new int[n]; //节点v到s数组下标节点是否找到最近路径标示
		for(int i =0;i<n;i++){
			D[i] = dist[v][i];
			if(D[i] == MAX){
				p[i] = 0;
			}else{
				p[i] = v;
			}
			s[i] = 0;
		}
		s[v] = 1; //v到v自己的路径可以确定为0;
		for(int i=0;i<n-1;i++){
			min = MAX +1;
			for(int j = 0;j<n;j++){
				if(s[j] ==0&& D[j] < min){
					min = D[j];
					k = j;
				}
			}
			s[k] =1;
			for(int j = 0;j<n;j++){
				if(s[j] == 0 && D[j] > D[k]+dist[k][j]){
					D[j] = D[k]+dist[k][j];
					p[j] = k;
				}
			}
		}
		for(int i = 0;i<n;i++){
			int pre;
			System.out.print("距离: "+D[i] + "  " + i);
			pre = p[i];
			while(pre != 0 && pre != v){
				System.out.print("<--"+ (pre));
				pre = p[pre];
			}
			System.out.println("<---"+v);
		}
	}
	//todo于20190405
	private void floyd(){

	}

	private void toposorta(Node root,int n){
		int v = 1,found = -1;
		int D[] = new int[n];
		for(int k =0;k<n;k++){
			for(int j= 0;j<n;j++) {
				if (D[j] == 0) {
					boolean flag = true;
					for (int m = 0; m < n; m++) {
						if (root.arcs[m][j] == 1) {
							flag = false;
							break;
						}
					}
					if (flag) {
						found = j;
						break;
					}
				}
			}
			if(found != -1 ){
				D[found] = v++;
				System.out.println(root.vextype[found]);
				for(int i=0;i<n;i++){
					root.arcs[found][i] = 0;
				}
			}
		}
		if(v != n+1){
			System.out.println("has a cycle");
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		//graph.createGraph(7,8,true);
		//graph.toposorta(graph.root,7);
		// graph.DFSA(0);
		// graph.BFSA(0);
		// graph.prime(0);
		 
		// graph.createGraph2(5, 5);
		// graph.DFSL(0);
		// graph.BFSL(0);
		//graph.kruskal(6, 10);
		graph.dijkstra(5);
		//System.out.println("hello world  ");
	}
}
