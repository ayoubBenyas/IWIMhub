package com.example.iwimhub.ui.calendar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.ui.hub.HubListAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarFragment extends Fragment {

    private static FirebaseFirestore db;
    private HashMap<String , List<ModuleModel>> hashCalendar;
    private CalendarViewModel calendarViewModel;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
                new ViewModelProvider(this).get(CalendarViewModel.class);
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db.collection("modules")
                //.orderBy("schedule.day")
                .orderBy("schedule.startAt")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot result  = task.getResult();
                List<ModuleModel> listModules = result.toObjects(ModuleModel.class);

                ModuleModel[] arrayModules = new ModuleModel[listModules.size()];
                listModules.toArray(arrayModules);

                fillListView( view, R.id.container_list_calendar, arrayModules);

            }else{
                Toast.makeText(getActivity(), "Something went wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("firror : ",task.getException().getMessage() );
                clipboardManager.setPrimaryClip(clip);
            }
        });

    }


    public ModuleModel[] getModulesOfDay(HashMap<String , List<ModuleModel>> hashMap, String keyString){
        List<ModuleModel> listModules = hashMap.get(keyString);

        ModuleModel[] arrayModules = new ModuleModel[listModules.size()];
        listModules.toArray(arrayModules);
        return arrayModules;
    }

    public void fillListView(View v, int id, ModuleModel[] array){
        CalendarListAdapter calendarListAdapter = new CalendarListAdapter( getActivity(), array);
        ListView listView = (ListView) v.findViewById( id);

        listView.setAdapter( calendarListAdapter );
    }
}