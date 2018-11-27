package cn.bywind.rpc.v_one;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServiceProviderHandler extends ChannelHandlerAdapter {

    private static final Service SERVICE = new ServiceProvider();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = msg.toString();
        System.out.println("get str from client:"+s);
        ctx.writeAndFlush(SERVICE.sayHelloWithName(s));
    }
}
