package com.sankuai;

import java.util.Random;

public class SkipList<E> {
	private final int LEVEL = 4;
	private Node<E> header;
	
	class Node<E>{
		E value;
		Node<E>[] forwards;
		public Node(E value,int level){
			this.value = value;
			forwards = new Node[level];
		}
		public Node(E value){
			this(value, LEVEL);
		}
		
	}
	
	public boolean find(E value){
		Node<E> header = this.header;
		if (header == null){
			throw new RuntimeException("header is null");
		}
		while(true){
			if (header.value.equals(value)){
				return true;
			}else{
				for(int level = header.forwards.length - 1; level>=0; level--){
					Node<E> tmpNode = header.forwards[level];
					if ( null != tmpNode && compare(value,tmpNode.value)){
						header =tmpNode;
						break;
					}else if ((null == tmpNode|| !header.value.equals(value)) && level == 0 ) {
						System.out.println("can not find the value,value= "+ value);
						return false;
					}
				}
			}
		}
	}
	
	public boolean insert(E value){
		Node<E> header = this.header;
		if (header == null){
			this.header = new Node<E>(value);
			return true;
		}
		Node<E> preNodes[] = new Node[LEVEL];
		while(true){
			if (header.value.equals(value)){
				return false;
			}else{
				for(int level = header.forwards.length - 1; level>=0; level--){
					Node<E> tmpNode = header.forwards[level];
					preNodes[level] = header;
					if ( null != tmpNode && compare(value,tmpNode.value)){
						header =tmpNode;
						break;
					}else if ((null == tmpNode|| !header.value.equals(value)) && level == 0 ) {
						if(header == this.header && compare(header.value, value)){
							int newLevel = LEVEL;
							Node<E> newNode = new Node<E>(value,newLevel);
							for(;newLevel > 0 ;newLevel--){
								newNode.forwards[newLevel-1] = header;
							}
							this.header = newNode;
						}else{
						int newLevel = getRandomLevel();
						Node<E> newNode = new Node<E>(value,newLevel);
						for(;newLevel > 0 ;newLevel--){
							newNode.forwards[newLevel-1] = preNodes[newLevel -1].forwards[newLevel -1];
							preNodes[newLevel -1].forwards[newLevel -1] = newNode;
						}
						}
						return true;
					}
				}
			}
		}
		
	
	}
	
	public boolean delete(E value){
		Node<E> header = this.header;
		if (header == null){
			throw new RuntimeException("header is null");
		}
		Node<E> preNodes[] = new Node[LEVEL];
		while(true){
			if (header.value.equals(value) ){
				System.out.println("find the value,value= "+ value);
				if(header == this.header){
					this.header = header.forwards[0];
				}else {
					for(int level = header.forwards.length-1;level>=0;level--){
						if(preNodes[level]== null){
							Node<E> tmp = this.header.forwards[level],pre=this.header.forwards[level];
							while(!tmp.value.equals(value)){
								pre = tmp;
								tmp = tmp.forwards[level];
							}
							pre.forwards[level] = header.forwards[level];
						}else {
							preNodes[level].forwards[level]=header.forwards[level];
						}
					}	
				}
				return true;
			}else{
				for(int level = header.forwards.length - 1; level>=0; level--){
					Node<E> tmpNode = header.forwards[level];
					preNodes[level] = header;
					if ( null != tmpNode && compare(value,tmpNode.value)){
						header =tmpNode;
						break;
					}else if ((null == tmpNode|| !header.value.equals(value)) && level == 0 ) {
						System.out.println("can not find the value,value= "+ value);
						return false;
					}
				}
			}
		}
	}
	private int getRandomLevel(){
		Random random = new Random();
		return random.nextInt(LEVEL-1)+1;
	}

	private boolean compare(E value1,E  value2){
		Comparable<? super E> k1 = (Comparable<? super E>) value1;
		return k1.compareTo(value2) >= 0?true :false;
	}
	public static void main(String[] args) {
		SkipList<Integer> skipList = new SkipList<Integer>();
		skipList.insert(3);
		skipList.insert(5);
		skipList.insert(7);
		skipList.insert(1);
		skipList.insert(19);
		skipList.insert(10);
		skipList.insert(30);
		skipList.insert(41);
		skipList.insert(49);
		skipList.insert(15);
		skipList.insert(190);
		skipList.insert(1);
		skipList.insert(109);
		skipList.find(4);
		skipList.find(2);
		skipList.delete(8);
		skipList.delete(5);
		skipList.delete(1);
		

	}

}
