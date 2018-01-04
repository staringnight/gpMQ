package org.edl.gpmq.socket.udp;

import com.sun.istack.internal.NotNull;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.edl.gpmq.socket.handler.SocketClientHandler;
import org.edl.gpmq.socket.protocol.HeadLengthObjectCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * tcp客户端
 * Created by yks on 2017/7/25.
 */
public class UdpSocketClient {
    private InetSocketAddress inetSocketAddress;

    private IoSession session;

    private IoConnector connector;
    private static UdpSocketClient tcpSocketClient = null;
    protected static Logger logger = LoggerFactory.getLogger(UdpSocketClient.class);
    public UdpSocketClient() {
        super();
    }

    public UdpSocketClient(String host, int port) {

        inetSocketAddress = new InetSocketAddress(host, port);

    }

    public static UdpSocketClient currentInstance(){
        return tcpSocketClient;
    }

    /**
     *如果最近没有Client，则新建传入的host、port的Client
     * @return
     */
    public static UdpSocketClient currentInstance(@NotNull String host, @NotNull int port){
        if(tcpSocketClient == null){
            newInstance(host, port);
        }
        return tcpSocketClient;
    }

    public static UdpSocketClient newInstance(@NotNull String host, @NotNull int port) {
        UdpSocketClient minaClient = new UdpSocketClient(host, port);
        minaClient.setConnector(new NioDatagramConnector());
        minaClient.getConnector().setHandler(new SocketClientHandler());
        IoConnector connector = minaClient.getConnector();
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new HeadLengthObjectCodecFactory((byte) 0x99)));

        ConnectFuture connectFuture = connector.connect(minaClient.getInetSocketAddress());
        // 等待是否连接成功，相当于是转异步执行为同步执行。
        connectFuture.awaitUninterruptibly();
        //连接成功后获取会话对象。如果没有上面的等待，由于connect()方法是异步的，
        //connectFuture.getSession(),session可能会无法获取。
        minaClient.setSession(connectFuture.getSession());
        tcpSocketClient = minaClient;
        return minaClient;
    }


    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public IoConnector getConnector() {
        return connector;
    }

    public void setConnector(IoConnector connector) {
        this.connector = connector;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }
}
