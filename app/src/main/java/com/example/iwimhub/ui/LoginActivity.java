package com.example.iwimhub.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.example.iwimhub.TotoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextview;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        progressbar = findViewById(R.id.loading);
        loginButton = findViewById(R.id.login);
        registerTextview = findViewById(R.id.register_message);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
            if(mFirebaseUser != null){
                Intent i = new Intent(LoginActivity.this, DefaultActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        };

        loginButton.setOnClickListener(v -> {
            String email = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if(email.isEmpty()){
                usernameEditText.setError("Please enter your email !");
                usernameEditText.requestFocus();
                return;
            }

            if(password.isEmpty()){
                passwordEditText.setError("Please enter your password !");
                return;
            }

            progressbar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressbar.setVisibility(View.GONE);
                    if(!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Incorrect email or password !", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i = new Intent(LoginActivity.this, DefaultActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
            });
        });

        registerTextview.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });


    }
}
