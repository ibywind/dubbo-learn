package cn.bywind.rpc.v_two;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Callable;

public class ServiceConsumerHandler extends ChannelHandlerAdapter implements Callable{

    private ChannelHandlerContext context;
    private RpcResponse rpcResponse;
    private Object result;
    private RpcRequest params;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       context = ctx;

    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        rpcResponse = (RpcResponse) msg;
        result = rpcResponse.getResult();
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(params);
        wait();
        return result;
    }


    public RpcRequest getParams() {
        return params;
    }

    public void setParams(RpcRequest params) {
        this.params = params;
    }
}
