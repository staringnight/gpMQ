package org.edl.gpmq.socket.bo;

import java.io.*;

/**
 * loginInfoToServer
 * Created by yks on 2017/8/17.
 */
public class m_login_tos extends Message {
    private String mSession;
    private String password;
    private String time;
    private String token;

    public m_login_tos() {
        MESSAGE_ID = 1002;
    }

    public byte[] encode() {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        DataOutputStream bytes = new DataOutputStream(ret);

        try {

            bytes.writeShort(MESSAGE_ID);
            bytes.writeUTF(this.mSession);
            bytes.writeUTF(this.password);
            bytes.writeUTF(this.time);
            bytes.writeUTF(this.token);
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
        int i, len;
        try {
            this.mSession = bytes.readUTF();
            this.password = bytes.readUTF();
            this.time = bytes.readUTF();
            this.token = bytes.readUTF();
        } catch (IOException e) {
            //todo:错误处理
            return false;
        } finally {
        }
        return true;
    }

    public String getmSession() {
        return mSession;
    }

    public void setmSession(String mSession) {
        this.mSession = mSession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "m_login_tos{" +
                "mSession='" + mSession + '\'' +
                ", password='" + password + '\'' +
                ", time='" + time + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
