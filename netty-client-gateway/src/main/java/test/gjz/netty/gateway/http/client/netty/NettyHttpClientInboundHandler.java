package test.gjz.netty.gateway.http.client.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Netty实现Http客户端处理
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
public class NettyHttpClientInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyHttpClientInboundHandler.class);

    private static final String LOGGER_HEAD = "[Netty实现Http客户端处理]";

    private NettyHttpClientOutBoundHandler handler;

    /**
     * 服务端地址
     */
    private String serverUrl;

    private ChannelHandlerContext sourceContext;
    private FullHttpRequest fullRequest;

    public NettyHttpClientInboundHandler(String serverUrl, ChannelHandlerContext sourceContext, FullHttpRequest fullRequest) {
        this.serverUrl = serverUrl;
        handler = new NettyHttpClientOutBoundHandler(serverUrl);
    }

    /**
     * 当客户端连接上时，回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("{}, channelInactive, 连接上服务器：{}, 开始发送信息。。。。。" ,LOGGER_HEAD, serverUrl);
        handler.sendGetHttp(ctx, fullRequest);
    }


    /**
     * 响应时回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("{}, channelRead, 服务器{}, 已回应, 转发回去。。。。。" ,LOGGER_HEAD, serverUrl);
        handler.handleResponse(sourceContext, (FullHttpResponse) msg);
    }
}

