package cn.bywind.rpc.v_one;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServiceServer {

    private int port = 0;

    public ServiceServer(int port) {
        this.port = port;
    }

    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    public void run(){
        System.out.println("running on port ："+port);
        try {
            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new StringDecoder());
                    p.addLast(new StringEncoder());
                    p.addLast(new ServiceProviderHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }



    public static void main(String[] args) {
        ServiceServer serviceServer = new ServiceServer(9999);
        serviceServer.run();
    }


}
