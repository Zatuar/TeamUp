package com.webstart.teamup;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.webstart.teamup.models.Profil;
import com.webstart.teamup.models.Team;

import java.util.ArrayList;

public class Firebase extends AppCompatActivity {

    private static final Firebase FB = new Firebase();
    boolean result;

    private Profil User ;
    public ArrayList<Team> teamsUser;
    private FirebaseUser FBuser;
    private FirebaseAuth mAuth;

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://teamup-57580.appspot.com");
    StorageReference storageRef = storage.getReference();
    public final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Profil getUser() {
        return User;
    }

    public void setUser(Profil user) {
        User = user;
    }
    public FirebaseUser getFBuser() {
        return FBuser;
    }
    public void setFBuser(FirebaseUser FBuser) {
        this.FBuser = FBuser;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public static Firebase getInstance(){
        return  FB;
    }
    private Firebase() {
        mAuth = FirebaseAuth.getInstance();
        User = new Profil();
        teamsUser = new ArrayList<>();
    }

    public boolean signIn(String e, String pw, Intent ct) {
        mAuth.signInWithEmailAndPassword(e, pw)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            result = true;
                        } else {
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Wrong email or password",
                                    Toast.LENGTH_SHORT).show();
                            result =false;
                        }
                    }
                });
        return result;
    }
    public Boolean signUp(String e, String pw, Context ct){
        final Boolean[] result = {false};
        mAuth.createUserWithEmailAndPassword(e, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCESS", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            result[0]=true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SUCESS", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ct, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        return result[0];
    }
    public void setData(String collection, Object obj){
        db.collection(collection)
                .add(obj)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Failure", "Error adding document", e);
                    }
                });
    }
}
