package org.edl.gpmq.socket.recorder;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by yks on 2017/8/18.
 */
public class McuRecord {
    private static final Logger log = LoggerFactory.getLogger(McuRecord.class);
    private String id;
    private String ip;
    private int port;
    private String company;
    private IoSession session;
    private long startLinkTime = 0L;
    private long lastOnlineTime = 0L;


    /**
     * 心跳一次
     */
    public void heartbeat() {
        lastOnlineTime = System.currentTimeMillis();
        log.info("设备在线 ID[" + id + "]-时间戳-" + lastOnlineTime);
    }

    public McuRecord(IoSession session) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        this.session = session;
        InetSocketAddress inetSocketAddress = (InetSocketAddress) session.getRemoteAddress();
        ip = inetSocketAddress.getAddress().getHostAddress();
        port = inetSocketAddress.getPort();
        startLinkTime = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getLastOnlineTime() {
        return lastOnlineTime;
    }

    public long getStartLinkTime() {
        return startLinkTime;
    }

    public void setStartLinkTime(long startLinkTime) {
        this.startLinkTime = startLinkTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
