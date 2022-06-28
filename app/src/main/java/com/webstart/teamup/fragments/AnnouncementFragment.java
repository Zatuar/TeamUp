package com.webstart.teamup.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
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
        getAnnounces();
    }

    private void getAnnounces() {
        Firebase.getInstance().db.collection("annonces")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Gson gson = new Gson();
                        String datatoString = gson.toJson(document.getData());
                        Annonce annonce = gson.fromJson(datatoString, Annonce.class);
                        Log.i("ANNONCE", ""+annonce.getTeam());
                        if (annonce.getTeam().length() > 0){
                            Firebase.getInstance().db.collection("teams")
                            .whereEqualTo("id", annonce.getTeam())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Gson gson = new Gson();
                                            String datatoString = gson.toJson(document.getData());
                                            Team team = gson.fromJson(datatoString, Team.class);
                                            annonce.setTeam(team.getName());
                                        }
                                    }
                                }
                            });
                        }
                        annonces.add(annonce);
                    }
                } else {
                    Log.d("Error", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void showAnnonce(ArrayList<Annonce> annonces) {

    }
}