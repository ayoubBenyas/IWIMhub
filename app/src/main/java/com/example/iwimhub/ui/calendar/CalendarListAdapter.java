package com.example.iwimhub.ui.calendar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;

public class CalendarListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private ModuleModel[] modules;

    CalendarListAdapter(Activity context, ModuleModel[] modules){
        super(context, R.layout.item_calendar, modules);
        this.context = context;
        this.modules = modules;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_calendar, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView dateTimeText = (TextView) rowView.findViewById(R.id.dateTime);

        titleText.setText(modules[position].getTitle());
        dateTimeText.setText(modules[position].getSchedule().toString());

        return rowView;
    }
}
