package com.example.iwimhub.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwimhub.R;
import com.example.iwimhub.ui.DefaultActivity;
import com.example.iwimhub.ui.home.professor.ProfessorsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button professorButton, studentButton;
        professorButton = view.findViewById(R.id.show_professors);
        studentButton = view.findViewById(R.id.show_students);

        professorButton.setOnClickListener(v -> {
            ((DefaultActivity)getActivity()).openFragmentWithBackStack(ProfessorsFragment.newInstance(), "Professors");
        });

        studentButton.setOnClickListener(v -> {
            Toast.makeText( getActivity(), "Studentssss", Toast.LENGTH_SHORT).show();
        });
    }
}