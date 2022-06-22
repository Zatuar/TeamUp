package com.webstart.teamup;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.net.URL;
import java.util.ArrayList;

public class TeamsRankingFragment extends Fragment {
    ArrayList<Structure_Team> teams = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TeamsRankingFragment newInstance(String param1, String param2) {
        TeamsRankingFragment fragment = new TeamsRankingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_ranking, container, false);
        RecyclerView teamsRV = view.findViewById(R.id.rv_tr);
        teamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        teamsRV.setLayoutManager(manager);
        RecyclerView.Adapter<TeamAdapter.Holder> adapter = new TeamAdapter(teams,new ClickTeamListenner(){
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
//        ArrayList<Structure_Profil_Min> members = new ArrayList<>();
//        ArrayList<String> annonceIds = new ArrayList<>();
//        Structure_Jeu game = new Structure_Jeu("Jeu 1", "url", 1);;
//        for (int i = 0; i < 5; i++) {
//            members.add(new Structure_Profil_Min("Member "+i, "photo_url", String.valueOf(i)));
//            annonceIds.add("Annonce #"+i);
//        }
//        teams.add(new Structure_Team("Team A", "url_logo", "description", String.valueOf(1), 1000, 1, members, game, annonceIds));
        getTeams();
        showTeams(teams);
    }

    private void getTeams() {
        Firebase.getInstance().db.collection("teams")
        .orderBy("rank")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
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