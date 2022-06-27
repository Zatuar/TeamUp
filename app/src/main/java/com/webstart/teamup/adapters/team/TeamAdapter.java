package com.webstart.teamup.adapters.team;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.interfaces.ClickMemberListener;
import com.webstart.teamup.interfaces.ClickTeamListenner;
import com.webstart.teamup.R;
import com.webstart.teamup.models.ProfilMin;
import com.webstart.teamup.models.Team;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.Holder> {
    private ClickTeamListenner listener;
    private List<Team> teamsList;
    private Context context;

    public TeamAdapter(List<Team> l, ClickTeamListenner listener, Context ctx) {
        this.teamsList = l;
        this.context = ctx;
        this.listener = listener;
    }
    public static class Holder extends RecyclerView.ViewHolder {
        TextView team_rank;
        TextView team_name;
        TextView team_score;
        ImageView team_logo;
        RecyclerView team_list_members_recycler;

        Holder(View itemView){
            super(itemView);
            team_rank = itemView.findViewById(R.id.team_rank);
            team_name = itemView.findViewById(R.id.team_name);
            team_score = itemView.findViewById(R.id.team_score);
            team_logo = itemView.findViewById(R.id.team_logo);
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
        final Team key = teamsList.get(position);
        holder.team_name.setText(key.getName());
        holder.team_score.setText(""+key.getScore()+"pts");
        holder.team_rank.setText("#"+key.getRank());
        if((key.getLogo() != null)){
            Picasso.with(context).load(key.getLogo()).into(holder.team_logo);
        }
        holder.itemView.findViewById(R.id.team_header).setOnClickListener(v -> listener.onTeamClick(key));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
        holder.team_list_members_recycler.setLayoutManager(layoutManager);
        RecyclerView.Adapter<TeamListMemberAdapter.Holder> adapter = new TeamListMemberAdapter(key.getMembers(),new ClickMemberListener(){
            @Override
            public void onMemberClick(ProfilMin member) {
                selectedMember(member);
            }
        },this.context);
        holder.team_list_members_recycler.setAdapter(adapter);
    }

    private void selectedMember(ProfilMin member) {
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }
}