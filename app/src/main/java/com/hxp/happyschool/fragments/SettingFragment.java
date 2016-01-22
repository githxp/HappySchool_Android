package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;

/**
 * Created by hxp on 16-1-20.
 */
public class SettingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting,container,false);
        view.findViewById(R.id.btnComplete_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(SettingFragment.this).commit();
            }
        });
        return view;
    }
}