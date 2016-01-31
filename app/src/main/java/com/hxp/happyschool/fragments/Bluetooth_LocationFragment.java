package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;

/**
 * Created by hxp on 16-1-31.
 */
public class Bluetooth_LocationFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rvBluetooth_location;
    private SwipeRefreshLayout swipeRefreshLayoutBluetooth_location;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth_location, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvBluetooth_location = (RecyclerView) getView().findViewById(R.id.rvBluetooth_location);
        swipeRefreshLayoutBluetooth_location = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayoutBluetooth_location);

    }
}
