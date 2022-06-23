package com.webstart.teamup.adapters.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webstart.teamup.interfaces.ClickMemberListener;
import com.webstart.teamup.R;
import com.webstart.teamup.models.ProfilMin;

import java.util.ArrayList;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.Holder> {
    private ArrayList<ProfilMin> membersList;
    private ClickMemberListener clickMemberListener;
    private Context context;

    public TeamMemberAdapter(ArrayList<ProfilMin> membersList, ClickMemberListener clickMemberListener, Context context) {
        this.membersList = membersList;
        this.clickMemberListener = clickMemberListener;
        this.context = context;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView member_name;
        ImageView member_logo;

        Holder(View itemView){
            super(itemView);
            member_name = itemView.findViewById(R.id.member_name);
            member_logo = itemView.findViewById(R.id.member_logo);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_team_members, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ProfilMin key = membersList.get(position);
        holder.member_name.setText(key.getName());
        holder.itemView.setOnClickListener(v -> clickMemberListener.onMemberClick(key));
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }
}
