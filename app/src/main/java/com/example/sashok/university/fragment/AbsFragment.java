package com.example.sashok.university.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by sashok on 4.10.17.
 */

public abstract class AbsFragment extends Fragment {
    public boolean isNeedBack() {
        return needBack;
    }

    public void setNeedBack(boolean needBack) {
        this.needBack = needBack;
    }

    protected boolean needBack=false;
    public  void  onBackPressed(){
        needBack=true;
        this.getActivity().onBackPressed();
    }
}
