package com.webstart.teamup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    private ArrayList<Structure_Chat> chats = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView chatsRecycler = view.findViewById(R.id.chats_container);
        chatsRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(chatsRecycler.getContext());
        chatsRecycler.setLayoutManager(manager);
        RecyclerView.Adapter<ChatAdapter.Holder> adapter = new ChatAdapter(chats,new ClickChatListener(){
            @Override
            public void onChatClick(Structure_Chat chat) {
                selectedChat(chat);
            }
        },getContext());
        chatsRecycler.setAdapter(adapter);
        return view;
    }

    private void selectedChat(Structure_Chat chat) {
        Intent selectedChat = new Intent(getContext(),ChatActivity.class);
        startActivity(selectedChat);
    }

    void getData() {
        Structure_Profil_Min other_user = new Structure_Profil_Min();

        for (int i = 0; i < 2; i++) {
            other_user = new Structure_Profil_Min("User #"+1, "", String.valueOf(i));
        }

        for (int i = 0; i < 5; i++) {
            chats.add(new Structure_Chat(1, "Message de test", "19/06/2022 15h20", other_user));
        }
        showChat(chats);
    }

    private void showChat(ArrayList<Structure_Chat> chats) {
        //
    }
}