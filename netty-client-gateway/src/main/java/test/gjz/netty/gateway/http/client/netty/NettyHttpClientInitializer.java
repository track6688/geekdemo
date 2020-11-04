package test.gjz.netty.gateway.http.client.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * <pre>
 * Netty实现Http客户端通道初始化
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
public class NettyHttpClientInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 访问服务器地址
     */
    private String serverUrl;
    private ChannelHandlerContext ctx;

    public NettyHttpClientInitializer(String serverUrl, ChannelHandlerContext ctx) {
        this.serverUrl = serverUrl;
        this.ctx = ctx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpResponseDecoder())
                .addLast(new HttpRequestEncoder())
                .addLast(new HttpObjectAggregator(1024 * 1024))
                .addLast(new NettyHttpClientInboundHandler(serverUrl, ctx));
    }
}

