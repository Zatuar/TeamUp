package com.webstart.teamup.activities.teams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.adapters.team.TeamMemberAdapter;
import com.webstart.teamup.interfaces.ClickMemberListener;
import com.webstart.teamup.models.ProfilMin;
import com.webstart.teamup.models.Team;

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
        Team team = gson.fromJson(this.data, Team.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView membersRecycler = this.findViewById(R.id.team_members_container);
        membersRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(membersRecycler.getContext());
        membersRecycler.setLayoutManager(manager);
        RecyclerView.Adapter<TeamMemberAdapter.Holder> adapter = new TeamMemberAdapter(team.getMembers(),new ClickMemberListener(){
            @Override
            public void onMemberClick(ProfilMin member) {
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

        if (!team.getMembers().get(0).getId().equals(Firebase.getInstance().getUser().getId())){
            findViewById(R.id.see_applications).setVisibility(View.INVISIBLE);
            findViewById(R.id.edit_btn).setVisibility(View.INVISIBLE);
        }
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }

    public void editTeam(View view) {
        Intent teamEditActivity = new Intent(this, TeamEditActivity.class);
        teamEditActivity.putExtra("team", data);
        startActivity(teamEditActivity);
    }
}