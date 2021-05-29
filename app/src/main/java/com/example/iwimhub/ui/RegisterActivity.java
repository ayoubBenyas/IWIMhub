package com.example.iwimhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.StudentModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
	private EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private ProgressBar progressbar;
    private static final FirebaseAuth mAuth;
	private static final FirebaseFirestore db;

	static{
    	mAuth = FirebaseAuth.getInstance();
        db =  FirebaseFirestore.getInstance();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);

        Button registerButton = findViewById(R.id.register);
        TextView signInTextView = findViewById(R.id.signin_message);

		progressbar = findViewById(R.id.loading);

        registerButton.setOnClickListener(v -> {
 			String firstName, lastName, email, password, confirmPassword;
 			
			firstName = firstNameEditText.getText().toString();
            if(firstName.isEmpty()){
                firstNameEditText.setError("First name cannot be empty!");
                firstNameEditText.requestFocus();
                return;
            }

            lastName = lastNameEditText.getText().toString();
            if(lastName.isEmpty()){
                lastNameEditText.setError("Last name cannot be empty!");
                lastNameEditText.requestFocus();
                return;
            }


			email = emailEditText.getText().toString();
            if(email.isEmpty()){
                emailEditText.setError("Email cannot be empty!");
                emailEditText.requestFocus();
                return;
            }

			password = passwordEditText.getText().toString();
            if(password.isEmpty()){
                passwordEditText.setError("Password cannot be empty!");
                passwordEditText.requestFocus();
                return;
            }

			confirmPassword = confirmPasswordEditText.getText().toString();
            if(confirmPassword.isEmpty()){
                confirmPasswordEditText.requestFocus();
                confirmPasswordEditText.setError("Confirm Password cannot be empty!");
                return;
            }

            if(!confirmPassword.equals(password)){
                 confirmPasswordEditText.setError("Confirm password don't match!");
                 confirmPasswordEditText.requestFocus();
                 return;
             }

            StudentModel student = new StudentModel();
            student.id(email);
            student.setFirstName(firstName);
			student.setLastName(lastName);

			progressbar.setVisibility(View.VISIBLE);

        	mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        db.collection("students").document(student.id()).set(student);
                        //mAuth.getCurrentUser().sendEmailVerification();
                        progressbar.setVisibility(View.GONE);
                        Intent i = new Intent(RegisterActivity.this, DefaultActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Register", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });


        	
	        /*new Handler().postDelayed(() -> {
	            progressbar.setVisibility(View.GONE);
	        	Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
		        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(i);
		        finish();
        	}, 1000);*/
        });

        signInTextView.setOnClickListener(v -> {
        	Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }

}