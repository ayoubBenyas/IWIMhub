package com.example.iwimhub.ui.home.professor;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ModuleListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private List<ModuleModel> modules;

    ModuleListAdapter(Activity context, List<ModuleModel> modules){
        super(context, R.layout.item_module, modules);
        this.context = context;
        this.modules = modules;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent)  {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_module, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        titleText.setText(modules.get(position).getTitle());

        return rowView;
    }
}
