package com.webstart.teamup;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu = findViewById(R.id.home_menu);
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
        menu.setSelectedItemId(R.id.announces);
    }

    public void goToProfile(View view) {
    }
}