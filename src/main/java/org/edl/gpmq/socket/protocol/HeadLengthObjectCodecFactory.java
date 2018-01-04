package org.edl.gpmq.socket.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 定义HL协议Factory
 * Created by yks on 2017/8/17.
 */
public class HeadLengthObjectCodecFactory implements ProtocolCodecFactory{
    private final HeadLengthObjectEncoder encoder;
    private final HeadLengthObjectDecoder decoder;

    public HeadLengthObjectCodecFactory(byte head) {
        this.encoder = new HeadLengthObjectEncoder(head);
        this.decoder = new HeadLengthObjectDecoder(head);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
