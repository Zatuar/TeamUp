package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

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
        Structure_Annonce annonce = gson.fromJson(this.data, Structure_Annonce.class);

        TextView annonce_title = findViewById(R.id.annonce_title);
        annonce_title.setText(annonce.getTitle());        
        TextView annonce_team_name = findViewById(R.id.annonce_team_name);
        annonce_team_name.setText(annonce.getTeam());
        TextView annonce_content = findViewById(R.id.annonce_content);
        annonce_content.setText(annonce.getBody());
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }
}