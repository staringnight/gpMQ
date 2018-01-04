package org.edl.gpmq.socket.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.util.Arrays;

/**
 * HL协议解码器
 * Created by yks on 2017/8/15.
 */
public class HeadLengthObjectDecoder extends CumulativeProtocolDecoder {
    private byte head;

    public HeadLengthObjectDecoder(byte head) {
        this.head = head;
    }

    @Override
    public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(1000).setAutoExpand(true);
        int length;
        int start;
        while (in.hasRemaining()) {
            in.mark();
            byte b = in.get();
            buffer.put(b);
            if (b == head) {
                start = buffer.position();
                if (in.remaining() < 4) {
                    in.reset();
                    return false;
                }
                length = in.getInt();
                if (length >= 0 && in.remaining() >= length) {
                    for (int i = 0; i < length; i++) {
                        buffer.put(in.get());
                    }
                    HeadLengthObject headLengthObject = new HeadLengthObject();
                    headLengthObject.setLength(length);
                    headLengthObject.setObject(Arrays.copyOfRange(buffer.array(), start, buffer.position()));
                    out.write(headLengthObject);
                    return true;
                } else {
                    in.reset();
                    return false;
                }
            }
        }
        return false;
    }

}