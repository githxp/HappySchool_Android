package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;

/**
 * 下载Fragment
 * Created by hxp on 16-1-31.
 */
public class Download_Classunion_Fragment extends Fragment{


    //定义成员变量
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.download_classunion_fragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化成员变量
    }
}
