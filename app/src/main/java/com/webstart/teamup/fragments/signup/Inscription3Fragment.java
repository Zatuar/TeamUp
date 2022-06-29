package com.webstart.teamup.fragments.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.webstart.teamup.R;
import com.webstart.teamup.adapters.team.TeamFuturMember;
import com.webstart.teamup.interfaces.ClickRemoveMembers;

import java.util.ArrayList;

public class Inscription3Fragment extends Fragment {
    private Spinner spinner;
    ArrayList<String> games = new ArrayList<>();

    public Inscription3Fragment() {}

    public static Inscription3Fragment newInstance(String param1, String param2) {
        Inscription3Fragment fragment = new Inscription3Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        games.add("Counter Strike : Global Offensive");
        games.add("League Of Legends");
        games.add("Rainbow Six: Siege");
        games.add("Rocket League");
        games.add("Team Fortress 2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inscription3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.team_game);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, games);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
    }
}