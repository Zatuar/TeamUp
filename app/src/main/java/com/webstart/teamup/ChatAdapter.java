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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder> {
    private ArrayList<Structure_Chat> chatsList;
    private ClickChatListener clickChatListener;
    private Context context;

    public ChatAdapter(ArrayList<Structure_Chat> chatsList, ClickChatListener clickChatListener, Context context) {
        this.chatsList = chatsList;
        this.clickChatListener = clickChatListener;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_chats, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Structure_Chat key = chatsList.get(position);
        holder.other_user_name.setText(key.getOther_user().getName());
        holder.last_message.setText(key.getLast_message());
        holder.itemView.setOnClickListener(v -> clickChatListener.onChatClick(key));
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public void setItem(ArrayList<Structure_Chat> chats) {
        this.chatsList.addAll(chats);
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView other_user_pp;
        TextView other_user_name;
        TextView last_message;

        Holder(View itemView){
            super(itemView);
            other_user_pp = itemView.findViewById(R.id.other_user_pp);
            other_user_name = itemView.findViewById(R.id.other_user_name);
            last_message = itemView.findViewById(R.id.last_message);
        }
    }
}
