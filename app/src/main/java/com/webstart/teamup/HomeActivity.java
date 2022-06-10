package com.webstart.teamup;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu = findViewById(R.id.home_menu);
        menu.setOnNavigationItemSelectedListener(this);
        menu.setSelectedItemId(R.id.teams);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.announces:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_content, new SwipeFragment()).commit();
                return true;

            case R.id.teams:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new TeamsFragment()).commit();
                return true;

            case R.id.tournaments:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new TournamentFragment()).commit();
                return true;

            case R.id.chats:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new ChatFragment()).commit();
                return true;

            case R.id.shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_content,new ShopFragment()).commit();
                return true;
        }
        return false;
    }

}