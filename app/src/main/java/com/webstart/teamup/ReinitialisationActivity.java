package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ReinitialisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinitialisation);
    }

    public void reinitialiserPw(View view) {
        //vérifier le nouveau mot de passe
        //le modifier dans la bdd
        //retourner à la page de connexion
    }
}