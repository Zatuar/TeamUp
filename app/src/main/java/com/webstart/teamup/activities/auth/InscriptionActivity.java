package com.webstart.teamup.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.activities.HomeActivity;
import com.webstart.teamup.fragments.signup.Inscription1Fragment;
import com.webstart.teamup.fragments.signup.Inscription2Fragment;
import com.webstart.teamup.fragments.signup.Inscription3Fragment;
import com.webstart.teamup.R;
import com.webstart.teamup.models.Abonnement;
import com.webstart.teamup.models.Jeu;
import com.webstart.teamup.models.Profil;

import java.util.ArrayList;

public class InscriptionActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;
    Profil profil = new Profil();
    String pw;
    String games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_inscription, Inscription1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void goNext(View view) {
        String pageNumber = view.getTag().toString();
        transaction = manager.beginTransaction();

        switch (pageNumber) {
            case "2" :
                EditText verifemail = findViewById(R.id.edit_email);
                EditText verifpw = findViewById(R.id.edit_pw);
                EditText verifphone = findViewById(R.id.edit_phone);
                if(!(verifemail.getText().toString().equals("")||verifpw.getText().toString().equals("")||verifphone.getText().toString().equals(""))) {
                    pw = verifpw.getText().toString();
                    profil.setEmail(verifemail.getText().toString());
                    profil.setPhone(verifphone.getText().toString());
                    //Log.i("ProfilE", profil.getEmail());
                    //Log.i("ProfilP", profil.getPhone());
                    transaction.replace(R.id.fragment_inscription, Inscription2Fragment.class, null, "Page 2");
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack("Page 1");
                    transaction.commit();
                }
                break;
            case "3" :
                EditText pseudo = findViewById(R.id.edit_name);
                EditText bio = findViewById(R.id.edit_description);
                if(!(pseudo.getText().toString().equals("")))  {
                    profil.setPseudo(pseudo.getText().toString());
                    profil.setDescription(bio.getText().toString());
                    transaction.replace(R.id.fragment_inscription, Inscription3Fragment.class, null, "Page 3");
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack("Page 2");
                    transaction.commit();
            }
                break;
        }
    }

    public void addGame(){

    }

    public void goToHome(View view) {
        EditText selectGame = findViewById(R.id.edit_game);
        Intent home = new Intent(this, HomeActivity.class);
        //Log.i("Verif",(profil.getEmail()+" "+ pw));
        if(!selectGame.getText().toString().equals("")) {
            Firebase.getInstance().getmAuth().createUserWithEmailAndPassword(profil.getEmail(), pw)
                .addOnCompleteListener(InscriptionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SUCESS", "createUserWithEmail:success");
                            Firebase.getInstance().setFBuser(Firebase.getInstance().getmAuth().getCurrentUser());
                            Firebase.getInstance().getUser().setId(Firebase.getInstance().getmAuth().getUid());
                            profil.setId(Firebase.getInstance().getmAuth().getUid());
                            profil.setGames(new ArrayList<Jeu>());
                            profil.setAbonnements(new ArrayList<Abonnement>());
                            profil.setPictureProfil("");
                            profil.setTeams(new ArrayList<>());
                            Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getFBuser().getUid()).set(profil);
                            startActivity(home);
                        } else {
                            Log.w("SUCESS", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }
}