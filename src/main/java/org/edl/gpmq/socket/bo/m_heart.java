package org.edl.gpmq.socket.bo;

import java.io.*;

/**
 * 心跳
 * Created by yks on 2017/8/17.
 */
public class m_heart extends Message {


    public m_heart() {
        MESSAGE_ID = 1001;
    }

    public byte[] encode() {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        DataOutputStream bytes = new DataOutputStream(ret);

        try {

            bytes.writeShort(MESSAGE_ID);

        } catch (Exception e) {
        } finally {
        }

        return ret.toByteArray();
    }

    public boolean decode(byte[] b) {
        DataInputStream bytes = new DataInputStream(new ByteArrayInputStream(b));
        return decodeBytes(bytes);
    }

    public boolean decodeBytes(DataInputStream bytes) {
        return true;
    }

    @Override
    public String toString() {
        return "m_heart{" +
                "MESSAGE_ID=" + MESSAGE_ID +
                '}';
    }
}
