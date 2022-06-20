package com.webstart.teamup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamsRankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsRankingFragment extends Fragment {

    public TeamsRankingFragment() {
        // Required empty public constructor
    }

    public static TeamsRankingFragment newInstance(String param1, String param2) {
        TeamsRankingFragment fragment = new TeamsRankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_ranking, container, false);
    }
}