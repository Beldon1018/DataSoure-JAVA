package com.api.open.websocket;

import java.io.Serializable;

/**
 * Created by 003 on 2018/9/5.
 */
public class Message implements Serializable {
    public static final int ONLINE = 0;//上线
    public static final int OFF_LINE = 1;//离线
    public static final int CHAT = 2;//聊天

    private String id;
    private int type = CHAT;
    private String name;
    private String time;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
