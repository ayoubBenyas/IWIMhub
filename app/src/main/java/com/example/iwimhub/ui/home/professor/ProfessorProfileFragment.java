package com.example.iwimhub.ui.home.professor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.example.iwimhub.data.model.StudentModel;
import com.example.iwimhub.ui.hub.HubListAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorProfileFragment extends Fragment {

    private ProfessorModel professor;
    private List<ModuleModel> listModules;
    private TextView displayNameView, emailView;
    private ImageView photoView;
    private ListView moduleListView;
    private static FirebaseFirestore db;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public ProfessorProfileFragment() {
        // Required empty public constructor
    }

    public ProfessorProfileFragment(ProfessorModel professor) {
        this.professor = professor;
    }


    public static ProfessorProfileFragment newInstance() {
        ProfessorProfileFragment fragment = new ProfessorProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professor_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayNameView = view.findViewById(R.id.displayName);
        emailView = view.findViewById(R.id.email);
        photoView = view.findViewById(R.id.photo);
        updateUI();

        db.collection("modules")
                .whereEqualTo("chef", db.collection("professors").document(professor.id()))
                .get().addOnCompleteListener(querySnapshot -> {
            if (querySnapshot.isSuccessful()) {
                QuerySnapshot result = querySnapshot.getResult();

                listModules = new ArrayList<ModuleModel>();
                for (QueryDocumentSnapshot document : querySnapshot.getResult()) {
                    ModuleModel module = document.toObject(ModuleModel.class);
                    module.id(document.getId());
                    listModules.add(module);
                }

                ModuleListAdapter adapter = new ModuleListAdapter( getActivity(), listModules);
                moduleListView = (ListView) view.findViewById(R.id.container_list_module);
                moduleListView.setAdapter(adapter);
            }else{
                Toast.makeText(getActivity(), "Something went wrong!"+querySnapshot.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void updateUI(){
        displayNameView.setText(professor.toString());
        emailView.setText(professor.getEmail());

        emailView.setOnClickListener(v -> {
            try {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, professor.getEmail() );
                startActivity(Intent.createChooser(intent, "Send Email"));

            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        });
        emailView.setOnLongClickListener(v -> {
            Toast.makeText(getActivity(), "Email copied", Toast.LENGTH_SHORT).show();
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("EmailAddress",professor.getEmail() );
            clipboardManager.setPrimaryClip(clip);
            return true;
        });
    }
}