package com.sankuai.trietree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class TrieTree {

	public Node root;
	HashSet<String> result = new HashSet();

	Map<String, Integer> resultMap = new HashMap();

	public Node insert(String word, Node node, Integer index, Integer indexOut) {
		if ((word == null) || ("".equals(word))) {
			return null;
		}

		char[] charArray = word.toCharArray();

		if (node == null) {
			node = new Node(charArray[index.intValue()], false, indexOut);
		}

		if (charArray[index.intValue()] < node.storeChar) {
			node.leftChild = insert(word, node.leftChild, index, indexOut);
		} else if (charArray[index.intValue()] > node.storeChar) {
			node.rightChild = insert(word, node.rightChild, index, indexOut);
		} else if (index.intValue() + 1 == charArray.length) {
			node.isComplete = true;
			node.indexOut = indexOut;
		} else {
			node.centerChild = insert(word, node.centerChild, Integer
					.valueOf(index.intValue() + 1), indexOut);
		}

		return node;
	}

	public void insert(String word, Integer index) {
		this.root = insert(word, this.root, Integer.valueOf(0), index);
	}

	public String toString() {
		clearMap();
		traverse(this.root, "");
		return "\nTernary Search Tree : " + this.result;
	}

	private void traverse(Node node, String str) {
		if (node == null)
			return;
		traverse(node.leftChild, str);

		str = str + node.storeChar;
		if (node.isComplete) {
			this.result.add(str);
		}
		traverse(node.centerChild, str);
		str = str.substring(0, str.length() - 1);

		traverse(node.rightChild, str);
	}

	public boolean search(String word) {
		return search(this.root, word.toCharArray(), 0);
	}

	private boolean search(Node node, char[] word, int index) {
		if (node == null) {
			return false;
		}
		if (word[index] < node.storeChar)
			return search(node.leftChild, word, index);
		if (word[index] > node.storeChar) {
			return search(node.rightChild, word, index);
		}

		if ((node.isComplete) && (index == word.length - 1))
			return true;
		if (index == word.length - 1) {
			return false;
		}
		return search(node.centerChild, word, index + 1);
	}

	public Node findNode(String prefix) {
		return findNode(this.root, prefix.toCharArray(), 0);
	}

	public Node findNode(Node node, char[] word, int index) {
		if (node == null) {
			return null;
		}
		if (word[index] < node.storeChar)
			return findNode(node.leftChild, word, index);
		if (word[index] > node.storeChar) {
			return findNode(node.rightChild, word, index);
		}

		if (index == word.length - 1) {
			return node;
		}
		return findNode(node.centerChild, word, index + 1);
	}

	public Map<String, Integer> prefixSearch(String prefix, Node node) {
		if (node != null) {
			if (node.isComplete) {
				this.result.add(prefix + node.storeChar);
				this.resultMap.put(prefix + node.storeChar, node.indexOut);
			}

			prefixSearch(prefix, node.leftChild);
			prefixSearch(prefix + node.storeChar, node.centerChild);
			prefixSearch(prefix, node.rightChild);
		}

		return this.resultMap;
	}

	public Map<String, Integer> prefixSearch(String prefix) {
		clearMap();
		Node node = findNode(prefix);
		if (node == null)
			return null;
		if (node.isComplete) {
			this.resultMap.put(prefix, node.indexOut);
		}

		return prefixSearch(prefix, node.centerChild);
	}

	public void clearMap() {
		this.result.clear();
		this.resultMap.clear();
	}

	public static void main(String[] args) {
		TrieTree t = new TrieTree();
		t.insert("ab", Integer.valueOf(2));
		t.insert("abba", Integer.valueOf(3));
		t.insert("abcd", Integer.valueOf(5));
		t.insert("bcd", Integer.valueOf(1));
		t.insert("bce", Integer.valueOf(1));
		t.insert("bcr34", Integer.valueOf(1));

		System.out.println(t);

		Map a = t.prefixSearch("bc");
		if (a == null)
			return;

		Iterator<Map.Entry<String, Integer>> it = a.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}

		//------------------------------------------第二种写法的测试-------------------
		t = new TrieTree();
		t.insert("abc");
		t.insert("ab");
		t.insert("abg");
		t.insert("acg");
		t.delete("ab");
		t.delete("abcd");
		System.out.println("----------------result--------------");
		t.prefixSearchV2("ab");
		System.out.println(t);
	}

	class Node {
		char storeChar;
		boolean isComplete;
		Integer indexOut;
		Node leftChild;
		Node centerChild;
		Node rightChild;

		public Node(char paramChar, boolean paramBoolean, Integer paramInteger) {
			this.storeChar = paramChar;
			this.isComplete = paramBoolean;
			this.indexOut = paramInteger;
		}
	}
	//-------------------------------------前缀树第二种简单的实现-------------

	class TrieNode {
		boolean isEnd;
		//根据业务需要可以添加其它属性
		Map<Character,TrieNode> children = new HashMap<>();
	}

	private TrieNode headV2 = new TrieNode();

	public boolean insert(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		TrieNode curr = headV2;
		for (int i = 0 ;i < str.length();i++) {
			char ch = str.charAt(i);
			if (!curr.children.containsKey(ch)) {
				curr.children.put(ch,new TrieNode());
			}
			curr = curr.children.get(ch);
		}
		if(curr.isEnd) {
			System.out.println(str + " exists!!");
			return false;
		}
		curr.isEnd = true;
		return true;
	}

	public boolean delete(String str) {
		if (str == null || str.isEmpty()) {
			return true;

		}
		TrieNode curr = headV2;
		for (int i = 0 ;i < str.length();i++) {
			char ch = str.charAt(i);
			if (!curr.children.containsKey(ch)) {
				System.out.println(str + " can not find!!");
				return false;
			}
			curr = curr.children.get(ch);
		}
		curr.isEnd = false;
		return true;
	}

	public TrieNode findNodeV2(String str) {
		TrieNode curr = headV2;
		for (int i = 0 ;i < str.length();i++) {
			char ch = str.charAt(i);
			if (!curr.children.containsKey(ch)) {
				System.out.println(str + " can not find!!");
				return null;
			}
			curr = curr.children.get(ch);
		}
		return curr;
	}

	public void prefixSearchV2(String str) {
		TrieNode node = findNodeV2(str);
		recruit(node,str);
	}

	private void recruit(TrieNode node, String str) {
		if (node != null) {
			if (node.isEnd) {
				System.out.println(str);
			}
			for (Map.Entry<Character,TrieNode> entry:node.children.entrySet()) {
				recruit(entry.getValue(),str + entry.getKey());
			}
		}
	}

}
