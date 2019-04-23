package com.sankuai.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,12,120l, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20));

    public static void main(String[] args) throws Exception {
        int port = 8080;
        //bio(port);
        nio(port);
    }

    private static void nio(int port) {
        MultiplexerTimeService multiplexerTimeService = new MultiplexerTimeService(port);
        new Thread(multiplexerTimeService,"server").start();
    }

    static class MultiplexerTimeService implements  Runnable{
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile  boolean stop = false;
        public MultiplexerTimeService(int port){
            try{
                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            }catch (Exception e){
                System.out.println("MultiplexerTimeService construct"+ e);
            }
        }

        @Override
        public void run() {
            while (!stop){
                try{
                    selector.select(1000);
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeySet.iterator();
                    SelectionKey key = null;
                    while(it.hasNext()){
                        key = it.next();
                        it.remove();
                        handInput(key);
                    }
                }catch (Exception e){
                    System.out.println("run "+ e);
                    stop = true;
                    if(selector != null){
                        try {
                            selector.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }

        private void handInput(SelectionKey key) throws  IOException {
            if(key.isValid()){
                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                }
                if(key.isReadable()){
                    SocketChannel sc =(SocketChannel)key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int count = sc.read(byteBuffer);
                    if(count > 0) {
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        String body = new String(bytes,"UTF-8");
                        System.out.println("body:" + body);
                        String currentTime = "query time".equals(body) ? (new Date()).toString() : "bad order";
                        doWrite(currentTime,sc);
                    }else if(count<0){
                        key.cancel();
                        sc.close();
                    }else{

                    }
                }else if(key.isWritable()){
                    System.out.println("isWritable occur!");
                }
            }
        }

        private void doWrite(String currentTime,SocketChannel sc) throws  IOException {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            int writeCount =  sc.write(byteBuffer);
            System.out.println("doWrite!"+currentTime+" writeCount: "+writeCount);
        }
    }


    private static void  bio(int port) throws IOException{
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            Socket socket =null;
            while (true){
                socket = serverSocket.accept();
                //一个请求新起一个线程
                //new Thread(new TimeServerHandler(socket)).start();
                //线程池 ，伪异步
                threadPoolExecutor.submit(new TimeServerHandler(socket));
            }
        }finally {
            if(serverSocket != null){
                serverSocket.close();
            }

        }
    }

    static class TimeServerHandler implements  Runnable{
        private Socket socket;
        TimeServerHandler(Socket socket){
            this.socket = socket;
        }


        @Override
        public void run()  {
            BufferedReader in ;
            PrintWriter out ;
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                String currentTime,body;
                while(true){
                    body = in.readLine();
                    System.out.println("body: "+body+ " "+new Date());
                    if(body == null){
                        break;
                    }
                    currentTime = "query time".equals(body) ? (new Date()).toString() : "bad order";
                    out.println(currentTime);
                }
            }catch (Exception e){
                System.out.println("TimeServerHandler err:"+e);
            }
            finally {

            }
        }
    }
}
