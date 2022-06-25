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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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
    //ArrayList<ProfilMin> members = new ArrayList<>();
    Annonce annonce = null;
    Profil temp;
    TeamCreate1Fragment tc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        //ajout par défaut du User dans les joueurs de l'equipe
        //members.add(new ProfilMin(Firebase.getInstance().getUser().getPseudo(),Firebase.getInstance().getUser().getPictureProfil(),Firebase.getInstance().getUser().getId()));
        tc1 = new TeamCreate1Fragment();
        tc1.mates.add(new ProfilMin(Firebase.getInstance().getUser().getPseudo(),Firebase.getInstance().getUser().getPictureProfil(),Firebase.getInstance().getUser().getId()));
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_team_create, TeamCreate1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void addMates(View view) {
        EditText team_member = findViewById(R.id.team_member);
        if(notInTeam(team_member.getText().toString())) {
            Firebase.getInstance().db.collection("users")
                    .whereEqualTo("pseudo", team_member.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //transformation de la map en json pour récupérer les infos du user
                                    Gson gson = new Gson();
                                    String datatoString = gson.toJson(document.getData());
                                    temp = gson.fromJson(datatoString, Profil.class);
                                    tc1.mates.add(new ProfilMin(temp.getPseudo(), temp.getPictureProfil(), temp.getId()));
                                    Toast.makeText(TeamCreateActivity.this, temp.getPseudo() + " a bien été ajouté à votre équipe", Toast.LENGTH_SHORT).show();
                                    //tc1.adapterRemove.notifyItemRangeChanged(tc1.mates.size(),tc1.mates.size());
                                    //tc1.adapterRemove.notifyDataSetChanged();

                                }
                            } else {
                                Log.d("Error", "Error getting documents: ", task.getException());
                                Toast.makeText(TeamCreateActivity.this, "Ce joueur n'existe pas, essayez à nouveau", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        team_member.setText("");
    }

    private boolean notInTeam(String s) {
        for (ProfilMin pm: tc1.mates) {
            if(pm.getName().equals(s))return false;
        }
        return true;
    }

    public void goNext(View view) {
        transaction = manager.beginTransaction();
        EditText team_name = findViewById(R.id.team_name);
        Spinner team_game = findViewById(R.id.team_game);
        EditText team_member = findViewById(R.id.team_member);
        EditText team_bio = findViewById(R.id.team_description);
        if (!team_name.getText().toString().equals("")) {
            game.setName(team_game.getSelectedItem().toString());
            team.setName(team_name.getText().toString());
            team.setDescription(team_bio.getText().toString());
            team.setGame(game);
            team.setMembers(tc1.mates);
            transaction.replace(R.id.fragment_team_create, TeamCreate2Fragment.class, null, "Page 2");
            transaction.setReorderingAllowed(true);
            transaction.addToBackStack("Page 1");
            transaction.commit();
        }
    }

    public void goToProfile(View view) {
        Intent profil = new Intent(this, ProfilActivity.class);
        startActivity(profil);
    }

    public void createTeam(View view){
        EditText annonce_title = findViewById(R.id.annonce_title);
        EditText annonce_body = findViewById(R.id.annonce_body);
        //initiallisation des valeur de l'équipe
        team.setRank(-1);
        team.setScore(0);
        //set info annonce
        team.setAnnonceIds(new ArrayList<>());
        if(!annonce_title.getText().toString().equals("")) {
            annonce = new Annonce();
            annonce.setTitle(annonce_title.getText().toString());
            annonce.setBody(annonce_body.getText().toString());
            actionForAnnonce();
        }
        else {
            actionForTeam();
        }
        finish();
    }

    private void actionForTeam() {
        Firebase.getInstance().db.collection("teams").add(team)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Success", "DocumentSnapshot written with ID: " + documentReference.getId());
                        team.setId(documentReference.getId());
                        //Ajouter l'ID dans l'équipe de Firebase
                        Firebase.getInstance().db.collection("teams").document(team.getId()).update("id",team.getId());
                        //Ajouter l'ID de l'equipe aux données locales du User
                        Firebase.getInstance().getUser().getTeams().add(team.getId());
                        //Ajouter l'équipe à la liste local des équipes du Users
                        Firebase.getInstance().teamsUser.add(team);
                        //Ajouter l'ID de l'équipe aux données du User de Firebase
                        for (ProfilMin pm:team.getMembers()) {
                            Firebase.getInstance().db.collection("users").document(pm.getId()).update("teams",Firebase.getInstance().getUser().getTeams());
                        }
                        //Ajouter l'ID de l'équipe à l'annonce dans FireBase s'il y a une annonce
                        if(annonce != null) {
                            annonce.setTeam(team.getId());
                            Firebase.getInstance().db.collection("annonces").document(annonce.getId()).update("team", annonce.getTeam());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error", "Error adding document", e);
                    }
                });
    }

    private void actionForAnnonce() {
        Firebase.getInstance().db.collection("annonces").add(annonce)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Success", "DocumentSnapshot written with ID: " + documentReference.getId());
                        annonce.setId(documentReference.getId());
                        //Update Annonce
                        Firebase.getInstance().db.collection("annonces").document(annonce.getId()).update("id",annonce.getId());
                        //recupération de l'ID de l'annonce
                        team.getAnnonceIds().add(annonce.getId());
                        actionForTeam();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error", "Error adding document", e);
                    }
                });
    }

}