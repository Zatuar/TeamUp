package com.webstart.teamup.fragments.team;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webstart.teamup.R;

public class TeamsFragment extends Fragment {
    public TeamsListFragment teams = TeamsListFragment.newInstance();
    public TeamsRankingFragment ranking = TeamsRankingFragment.newInstance();

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
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.team_content, teams).commit();
    }
}