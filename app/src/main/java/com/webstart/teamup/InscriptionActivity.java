package com.webstart.teamup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InscriptionActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;
    Structure_Profil profil = new Structure_Profil();
    String pw;
    String games;

    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_inscription,Inscription1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
        mAuth = FirebaseAuth.getInstance();
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
                    Log.i("ProfilE", profil.getEmail());
                    Log.i("ProfilP", profil.getPhone());
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
        Log.i("Verif",(profil.getEmail()+" "+ pw));
        if(!selectGame.getText().toString().equals("")) {
            mAuth.createUserWithEmailAndPassword(profil.getEmail(), pw)
                .addOnCompleteListener(InscriptionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCESS", "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                            //updateUI(user);
                            startActivity(home);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SUCESS", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        }
    }
}