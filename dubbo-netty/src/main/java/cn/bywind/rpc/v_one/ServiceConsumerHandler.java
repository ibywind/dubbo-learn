package cn.bywind.rpc.v_one;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Callable;

public class ServiceConsumerHandler extends ChannelHandlerAdapter implements Callable{

    private ChannelHandlerContext context;
    private String result;
    private String params;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       context = ctx;

    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(params);
        wait();
        return result;
    }


    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
