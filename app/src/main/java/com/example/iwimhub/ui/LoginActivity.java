package com.example.iwimhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwimhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
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
        Button loginButton = findViewById(R.id.login);
        TextView signUpTextView = findViewById(R.id.register_message);
        TextView forgotPasswordTextView = findViewById(R.id.forgot_password_message);

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

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, task -> {
                progressbar.setVisibility(View.GONE);
                if(!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Incorrect email or password !", Toast.LENGTH_SHORT).show();
                    forgotPasswordTextView.setVisibility(View.VISIBLE);
                }else{
                    if(true || mAuth.getCurrentUser().isEmailVerified()){
                        Intent i = new Intent(LoginActivity.this, DefaultActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Please verify your email address.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        signUpTextView.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        forgotPasswordTextView.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(i);
        });


    }
}
