package com.webstart.teamup;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Drawable white;
    Drawable orange;
    TeamsFragment tf = TeamsFragment.newInstance();


    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getUserProfil();
        white = getDrawable(R.drawable.bottom_border_white);
        orange = getDrawable(R.drawable.bottom_border_light_orange);
        menu = findViewById(R.id.home_menu);
        tf.ranking.getData();
        tf.teams.getData();

        setNavBar();
    }

    private void setNavBar() {
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.announces:
                        AnnouncementFragment announcementFragment = new AnnouncementFragment();
                        announcementFragment.getData();
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content, announcementFragment).commit();
                        return true;

                    case R.id.teams:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,tf).commit();
                        return true;

                    case R.id.tournaments:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new TournamentFragment()).commit();
                        return true;

                    case R.id.chats:
                        ChatFragment chatFragment = new ChatFragment();
                        chatFragment.getData();
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content, chatFragment).commit();
                        return true;

                    case R.id.shop:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new ShopFragment()).commit();
                        return true;
                }
                return true;
            }
        });
        menu.setSelectedItemId(R.id.teams);
    }

    @Override
    public void finish() {
        super.finish();
        // pop-up deconnexion
        Firebase.getInstance().getmAuth().signOut();
    }


    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }
    //TeamListFragment
    public void createTeamForm(View view) {
        Intent createTeam = new Intent(this,TeamCreateActivity.class);
        startActivity(createTeam);
    }

    //TeamsFragment
    public void listRanking(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        myteam.setBackground(white);
        ranking.setBackground(orange);
        TeamsRankingFragment t = new TeamsRankingFragment();
        //t.getData();
        getSupportFragmentManager().beginTransaction().replace(R.id.team_content, tf.ranking).commit();
    }

    public void listMyTeams(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        myteam.setBackground(orange);
        ranking.setBackground(white);
        TeamsListFragment t = new TeamsListFragment();
        //t.getData();
        getSupportFragmentManager().beginTransaction().replace(R.id.team_content,tf.teams).commit();
    }

}