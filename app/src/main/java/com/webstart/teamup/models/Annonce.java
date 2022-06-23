package com.webstart.teamup.models;

public class Annonce {
    private String title, body;
    private String id;
    private String team;

    public Annonce() {
    }

    public Annonce(String title, String body, String id, String team) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
