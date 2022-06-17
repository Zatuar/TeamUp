package com.webstart.teamup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionActivity extends AppCompatActivity {
    Firebase fb;
    EditText email,password;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
    }
    public void onStart() {
        super.onStart();
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }*/
        mAuth = FirebaseAuth.getInstance();
    }
    public void goToHome(View view) {
        String e,pw;
        e=email.getText().toString();
        pw=password.getText().toString();
        fb = Firebase.getInstance();
        Intent home = new Intent(this,HomeActivity.class);
        if(!(e.equals("") || pw.equals(""))){
            mAuth.signInWithEmailAndPassword(e, pw)
                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                //updateUI(user);
                                //user.reload();
                                //result = true;
                                Log.d("Success", "signInWithEmail:success");
                                startActivity(home);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Error", "signInWithEmail:failure", task.getException());
                                Toast.makeText(ConnexionActivity.this, "Wrong email or password",
                                        Toast.LENGTH_SHORT).show();
                                //result =false;
                                //updateUI(null);
                            }
                        }
                    });
        }
    }

    public void goToSignUp(View view) {
        //go to SignUp Page
        Intent signUp = new Intent(this,InscriptionActivity.class);
        startActivity(signUp);
    }

    public void goToGoogleConnexion(View view) {
    }
}