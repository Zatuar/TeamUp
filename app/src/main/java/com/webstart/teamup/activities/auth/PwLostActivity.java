package com.webstart.teamup.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.webstart.teamup.R;

public class PwLostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_lost);
    }

    public void sendNewPassword(View view) {
        //vérifier l'adresse email
        //envoyer un mail pour réinitialiser le mot de passe
    }
}