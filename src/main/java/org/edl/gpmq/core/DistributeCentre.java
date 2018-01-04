package org.edl.gpmq.core;

import org.edl.gpmq.socket.bo.*;
import org.edl.gpmq.socket.protocol.HeadLengthObject;
import org.edl.gpmq.socket.recorder.McuRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * 业务分发中心
 * Created by yks on 2018/1/4.
 */
public class DistributeCentre {

    private Logger logger = LoggerFactory.getLogger(DistributeCentre.class);

    public void distributeMessageFormClient(HeadLengthObject headLengthObject, McuRecord mcuRecord) {

        boolean isLogin = (mcuRecord != null && mcuRecord.getId() != null) ? true : false;
        ByteArrayInputStream byteInt = new ByteArrayInputStream(headLengthObject.getObject());
        DataInputStream dataInputStream;
        dataInputStream = new DataInputStream(byteInt);
        Message message = MessageHandle.decode(dataInputStream);
        if (isLogin) {
            logger.info("传输数据" + message.toString());
            switch (message.MESSAGE_ID) {
                case 1001:
                    //心跳
                    mcuRecord.heartbeat();
                    break;
                case 1003:
                    //sendMessageToServer
                    handleMessageFormClient((m_sendMessage_tos) message);
                    break;
                case 1004:
                    //subscribe
                    handleSubscribe((m_subscribe) message);
                    break;
                case 1005:
                    //competePull
                    handleCompetePull((m_pull_compete) message);
                    break;
                case 1006:
                    //m_pull_uncompete
                    handleUnCompetePull((m_pull_uncompete) message);
                    break;

            }

        } else if (message.MESSAGE_ID == 1002) {
            logger.info("登陆" + message.toString());
            //loginInfoToServier
            login((m_login_tos) message, mcuRecord);
        } else {
            logger.info("尚未登陆成功，该消息将被丢弃" + message.toString());
        }
    }

    private void login(m_login_tos message, McuRecord mcuRecord) {

    }

    private void handleUnCompetePull(m_pull_uncompete message) {

    }

    private void handleCompetePull(m_pull_compete message) {

    }

    private void handleSubscribe(m_subscribe message) {

    }

    private void handleMessageFormClient(m_sendMessage_tos message) {

    }
}
