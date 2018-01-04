package org.edl.gpmq.socket.bo;

import java.io.*;

/**
 * loginInfoToClient
 * Created by yks on 2017/8/17.
 */
public class m_login_toc extends Message {
    public int code = 0;
    public String msg = "";
    public String mSession = "";

    public m_login_toc() {
        MESSAGE_ID = 1007;
    }

    public byte[] encode() {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        DataOutputStream bytes = new DataOutputStream(ret);

        try {
            bytes.writeShort(MESSAGE_ID);
            bytes.writeInt(this.code);
            bytes.writeUTF(this.msg);
            bytes.writeUTF(this.mSession);

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
            this.code = bytes.readInt();
            this.msg = bytes.readUTF();
            this.mSession = bytes.readUTF();

        } catch (IOException e) {
            //todo:错误处理
            return false;
        } finally {
        }
        return true;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getmSession() {
        return mSession;
    }

    public void setmSession(String mSession) {
        this.mSession = mSession;
    }

    @Override
    public String toString() {
        return "m_login_toc{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", mSession='" + mSession + '\'' +
                '}';
    }
}
