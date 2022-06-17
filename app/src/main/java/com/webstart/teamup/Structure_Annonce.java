package com.webstart.teamup;

public class Structure_Annonce {
    private String title, body;
    private Integer id;
    private Structure_Team team;

    public Structure_Annonce() {
    }

    public Structure_Annonce(String title, String body, Integer id, Structure_Team team) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Structure_Team getTeam() {
        return team;
    }

    public void setTeam(Structure_Team team) {
        this.team = team;
    }
}
