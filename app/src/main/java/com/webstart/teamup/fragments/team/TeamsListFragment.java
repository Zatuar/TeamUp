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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.teams.TeamActivity;
import com.webstart.teamup.adapters.team.TeamAdapter;
import com.webstart.teamup.interfaces.ClickTeamListenner;
import com.webstart.teamup.models.Jeu;
import com.webstart.teamup.models.ProfilMin;
import com.webstart.teamup.models.Team;

import java.util.ArrayList;

public class TeamsListFragment extends Fragment {
    ArrayList<Team> teams = new ArrayList<>();
    RecyclerView teamsRV;
    RecyclerView.Adapter<TeamAdapter.Holder> adapter;

    public static TeamsListFragment newInstance() {
        return new TeamsListFragment();
    }
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_list, container, false);
        teamsRV= view.findViewById(R.id.rv_tl);
        teamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        teamsRV.setLayoutManager(manager);
        adapter = new TeamAdapter(teams,new ClickTeamListenner(){
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
        //appelle API
        ArrayList<ProfilMin> members = new ArrayList<>();
        ArrayList<String> annonceIds = new ArrayList<>();
        Jeu game = new Jeu("Jeu 1", "url", 1);;
        for (int i = 0; i < 5; i++) {
            members.add(new ProfilMin("Member "+i, "photo_url", String.valueOf(i)));
            annonceIds.add("Annonce #"+i);
        }
        //teams.add(new Structure_Team("Team A", "url_logo", "description", String.valueOf(1), 1000, 1, members, game, annonceIds));
        getTeamsUser();
    }

    private void getTeamsUser() {

        Log.d("TeamsName", " => " + Firebase.getInstance().getUser().getTeams());
        Firebase.getInstance().db.collection("teams")
                .whereIn("name", Firebase.getInstance().getUser().getTeams())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //transformation de la map en json pour récupérer les infos du user
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