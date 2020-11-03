package test.gjz.netty.gateway.http.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Netty实现Http客户端
 * </pre>
 *
 * @author guojz
 * @version 1.00.00
 * @createDate 2020/11/3
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class NettyHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyHttpClient.class);

    private String LOGGER_HEAD = "[Netty实现HttpClient]";

    /**
     *连接服务器的地址
     */
    private String serverUrl;

    /**
     * 服务端口
     */
    private int port;

    public NettyHttpClient(String serverUrl, int port) {
        this.serverUrl = serverUrl;
        this.port = port;
    }


    /**
     * 启动客户端
     */
    public void clientHandle(final ChannelHandlerContext ctx, final FullHttpRequest fullRequest){

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class)
                    .handler(new NettyHttpClientInitializer(serverUrl, ctx, fullRequest));

            ChannelFuture future = bootstrap.connect(serverUrl, port).sync();
            logger.info("{}, clientHandle, 客户端已启动, 可以发送消息了。。。。。。" ,LOGGER_HEAD);
            // 等待， 直接连接关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }


    }
}

