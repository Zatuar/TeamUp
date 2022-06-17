package com.webstart.teamup;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionActivity extends AppCompatActivity {
    Firebase fb = Firebase.getInstance();
    EditText email,password;
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
    }
    public void goToHome(View view) {
        String e,pw;
        e=email.getText().toString();
        pw=password.getText().toString();
        if(!(e.equals("") || pw.equals(""))){
            if(fb.signIn(e,pw, this)){
                Toast.makeText(this, "Connection",
                        Toast.LENGTH_SHORT).show();
                Intent home = new Intent(this,HomeActivity.class);
                startActivity(home);
            }

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