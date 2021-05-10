package com.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName:
 * @Description:
 * @author: baoguangyu
 * @date: 2021-05-10 16:22
 * @version: 1.0
 */
@Component
public class NettyClient {
    @Value("${socket.keepalive}")
    public  boolean KEEP_ALIVE;
    @Value("${socket.backlog}")
    public int SO_BACKLOG;
    @Value("${socket.port}")
    private  int PORT;
    @Value("${socket.ip}")
    private String IP;

    Channel channel;
    public void start(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, KEEP_ALIVE);
            bootstrap.handler(new ClientChannelInitializer());
            ChannelFuture f = bootstrap.connect(IP, PORT).sync();
            // 等待连接关闭
            ChannelFuture channelFuture = f.channel().closeFuture();
            channel = channelFuture.channel();
        } catch (Exception e) {
            e.printStackTrace();
            workerGroup.shutdownGracefully();
        }
    }

//    // 每分钟启动
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void task() {
//        String str = "13,14";
//        channel.writeAndFlush(str);
//    }





}
