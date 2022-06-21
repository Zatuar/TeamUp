package com.webstart.teamup;

import java.util.ArrayList;

public class Structure_Team {
    private String name, logo, description;
    private String id;
    private Integer score;
    private Integer rank;
    private ArrayList<Structure_Profil_Min> members;
    private Structure_Jeu game;
    private ArrayList<String> annonceIds;

    public Structure_Team() {
    }

    public Structure_Team(String name, String logo, String description, String id, Integer score, Integer rank, ArrayList<Structure_Profil_Min> members, Structure_Jeu game, ArrayList<String> annonceIds) {
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
