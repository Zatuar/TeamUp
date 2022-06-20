package com.webstart.teamup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inscription3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inscription3Fragment extends Fragment {

    public Inscription3Fragment() {
        // Required empty public constructor
    }

    public static Inscription3Fragment newInstance(String param1, String param2) {
        Inscription3Fragment fragment = new Inscription3Fragment();
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
        return inflater.inflate(R.layout.fragment_inscription3, container, false);
    }

    public void addGame(View view) {
        //ajouter le jeu à la liste de l'utilisateur et vider l'EditText
    }
}