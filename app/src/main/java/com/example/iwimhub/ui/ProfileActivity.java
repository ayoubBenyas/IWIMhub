package com.example.iwimhub.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView displayNameView, emailView;
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        displayNameView = findViewById(R.id.displayName);
        emailView = findViewById(R.id.email);
        photoView = findViewById(R.id.photo);

        user = mAuth.getCurrentUser();

        if( user != null) {
            Log.d(TAG, "Good User");
            updateUI(user);
        }
        else{
            Log.d(TAG, "Empty User!");
        }

    }

    private void updateUI(FirebaseUser u){
        // UID specific to the provider
        String uid = u.getUid();
        // Name, email address, and profile photo Url
        String fullName = u.getDisplayName();
        String email = u.getEmail();
        Uri photoUrl = u.getPhotoUrl();

        displayNameView.setText(fullName);
        emailView.setText(email);
    }

}