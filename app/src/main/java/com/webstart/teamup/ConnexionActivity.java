package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
    }

    public void goToHome(View view) {
        //vérification des informations de connexion
        //aller à la Home Page
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
    }

    public void goToGoogleConnxion(View view) {
        //créer un compte avec les informations google de la personne
    }
}