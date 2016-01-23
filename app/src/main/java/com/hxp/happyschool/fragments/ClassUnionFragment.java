package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.happyschool.R;

/**
 * Created by hxp on 16-1-20.
 */
public class ClassUnionFragment extends Fragment {

    private View view;

    private FloatingActionButton fab_classunion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classunion, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab_classunion = (FloatingActionButton) getView().findViewById(R.id.fab_classunion);
        fab_classunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
