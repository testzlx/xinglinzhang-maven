package com.sankuai.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) throws  Exception{
        int port = 8080;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(bossGroup).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY,true).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception{
                            socketChannel.pipeline().addLast(new TimeClientHandle1());
                        }
                    });
            ChannelFuture f = b.connect("127.0.0.1",port).sync();
            f.channel().closeFuture().sync();
        }finally {

        }
    }

   static class  TimeClientHandle extends ChannelHandlerAdapter {
        private final ByteBuf byteBuf ;

        public TimeClientHandle(){
            byte[] msg = "query time".getBytes();
            byteBuf = Unpooled.buffer(msg.length);
            byteBuf.writeBytes(msg);
        }

       @Override
       public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
          ctx.close();
       }

       @Override
       public void channelActive(ChannelHandlerContext ctx) throws Exception {
          ctx.writeAndFlush(byteBuf);
       }

       @Override
       public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           ByteBuf byteBuf = (ByteBuf) msg;
           byte[] req = new byte[byteBuf.readableBytes()];
           byteBuf.readBytes(req);
           String body = new String(req,"UTF-8");
           System.out.println("client is "+body);
       }
   }

   static class TimeClientHandle1 extends ChannelHandlerAdapter  {
       private  int  count ;
       byte[] msg;

       public TimeClientHandle1(){
            msg = "query time".getBytes();

       }

       @Override
       public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
           ctx.close();
       }

       @Override
       public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ByteBuf byteBuf = null;
            for(int i=0;i<100;i++) {
                byteBuf = Unpooled.buffer(msg.length);
                byteBuf.writeBytes(msg);
                ctx.writeAndFlush(byteBuf);
            }
       }

       @Override
       public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           ByteBuf byteBuf = (ByteBuf) msg;
           byte[] req = new byte[byteBuf.readableBytes()];
           byteBuf.readBytes(req);
           String body = new String(req,"UTF-8");
           System.out.println("client is "+body+ " cnt:"+ ++count);
       }
   }
}
