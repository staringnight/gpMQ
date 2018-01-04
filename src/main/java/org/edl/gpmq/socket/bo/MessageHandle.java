package org.edl.gpmq.socket.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;

/**
 * 消息体处理器
 * Created by yks on 2017/8/17.
 */
public class MessageHandle {
    private static Logger logger = LoggerFactory.getLogger(MessageHandle.class);

    public static Message decode(DataInputStream dataInputStream) {
        Message message = null;
        try {
            int MESSAGE_ID = dataInputStream.readShort();
            switch (MESSAGE_ID) {
                case 1001:
                    message = new m_heart();
                    break;
                case 1002:
                    message = new m_login_tos();
                    break;
                case 1003:
                    message = new m_sendMessage_tos();
                    break;
                case 1004:
                    message = new m_subscribe();
                    break;
                case 1005:
                    message = new m_pull_compete();
                    break;
                case 1006:
                    message = new m_pull_uncompete();
                    break;
                case 1007:
                    message = new m_login_toc();
                    break;
                case 1008:
                    message = new m_sendMessage_toc();
                    break;
                default:
                    logger.error("无法识别的MESSAGE_ID：" + MESSAGE_ID);
                    return null;
            }
            message.decodeBytes(dataInputStream);
        } catch (Exception e) {
            logger.info("反序列化出错", e);
        }
//        System.out.println(message.toString());
        return message;
    }
}
