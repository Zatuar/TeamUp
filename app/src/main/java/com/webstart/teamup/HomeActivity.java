package com.webstart.teamup;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Structure_Profil users;
    BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setNavBar();
        getUserProfil();
    }

    private void setNavBar() {
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
        menu.setSelectedItemId(R.id.teams);
    }

    @Override
    public void finish() {
        super.finish();
        // pop-up deconnexion
        Firebase.getInstance().mAuth.signOut();
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
                                Log.d("User", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void goToProfile(View view) {
    }
}