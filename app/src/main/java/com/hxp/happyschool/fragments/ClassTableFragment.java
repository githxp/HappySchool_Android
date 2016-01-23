package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.WifiAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-1-20.
 */
public class ClassTableFragment extends Fragment {


    private View view;
    private RecyclerView rvWifi_leader;
    private WifiAdapter mWifiAdapter;
    private List<String> mDatas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classtable, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvWifi_leader = (RecyclerView) getView().findViewById(R.id.rvWifi_leader);
        mDatas = new ArrayList<String>();
        mDatas.add("1");
        mDatas.add("2");
        mWifiAdapter = new WifiAdapter(getActivity(), mDatas);
        rvWifi_leader.setAdapter(mWifiAdapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvWifi_leader.setLayoutManager(mLinearLayoutManager);
    }
}