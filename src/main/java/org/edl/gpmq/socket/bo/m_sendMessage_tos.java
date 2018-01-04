package org.edl.gpmq.socket.bo;

import java.io.*;

/**
 * SendMessageToServer
 * Created by yks on 2017/8/17.
 */
public class m_sendMessage_tos extends Message {
    public String topic = "";
    public String msg = "";


    public m_sendMessage_tos() {
        MESSAGE_ID = 1003;
    }

    public byte[] encode() {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        DataOutputStream bytes = new DataOutputStream(ret);

        try {
            bytes.writeShort(MESSAGE_ID);
            bytes.writeUTF(this.topic);
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
            this.msg = bytes.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "m_sendMessage_tos{" +
                "topic='" + topic + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
