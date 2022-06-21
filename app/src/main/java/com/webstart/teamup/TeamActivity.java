package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class TeamActivity extends AppCompatActivity {
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        data = getIntent().getStringExtra("team");
        printTeam();
    }

    private void printTeam() {
        Gson gson = new Gson();
        Structure_Team team = gson.fromJson(this.data, Structure_Team.class);

//        TextView titleNote = findViewById(R.id.titleN);
//        titleNote.setText(note.getTitle());
//        TextView bodyNote = findViewById(R.id.bodyN);
//        bodyNote.setText(note.getBody());
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }
}