package com.webstart.teamup.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;

public class ProfileEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        TextView name = this.findViewById(R.id.name);
        name.setText(Firebase.getInstance().getUser().getPseudo());
        TextView description = this.findViewById(R.id.description);
        description.setText(Firebase.getInstance().getUser().getDescription());
    }


    public void editProfile(View view) {
    }
}