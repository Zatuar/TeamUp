package com.webstart.teamup.fragments.team;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.teams.TeamActivity;
import com.webstart.teamup.adapters.team.TeamAdapter;
import com.webstart.teamup.interfaces.ClickTeamListenner;
import com.webstart.teamup.models.Team;
import com.webstart.teamup.operators.sortTeams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TeamsRankingFragment extends Fragment {
    ArrayList<Team> teams = new ArrayList<>();
    RecyclerView.Adapter<TeamAdapter.Holder> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getData();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        //teams.clear();
        //getData();
        adapter.notifyDataSetChanged();
    }

    public static TeamsRankingFragment newInstance() {
        return new TeamsRankingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_ranking, container, false);
        RecyclerView teamsRV = view.findViewById(R.id.rv_tr);
        teamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        teamsRV.setLayoutManager(manager);
         adapter= new TeamAdapter(teams,new ClickTeamListenner(){
            @Override
            public void onTeamClick(Team team){
                selectedTeam(team);
            }
        },getContext());
        teamsRV.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void getData() {
        getTeams();
    }

    private void getTeams() {
        Firebase.getInstance().db.collection("teams")
        .whereGreaterThan("rank", "0")
        .orderBy("rank")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Gson gson = new Gson();
                        String datatoString = gson.toJson(document.getData());
                        teams.add(gson.fromJson(datatoString, Team.class));
                    }
                } else {
                    Log.d("Error", "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void selectedTeam(Team team) {
        Intent selectedTeam = new Intent(getContext(), TeamActivity.class);
        Gson gson = new Gson();
        String teamToString = gson.toJson(team);
        selectedTeam.putExtra("team", teamToString);
        startActivity(selectedTeam);
    }
}