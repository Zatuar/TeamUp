package com.webstart.teamup.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webstart.teamup.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TournamentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TournamentFragment extends Fragment {

    public TournamentFragment() {
        // Required empty public constructor
    }

    public static TournamentFragment newInstance(String param1, String param2) {
        TournamentFragment fragment = new TournamentFragment();
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
        return inflater.inflate(R.layout.fragment_tournament, container, false);
    }
}