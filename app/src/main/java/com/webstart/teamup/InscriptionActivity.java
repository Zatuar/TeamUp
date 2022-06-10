package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InscriptionActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_inscription,Inscription1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void goNext(View view) {
        String pageNumber = view.getTag().toString();
        transaction = manager.beginTransaction();

        switch (pageNumber) {
            case "2" :
                transaction.replace(R.id.fragment_inscription,Inscription2Fragment.class,null, "Page 2");
                transaction.setReorderingAllowed(true);
                transaction.addToBackStack("Page 1");
                transaction.commit();
                break;
            case "3" :
                transaction.replace(R.id.fragment_inscription,Inscription3Fragment.class,null, "Page 3");
                transaction.setReorderingAllowed(true);
                transaction.addToBackStack("Page 2");
                transaction.commit();
                break;
        }
    }

    public void addGame(){

    }

    public void goToHome(View view) {
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
    }
}