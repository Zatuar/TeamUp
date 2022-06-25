package com.webstart.teamup.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.AnnouncementActivity;
import com.webstart.teamup.adapters.AnnonceAdapter;
import com.webstart.teamup.fragments.team.TeamsFragment;
import com.webstart.teamup.interfaces.ClickAnnonceListener;
import com.webstart.teamup.models.Annonce;
import com.webstart.teamup.models.Jeu;
import com.webstart.teamup.models.ProfilMin;
import com.webstart.teamup.models.Team;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment {
    private ArrayList<Annonce> annonces = new ArrayList<>();

    public static AnnouncementFragment newInstance() {
        return new AnnouncementFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);
        RecyclerView annoncesRecycler = view.findViewById(R.id.announces_container);
        annoncesRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(annoncesRecycler.getContext());
        annoncesRecycler.setLayoutManager(manager);
        RecyclerView.Adapter<AnnonceAdapter.Holder> adapter = new AnnonceAdapter(annonces,new ClickAnnonceListener(){
            @Override
            public void onAnnonceClick(Annonce annonce){
                selectedAnnonce(annonce);
            }
        },getContext());
        annoncesRecycler.setAdapter(adapter);
        return view;
    }

    private void selectedAnnonce(Annonce annonce) {
        Intent selectedAnnonce = new Intent(getContext(), AnnouncementActivity.class);
        Gson gson = new Gson();
        String annonceToString = gson.toJson(annonce);
        selectedAnnonce.putExtra("annonce", annonceToString);
        startActivity(selectedAnnonce);
    }

    public void getData() {
        Team team;
        ArrayList<ProfilMin> members = new ArrayList<>();
        Jeu game;
        ArrayList<String> annonceIds = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            members.add(new ProfilMin("Membre #"+1, "", String.valueOf(i)));
            annonceIds.add("Annonce #"+i);
        }

        for (int i = 0; i < 5; i++) {
            game = new Jeu("Jeu #"+1, "", i);
            team = new Team("Team #"+i, "", "description", String.valueOf(i), i*10, i, members, game, annonceIds);
            annonces.add(new Annonce("Annonce #"+i, "Lorem ipsum dolor sit amet", String.valueOf(i), team.getName()));
        }
        showAnnonce(annonces);
    }

    private void showAnnonce(ArrayList<Annonce> annonces) {

    }
}