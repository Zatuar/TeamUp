package com.webstart.teamup.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;

public class ProfilActivity extends AppCompatActivity {
    TextView name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        name = this.findViewById(R.id.name);
        name.setText(Firebase.getInstance().getUser().getPseudo());
        description = this.findViewById(R.id.description);
        description.setText(Firebase.getInstance().getUser().getDescription());
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(Firebase.getInstance().getUser().getPseudo());
        description.setText(Firebase.getInstance().getUser().getDescription());
    }

    public void goToEditProfile(View view) {
        Intent profileEdit = new Intent(this,ProfileEditActivity.class);
        startActivity(profileEdit);
    }
}