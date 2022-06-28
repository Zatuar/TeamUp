package com.webstart.teamup.activities.teams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.models.Team;

public class TeamEditActivity extends AppCompatActivity {
    private String data;
    EditText team_name, team_description;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_edit);
        data = getIntent().getStringExtra("team");
        printTeam();
    }

    private void printTeam() {
        Gson gson = new Gson();
        team = gson.fromJson(this.data, Team.class);

        team_name = findViewById(R.id.team_name);
        team_name.setText(team.getName());
        team_description = findViewById(R.id.team_description);
        team_description.setText(team.getDescription());
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }

    public void editTeam(View view) {
        String newName = team_name.getText().toString();
        String newDescription = team_description.getText().toString();
//        Firebase.getInstance().db.collection("teams").document(Firebase.getInstance().getUser().getId()).update("pseudo",newPseudo);

        Firebase.getInstance().db.collection("teams")
        .document(""+team.getId())
        .update("name", newName);

        Firebase.getInstance().db.collection("teams")
        .document(""+team.getId())
        .update("description", newDescription);
        finish();
    }
}