package com.webstart.teamup.adapters.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webstart.teamup.R;
import com.webstart.teamup.interfaces.ClickRemoveMembers;
import com.webstart.teamup.models.ProfilMin;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamFuturMember extends RecyclerView.Adapter<TeamFuturMember.Holder> {
    private ClickRemoveMembers listener;
    private ArrayList<ProfilMin> teamsList;
    private Context context;

    public TeamFuturMember(ArrayList<ProfilMin> l, ClickRemoveMembers listener, Context ctx) {
        this.teamsList = l;
        this.context = ctx;
        this.listener = listener;
    }
    public static class Holder extends RecyclerView.ViewHolder {
        TextView mate;
        ImageView remove;

        Holder(View itemView){
            super(itemView);
            mate = itemView.findViewById(R.id.futur_name_member);
            remove = itemView.findViewById(R.id.remove_mate);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_teams_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamFuturMember.Holder holder, int position) {
        final ProfilMin key = teamsList.get(position);
        holder.mate.setText(key.getName());
        holder.remove.findViewById(R.id.team_header).setOnClickListener(v -> listener.onRemoveMember(position));
    }
    @Override
    public int getItemCount() {
        return teamsList.size();
    }
}
