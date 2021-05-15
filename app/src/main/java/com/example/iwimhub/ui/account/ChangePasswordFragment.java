 package com.example.iwimhub.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class ChangePasswordFragment extends Fragment {

    private static final String ARG_CURRENT_USER = "current-user";

    private FirebaseUser user;
    private EditText newPasswordEditText, confirmPasswordEditText;
    private Button submitChangeButton;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    public static ChangePasswordFragment newInstance(FirebaseUser user) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        Bundle args = new Bundle();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
         newPasswordEditText = view.findViewById(R.id.new_password);
         confirmPasswordEditText = view.findViewById(R.id.confirm_password);
         submitChangeButton = view.findViewById(R.id.submit_change);

         submitChangeButton.setOnClickListener(v->{
             String confirmPassword, newPassword;

             newPassword = newPasswordEditText.getText().toString();
             if(newPassword.isEmpty()){
                 newPasswordEditText.setError("New password cannot be empty!");
                 newPasswordEditText.requestFocus();
                 return;
             }

             confirmPassword = confirmPasswordEditText.getText().toString();
             if(confirmPassword.isEmpty()){
                 confirmPasswordEditText.setError("Confirm password cannot be empty!");
                 confirmPasswordEditText.requestFocus();
                 return;
             }

             if(!confirmPassword.equals(newPassword)){
                 confirmPasswordEditText.setError("Confirm password don't match!");
                 confirmPasswordEditText.requestFocus();
                 return;
             }

             FirebaseAuth.getInstance().getCurrentUser().updatePassword(newPassword);

             Toast.makeText( getActivity(), "password changed()", Toast.LENGTH_SHORT).show();
             getActivity().getFragmentManager().popBackStack();
         });

     }
 }