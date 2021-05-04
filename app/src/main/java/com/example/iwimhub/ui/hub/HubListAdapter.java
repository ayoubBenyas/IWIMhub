package com.example.iwimhub.ui.hub;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class HubListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private ModuleModel[] modules;

    HubListAdapter(Activity context, ModuleModel[] modules){
        super(context, R.layout.list_hub, modules);
        this.context = context;
        this.modules = modules;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_hub, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView professorText = (TextView) rowView.findViewById(R.id.professor);

        titleText.setText(modules[position].getTitle());

        modules[position].getChef().get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot professorDocument = task.getResult();
                if (professorDocument.exists()) {
                    ProfessorModel professorModel = professorDocument.toObject(ProfessorModel.class);
                    professorText.setText(professorModel.toString());
                }
            }
        });
        return rowView;
    }
}
