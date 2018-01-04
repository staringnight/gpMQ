package org.edl.gpmq.socket.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * HL协议编码器
 * Created by yks on 2017/8/17.
 */
public class HeadLengthObjectEncoder extends ProtocolEncoderAdapter {
    private byte head;

    public HeadLengthObjectEncoder(byte head) {
        this.head = head;
    }

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        if(!(message instanceof HeadLengthObjectMessage)) {
            throw new NotHeadLengthObjectMessageException(message.getClass().getName()+"未实现HeadLengthObjectMessage");
        } else {
            byte[] bytes = ((HeadLengthObjectMessage)message).encode();
            IoBuffer buff = IoBuffer.allocate(5 + bytes.length);
            buff.put(head);
            buff.putInt(bytes.length);
            buff.put(bytes);
            buff.flip();
            protocolEncoderOutput.write(buff);
        }
    }
}
