package com.example.iwimhub.ui.home.professor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwimhub.R;
import com.example.iwimhub.data.model.ProfessorModel;
import com.example.iwimhub.ui.DefaultActivity;

import java.util.List;

public class ProfessorListAdapter extends RecyclerView.Adapter<ProfessorListAdapter.ProfessorViewAdapter> {
    private List<ProfessorModel> professors;
    private Context context;

    public ProfessorListAdapter(Context context, List<ProfessorModel> professors){
        this.professors = professors;
        this.context = context;
    }

    public class ProfessorViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView professorNameTextView;

        public ProfessorViewAdapter(final View view) {
            super(view);
            view.setOnClickListener(this);
            professorNameTextView = view.findViewById(R.id.professor_name);
        }

        @Override
        public void onClick(View view)
        {
            int pos = getLayoutPosition();
            Toast.makeText( context, "Students()", Toast.LENGTH_SHORT).show();
            ((DefaultActivity)context).openFragmentWithBackStack(new ProfessorProfileFragment(professors.get(pos)));
        }


    }

    @NonNull
    @Override
    public ProfessorListAdapter.ProfessorViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_professor, parent, false);
        return new ProfessorViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewAdapter holder, int position) {
        ProfessorModel professor = professors.get(position);
        holder.professorNameTextView.setText(professor.toString());
    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

}
