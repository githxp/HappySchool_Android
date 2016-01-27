package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.WifiAdapter;
import com.hxp.happyschool.beans.WifiBean;
import com.hxp.happyschool.services.LeaderService;
import com.hxp.happyschool.utils.WifiDetecter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-1-27.
 */
public class LocationFragment extends Fragment implements OnClickListener {


    //设置成员变量
    private View view;
    private RecyclerView rvWifi_location;
    private WifiDetecter mWifiDetecter;
    private WifiAdapter mWifiAdapter;
    private Intent mIntent;
    private List<WifiBean> mWifiBeanList;
    private List<ScanResult> mWifiList;
    private WifiBean mWifiBean;
    private ArrayList<String> mMacList;
    private RelativeLayout layoutWifiFail_location;
    private Button btnOpenWifi_location;
    private FloatingActionButton fabWifi_location;
    private FloatingActionButton fabPicture_location;
    private FloatingActionButton fabChart_location;
    private SwipeRefreshLayout swfreshWifiList_location;
    private BroadcastReceiver mBroadcastReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter mIntentFilter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //初始化成员变量
        rvWifi_location = (RecyclerView) getView().findViewById(R.id.rvWifi_location);
        btnOpenWifi_location = (Button) getView().findViewById(R.id.btnOpenWifi_location);
        fabWifi_location = (FloatingActionButton) getView().findViewById(R.id.fabWifi_location);
        fabPicture_location = (FloatingActionButton) getView().findViewById(R.id.fabPicture_location);
        fabChart_location = (FloatingActionButton) getView().findViewById(R.id.fabChart_location);
        layoutWifiFail_location = (RelativeLayout) getView().findViewById(R.id.layoutWifiFail_location);
        swfreshWifiList_location = (SwipeRefreshLayout) getView().findViewById(R.id.swfreshWifiList_location);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("act_success");
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("act_success".equals(intent.getAction())) {
                    swfreshWifiList_location.setRefreshing(false);
                }
            }
        };
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, mIntentFilter);
        swfreshWifiList_location.setColorSchemeResources(R.color.primaryBlue, R.color.primaryRed, R.color.primaryGreen);
        swfreshWifiList_location.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWifiList = mWifiDetecter.getWifiList();
                getMacAddress();
                initService();
                mWifiAdapter.notifyDataSetChanged();
            }
        });
        btnOpenWifi_location.setOnClickListener(this);
        fabWifi_location.setOnClickListener(this);
        fabPicture_location.setOnClickListener(this);
        fabChart_location.setOnClickListener(this);
        mWifiDetecter = new WifiDetecter(getActivity());
        mWifiBeanList = new ArrayList<WifiBean>();
        mWifiList = mWifiDetecter.getWifiList();

        //判断wifi是否打开并显示相应布局
        setOpenWifi();
        //为RecycleView设置适配器
        setAdapter();
        //获取ssid和mac地址
        getMacAddress();
        //开启地址查询服务
        initService();
    }


    //定义判断wifi是否打开并显示相应布局方法
    public void setOpenWifi() {
        if (mWifiDetecter.getWifiStatus() == 1) {
            swfreshWifiList_location.setVisibility(View.GONE);
            rvWifi_location.setVisibility(View.GONE);
            layoutWifiFail_location.setVisibility(View.VISIBLE);
        }
    }


    //定义为RecycleView设置适配器方法
    private void setAdapter() {
        mWifiAdapter = new WifiAdapter(getActivity(), mWifiBeanList);
        rvWifi_location.setAdapter(mWifiAdapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvWifi_location.setLayoutManager(mLinearLayoutManager);
    }


    //定义获取ssid和mac地址方法
    private void getMacAddress() {
        if (mWifiDetecter.getWifiStatus() == 2 || mWifiDetecter.getWifiStatus() == 3) {
            for (int i = 0; i < mWifiList.size(); i++) {
                mWifiBean = new WifiBean();
                mWifiBean.setSsid(mWifiList.get(i).SSID);
                mWifiBean.setMac(mWifiList.get(i).BSSID);
                mWifiBeanList.add(mWifiBean);
            }
            mMacList = new ArrayList<String>();
            for (int k = 0; k < mWifiBeanList.size(); k++) {
                mMacList.add(mWifiBeanList.get(k).getMac());
            }
            Toast.makeText(getActivity(), "ok2", Toast.LENGTH_SHORT).show();

        }
    }


    //定义开启地址查询服务方法
    private void initService() {
        if (mWifiDetecter.getWifiStatus() == 2 || mWifiDetecter.getWifiStatus() == 3) {
            mIntent = new Intent(getActivity(), LeaderService.class);
            mIntent.setAction("act_mac");
            mIntent.putStringArrayListExtra("extra_mac", mMacList);
            getActivity().startService(mIntent);
            Toast.makeText(getActivity(), "ok1", Toast.LENGTH_SHORT).show();
        }
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenWifi_location:
                mWifiDetecter.setWifiOpen();
                layoutWifiFail_location.setVisibility(View.GONE);
                swfreshWifiList_location.setVisibility(View.VISIBLE);
                rvWifi_location.setVisibility(View.VISIBLE);
                if (mWifiDetecter.getWifiStatus() == 2 || mWifiDetecter.getWifiStatus() == 3) {
                    getMacAddress();
                    initService();
                }
                Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fabWifi_location:
                Toast.makeText(getActivity(), "ok1", Toast.LENGTH_SHORT).show();

                break;

            case R.id.fabPicture_location:
                Toast.makeText(getActivity(), "ok2", Toast.LENGTH_SHORT).show();

                break;

            case R.id.fabChart_location:
                Toast.makeText(getActivity(), "ok3", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}
