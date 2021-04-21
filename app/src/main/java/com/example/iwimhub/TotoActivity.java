package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwimhub.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class TotoActivity extends AppCompatActivity {

        private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto);

        logoutButton = findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent( TotoActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}