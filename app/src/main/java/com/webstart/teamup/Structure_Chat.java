package com.webstart.teamup;

import java.util.ArrayList;

public class Structure_Chat {
    private Integer id;
    private String last_message, last_message_date;
    private Structure_Profil_Min other_user;

    public Structure_Chat() {
    }

    public Structure_Chat(Integer id, String last_message, String last_message_date, Structure_Profil_Min other_user) {
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

    public Structure_Profil_Min getOther_user() {
        return other_user;
    }

    public void setOther_user(Structure_Profil_Min other_user) {
        this.other_user = other_user;
    }
}
