package com.webstart.teamup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

public class TeamCreateActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;
    Structure_Team team = new Structure_Team();
    Structure_Jeu game = new Structure_Jeu();
    ArrayList<Structure_Profil_Min> members = new ArrayList<>();
    Structure_Annonce annonce = new Structure_Annonce();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_team_create,TeamCreate1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void goNext(View view) {
        transaction = manager.beginTransaction();
        EditText team_name = findViewById(R.id.team_name);
        Spinner team_game = findViewById(R.id.team_game);
        EditText team_member = findViewById(R.id.team_member);
        EditText team_bio = findViewById(R.id.team_description);
        if (!(team_name.getText().toString().equals("") || team_game.getSelectedItem().toString().equals(""))) {
            game.setName(team_game.getSelectedItem().toString());
            team.setName(team_name.getText().toString());
            team.setDescription(team_bio.getText().toString());
            team.setGame(game);

            transaction.replace(R.id.fragment_team_create, TeamCreate2Fragment.class, null, "Page 2");
            transaction.setReorderingAllowed(true);
            transaction.addToBackStack("Page 1");
            transaction.commit();
        }
    }

    public void createTeam(View view){
        EditText annonce_title = findViewById(R.id.annonce_title);
        EditText annonce_body = findViewById(R.id.annonce_body);

        annonce.setTitle(annonce_title.getText().toString());
        annonce.setBody(annonce_body.getText().toString());
        annonce.setTeam(team.getName());
        Firebase.getInstance().db.collection("teams").document().set(team);
        Firebase.getInstance().db.collection("annonces").document().set(annonce);
        finish();
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }
}