package com.webstart.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        TextView name = this.findViewById(R.id.name);
        name.setText(Firebase.getInstance().getUser().getPseudo());
        TextView description = this.findViewById(R.id.description);
        description.setText(Firebase.getInstance().getUser().getDescription());
    }


    public void goToEditProfile(View view) {
    }
}