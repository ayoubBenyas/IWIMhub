package com.example.iwimhub.ui.hub;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ModuleModel;
import com.example.iwimhub.data.model.ProfessorModel;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class HubListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private ModuleModel[] modules;

    HubListAdapter(Activity context, ModuleModel[] modules){
        super(context, R.layout.list_hub, modules);
        this.context = context;
        this.modules = modules;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_hub, null,true);

        /*
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayName = (new DateFormatSymbols()).getWeekdays()[dayOfWeek];

        LocalTime nowTime = LocalTime.now();
        LocalTime startTime = LocalTime.parse(modules[position].getSchedule().getStartAt().concat(":00"));
        LocalTime endTime = LocalTime.parse(modules[position].getSchedule().getEndAt().concat(":00"));

        if(  dayName.equals( modules[position].getSchedule().getDay() ) ) {
            if (nowTime.isBefore(startTime)) { //not yet, but very soon
                //Meeting will start in
                long secondsBeforeStart =  startTime.until(startTime, ChronoUnit.MINUTES);
                if( secondsBeforeStart < 30 ){

                }
            } else if (nowTime.isBefore(endTime)) { //On going

            } else { // Ended

            }
        }
        */

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
