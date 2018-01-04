package org.edl.gpmq.socket.bo;

import java.io.*;

/**
 * SendMessageToClient
 * Created by yks on 2017/8/17.
 */
public class m_sendMessage_toc extends Message {
    public String topic = "";
    public int offset = 0;
    public String msg = "";


    public m_sendMessage_toc() {
        MESSAGE_ID = 1004;
    }

    public byte[] encode() {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        DataOutputStream bytes = new DataOutputStream(ret);

        try {
            bytes.writeShort(MESSAGE_ID);
            bytes.writeUTF(this.topic);
            bytes.writeInt(this.offset);
            bytes.writeUTF(this.msg);

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
        try {
            this.topic = bytes.readUTF();
            this.offset = bytes.readInt();
            this.msg = bytes.readUTF();

        } catch (IOException e) {
            //todo:错误处理
            return false;
        } finally {
        }
        return true;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "m_sendMessage_toc{" +
                "topic='" + topic + '\'' +
                ", offset=" + offset +
                ", msg='" + msg + '\'' +
                '}';
    }
}
