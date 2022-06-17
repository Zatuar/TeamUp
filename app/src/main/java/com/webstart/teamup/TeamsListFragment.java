package com.webstart.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TeamsListFragment extends Fragment {
    ArrayList<Structure_Team> teams = new ArrayList<>();
    private RecyclerView TeamsRV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_list, container, false);
        TeamsRV = view.findViewById(R.id.rv_tl);
        TeamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        TeamsRV.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new TeamAdapter(teams,new ClickTeamListenner(){
            @Override
            public void onTeamClick(Structure_Team team){
                selectedTeam(team);
            }
        },getContext());
        TeamsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    void getData() {
        //appelle API
        ArrayList<Structure_Profil_Min> members = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            members.add(new Structure_Profil_Min("Member "+i, "photo_url", i));
        }
        teams.add(new Structure_Team("Team A", "url_logo", 1, 1000, members, 1));
        showTeams(teams);
    }

    private void showTeams(ArrayList<Structure_Team> teams) {
        //
    }

    private void selectedTeam(Structure_Team team) {
        Intent selectedTeam = new Intent(getContext(),TeamActivity.class);
    }

    public void createTeamForm(View view) {
    }
}