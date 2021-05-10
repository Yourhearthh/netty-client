package com.example.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName:
 * @Description:
 * @author: baoguangyu
 * @date: 2021-05-10 16:23
 * @version: 1.0
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //int length = ((String) msg).length();
        //System.out.println(msg+" "+length);
        String _msg=(String)msg;
        System.out.println(_msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 无限发送数据给服务端
        while (true) {
            super.channelActive(ctx);
            String str = "16,18";
            ctx.writeAndFlush(str);
            // 有点像定时发送，目前不知道怎么设置客户端的数据按照物联网那样实时获取数据，再发送到服务端
            Thread.sleep(10*1000);
        }
    }
}
