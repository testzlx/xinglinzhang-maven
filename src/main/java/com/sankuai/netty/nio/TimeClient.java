package com.sankuai.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClient {

    public static void main(String[] args) throws  Exception{
        int port = 8080;
       // bio(port);
        nio(port);
    }

    private static void nio(int port) {
            new Thread(new TimeClientHandle(port),"client").start();
    }

    private static void bio(int port) throws  Exception{
        Socket socket = new Socket("127.0.0.1",port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("query time");
        System.out.println("send query time");
        String resp = in.readLine();
        System.out.println("resp: "+resp);
        Thread.sleep(4000);
    }

     static class  TimeClientHandle implements  Runnable{
        private Selector selector;
        private SocketChannel socketChannel;
        private String host = "127.0.0.1";
        private Integer port;
        private volatile boolean stop = false;

        public TimeClientHandle(int port){
            try {
                this.port = port;
                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        @Override
        public void run() {
            try{
                doConnect();
            }catch (Exception e){
                System.out.println("exception:"+e);
            }
            while(!stop){
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeySet.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        handInput(key);
                    }
                }catch (Exception e){
                    System.out.println("e:"+e);
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
                 SocketChannel sc =(SocketChannel)key.channel();
                 if(key.isConnectable()){
                     if(sc.finishConnect()){
                         sc.register(selector,SelectionKey.OP_READ);
                         doWrite(sc);
                     }else{

                     }
                 }
                 if(key.isReadable()) {
                     ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                     int count = sc.read(byteBuffer);
                     if (count > 0) {
                         byteBuffer.flip();
                         byte[] bytes = new byte[byteBuffer.remaining()];
                         byteBuffer.get(bytes);
                         String body = new String(bytes, "UTF-8");
                         System.out.println("body:" + body);
                         stop = true;
                     } else if (count < 0) {
                         key.cancel();
                         sc.close();
                     } else {

                     }
                 }
             }
         }

         private void doConnect() throws  Exception {
            if(socketChannel.connect(new InetSocketAddress(host,port))){
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else{
                socketChannel.register(selector,SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel socketChannel) throws IOException {
            byte[] bytes = "query time".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            System.out.println("client send msg");
        }
    }
}
