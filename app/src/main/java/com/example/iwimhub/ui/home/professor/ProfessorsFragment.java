package com.example.iwimhub.ui.home.professor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.example.iwimhub.ui.hub.HubListAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfessorsFragment extends Fragment {

    private RecyclerView professorsRecyclerView;
    private List<ProfessorModel> listProfessors;
    private static FirebaseFirestore db;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public ProfessorsFragment() {
        // Required empty public constructor
    }


    public static ProfessorsFragment newInstance() {
        ProfessorsFragment fragment = new ProfessorsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        professorsRecyclerView = view.findViewById(R.id.container_list_professors);


        db.collection("professors")
                .get().addOnCompleteListener(querySnapshot -> {
            if (querySnapshot.isSuccessful()) {
                QuerySnapshot result = querySnapshot.getResult();
                listProfessors = new ArrayList<ProfessorModel>();
                for (QueryDocumentSnapshot document : querySnapshot.getResult()) {
                    ProfessorModel professor = document.toObject(ProfessorModel.class);
                    professor.id(document.getId());
                    listProfessors.add(professor);
                }
                setAdapter();
            }else{
                Toast.makeText(getActivity(), "Something went wrong!"+querySnapshot.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setAdapter(){
        ProfessorListAdapter adapter = new ProfessorListAdapter(getContext(), listProfessors);
        professorsRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        professorsRecyclerView.setLayoutManager(linearLayoutManager);
    }
}