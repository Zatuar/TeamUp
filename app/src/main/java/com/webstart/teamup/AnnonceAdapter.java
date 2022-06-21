package com.webstart.teamup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.Holder>  {
    private ArrayList<Structure_Annonce> annoncesList;
    private ClickAnnonceListener clickAnnonceListener;
    private Context context;

    public AnnonceAdapter(ArrayList<Structure_Annonce> annoncesList, ClickAnnonceListener clickAnnonceListener, Context context) {
        this.annoncesList = annoncesList;
        this.clickAnnonceListener = clickAnnonceListener;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_annonces, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Structure_Annonce key = annoncesList.get(position);
        holder.annonce_title.setText(key.getTitle());
        holder.annonce_team_name.setText(key.getTeam());
        holder.annonce_body.setText(key.getBody());
        holder.itemView.setOnClickListener(v -> clickAnnonceListener.onAnnonceClick(key));
    }

    @Override
    public int getItemCount() {
        return annoncesList.size();
    }

    public void setItem(ArrayList<Structure_Annonce> annonces) {
        this.annoncesList.addAll(annonces);
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView annonce_title;
        ImageView annonce_team_logo;
        TextView annonce_team_name;
        TextView annonce_body;

        Holder(View itemView){
            super(itemView);
            annonce_title = itemView.findViewById(R.id.annonce_title);
            annonce_team_logo = itemView.findViewById(R.id.annonce_team_logo);
            annonce_team_name = itemView.findViewById(R.id.annonce_team_name);
            annonce_body = itemView.findViewById(R.id.annonce_body);
        }
    }
}
