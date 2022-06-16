package com.webstart.teamup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.Holder> {
//    private ClickItemListenne listener;
    private List<Structure_Team> mItems;
    private Context context;
//    private PGDnote pgd;

    TeamAdapter(Context ctx, List<Structure_Team> l) {
        this.mItems = l;
        this.context = ctx;
//        this.pgd = pgd;
    }
    public void setItem(ArrayList<Structure_Team> notes) {
        this.mItems.addAll(notes);
//        this.listener = listener;
    }
    static class Holder extends RecyclerView.ViewHolder {
        TextView team_rank;
        TextView team_name;
        TextView team_score;

        Holder(View itemView){
            super(itemView);
            team_rank = itemView.findViewById(R.id.team_rank);
            team_name = itemView.findViewById(R.id.team_name);
            team_score = itemView.findViewById(R.id.team_score);
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
        final Structure_Team key = mItems.get(position);
        //holder.title.setText(key.getTitle());
        holder.team_rank.setText(key.getRank());
        holder.team_name.setText(key.getName());
        holder.team_score.setText(key.getScore());
        //holder.itemView.setOnClickListener(v -> listener.onItemClick(key));
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}