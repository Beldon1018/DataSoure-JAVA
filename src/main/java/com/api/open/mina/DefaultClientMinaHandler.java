package com.api.open.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by 003 on 2018/9/5.
 */
public class DefaultClientMinaHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("我已经连接成功了，" + session.getRemoteAddress());
    }


    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("我已经和服务器[" + session.getRemoteAddress() + "]断开连接");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        //Message mes = (Message) message;
        System.out.println("服务返回消息：" + message);
    }
}