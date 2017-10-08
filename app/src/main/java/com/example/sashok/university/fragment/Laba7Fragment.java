package com.example.sashok.university.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sashok.university.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class Laba7Fragment extends AbsFragment {
    public int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    public static Laba7Fragment newInstance(Bundle args) {
        Laba7Fragment fragment = new Laba7Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.lab_seven_fragment, container, false);
        Button btn = (Button) main.findViewById(R.id.ask_perm);
        final TextView access_text_view = (TextView) main.findViewById(R.id.access_text_view);
        final List<String> perms = new ArrayList<>();
        perms.add("Чтение контактов");
        perms.add("Запись контактов");
        perms.add("Состояние батареи");
        perms.add("Состояние вай-фая");
        perms.add("Чтение данных");
        perms.add("Запись данных");
        perms.add("Подключение к интернету");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, perms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = (Spinner) main.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Выберите разрешение");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getContext().getPackageManager();
                int hasPerm = pm.checkPermission(
                        getPermissionFromInt(spinner.getSelectedItemPosition()),
                        getContext().getPackageName());
                if (hasPerm != PackageManager.PERMISSION_GRANTED) {
                    access_text_view.setText(R.string.no_access);
                } else {
                    access_text_view.setText(R.string.has_access);
                }
            }
        });
        return main;
    }

    public String getPermissionFromInt(int perm) {
        switch (perm) {
            case 0:
                return Manifest.permission.WRITE_CONTACTS;
            case 1:
                return Manifest.permission.READ_CONTACTS;
            case 2:
                return Manifest.permission.BATTERY_STATS;
            case 3:
                return Manifest.permission.ACCESS_WIFI_STATE;
            case 4:
                return Manifest.permission.READ_EXTERNAL_STORAGE;
            case 5:
                return Manifest.permission.WRITE_EXTERNAL_STORAGE;
            case 6:
                return Manifest.permission.INTERNET;

            default:
                return Manifest.permission.WRITE_CONTACTS;
        }

    }
}
