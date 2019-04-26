package com.sankuai;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhanglinxing on 2018/1/2.
 * 思路:使用链表（易于移动数据，查找都是根据key来，数组下标优势丧失）+capacity
 * 尝试：使用当前这种情况，get效率很低，需要O(n)
 * 照抄网上hashmap+list实现http://blog.csdn.net/flying_panda/article/details/48352207
 * 更新于20190327 主要思想是Map+队列（只不过队列是双向且需要调整元素位置）
 */
public class LRUCache1<K, V> {
   // private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache1.class);
    private final int MAX_CACHE_SIZE;
    private Entry first;
    private Entry last;

    private HashMap<K, Entry<K, V>> hashMap;

    public LRUCache1(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new HashMap<K, Entry<K, V>>();
    }

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;
            if (entry == first) first = entry.next;
            if (entry == last) last = entry.pre;
        }
        hashMap.remove(key);
    }

    private void moveToFirst(Entry entry) {
        if (entry == first) return;
        if (entry.pre != null)
        {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        if (entry == last) {
            last = last.pre;
        }

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) first = null;
            else last.next = null;
        }
    }


    private Entry<K, V> getEntry(K key) {
        return hashMap.get(key);
    }

    //使用LinkedHashMap实现
    public static class LRUCache<K,V> extends LinkedHashMap<K,V> {

        private int size;

        public LRUCache(int size){
            this(size,16,0.75f,true);
        }
        public LRUCache(int size,int initialSize,float loadFactor,boolean accessOrder){
            super(initialSize,loadFactor,accessOrder);
            this.size = size;
        }

        protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
            return this.size() > size;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }

    class Entry<K, V> {
        public Entry pre;
        public Entry next;
        public K key;
        public V value;
    }

    static   void lruCache1() {
        System.out.println();
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        lru.put(5, "12");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();

        System.out.println("===========================LinkedHashMap 链表实现===========================");
        LRUCache<Integer, String> lru1 = new LRUCache(5);
        lru1.put(1, "11");
        lru1.put(2, "11");
        lru1.put(3, "11");
        lru1.put(4, "11");
        lru1.put(5, "11");
        lru1.put(5, "12");
        System.out.println(lru1.toString());
        lru1.put(6, "66");
        lru1.get(2);
        lru1.put(7, "77");
        lru1.get(4);
        System.out.println(lru1.toString());
        System.out.println();
    }

    public static void main(String[] args) {
          lruCache1();
    }
}


