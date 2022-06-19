package com.webstart.teamup;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

public class Structure_Profil {


    private int id;
    private String pseudo;
    private String email;
    private String phone;
    private String pictureProfil;
    private String description;
    private ArrayList<Structure_Team> teams;
    private ArrayList<Structure_Jeu> games;
    private ArrayList<Structure_Abonnement> abonnements;

    public Structure_Profil() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public ArrayList<Structure_Team> getTeams() {
        return teams;
    }
    public void setTeams(ArrayList<Structure_Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Structure_Jeu> getGames() {
        return games;
    }
    public void setGames(ArrayList<Structure_Jeu> games) {
        this.games = games;
    }

}
