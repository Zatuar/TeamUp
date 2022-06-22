package com.webstart.teamup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.Holder> {
    private ClickTeamListenner listener;
    private List<Structure_Team> teamsList;
    private Context context;

    TeamAdapter( List<Structure_Team> l,ClickTeamListenner listener,Context ctx) {
        this.teamsList = l;
        this.context = ctx;
        this.listener = listener;
    }
    static class Holder extends RecyclerView.ViewHolder {
        TextView team_rank;
        TextView team_name;
        TextView team_score;
        RecyclerView team_list_members_recycler;

        Holder(View itemView){
            super(itemView);
            team_rank = itemView.findViewById(R.id.team_rank);
            team_name = itemView.findViewById(R.id.team_name);
            team_score = itemView.findViewById(R.id.team_score);
            team_list_members_recycler = itemView.findViewById(R.id.team_list_members_recycler);
            team_list_members_recycler.setHasFixedSize(true);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_teams_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Structure_Team key = teamsList.get(position);
        holder.team_name.setText(key.getName());
        holder.team_score.setText(""+key.getScore()+"pts");
        holder.team_rank.setText("#"+key.getRank());
        holder.itemView.findViewById(R.id.team_header).setOnClickListener(v -> listener.onTeamClick(key));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
        holder.team_list_members_recycler.setLayoutManager(layoutManager);
        RecyclerView.Adapter<TeamListMemberAdapter.Holder> adapter = new TeamListMemberAdapter(key.getMembers(),new ClickMemberListener(){
            @Override
            public void onMemberClick(Structure_Profil_Min member) {
                selectedMember(member);
            }
        },this.context);
        holder.team_list_members_recycler.setAdapter(adapter);
    }

    private void selectedMember(Structure_Profil_Min member) {
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }
}