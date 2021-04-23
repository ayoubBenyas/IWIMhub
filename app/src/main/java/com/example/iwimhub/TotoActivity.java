package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwimhub.ui.ProfileActivity;
import com.example.iwimhub.ui.LoginActivity;
import com.example.iwimhub.ui.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class TotoActivity extends AppCompatActivity {

        private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto);

    }

    public void profile(View view) {
        Intent i = new Intent( TotoActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public void signup(View view) {
        Intent i = new Intent( TotoActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent( TotoActivity.this, LoginActivity.class);
        startActivity(i);
    }

}