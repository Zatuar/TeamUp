package com.webstart.teamup.fragments.team;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.webstart.teamup.R;
import com.webstart.teamup.adapters.team.TeamFuturMember;
import com.webstart.teamup.interfaces.ClickRemoveMembers;
import com.webstart.teamup.models.ProfilMin;

import java.util.ArrayList;

public class TeamCreate1Fragment extends Fragment {
    private Spinner spinner;
    ArrayList<String> games = new ArrayList<>();
    public ArrayList<ProfilMin> mates = new ArrayList<ProfilMin>();
    RecyclerView addedMates;
    public RecyclerView.Adapter<TeamFuturMember.Holder> adapterRemove;
    public TeamCreate1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 5; i++) {
            games.add("Jeu #"+i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_create1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.team_game);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, games);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addedMates = view.findViewById(R.id.added_mates);
        addedMates.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        addedMates.setLayoutManager(layoutManager);

        adapterRemove = new TeamFuturMember(mates,new ClickRemoveMembers(){
            @Override
            public void onRemoveMember(int position) {
                mates.remove(position);
                addedMates.removeViewAt(position);
                adapterRemove.notifyItemRemoved(position);
                adapterRemove.notifyItemRangeChanged(position,mates.size());
                adapterRemove.notifyDataSetChanged();
            }
        },view.getContext());
        addedMates.setAdapter(adapterRemove);
    }

}