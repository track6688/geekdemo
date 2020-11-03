package test.gjz.netty.gateway.http.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

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

            ChannelFuture future = bootstrap.connect(getServerUrl(serverUrl), port).sync();
            logger.info("{}, clientHandle, 客户端已启动, 可以发送消息了。。。。。。" ,LOGGER_HEAD);
            // 等待， 直接连接关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder());
                        }
                    });

            ChannelFuture future = bootstrap.connect("localhost", 8802).sync();
            logger.info("{}, clientHandle, 客户端已启动, 可以发送消息了。。。。。。" ,"aaaa");
            // 等待， 直接连接关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * 去掉端口号
     * @param url
     * @return
     */
    private String getServerUrl(String url){
        Pattern p = compile("((http|https):\\/\\/([a-zA-Z1-9]?[a-zA-Z0-9\\.]+))(:(\\d+))?");
        Matcher m = p.matcher(url);

        String value = "127.0.0.1";
        if (m.find()) {
            value = m.group(3);
        }

        return value;
    }
}

