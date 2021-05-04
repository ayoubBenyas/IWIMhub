package com.example.iwimhub.ui.hub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.iwimhub.R;

public class HubFragment extends Fragment {

    private HubViewModel hubViewModel;

    public static HubFragment newInstance() {
        HubFragment fragment = new HubFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        hubViewModel =
                new ViewModelProvider(this).get(HubViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hub, container, false);
        final TextView textView = root.findViewById(R.id.text_hub);
        hubViewModel.getText().observe(getViewLifecycleOwner(), (Observer<String>) s -> textView.setText(s));
        return root;
    }
}