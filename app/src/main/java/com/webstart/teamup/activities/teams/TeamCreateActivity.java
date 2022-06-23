package com.webstart.teamup.activities.teams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.profile.ProfilActivity;
import com.webstart.teamup.fragments.team.TeamCreate1Fragment;
import com.webstart.teamup.fragments.team.TeamCreate2Fragment;
import com.webstart.teamup.models.Annonce;
import com.webstart.teamup.models.Jeu;
import com.webstart.teamup.models.Profil;
import com.webstart.teamup.models.ProfilMin;
import com.webstart.teamup.models.Team;

import java.util.ArrayList;

public class TeamCreateActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction transaction;
    Team team = new Team();
    Jeu game = new Jeu();
    ArrayList<ProfilMin> members = new ArrayList<>();
    Annonce annonce = new Annonce();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_team_create, TeamCreate1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void goNext(View view) {
        transaction = manager.beginTransaction();
        EditText team_name = findViewById(R.id.team_name);
        Spinner team_game = findViewById(R.id.team_game);
        EditText team_member = findViewById(R.id.team_member);
        EditText team_bio = findViewById(R.id.team_description);
        if (!(team_name.getText().toString().equals("") || team_game.getSelectedItem().toString().equals(""))) {
            game.setName(team_game.getSelectedItem().toString());
            team.setName(team_name.getText().toString());
            team.setDescription(team_bio.getText().toString());
            team.setGame(game);
            addMembers();
            transaction.replace(R.id.fragment_team_create, TeamCreate2Fragment.class, null, "Page 2");
            transaction.setReorderingAllowed(true);
            transaction.addToBackStack("Page 1");
            transaction.commit();
        }
    }

    private void addMembers() {
        ArrayList<ProfilMin> members = new ArrayList<>();
        members.add(new ProfilMin(Firebase.getInstance().getUser().getPseudo(),Firebase.getInstance().getUser().getPictureProfil(),Firebase.getInstance().getUser().getId()));
        team.setMembers(members);
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }
    public void createTeam(View view){
        EditText annonce_title = findViewById(R.id.annonce_title);
        EditText annonce_body = findViewById(R.id.annonce_body);

        annonce.setTitle(annonce_title.getText().toString());
        annonce.setBody(annonce_body.getText().toString());
        annonce.setTeam(team.getName());
        team.setScore(0);
        //Firebase.getInstance().db.collection("annonces").document().set(annonce);
        Firebase.getInstance().teamsUser.add(team);
        Firebase.getInstance().getUser().getTeams().add(team.getName());
        Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getUser().getId()).update("teams",Firebase.getInstance().getUser().getTeams());
        Firebase.getInstance().db.collection("teams").document(team.getName()).set(team);
        finish();
    }

    private void getAnnounce() {
        Firebase.getInstance().db.collection("annonces")
                .whereEqualTo("email", Firebase.getInstance().getFBuser().getEmail())
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
                                Firebase.getInstance().setUser(gson.fromJson(datatoString, Profil.class));
                                Firebase.getInstance().getUser().setId(document.getId());
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

}