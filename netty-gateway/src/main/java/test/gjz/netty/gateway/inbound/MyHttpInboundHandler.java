package test.gjz.netty.gateway.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 测试
 * </pre>
 *
 * @author guojz
 * @version 1.00.00
 * @createDate 2020/10/30 18:26
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class MyHttpInboundHandler extends ChannelInboundHandlerAdapter {
    Logger log = LoggerFactory.getLogger(MyHttpInboundHandler.class);
    private final String LOGGER_HEAD = "MyHttpInboundHandler";


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelRegistered, " ,LOGGER_HEAD);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelUnregistered, " ,LOGGER_HEAD);
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelActive, " ,LOGGER_HEAD);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelInactive, " ,LOGGER_HEAD);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{}, channelRead, " ,LOGGER_HEAD);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelReadComplete, " ,LOGGER_HEAD);
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("{}, userEventTriggered, " ,LOGGER_HEAD);
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("{}, channelWritabilityChanged, " ,LOGGER_HEAD);
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("{}, exceptionCaught, " ,LOGGER_HEAD);
        super.exceptionCaught(ctx, cause);
    }
}

