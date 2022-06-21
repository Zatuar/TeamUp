package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView membersRecycler = this.findViewById(R.id.team_members_container);
        membersRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(membersRecycler.getContext());
        membersRecycler.setLayoutManager(manager);
        RecyclerView.Adapter<TeamMemberAdapter.Holder> adapter = new TeamMemberAdapter(team.getMembers(),new ClickMemberListener(){
            @Override
            public void onMemberClick(Structure_Profil_Min member) {
            }
        }, this);
        membersRecycler.setAdapter(adapter);
        membersRecycler.setLayoutManager(layoutManager);

        TextView team_rank = findViewById(R.id.team_rank);
        team_rank.setText("#"+team.getRank()+" ");
        TextView team_score = findViewById(R.id.team_score);
        team_score.setText("("+team.getScore()+"pts)");
        TextView team_name = findViewById(R.id.team_name);
        team_name.setText(team.getName());
//        TextView bodyNote = findViewById(R.id.bodyN);
//        bodyNote.setText(note.getBody());
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }


}