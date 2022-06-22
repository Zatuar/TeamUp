package com.webstart.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment {
    private ArrayList<Structure_Annonce> annonces = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            public void onAnnonceClick(Structure_Annonce annonce){
                selectedAnnonce(annonce);
            }
        },getContext());
        annoncesRecycler.setAdapter(adapter);
        return view;
    }

    private void selectedAnnonce(Structure_Annonce annonce) {
        Intent selectedAnnonce = new Intent(getContext(),AnnouncementActivity.class);
        startActivity(selectedAnnonce);
    }

    void getData() {
        Structure_Team team;
        ArrayList<Structure_Profil_Min> members = new ArrayList<>();
        Structure_Jeu game;
        ArrayList<String> annonceIds = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            members.add(new Structure_Profil_Min("Membre #"+1, "", String.valueOf(i)));
            annonceIds.add("Annonce #"+i);
        }

        for (int i = 0; i < 5; i++) {
            game = new Structure_Jeu("Jeu #"+1, "", i);
            team = new Structure_Team("Team #"+i, "", "description", String.valueOf(i), i*10, i, members, game, annonceIds);
            annonces.add(new Structure_Annonce("Annonce #"+i, "Lorem ipsum dolor sit amet", String.valueOf(i), team.getName()));
        }
        showAnnonce(annonces);
    }

    private void showAnnonce(ArrayList<Structure_Annonce> annonces) {
        //
    }
}