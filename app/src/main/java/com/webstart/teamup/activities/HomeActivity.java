package com.webstart.teamup.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;
import com.webstart.teamup.activities.money.SubscriptionActivity;
import com.webstart.teamup.activities.money.ShopActivity;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.activities.teams.TeamCreateActivity;
import com.webstart.teamup.fragments.AnnouncementFragment;
import com.webstart.teamup.fragments.ChatFragment;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.fragments.ShopFragment;
import com.webstart.teamup.fragments.team.TeamsFragment;
import com.webstart.teamup.fragments.TournamentFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Drawable white;
    Drawable orange;
    TeamsFragment tf = new TeamsFragment();
    AnnouncementFragment announcementFragment = AnnouncementFragment.newInstance();
    TournamentFragment tournamentFragment = new TournamentFragment();
    ChatFragment chatFragment = new ChatFragment();
    ShopFragment shopFragment = new ShopFragment();

    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        white = getDrawable(R.drawable.bottom_border_white);
        orange = getDrawable(R.drawable.bottom_border_light_orange_bg_light_purple);
        menu = findViewById(R.id.home_menu);
        //tf.ranking.getData();
        //tf.teams.getData();
        announcementFragment.getData();
        chatFragment.getData();

        if((Firebase.getInstance().getUser().getPictureProfil() != null)){
            Picasso.with(this).load(Firebase.getInstance().getUser().getPictureProfil()).into((ImageView) findViewById(R.id.home_picture));
        }

        setNavBar();
    }
    private void setNavBar() {
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.announces:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content, announcementFragment).commit();
                        return true;

                    case R.id.teams:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,tf).commit();
                        return true;

                    case R.id.tournaments:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,tournamentFragment).commit();
                        return true;

                    case R.id.chats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content, chatFragment).commit();
                        return true;

                    case R.id.shop:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,shopFragment).commit();
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
        Firebase.getInstance().teamsUser.clear();

    }


    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }
    //TeamListFragment
    public void createTeamForm(View view) {
        Intent createTeam = new Intent(this, TeamCreateActivity.class);
        startActivity(createTeam);
    }

    //TeamsFragment
    public void listRanking(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        myteam.setBackground(white);
        ranking.setBackground(orange);
        tf.getChildFragmentManager().beginTransaction().replace(R.id.team_content, tf.ranking).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.team_content, tf.ranking).commit();
    }
    public void listMyTeams(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        myteam.setBackground(orange);
        ranking.setBackground(white);
        tf.getChildFragmentManager().beginTransaction().replace(R.id.team_content, tf.teams).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.team_content,tf.teams).commit();
    }
    //ShopFragment
    public void goToSponsored(View view) {
        Intent shop =new Intent(this, ShopActivity.class);
        startActivity(shop);
    }
    public void goToSubscriptions(View view) {
        Intent sub =new Intent(this, SubscriptionActivity.class);
        startActivity(sub);
    }
    public void goToFeatures(View view) {
    }
    public void goToOthers(View view) {
    }
}