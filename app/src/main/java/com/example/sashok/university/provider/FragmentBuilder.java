package com.example.sashok.university.provider;

import android.os.Bundle;

import com.example.sashok.university.fragment.AbsFragment;
import com.example.sashok.university.fragment.Laba1Fragment;
import com.example.sashok.university.fragment.Laba2Fragment;
import com.example.sashok.university.fragment.Laba3Fragment;
import com.example.sashok.university.fragment.Laba4Fragment;
import com.example.sashok.university.fragment.Laba5Fragment;
import com.example.sashok.university.fragment.Laba6Fragment;
import com.example.sashok.university.fragment.Laba7Fragment;
import com.example.sashok.university.fragment.Laba8Fragment;
import com.example.sashok.university.fragment.MainWindowFragment;
import com.example.sashok.university.helper.FragmentTag;

/**
 * Created by sashok on 4.10.17.
 */

public class FragmentBuilder {
    protected Bundle args;
    protected FragmentTag tag;

    public FragmentBuilder(FragmentTag tag) {
        this.tag = tag;
    }

    public FragmentBuilder setArgs(Bundle args) {
        this.args = args;
        return this;
    }

    public AbsFragment build() {
        switch (tag) {
            case Lab1FragmentTag:
                return Laba1Fragment.newInstance(args);
            case Lab2FragmentTag:
                return Laba2Fragment.newInstance(args);
            case Lab3FragmentTag:
                return Laba3Fragment.newInstance(args);
            case Lab4FragmentTag:
                return Laba4Fragment.newInstance(args);
            case Lab5FragmentTag:
                return Laba5Fragment.newInstance(args);
            case Lab6FragmentTag:
                return Laba6Fragment.newInstance(args);
            case Lab7FragmentTag:
                return Laba7Fragment.newInstance(args);
            case Lab8FragmentTag:
                return Laba8Fragment.newInstance(args);
            case MainFragmentTag:
                return MainWindowFragment.newInstance(args);
            default:
                return null;
        }

    }
}
