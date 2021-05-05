package com.example.iwimhub.ui.calendar;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.google.firebase.firestore.DocumentSnapshot;

public class CalendarListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private ModuleModel[] modules;

    CalendarListAdapter(Activity context, ModuleModel[] modules){
        super(context, R.layout.list_calendar, modules);
        Log.d("Calendar len ", " [" + modules.length );
        this.context = context;
        this.modules = modules;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_calendar, null,true);

        Log.d("Calendar index ", modules[position].getTitle()+": [" +position+"]" );
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView timeText = (TextView) rowView.findViewById(R.id.time);

        titleText.setText(modules[position].getTitle());
        timeText.setText(modules[position].getSchedule().toTime());

        return rowView;
    }
}
