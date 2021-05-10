package com.example.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @ClassName:
 * @Description:
 * @author: baoguangyu
 * @date: 2021-05-10 16:22
 * @version: 1.0
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new ClientHandler());
    }
}
