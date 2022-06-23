package com.webstart.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.models.Annonce;

public class AnnouncementActivity extends AppCompatActivity {
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        data = getIntent().getStringExtra("annonce");
        printAnnonce();
    }

    private void printAnnonce() {
        Gson gson = new Gson();
        Annonce annonce = gson.fromJson(this.data, Annonce.class);

        TextView annonce_title = findViewById(R.id.annonce_title);
        annonce_title.setText(annonce.getTitle());        
        TextView annonce_team_name = findViewById(R.id.annonce_team_name);
        annonce_team_name.setText(annonce.getTeam());
        TextView annonce_content = findViewById(R.id.annonce_content);
        annonce_content.setText(annonce.getBody());
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }
}