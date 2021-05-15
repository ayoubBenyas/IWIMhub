package com.example.iwimhub.ui.hub;

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

public class HubListAdapter extends ArrayAdapter<ModuleModel> {

    private Activity context;
    private List<ModuleModel> modules;

    HubListAdapter(Activity context, List<ModuleModel> modules){
        super(context, R.layout.list_hub, modules);
        this.context = context;
        this.modules = modules;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent)  {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_hub, null,true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView professorText = (TextView) rowView.findViewById(R.id.professor);
        TextView timingText = (TextView) rowView.findViewById(R.id.timing);
        ImageView iconImageView = (ImageView) rowView.findViewById(R.id.icon_hub);
        /*
        LocalTime nowTime = LocalTime.now();
        LocalTime startTime = LocalTime.parse(modules[position].getSchedule().getStartAt(), DateTimeFormatter.ofPattern("hh:mm"));
        LocalTime endTime   = LocalTime.parse(modules[position].getSchedule().getEndAt(),   DateTimeFormatter.ofPattern("hh:mm"));

        if (nowTime.isBefore(startTime)) { //not yet, but very soon
            //Meeting will start in
            long secondsBeforeStart =  startTime.until(startTime, ChronoUnit.MINUTES);

            if( secondsBeforeStart < 30 ){
                timingText.setText("Meeting will start in "+secondsBeforeStart);
            }else{
                timingText.setText("Scheduled meeting ");
            }
        } else if (nowTime.isBefore(endTime)) { //On going
            timingText.setText("Meeting started ");
            iconImageView.setVisibility(View.VISIBLE);
        } else { // Ended
            timingText.setText("Meeting ended ");
        }
        */
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

        try {
            Date startTime = sdf.parse(modules.get(position).getSchedule().getStartAt());
            Date endTime   = sdf.parse(modules.get(position).getSchedule().getEndAt());
            if (now.before(startTime)) { //not yet, but very soon
                //Meeting will start in
                long diffInMillies  = Math.abs(startTime.getTime() - now.getTime());
                long minutesBeforeStar = TimeUnit.MINUTES.convert(diffInMillies , TimeUnit.MILLISECONDS);

                if( minutesBeforeStar  < 30 ){
                    timingText.setText("Meeting will start in " + diffInMillies );
                }else{
                    timingText.setText("Scheduled meeting ");
                }
            } else if (now.before(endTime)) { //On going
                timingText.setText("Meeting started ");
                iconImageView.setVisibility(View.VISIBLE);
            } else { // Ended
                timingText.setText("Meeting ended ");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        timingText.setText("Today");
        iconImageView.setVisibility(View.VISIBLE);
        */
        Log.d("HubAdapter:", "item "+position );
        titleText.setText(modules.get(position).getTitle());
        modules.get(position).getChef().get().addOnCompleteListener(task -> {
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
