package com.example.iwimhub.ui.hub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.ui.LoginActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class HubFragment extends Fragment {

    private static FirebaseFirestore db;
    private HubViewModel hubViewModel;
    private ListView hubListView;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public static HubFragment newInstance() {
        HubFragment fragment = new HubFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        hubViewModel = new ViewModelProvider(this).get(HubViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hub, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db.collection("modules").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot result  = task.getResult();
                List<ModuleModel> listModules = result.toObjects(ModuleModel.class);

                ModuleModel[] arrayModules = new ModuleModel[listModules.size()];
                listModules.toArray(arrayModules);

                HubListAdapter adapter = new HubListAdapter( getActivity(), arrayModules);
                hubListView = (ListView) view.findViewById(R.id.container_list_hub);
                hubListView.setAdapter(adapter);

                hubListView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, v, position, id) -> {
                    Toast.makeText(getActivity(),"Position["+position+"] : "+arrayModules[position].getTitle(),Toast.LENGTH_SHORT).show();
                });
                    }else{
                Toast.makeText(getActivity(), "Something went wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}