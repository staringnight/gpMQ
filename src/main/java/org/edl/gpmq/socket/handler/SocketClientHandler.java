package org.edl.gpmq.socket.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class SocketClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession iosession) throws Exception {


        iosession.close(true);
    }

    @Override
    public void messageReceived(IoSession iosession, Object obj)
            throws Exception {

    }

    @Override
    public void messageSent(IoSession iosession, Object obj) throws Exception {



    }

}  