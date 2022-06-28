package com.webstart.teamup.activities.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.models.Team;

public class TeamEditActivity extends AppCompatActivity {
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_edit);
        data = getIntent().getStringExtra("team");
        printTeam();
    }

    private void printTeam() {
        Gson gson = new Gson();
        Team team = gson.fromJson(this.data, Team.class);
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }
}