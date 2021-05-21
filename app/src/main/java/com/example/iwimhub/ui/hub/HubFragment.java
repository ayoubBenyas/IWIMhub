package com.example.iwimhub.ui.hub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.example.iwimhub.data.model.MeetingModel;
import com.example.iwimhub.data.model.ModuleModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HubFragment extends Fragment {

    private static FirebaseFirestore db;
    private HubViewModel hubViewModel;
    private ListView hubListView;

    static{
        db =  FirebaseFirestore.getInstance();
    }

    public static HubFragment newInstance() {
        HubFragment fragment = new HubFragment();
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
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayName = (new DateFormatSymbols()).getWeekdays()[dayOfWeek];
        db.collection("modules")
                .whereEqualTo("schedule.day", dayName)
                .orderBy("schedule.startAt")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot result  = task.getResult();

                List<ModuleModel> listModules = new ArrayList<ModuleModel>();
                for (QueryDocumentSnapshot document: task.getResult()) {
                    ModuleModel module = document.toObject(ModuleModel.class);
                    module.id(document.getId());
                    listModules.add((module));
                }

                HubListAdapter adapter = new HubListAdapter( getActivity(), listModules);
                hubListView = (ListView) view.findViewById(R.id.container_list_hub);
                hubListView.setAdapter(adapter);

                hubListView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, v, position, id) -> {
                    String meet = "https://meet.google.com/xim-fzsi-vkn";
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(meet));
                    startActivity(browserIntent);

                });
                    }else{
                Toast.makeText(getActivity(), "Something went wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void openMeetingLink(List<ModuleModel> listM, int pos ){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String  currentDate = sdf.format(new Date());
        Log.d("Hub", "Current Date : "+currentDate);

        db.collection("modules/"+listM.get(pos).id()+"/meetings")
                .whereEqualTo("date" , currentDate )
                .get().addOnCompleteListener(querySnapshot -> {
            if (querySnapshot.isSuccessful()) {
                QuerySnapshot res  = querySnapshot.getResult();
                List<MeetingModel> listMeeting = res.toObjects(MeetingModel.class);

                Log.d("Hub", "listMeeting.size() : "+listMeeting.size());
                if( listMeeting.size() > 0 ){
                    MeetingModel meeting = listMeeting.get(0);
                    Log.d("Hub", "meeting.getDate() : "+meeting.getDate());
                    String url = meeting.getLink();
                    Toast.makeText(getActivity(),"meeting : "+url ,Toast.LENGTH_SHORT).show();
                                /*if (!url.startsWith("http://") && !url.startsWith("https://"))
                                    url = "http://" + url;
                                Toast.makeText(getActivity(),"meeting : "+url,Toast.LENGTH_SHORT).show();
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(browserIntent);*/
                }
            }
        });
    }
}