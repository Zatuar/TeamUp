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

public class Firebase extends AppCompatActivity {

    boolean result;

    private FirebaseUser user;
    FirebaseAuth mAuth;
    Structure_Profil User = new Structure_Profil();
    private static final Firebase FB = new Firebase();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirebaseUser getUser() {
        return user;
    }
    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public static Firebase getInstance(){
        return  FB;
    }
    private Firebase() {
        mAuth = FirebaseAuth.getInstance();
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
