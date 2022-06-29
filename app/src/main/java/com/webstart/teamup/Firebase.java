package com.webstart.teamup;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import com.webstart.teamup.models.Profil;
import com.webstart.teamup.models.Team;

import java.util.ArrayList;

public class Firebase extends AppCompatActivity {

    private static final Firebase FB = new Firebase();
    public static Firebase getInstance(){
        return  FB;
    }
    private Firebase() {
        mAuth = FirebaseAuth.getInstance();
    }

    public final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAuth mAuth;
    public FirebaseAuth getmAuth() {
        return mAuth;
    }
    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    private FirebaseUser FBuser;
    public FirebaseUser getFBuser() {
        return FBuser;
    }
    public void setFBuser(FirebaseUser FBuser) {
        this.FBuser = FBuser;
    }

    private Profil User = new Profil();

    public Profil getUser() {
        return User;
    }
    public void setUser(Profil user) {
        User = user;
    }

    public ArrayList<Team> teamsUser = new ArrayList<>();

    public FirebaseStorage storage = FirebaseStorage.getInstance("gs://teamup-57580.appspot.com");


}
