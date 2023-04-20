package com.sankuai.veryeasy;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
	static  boolean isAnagram(String s1,String s2){
		if(s1.length() != s2.length())
			return false;
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		for(Character ch : s1.toCharArray()){
			if(countMap.containsKey(ch)){
				countMap.put(ch, countMap.get(ch)+1);
			}else{
				countMap.put(ch, 1);
			}
		}
		for(Character ch : s2.toCharArray()){
			if(countMap.containsKey(ch)){
				Integer count = countMap.get(ch);
				if(count > 0){
				countMap.put(ch, count-1);
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isAnagram("anagram", "nagaaram"));

	}

}
