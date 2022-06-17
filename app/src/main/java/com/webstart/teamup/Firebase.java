package com.webstart.teamup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class Firebase extends AppCompatActivity {
    FirebaseUser user;
    boolean result= true;

    private final FirebaseAuth mAuth;
    private static final Firebase FB = new Firebase();

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

    public boolean signIn(String e, String pw, Context ct) {
        mAuth.signInWithEmailAndPassword(e, pw)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            //updateUI(user);
                            //user.reload();
                            result = true;
                            Log.d("Success", "signInWithEmail:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ct, "Wrong email or password",
                                    Toast.LENGTH_SHORT).show();
                            result =false;
                            //updateUI(null);
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
}
