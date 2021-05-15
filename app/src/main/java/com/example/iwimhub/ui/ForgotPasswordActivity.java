package com.example.iwimhub.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.iwimhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextView emailTextView;
    private Button resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailTextView = findViewById(R.id.email);
        resetButton = findViewById(R.id.reset_change);

        resetButton.setOnClickListener(v->{
            String email  = resetButton.getText().toString();
            if( email.isEmpty() ){
                emailTextView.setError("Please enter your email !");
                emailTextView.requestFocus();
                return;
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email);

        });
    }
}