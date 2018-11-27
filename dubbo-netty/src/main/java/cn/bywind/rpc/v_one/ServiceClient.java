package cn.bywind.rpc.v_one;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceClient {

    private ServiceConsumerHandler handler ;
    private static ExecutorService executor = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Object createProxy(final Class<?> serviceClass){

        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(handler == null){
                    startClient();
                }
                handler.setParams(args[0].toString());
                return executor.submit(handler).get();
            }
        });
        return o;
    }


    public void startClient(){
        handler = new ServiceConsumerHandler();
        try {
            NioEventLoopGroup worker = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new StringDecoder());
                    p.addLast(new StringEncoder());
                    p.addLast(handler);
                }
            });
            bootstrap.connect("127.0.0.1", 9999).sync();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
