package com.webstart.teamup.activities.auth;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.webstart.teamup.Firebase;
import com.webstart.teamup.activities.HomeActivity;
import com.webstart.teamup.activities.teams.TeamCreateActivity;
import com.webstart.teamup.fragments.signup.Inscription1Fragment;
import com.webstart.teamup.fragments.signup.Inscription2Fragment;
import com.webstart.teamup.fragments.signup.Inscription3Fragment;
import com.webstart.teamup.R;
import com.webstart.teamup.models.Abonnement;
import com.webstart.teamup.models.Jeu;
import com.webstart.teamup.models.Profil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {
    int SELECT_PICTURE = 200;
    FragmentManager manager;
    FragmentTransaction transaction;
    Profil profil = new Profil();
    String pw;
    String games;
    Uri selectedImageUri = null;

    ShapeableImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_inscription, Inscription1Fragment.class,null,"Page 1");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void goNext(View view) {
        String pageNumber = view.getTag().toString();
        transaction = manager.beginTransaction();

        switch (pageNumber) {
            case "2" :
                EditText verifemail = findViewById(R.id.edit_email);
                EditText verifpw = findViewById(R.id.edit_pw);
                EditText verifphone = findViewById(R.id.edit_phone);
                if(!(verifemail.getText().toString().equals("") || verifpw.getText().toString().equals("")
                        || verifphone.getText().toString().equals("")) && isEmailValid(verifemail.getText().toString())) {
                    pw = verifpw.getText().toString();
                    profil.setEmail(verifemail.getText().toString());
                    profil.setPhone(verifphone.getText().toString());
                    transaction.replace(R.id.fragment_inscription, Inscription2Fragment.class, null, "Page 2");
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack("Page 1");
                    transaction.commit();
                }
                break;
            case "3" :
                EditText pseudo = findViewById(R.id.edit_name);
                EditText bio = findViewById(R.id.edit_description);
                if(!(pseudo.getText().toString().equals("")))  {
                    profil.setPseudo(pseudo.getText().toString());
                    profil.setDescription(bio.getText().toString());
                    transaction.replace(R.id.fragment_inscription, Inscription3Fragment.class, null, "Page 3");
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack("Page 2");
                    transaction.commit();
            }
                break;
        }
    }

    public void addGame(){

    }

    public void goToHome(View view) {
        EditText selectGame = findViewById(R.id.edit_game);
        if(!selectGame.getText().toString().equals("")) {
            signUp();
        }
    }

    public void selectPictureProfil(View view) {
        selectedImage = findViewById(R.id.profil);
        Intent selector = new Intent(Intent.ACTION_GET_CONTENT);
        selector.setType("image/*");
        startActivityForResult(Intent.createChooser(selector, "Select Picture"), SELECT_PICTURE);/**/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                if (data.getData() != null) {
                    selectedImageUri = data.getData();
                    selectedImage.setImageURI(selectedImageUri);
                    //profil.setPictureProfil(selectedImageUri.getLastPathSegment());
                }
            }
        }
    }

    private void actionForImageProfil(){
        if(selectedImageUri != null){
            StorageReference pictureProfil = Firebase.getInstance().storage.getReference().child("pictureTeam/"+profil.getId());
            UploadTask uploadTask = pictureProfil.putFile(selectedImageUri);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String uploadedImageUrl = task.getResult().toString();
                            //Ajouter l'image de l'équipe aux données locales du User
                            profil.setPictureProfil(uploadedImageUrl);
                            //Ajouter l'Image dans l'equipe de Firebase
                            Firebase.getInstance().db.collection("users").document(profil.getId()).update("profilPicture",profil.getPictureProfil());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(InscriptionActivity.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void signUp() {
        Intent home = new Intent(this, HomeActivity.class);
        Firebase.getInstance().getmAuth().createUserWithEmailAndPassword(profil.getEmail(), pw)
                .addOnCompleteListener(InscriptionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SUCESS", "createUserWithEmail:success");
                            //Stocker le FireBase User
                            Firebase.getInstance().setFBuser(Firebase.getInstance().getmAuth().getCurrentUser());
                            //stocker les infos saisie du User
                            profil.setId(Firebase.getInstance().getmAuth().getUid());
                            profil.setGames(new ArrayList<Jeu>());
                            profil.setAbonnements(new ArrayList<Abonnement>());
                            //profil.setPictureProfil("");
                            profil.setTeams(new ArrayList<>());
                            //Stocker dans Firebase des infos du User
                            if(selectedImageUri != null){
                                StorageReference pictureProfil = Firebase.getInstance().storage.getReference().child("pictureProfil/"+profil.getId());
                                UploadTask uploadTask = pictureProfil.putFile(selectedImageUri);

                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                String uploadedImageUrl = task.getResult().toString();
                                                profil.setPictureProfil(uploadedImageUrl);
                                                Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getFBuser().getUid()).set(profil);
                                                //Passer des infos du User en global
                                                Firebase.getInstance().setUser(profil);
                                                //transaction.remove("Page 3");
                                                startActivity(home);
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(InscriptionActivity.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Firebase.getInstance().db.collection("users").document(Firebase.getInstance().getFBuser().getUid()).set(profil);
                                //Passer des infos du User en global
                                Firebase.getInstance().setUser(profil);
                                startActivity(home);
                            }
                        } else {
                            Log.w("SUCESS", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionActivity.this, "addresse e-mail déjà utilisé",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean isEmailValid = matcher.matches();

        return isEmailValid;
    }
}