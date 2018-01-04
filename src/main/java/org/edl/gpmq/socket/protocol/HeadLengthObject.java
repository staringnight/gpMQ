package org.edl.gpmq.socket.protocol;

import java.util.Arrays;

/**
 * HL协议接收的实体
 * Created by yks on 2017/8/16.
 */
public class HeadLengthObject {
    private int length;
    private byte[] object;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getObject() {
        return object;
    }

    public void setObject(byte[] object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "HeadLengthObject{" +
                "length=" + length +
                ", object=" + Arrays.toString(object) +
                '}';
    }
}
