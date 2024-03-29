package com.sankuai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testArraySort(){
        Integer[] arr = {-2,3,-6,6,9};
        Arrays.sort(arr,(a,b)->b-a);
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void binarySortTest(){
        int[] arr = {1,3,5,7,9};
        int ret = Arrays.binarySearch(arr,4);
        System.out.println(ret);
    }


    @Test
    public void listTest(){
        Queue<List<Integer>> aa = new LinkedList<List<Integer>>();
        aa.add(Arrays.asList(1,2,3,4));
        aa.add(Arrays.asList(1,3));
        aa.add(Arrays.asList(1,3));
        System.out.println(Arrays.toString(aa.peek().toArray()));
        System.out.println(aa.size());
        System.out.println(aa.poll());
        System.out.println(aa.size());
        List<Integer> list = new LinkedList<Integer>();
        List<Integer> bb = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(13);

    }

    @Test
    public void setTest(){
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        System.out.println(set.contains(1));
        for(Iterator<Integer> it = set.iterator();it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println(-1&15);

    }

    @Test
    public void byteTest(){
        int value = 1000,off = 0;
        byte[] bytes = new  byte[4];
        bytes[off + 3] = (byte) value;
        bytes[off + 2] = (byte) (value >>> 8);
        bytes[off + 1] = (byte) (value >>> 16);
        bytes[off] = (byte) (value >>> 24);
        System.out.println(Arrays.toString(bytes));

        //把以上byte数组还原
        int tt = ((bytes[off + 3] & 0xFF)) + ((bytes[off + 2] & 0xFF) << 8) + ((bytes[off + 1] & 0xFF) << 16) + ((bytes[off]) << 24);
        System.out.println("tt=" + tt);
    }

    @Test
    public void mapTest(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

    @Test
    public void randomTest(){
        Random random = new Random();
        for(int i=0;i<10;i++) {
            System.out.println(random.nextInt(3));
        }
    }

    @Test
    public void queueTest(){
        Deque<Integer> a = new ArrayDeque<Integer>();
        a.addFirst(1);
        a.addFirst(2);
        a.addLast(3);
        System.out.println(a.pollFirst());
        System.out.println(a.pollFirst());
        System.out.println(a.pollFirst());

    }

    @Test
    public void collectionSortTest(){
        List<Integer> list = Arrays.asList(1,3,2,4,5);
        Collections.sort(list,(a,b)->{return b-a;});
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void testPriorityQueue(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        priorityQueue.add(4);
        priorityQueue.add(42);
        priorityQueue.add(1);
        priorityQueue.add(7);
        priorityQueue.add(5);
        priorityQueue.add(4);
        while(!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }

    }

    @Test
    public void strTest(){
        String a = "abcd";
        System.out.println(a.substring(0,a.length()-1));
        char[] reverse = new char[a.length()];
        for(int i=0;i<reverse.length;i++){
            reverse[i] = a.charAt(reverse.length -i-1);
        }
        System.out.println(new String(reverse));

    }

    @Test
    public void operatorTest(){
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;
        System.out.println(123);
    }

    @Test
    public void completedFutureTest(){
        CompletableFuture<String> root = new CompletableFuture<>();
        root.thenApply(s->{
            System.out.println("3");return "3";});
        root.thenApply(s->{
            System.out.println("6");return "6";});
        root.thenApply(s->{
            System.out.println("9");return "9";});
        root.complete("1");

        System.out.println("=============================");
        CompletableFuture<String> root1 = new CompletableFuture<>();
        root1.thenApply(s->{return "2";});
        CompletableFuture bcf=root1.thenApply(s->{return "7";});
        root1.thenApply(s->{return "4";});

        bcf.thenApply(s->{
            System.out.println("3");return "3";});
        bcf.thenApply(s->{
            System.out.println("6");return "6";});
        bcf.thenApply(s->{
            System.out.println("9");return "9";});
        root1.complete("1");
    }


    static int count=0;

    @Test
    public  void testLocalClass() throws InterruptedException {
        System.out.println(0);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(123);
                count = 10;
            }
        };
        Thread t =  new Thread(runnable);
        //设置demon线程，主线程退出，demon线程也会退出
        //t.setDaemon(true);
        t.start();
        //t.join();
        System.out.println(456);
        System.out.println("count=" +count);
    }
}
