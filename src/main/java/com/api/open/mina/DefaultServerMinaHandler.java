package com.api.open.mina;

import com.api.open.util.JsonUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 003 on 2018/9/5.
 */

public class DefaultServerMinaHandler extends IoHandlerAdapter {
    private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Map<Long, IoSession> sessions = new HashMap<>();

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        System.out.println("客户端[" + session.getRemoteAddress() + "]：session已创建");
        sessions.put(session.getId(), session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("客户端[" + session.getRemoteAddress() + "]：已连接");
        Message mes = new Message();
        mes.setId(session.getId());
        mes.setType(Message.ONLINE);
        mes.setName(sim.format(new Date()));
        mes.setContent("用户" + session.getId() + "上线了");
        sendToAll(mes, session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("客户端[" + session.getRemoteAddress() + "]：已关闭");
        sessions.remove(session.getId());
        Message mes = new Message();
        mes.setId(session.getId());
        mes.setType(Message.OFF_LINE);
        mes.setName(sim.format(new Date()));
        mes.setContent("用户" + session.getId() + "下线了");
        sendToAll(mes, session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        Message mes = new Message();
        mes.setId(session.getId());
        mes.setName(sim.format(new Date()));
        mes.setContent(message.toString());
        if (message.toString().startsWith("@")) {
            String[] split = message.toString().split(" ");
            if (split.length > 0) {
                mes.setContent(split[1]);
                sendToSingle(mes, Long.valueOf(split[0].substring(1)));
            }
        } else {
            sendToAll(mes, session);
        }
        System.err.println("收到客户端发送的消息：" + mes.getContent());
    }

    /**
     * 发送消息给所有人
     */
    private void sendToAll(Message mes, IoSession session) {
        //收到某个人消息后，排除自己，将消息发送给聊天室所有人。
        for (IoSession value : sessions.values()) {
            if (value != session) {
                value.write(JsonUtil.toSuccess(mes));
            }
        }
    }

    /**
     * 发送消息给单个人
     */
    private void sendToSingle(Message mes, Long id) {
        IoSession ioSession = sessions.get(id);
        if (ioSession != null) {
            ioSession.write(JsonUtil.toSuccess(mes));
        }
    }
}