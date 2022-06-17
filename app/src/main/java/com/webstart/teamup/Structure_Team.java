package com.webstart.teamup;

import java.util.ArrayList;

public class Structure_Team {
    private String name, logo;
    private Integer id;
    private Integer score;
    private Integer rank;
    private ArrayList<Structure_Profil_Min> members;
    private Structure_Jeu game;

    public Structure_Team() {
    }

    public Structure_Team(String name, String logo, Integer id, Integer score, ArrayList<Structure_Profil_Min> members, Integer rank, Structure_Jeu game) {
        this.name = name;
        this.logo = logo;
        this.id = id;
        this.score = score;
        this.members = members;
        this.rank = rank;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ArrayList<Structure_Profil_Min> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Structure_Profil_Min> members) {
        this.members = members;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Structure_Jeu getGame() {
        return game;
    }

    public void setGame(Structure_Jeu game) {
        this.game = game;
    }
}
