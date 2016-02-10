package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.MyBluetoothAdapter;

/**
 * Created by hxp on 16-1-31.
 */
public class BluetoothFragment extends Fragment implements OnClickListener {


    //定义成员变量
    private View view;
    private RecyclerView rvBluetooth_location;
    private SwipeRefreshLayout swipeRefreshLayoutBluetooth_location;
    private RelativeLayout layoutBluetoothFail_location;
    private RelativeLayout layoutBluetoothLoading_location;
    private BluetoothAdapter mBluetoothAdapter;
    private Button btnOpenBluetooth_location;
    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mIntentFilterFound;
    private IntentFilter mIntentFilterFinished;
    private MyBluetoothAdapter mMyBluetoothAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth_location, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        //初始化成员变量
        swipeRefreshLayoutBluetooth_location = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayoutBluetooth_location);
        rvBluetooth_location = (RecyclerView) getView().findViewById(R.id.rvBluetooth_location);
        mMyBluetoothAdapter = new MyBluetoothAdapter(getActivity());
        rvBluetooth_location.setAdapter(mMyBluetoothAdapter);
        layoutBluetoothFail_location = (RelativeLayout) getView().findViewById(R.id.layoutBluetoothFail_location);
        layoutBluetoothLoading_location = (RelativeLayout) getView().findViewById(R.id.layoutBluetoothLoading_location);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btnOpenBluetooth_location = (Button) getView().findViewById(R.id.btnOpenBluetooth_location);
        btnOpenBluetooth_location.setOnClickListener(this);
        mIntentFilterFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mIntentFilterFinished = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFound);
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFinished);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
                    Log.d("bluetooth", "找到设备");
                } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                    Log.d("bluetooth", "扫描完成");
                    layoutBluetoothLoading_location.setVisibility(View.GONE);
                    rvBluetooth_location.setVisibility(View.VISIBLE);
                    swipeRefreshLayoutBluetooth_location.setVisibility(View.VISIBLE);
                }
            }
        };


        //根据蓝牙是否打开显示相应界面
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.startDiscovery();
        } else {
            layoutBluetoothLoading_location.setVisibility(View.GONE);
            layoutBluetoothFail_location.setVisibility(View.VISIBLE);
        }
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenBluetooth_location:
                mBluetoothAdapter.enable();
                layoutBluetoothFail_location.setVisibility(View.GONE);
                layoutBluetoothLoading_location.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
