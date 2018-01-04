package org.edl.gpmq.socket.bo;


import org.edl.gpmq.socket.protocol.HeadLengthObjectMessage;

import java.io.DataInputStream;

/**
 * socket消息体
 * by yks 2018年1月4日 22:34:36
 */
public class Message implements HeadLengthObjectMessage {
    public int MESSAGE_ID;

    public Message() {
    }

    public byte[] encode() {
        return null;
    }

    public boolean decode(byte[] b) {
        return false;
    }

    public boolean decodeBytes(DataInputStream bytes) {
        return false;
    }
}

