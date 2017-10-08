package com.example.sashok.university.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sashok.university.R;
import com.example.sashok.university.adapter.AddStudentDialog;
import com.example.sashok.university.adapter.StudentsAdapter;
import com.example.sashok.university.helper.FileUtil;
import com.example.sashok.university.interfaces.StudentListener;
import com.example.sashok.university.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba6Fragment extends AbsFragment {
    private FloatingActionButton fab;
    private RecyclerView studentsRV;
    private List<Student> students;
    private StudentsAdapter mAdapter;
    private AddStudentDialog dialog;
    public static Laba6Fragment newInstance(Bundle args) {
        Laba6Fragment fragment = new Laba6Fragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view=inflater.inflate(R.layout.lab_six_fragment,container,false);
        fab=(FloatingActionButton)main_view.findViewById(R.id.fab);
//        FileUtil.deleteFile(getContext());
        studentsRV=(RecyclerView)main_view.findViewById(R.id.students_recycler_view);
        students=new ArrayList<>();
        List<Student> file_stud= FileUtil.readStudents(getContext());
        if (file_stud!=null)  students.addAll(file_stud);
        mAdapter=new StudentsAdapter(this,students);
        studentsRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        studentsRV.setAdapter(mAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new AddStudentDialog(getActivity(), new StudentListener() {
                    @Override
                    public void onStudentAdded(Student student) {
                        students.add(student);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                dialog.getWindow().getAttributes().windowAnimations = R.style.RegistrationDialogAnimation;
                dialog.setTitle(R.string.addStudent);
                dialog.show();
            }
        });
        return main_view;
    }


}
