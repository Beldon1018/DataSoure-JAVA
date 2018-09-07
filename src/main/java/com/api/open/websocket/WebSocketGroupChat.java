package com.api.open.websocket;

import com.api.open.util.JsonUtil;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 003 on 2018/9/6.
 */
@ServerEndpoint(value = "/groupChat/{id}")
@Component
public class WebSocketGroupChat {

    private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Map<String, Session> sessionMap = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        sessionMap.put(session.getId(), session);
        System.out.println(id + "加入房间！当前在线人数为：" + sessionMap.size());
        Message mes = new Message();
        mes.setId(session.getId());
        mes.setName(id);
        mes.setType(Message.ONLINE);
        mes.setTime(sim.format(new Date()));
        mes.setContent(id + "加入房间");
        mes.setCount(sessionMap.size());
        sendToAll(mes, session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        sessionMap.remove(session.getId());  //从set中删除
        System.out.println(id + "离开房间！当前在线人数为：" + sessionMap.size());
        Message mes = new Message();
        mes.setId(session.getId());
        mes.setName(id);
        mes.setType(Message.OFF_LINE);
        mes.setTime(sim.format(new Date()));
        mes.setContent(id + "离开房间");
        mes.setCount(sessionMap.size());
        sendToAll(mes, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("id") String id) {
        System.out.println("来自" + id + "的消息：" + message);
        Message mes = new Message();
        mes.setId(id);
        mes.setName(id);
        mes.setTime(sim.format(new Date()));
        mes.setContent(message);
        sendToAll(mes, session);
    }


    /**
     * 发送消息给所有人
     */
    private void sendToAll(Message mes, Session session) {
        //收到某个人消息后，排除自己，将消息发送给聊天室所有人。
        for (Session value : sessionMap.values()) {
            if (value != session) {
                try {
                    value.getBasicRemote().sendText(JsonUtil.toJson(mes));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送消息给单个人
     */
    private void sendToSingle(Message mes, Long id) {
        Session ioSession = sessionMap.get(id);
        if (ioSession != null) {
            try {
                ioSession.getBasicRemote().sendText(JsonUtil.toJson(mes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
