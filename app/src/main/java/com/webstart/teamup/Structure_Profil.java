package com.webstart.teamup;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

public class Structure_Profil {

    private DatabaseReference databaseReference;
    private int id;
    private String pseudo;
    private String email;
    private ArrayList<Structure_Team> teams;
    private ArrayList<Structure_Abonnement> abonnements;
    public Structure_Profil() {
    }

    public Task<Void> add(Structure_Profil note){
        return databaseReference.push().setValue(note);
    }
    public Task<Void> update(String key, HashMap<String, Object> map) {
        return databaseReference.child(key).updateChildren(map);
    }
    public Task<Void> delete(String key){
        return databaseReference.child(key).removeValue();
    }
    public Query get() {
        Log.i("INFO","Query");
        return databaseReference.orderByKey();
    }
}
