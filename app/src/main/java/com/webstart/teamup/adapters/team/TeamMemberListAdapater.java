package com.webstart.teamup.adapters.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webstart.teamup.R;
import com.webstart.teamup.models.ProfilMin;

import java.util.ArrayList;
import java.util.List;

public class TeamMemberListAdapater extends RecyclerView.Adapter<TeamMemberListAdapater.Holder> {
    //    private ClickItemListenne listener;
    private List<ProfilMin> mItems;
    private Context context;
//    private PGDnote pgd;

    TeamMemberListAdapater(Context ctx, List<ProfilMin> l) {
        this.mItems = l;
        this.context = ctx;
//        this.pgd = pgd;
    }
    public void setItem(ArrayList<ProfilMin> notes) {
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
        final ProfilMin key = mItems.get(position);
        //holder.title.setText(key.getTitle());
        //holder.itemView.setOnClickListener(v -> listener.onItemClick(key));

    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}