package com.webstart.teamup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inscription1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inscription1Fragment extends Fragment {

    public Inscription1Fragment() {
        // Required empty public constructor
    }
    public static Inscription1Fragment newInstance(String param1, String param2) {
        Inscription1Fragment fragment = new Inscription1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //InscriptionActivity.profil.add()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inscription1, container, false);
    }
}