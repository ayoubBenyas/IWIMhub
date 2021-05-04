package com.example.iwimhub.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwimhub.ui.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.iwimhub.R;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private FirebaseUser currentUser;
    private TextView profileTextView, updateProfileTextView, changePasswordTextView, LogoutTextView;
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileTextView = view.findViewById(R.id.profile);
        updateProfileTextView = view.findViewById(R.id.update_profile);
        changePasswordTextView = view.findViewById(R.id.change_password);
        LogoutTextView = view.findViewById(R.id.logout);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        profileTextView.setOnClickListener(v -> {
            openFragment(ProfileFragment.newInstance(currentUser));
        });

        updateProfileTextView.setOnClickListener(v -> {
            //openFragment(UpdateProfileFragment.newInstance(currentUser));
            Toast.makeText( getActivity(), "updateProfile()", Toast.LENGTH_SHORT).show();
        });

        changePasswordTextView.setOnClickListener(v -> {
            openFragment(ChangePasswordFragment.newInstance(currentUser));
        });

        LogoutTextView.setOnClickListener(v -> {
            Toast.makeText( getActivity(), "logged out", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent( getActivity(), LoginActivity.class);
            startActivity(i);
        });
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void register(View view) {
        Toast.makeText( getActivity(), "register()", Toast.LENGTH_SHORT).show();
        /*Intent i = new Intent( TotoActivity.this, RegisterActivity.class);
        startActivity(i);*/
    }

}