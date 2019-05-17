package top.osfun.eg;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Jacky on 2019-05-16 22:53.
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责连接后的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInit());
        ChannelFuture sync = serverBootstrap.bind(9999).sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
