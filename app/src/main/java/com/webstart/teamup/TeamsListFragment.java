package com.webstart.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamsListFragment extends Fragment {
    ArrayList<Structure_Team> teams = new ArrayList<>();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_list, container, false);
        teamsRV= view.findViewById(R.id.rv_tl);
        teamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        teamsRV.setLayoutManager(manager);
        adapter = new TeamAdapter(teams,new ClickTeamListenner(){
            @Override
            public void onTeamClick(Structure_Team team){
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

    void getData() {
        //appelle API
        ArrayList<Structure_Profil_Min> members = new ArrayList<>();
        ArrayList<String> annonceIds = new ArrayList<>();
        Structure_Jeu game = new Structure_Jeu("Jeu 1", "url", 1);;
        for (int i = 0; i < 5; i++) {
            members.add(new Structure_Profil_Min("Member "+i, "photo_url", String.valueOf(i)));
            annonceIds.add("Annonce #"+i);
        }
        //teams.add(new Structure_Team("Team A", "url_logo", "description", String.valueOf(1), 1000, 1, members, game, annonceIds));
        getTeamsUser();
        showTeams(teams);
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
                                teams.add(gson.fromJson(datatoString, Structure_Team.class));

                            }
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void showTeams(ArrayList<Structure_Team> teams) {
        //
    }

    private void selectedTeam(Structure_Team team) {
        Intent selectedTeam = new Intent(getContext(),TeamActivity.class);
        Gson gson = new Gson();
        String teamToString = gson.toJson(team);
        selectedTeam.putExtra("team", teamToString);
        startActivity(selectedTeam);
    }
}