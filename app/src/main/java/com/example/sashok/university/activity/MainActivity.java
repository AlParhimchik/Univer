package com.example.sashok.university.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sashok.university.R;
import com.example.sashok.university.fragment.AbsFragment;
import com.example.sashok.university.helper.FragmentTag;
import com.example.sashok.university.interfaces.CardLabListener;
import com.example.sashok.university.provider.FragmentBuilder;

public class MainActivity extends AppCompatActivity implements CardLabListener {
    private static final String FRAGMENT_TAG_BUNDLE = "framgent";

    public FragmentTag CURRENTTAG;
    private TextView title_toolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title_toolbar = (TextView) findViewById(R.id.title_toolbar);
        if (savedInstanceState == null) {
            CURRENTTAG = FragmentTag.MainFragmentTag;
            replaceFragment(getFragment());
        } else {
            CURRENTTAG = FragmentTag.valueOf(savedInstanceState.getString(FRAGMENT_TAG_BUNDLE));
        }
        setTitle();
    }

    public void setTitle() {
        switch (CURRENTTAG) {
            case Lab1FragmentTag:
                title_toolbar.setText("Привет мир");
                break;
            case Lab2FragmentTag:
                title_toolbar.setText("Создание формы");
                break;
            case Lab3FragmentTag:
                title_toolbar.setText("О разработчике");
                break;
            case Lab4FragmentTag:
                title_toolbar.setText("Работа с анимацией");
                break;
            case Lab5FragmentTag:
                title_toolbar.setText("Обзор изображений");
                break;
            case Lab6FragmentTag:
                title_toolbar.setText("Работа с файлами");
                break;
            case Lab7FragmentTag:
                title_toolbar.setText("Работа с манифестом");
                break;
            case Lab8FragmentTag:
                title_toolbar.setText("Создание браузера");
                break;
            case MainFragmentTag:
                title_toolbar.setText("Лабораторные");
                break;
        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.frame_layout, fragment, CURRENTTAG.name());
        fragmentTransaction.commit();
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.add(R.id.frame_layout, fragment, fragment.getTag());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public Fragment getFragment() {
        return new FragmentBuilder(CURRENTTAG).build();
    }

    @Override
    public void onCardClicked(FragmentTag tag) {
        CURRENTTAG = tag;
        replaceFragment(getFragment());
        setTitle();
    }

    @Override
    public void onBackPressed() {
        if (CURRENTTAG == FragmentTag.Lab5FragmentTag) {
            AbsFragment fragment = (AbsFragment) getSupportFragmentManager().findFragmentByTag(FragmentTag.Lab5FragmentTag.name());

            if (fragment != null) {
                if (fragment.isNeedBack()==false)
                fragment.onBackPressed();
                else setMainFragment();
            }
        } else if (CURRENTTAG != FragmentTag.MainFragmentTag) {
            setMainFragment();
        }
        else
        super.onBackPressed();

    }

    public void setMainFragment(){
        CURRENTTAG = FragmentTag.MainFragmentTag;
        setTitle();
        replaceFragment(getFragment());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_TAG_BUNDLE, CURRENTTAG.name());
        super.onSaveInstanceState(outState);
    }
}
