package com.example.iwimhub.ui.account;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.StudentModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {
    private static final String ARG_CURRENT_USER = "current-user";

    private FirebaseUser user;
    private static FirebaseFirestore db;
    private TextView displayNameView, emailView, cneTextView, apogeeTextView;
    private ImageView photoView;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(FirebaseUser user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        user.toString();
        args.putParcelable(ARG_CURRENT_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_CURRENT_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayNameView = view.findViewById(R.id.displayName);
        emailView = view.findViewById(R.id.email);
        photoView = view.findViewById(R.id.photo);
        cneTextView = view.findViewById(R.id.cne);
        apogeeTextView = view.findViewById(R.id.apogee);

        if( user != null) {
            db.collection("students")
                    .document(user.getEmail()).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    StudentModel me = documentSnapshot.toObject(StudentModel.class);
                    updateUI(me);
                }
            });
        }else{
            Toast.makeText( getActivity(), "non-logged!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(StudentModel u){
        displayNameView.setText(u.toString());
        emailView.setText(user.getEmail());
        apogeeTextView.setText(u.getApogee());
        cneTextView.setText(u.getCne());
    }
}