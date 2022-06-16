package com.webstart.teamup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TeamsListFragment extends Fragment {
    ArrayList<Structure_Team> teams = new ArrayList<>();
    private RecyclerView NotesRV;
    private TeamAdapter adapter;
    public TeamsListFragment() {
    }

    public static TeamsListFragment newInstance(String param1, String param2) {
        TeamsListFragment fragment = new TeamsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotesRV = getActivity().findViewById(R.id.rv_tl);
        getData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        NotesRV.setLayoutManager(manager);
        adapter = new TeamAdapter(getActivity(), teams);
        NotesRV.setAdapter(adapter);
    }

    private void getData() {
        ArrayList<Structure_Profil_Min> members = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            members.add(new Structure_Profil_Min("Member "+i, "photo_url", i));
        }
        teams.add(new Structure_Team("Team A", "url_logo", 1, 1000, members, 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_list, container, false);
    }
}