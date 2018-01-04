package org.edl.gpmq.socket.handler;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.edl.gpmq.core.DistributeCentre;
import org.edl.gpmq.socket.protocol.HeadLengthObject;
import org.edl.gpmq.socket.recorder.McuRecord;
import org.edl.gpmq.socket.recorder.McuRecordHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yks on 2017/7/22.
 */

public class SocketServiceHandler extends IoHandlerAdapter {

    protected static Logger logger = LoggerFactory.getLogger(SocketServiceHandler.class);

    @Override
    public void sessionOpened(IoSession iosession) throws Exception {

        if(iosession.getRemoteAddress()!=null) {

            logger.info("新增连接[" + iosession.getRemoteAddress().toString() + "]");
            logger.info("sessionOpenediosession[" + iosession.toString() + "]");
            McuRecord mcuRecord = new McuRecord(iosession);
            McuRecordHolder.add(mcuRecord);
        }else {
            iosession.close(true);
        }

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        logger.info("exceptionCaught");
        McuRecord mcuRecord = McuRecordHolder.get(session);
        logger.error("协调器异常-ID[" + mcuRecord.toString() +"]，IP[" + mcuRecord.getIp() + "] ]");

        //移除登陆列表
        McuRecordHolder.remove(mcuRecord);
        mcuRecord = null;

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        //logger.info("messageReceived"+message.toString());
        logger.info("messageReceivediosession["+session.toString()+"]");
        HeadLengthObject headLengthObject = (HeadLengthObject) message;
        McuRecord mcuRecord = McuRecordHolder.get(session);
        if(mcuRecord==null){
            logger.info("不存在该条连接,该连接未注册"+session.toString());
            session.close(true);
            return;
        }
        new DistributeCentre().distributeMessageFormClient(headLengthObject,mcuRecord);
    }

    @Override
    public void messageSent(IoSession iosession, Object obj) throws Exception {
        logger.info("Serve端响应客户端发送数据:"+obj.toString());
    }
}
