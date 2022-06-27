package com.webstart.teamup.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.R;

public class ProfileEditActivity extends AppCompatActivity {
    EditText name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        name = this.findViewById(R.id.name);
        name.setText(Firebase.getInstance().getUser().getPseudo());
        description = this.findViewById(R.id.description);
        description.setText(Firebase.getInstance().getUser().getDescription());

        if((Firebase.getInstance().getUser().getPictureProfil() != null)){
            Picasso.with(this).load(Firebase.getInstance().getUser().getPictureProfil()).into((ImageView) findViewById(R.id.home_picture));
        }
    }


    public void editProfile(View view) {
        userModif();
        finish();
    }

    private void userModif() {
        String newPseudo = name.getText().toString();
        String newBio = description.getText().toString();
        Firebase.getInstance().getUser().setPseudo(newPseudo);
        Firebase.getInstance().getUser().setDescription(newBio);
        Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getUser().getId()).update("pseudo",newPseudo);
        Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getUser().getId()).update("description",newBio);

    }
}