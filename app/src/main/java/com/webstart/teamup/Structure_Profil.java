package com.webstart.teamup;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Structure_Profil {
    private String id;
    private String pseudo;
    private String email;
    private String phone;
    private String pictureProfil;
    private String description;
    private ArrayList<String> teams;
    private ArrayList<Structure_Jeu> games;
    private ArrayList<Structure_Abonnement> abonnements;

    public Structure_Profil() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPictureProfil() {
        return pictureProfil;
    }
    public void setPictureProfil(String pictureProfil) {
        this.pictureProfil = pictureProfil;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }
    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public ArrayList<Structure_Jeu> getGames() {
        return games;
    }
    public void setGames(ArrayList<Structure_Jeu> games) {
        this.games = games;
    }

    public ArrayList<Structure_Abonnement> getAbonnements() {
        return abonnements;
    }
    public void setAbonnements(ArrayList<Structure_Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

}
