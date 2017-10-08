package com.example.sashok.university.helper;

import android.content.Context;
import android.util.Log;

import com.example.sashok.university.model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class FileUtil {
    private static String FILE_NAME = "students.txt";

    public static void writeFile(Context context, Student student) {
        FileOutputStream outputStream;
        List<Student> students=readStudents(context);
        if (students==null) students=new ArrayList<Student>();
        students.add(student);
        File root = android.os.Environment.getExternalStorageDirectory();
        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;
        File file = new File(file_name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObjectOutputStream oos = null;
        try {
            outputStream = new FileOutputStream(file,false);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(students);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFile(Context context) {
        File root = android.os.Environment.getExternalStorageDirectory();

        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;

        File file = new File(file_name);
        if (file.exists()) {
            file.delete();
        }
    }

    public static List<Student> readStudents(Context context) {
        FileInputStream fin;
        ObjectInputStream ois = null;
        File root = android.os.Environment.getExternalStorageDirectory();

        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;
        File file = new File(file_name);
        if (!file.exists()) {
            return null;
        }
        ArrayList<Student> students = new ArrayList<>();
        try {
            fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);
            Object object = ois.readObject();
            if (object instanceof Student) {
                students.add((Student) object);
            } else {
                students.addAll((ArrayList<Student>) object);
            }
            ois.close();
            Log.v("TAG", "Records read successfully");
            return students;
        } catch (Exception e) {
            Log.e("TAG", "Cant read saved records" + e.getMessage());
            return students;
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (Exception e) {
                    Log.e("RAG", "Error in closing stream while reading records" + e.getMessage());
                }
        }
    }
}
