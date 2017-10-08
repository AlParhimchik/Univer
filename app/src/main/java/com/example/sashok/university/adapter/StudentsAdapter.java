package com.example.sashok.university.adapter;

/**
 * Created by sashok on 4.10.17.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sashok.university.R;
import com.example.sashok.university.model.Student;

import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {
    private Fragment context;
    private List<Student> students;

    public StudentsAdapter(Fragment context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.student_item, parent, false);

        // Return a new holder instance
        StudentViewHolder viewHolder = new StudentViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {
        final Student student = students.get(position);
        holder.name_text.setText(student.getLastName() + " " + student.getFirstName() + " " + student.getSurName());
        holder.faculty.setText(student.getFaculty());
        holder.group_name.setText(String.valueOf(student.getGroupNumber()));

    }

    @Override
    public int getItemCount() {
        return  (students==null)?0:students.size();

    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView name_text;
        private TextView group_name;
        private TextView faculty;

        public StudentViewHolder(View itemView) {
            super(itemView);
            name_text = (TextView) itemView.findViewById(R.id.name_text);
            group_name = (TextView) itemView.findViewById(R.id.group_name);
            faculty = (TextView) itemView.findViewById(R.id.faculty_name);

        }
    }
}
