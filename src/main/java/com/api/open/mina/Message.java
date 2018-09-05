package com.api.open.mina;

import java.io.Serializable;

/**
 * Created by 003 on 2018/9/5.
 */
public class Message implements Serializable {
    public static final int ONLINE = 0;//上线
    public static final int OFF_LINE = 1;//离线
    public static final int CHAT = 2;//聊天

    private Long id;
    private int type = CHAT;
    private String name;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
