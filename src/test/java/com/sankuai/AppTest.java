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
    public void mapTest(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
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
