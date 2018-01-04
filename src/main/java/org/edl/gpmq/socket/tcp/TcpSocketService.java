package org.edl.gpmq.socket.tcp;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.edl.gpmq.socket.handler.SocketServiceHandler;
import org.edl.gpmq.socket.protocol.HeadLengthObjectCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by yks on 2018/1/4.
 */
public class TcpSocketService {
    protected static Logger logger = LoggerFactory.getLogger(TcpSocketService.class);
    private IoAcceptor ioAcceptor;

    public TcpSocketService(int port) {
        ioAcceptor = new NioSocketAcceptor();
        try {
            //设置处理器
            ioAcceptor.setHandler(new SocketServiceHandler());
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 300);
            //规定协议，设定过滤，HeadLengthObjectCodecFactory
            ioAcceptor.getFilterChain().addLast("text", new ProtocolCodecFilter(new HeadLengthObjectCodecFactory((byte)0x99)));
            ioAcceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
            //ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
            //绑定端口
            ioAcceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            logger.error("socketError" + e);
        }
    }
}
