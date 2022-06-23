package com.webstart.teamup.models;

public class Chat {
    private Integer id;
    private String last_message, last_message_date;
    private ProfilMin other_user;

    public Chat() {
    }

    public Chat(Integer id, String last_message, String last_message_date, ProfilMin other_user) {
        this.id = id;
        this.last_message = last_message;
        this.last_message_date = last_message_date;
        this.other_user = other_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getLast_message_date() {
        return last_message_date;
    }

    public void setLast_message_date(String last_message_date) {
        this.last_message_date = last_message_date;
    }

    public ProfilMin getOther_user() {
        return other_user;
    }

    public void setOther_user(ProfilMin other_user) {
        this.other_user = other_user;
    }
}
