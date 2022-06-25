package com.webstart.teamup.models;

import java.util.ArrayList;

public class Team {
    private String name, logo, description,id;
    private Integer score;
    private Integer rank;
    private ArrayList<ProfilMin> members;
    private Jeu game;
    private String chatID;
    private ArrayList<String> annonceIds;

    public Team() {
    }

    public Team(String name, String logo, String description, String id, Integer score, Integer rank, ArrayList<ProfilMin> members, Jeu game, ArrayList<String> annonceIds) {
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.id = id;
        this.score = score;
        this.rank = rank;
        this.members = members;
        this.game = game;
        this.annonceIds = annonceIds;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ArrayList<ProfilMin> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<ProfilMin> members) {
        this.members = members;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Jeu getGame() {
        return game;
    }

    public void setGame(Jeu game) {
        this.game = game;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }


    public ArrayList<String> getAnnonceIds() {
        return annonceIds;
    }

    public void setAnnonceIds(ArrayList<String> annonceIds) {
        this.annonceIds = annonceIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
