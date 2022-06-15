package com.webstart.teamup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsFragment extends Fragment {

    public TeamsFragment() {
        // Required empty public constructor
    }
    public static TeamsFragment newInstance(String param1, String param2) {
        TeamsFragment fragment = new TeamsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView NotesRV;
    private TeamAdapter adapter;
    private Structure_Team pgd;
    private Structure_Profil user;
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

    private void loadData() {
/*
        pgd.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Notes.clear();
                ArrayList<Structure_Team> notes= new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()) {
                    Structure_Team note = data.getValue(Structure_Team.class);
                    if (note != null) {
                        note.setKey(data.getKey());
                        if(note.getId_user() == user.getId()){
                            notes.add(note);
                        }
                    }
                }
                adapter.setItem(notes, item -> selectMe(item));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("INFO","error");

            }
        });*/
    }
}