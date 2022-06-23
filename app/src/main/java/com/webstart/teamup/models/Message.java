package com.webstart.teamup.models;

import java.util.Date;

public class Message {
    private String id;
    private String id_chat;
    private String id_writter;
    private String body;
    private Date sended_at;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId_chat() {
        return id_chat;
    }
    public void setId_chat(String id_chat) {
        this.id_chat = id_chat;
    }

    public String getId_writter() {
        return id_writter;
    }
    public void setId_writter(String id_writter) {
        this.id_writter = id_writter;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Date getSended_at() {
        return sended_at;
    }
    public void setSended_at(Date sended_at) {
        this.sended_at = sended_at;
    }

}
