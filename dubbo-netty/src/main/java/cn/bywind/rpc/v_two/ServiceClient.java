package cn.bywind.rpc.v_two;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
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
                RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                request.setRequestId(UUID.randomUUID().toString());
                request.setClassName(method.getDeclaringClass().getName());
                request.setMethodName(method.getName());
                request.setParameterTypes(method.getParameterTypes());
                request.setArgs(args);
                handler.setParams(request);
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
                    p.addLast(new RPCEncoder(RpcRequest.class));
                    p.addLast(new RPCDecoder(RpcResponse.class));
                    p.addLast(handler);
                }
            });
            bootstrap.connect("127.0.0.1", 9999).sync();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
