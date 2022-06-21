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

    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        white = getDrawable(R.drawable.bottom_border_white);
        orange = getDrawable(R.drawable.bottom_border_light_orange);
        menu = findViewById(R.id.home_menu);
        setNavBar();
        getUserProfil();
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new TeamsFragment()).commit();
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

    private void getUserProfil() {
        Firebase.getInstance().db.collection("users")
                .whereEqualTo("email", Firebase.getInstance().getUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //transformation de la map en json pour récupérer les infos du user
                                Gson gson = new Gson();
                                String datatoString = gson.toJson(document.getData());
                                //Log.d("FirebaseClassTest", document.getId() + " => " + datatoString);
                                Firebase.getInstance().User = gson.fromJson(datatoString, Structure_Profil.class);
                                Firebase.getInstance().User.setId(document.getId());
                                //Log.d("UserId", " => " + Firebase.getInstance().User.getId());
                                //Log.d("UserEmail", " => " + Firebase.getInstance().User.getEmail());
                                //Log.d("UserPhone", " => " + Firebase.getInstance().User.getPhone());
                            }
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void goToProfile(View view) {
        Intent profil = new Intent(this,ProfilActivity.class);
        startActivity(profil);
    }

    public void createTeamForm(View view) {
        Intent createTeam = new Intent(this,TeamCreateActivity.class);
        startActivity(createTeam);
    }

    //TeamsFragment
    public void listRanking(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        //myteam.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.bottom_border_white)));
        myteam.setBackground(white);
        ranking.setBackground(orange);
        getSupportFragmentManager().beginTransaction().replace(R.id.team_content,new TeamsRankingFragment()).commit();
    }

    public void listMyTeams(View view) {
        TextView myteam = findViewById(R.id.my_teams);
        TextView ranking = findViewById(R.id.ranking);
        myteam.setBackground(orange);
        ranking.setBackground(white);
        TeamsListFragment t = new TeamsListFragment();
        t.getData();
        getSupportFragmentManager().beginTransaction().replace(R.id.team_content,t).commit();
    }
    //TeamsList
    public void createTeam(View view) {
    }
}