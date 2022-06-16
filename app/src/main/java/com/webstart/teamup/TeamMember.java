package com.webstart.teamup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamMember extends RecyclerView.Adapter<TeamMember.Holder> {
    //    private ClickItemListenne listener;
    private List<Structure_Profil_Min> mItems;
    private Context context;
//    private PGDnote pgd;

    TeamMember(Context ctx, List<Structure_Profil_Min> l) {
        this.mItems = l;
        this.context = ctx;
//        this.pgd = pgd;
    }
    public void setItem(ArrayList<Structure_Profil_Min> notes) {
        this.mItems.addAll(notes);
//        this.listener = listener;
    }
    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        Holder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_team_members_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Structure_Profil_Min key = mItems.get(position);
        //holder.title.setText(key.getTitle());
        //holder.itemView.setOnClickListener(v -> listener.onItemClick(key));

    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}