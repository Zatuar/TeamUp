package com.webstart.teamup.fragments.team;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webstart.teamup.R;

public class TeamsFragment extends Fragment {
    public TeamsListFragment teams = new TeamsListFragment();
    public TeamsRankingFragment ranking = new TeamsRankingFragment();

    public static TeamsFragment newInstance() {
        return new TeamsFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.team_content, teams).commit();
        //teams.getData();
        //ranking.getData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}