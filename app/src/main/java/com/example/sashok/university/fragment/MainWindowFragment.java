package com.example.sashok.university.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sashok.university.R;
import com.example.sashok.university.adapter.ShowAvLabsAdapter;
import com.example.sashok.university.helper.FragmentItem;
import com.example.sashok.university.helper.FragmentTag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class MainWindowFragment extends AbsFragment {
    private RecyclerView recyclerView;
    private ShowAvLabsAdapter showAvLabsAdapter;
    private List<FragmentItem> labs_names;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main_view=inflater.inflate(R.layout.main_fragment,container,false);
        recyclerView=(RecyclerView) main_view.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        labs_names=new ArrayList<>();
        setData();
        showAvLabsAdapter=new ShowAvLabsAdapter(getActivity(),labs_names);
        recyclerView.setAdapter(showAvLabsAdapter);
        return  main_view;
    }

    public static MainWindowFragment newInstance(Bundle args) {

        MainWindowFragment fragment = new MainWindowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setData() {
        FragmentItem item1 = new FragmentItem(FragmentTag.Lab1FragmentTag, "Лабораторная работа №1","Простейшее приложение  Android Java\nЦель:\n" +
                "Познакомиться с инструментами разработки Android-приложений.\n" +
                "На примере простейших программ разобрать структуру типичного Android-приложения.\n" +
                "Научиться запускать приложение на эмуляторе.");
        FragmentItem item2 = new FragmentItem(FragmentTag.Lab2FragmentTag, "Лабораторная работа №2","Интерфейс приложения и настройка его структуры \nЦель. Изучить структуру Android приложения и используемые им файл. Рассмотреть способы описания интерфейса.");
        FragmentItem item3 = new FragmentItem(FragmentTag.Lab3FragmentTag, "Лабораторная работа №3","Работа с кнопками\nЦель. \n" +
                "Изучить работу кнопок и создание для них обработчиков. Рассмотреть способы компоновки виджетов на сцене.");
        FragmentItem item4 = new FragmentItem(FragmentTag.Lab4FragmentTag, "Лабораторная работа №4","Анимация.\nИзучить простые средства (эффекты) анимации изображений в Java Android.");
        FragmentItem item5 = new FragmentItem(FragmentTag.Lab5FragmentTag, "Лабораторная работа №5","Отображение рисунков в Android Google.\nЦель:изучить  средства отображения графических файлов в Android Google.");
        FragmentItem item6 = new FragmentItem(FragmentTag.Lab6FragmentTag, "Лабораторная работа №6","Работа с файлами.\nИзучить  возможности обработки файлов в Android Google.");
        FragmentItem item7 = new FragmentItem(FragmentTag.Lab7FragmentTag, "Лабораторная работа №7","Конфигурирование файла манифеста Android.\nЦель:изучить  назначение и возможности конфигурирования манифест файла.");
        FragmentItem item8 = new FragmentItem(FragmentTag.Lab8FragmentTag, "Лабораторная работа №8","Навигация в web.\nЦель:изучить  назначение и возможности  работы с Internet ресурсами");
        labs_names.add(item1);
        labs_names.add(item2);
        labs_names.add(item3);
        labs_names.add(item4);
        labs_names.add(item5);
        labs_names.add(item6);
        labs_names.add(item7);
        labs_names.add(item8);
    }
}
