package com.webstart.teamup.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;
import com.webstart.teamup.activities.HomeActivity;
import com.webstart.teamup.models.Profil;

public class ConnexionActivity extends AppCompatActivity {
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        email.setText("");
        password.setText("");
        //Firebase.getInstance().mAuth = FirebaseAuth.getInstance();

    }

    public void goToHome(View view) {
        String e,pw;
        e=email.getText().toString();
        pw=password.getText().toString();
        if(!(e.equals("") || pw.equals(""))){
            Firebase.getInstance().getmAuth().signInWithEmailAndPassword(e, pw)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Firebase.getInstance().setFBuser(Firebase.getInstance().getmAuth().getCurrentUser());
                            Log.d("Success", "signInWithEmail:success");
                            getUserProfil();
                        } else {
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ConnexionActivity.this, "Wrong email or password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }

    private void getUserProfil() {
        Intent home = new Intent(ConnexionActivity.this, HomeActivity.class);
        Firebase.getInstance().db.collection("users")
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
                                Firebase.getInstance().setUser(gson.fromJson(datatoString, Profil.class));
                                Firebase.getInstance().getUser().setId(document.getId());
                                //Log.d("Teams", document.getId() + " => " + datatoString);
                                //Log.d("TestGetUserTeams", document.getId() + " => " + Firebase.getInstance().getUser().getTeams());
                                //Log.d("UserTeamsID", " => " + Firebase.getInstance().User.getTeams());
                                //Log.d("UserEmail", " => " + Firebase.getInstance().User.getEmail());
                                //Log.d("UserPhone", " => " + Firebase.getInstance().User.getPhone());
                                startActivity(home);
                            }
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    public void goToSignUp(View view) {
        Intent signUp = new Intent(this,InscriptionActivity.class);
        startActivity(signUp);
    }

    public void goToGoogleConnexion(View view) {
    }
}