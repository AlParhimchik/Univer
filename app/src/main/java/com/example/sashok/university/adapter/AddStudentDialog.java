package com.example.sashok.university.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sashok.university.R;
import com.example.sashok.university.helper.FileUtil;
import com.example.sashok.university.interfaces.StudentListener;
import com.example.sashok.university.model.Student;

/**
 * Created by sashok on 27.9.17.
 */
public class AddStudentDialog extends AlertDialog implements View.OnClickListener, View.OnFocusChangeListener {

    private TextView save_btn;
    private Activity activity;
    private StudentListener studentListener;
    private TextInputLayout layout_first_name;
    private TextInputEditText edit_first_name;
    private TextInputLayout layout_last_name;
    private TextInputEditText edit_last_name;
    private TextInputLayout layout_surname;
    private TextInputEditText edit_surname;
    private TextInputLayout group_nubmer_layout;
    private TextInputEditText group_nubmer_edit;
    private TextInputLayout faculty_layout;
    private TextInputEditText faculty_edit;

    public AddStudentDialog(final Activity activity, StudentListener listener) {
        super(activity);
        this.activity = activity;
        studentListener = listener;
        LayoutInflater inflater =
                (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View add_student_view = inflater.inflate(R.layout.lab_two_fragment, null);
        setView(add_student_view);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        save_btn = (TextView) add_student_view.findViewById(R.id.sumbit_btn);
        layout_first_name = (TextInputLayout) add_student_view.findViewById(R.id.layout_first_name);
        edit_first_name = (TextInputEditText) add_student_view.findViewById(R.id.edit_first_name);

        layout_last_name = (TextInputLayout) add_student_view.findViewById(R.id.last_name_layout);
        edit_last_name = (TextInputEditText) add_student_view.findViewById(R.id.last_name_edit);

        layout_surname = (TextInputLayout) add_student_view.findViewById(R.id.surname_layout);
        edit_surname = (TextInputEditText) add_student_view.findViewById(R.id.surname_edit);

        group_nubmer_layout = (TextInputLayout) add_student_view.findViewById(R.id.group_number_layout);
        group_nubmer_edit = (TextInputEditText) add_student_view.findViewById(R.id.group_nubmer_edit);

        faculty_layout = (TextInputLayout) add_student_view.findViewById(R.id.faculty_layout);
        faculty_edit = (TextInputEditText) add_student_view.findViewById(R.id.faculty_edit);
        save_btn.setOnClickListener(this);
        setTitle(R.string.addStudent);

    }


    @Override
    public void onClick(View view) {
        if (validateForm()) {
            Student student = new Student();
            student.setFirstName(edit_first_name.getText().toString());
            student.setLastName(edit_last_name.getText().toString());
            student.setFaculty(faculty_edit.getText().toString());
            student.setGroupNumber(Integer.parseInt(group_nubmer_edit.getText().toString()));
            student.setSurName(edit_surname.getText().toString());
            FileUtil.writeFile(getContext(),student );
            studentListener.onStudentAdded(student);
            dismiss();
        } else Toast.makeText(activity, "Добавьте перевод", Toast.LENGTH_LONG).show();
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(edit_first_name.getText().toString())) {
            layout_first_name.setError("Введите имя");
            return false;
        }
        if (TextUtils.isEmpty(edit_last_name.getText().toString())) {
            layout_last_name.setError("Введите фамилию");
            return false;
        }
        if (TextUtils.isEmpty(edit_surname.getText().toString())) {
            layout_surname.setError("Введите отчество");
            return false;
        }
        if (TextUtils.isEmpty(group_nubmer_edit.getText().toString())) {
            group_nubmer_layout.setError("Введите номер группы");
            return false;
        }
        if (!TextUtils.isDigitsOnly(group_nubmer_edit.getText().toString())){
            group_nubmer_layout.setError("Вводите только цифры!");
            return false;
        }
        if (TextUtils.isEmpty(faculty_edit.getText().toString())) {
            faculty_layout.setError("Введите факультет");
            return false;
        }
        return true;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            hideKeyboard(view);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}