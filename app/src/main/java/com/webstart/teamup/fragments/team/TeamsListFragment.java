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
    RecyclerView.Adapter<TeamAdapter.Holder> adapter;

    public static TeamsListFragment newInstance() {
        return new TeamsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return inflater.inflate(R.layout.fragment_teams_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView teamsRV = view.findViewById(R.id.rv_tl);
        teamsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        teamsRV.setLayoutManager(manager);
        adapter = new TeamAdapter(Firebase.getInstance().teamsUser, new ClickTeamListenner() {
            @Override
            public void onTeamClick(Team team) {
                selectedTeam(team);
            }
        }, getContext());
        teamsRV.setAdapter(adapter);
    }

    public void getData() {
        Log.d("TeamsName", " => " + Firebase.getInstance().getUser().getTeams());
        if(!Firebase.getInstance().getUser().getTeams().isEmpty()) {
            getTeamsUser();
        }
    }

    private void getTeamsUser() {
        Firebase.getInstance().db.collection("teams")
                .whereIn("id", Firebase.getInstance().getUser().getTeams())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //transformation de la map en json pour récupérer les infos du user
                                Gson gson = new Gson();
                                String datatoString = gson.toJson(document.getData());
                                Firebase.getInstance().teamsUser.add(gson.fromJson(datatoString, Team.class));
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