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
import android.support.v7.widget.LinearLayoutManager;
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
 * 蓝牙Fragment
 * Created by hxp on 16-1-31.
 */
public class BluetoothFragment extends Fragment implements OnClickListener {


    //定义成员变量
    private View view;
    private RecyclerView rv_bluetooth_bluetooth_location;
    private SwipeRefreshLayout swiperefresh_bluetoothList_bluetooth_location;
    private RelativeLayout relativelayout_bluetoothFail_bluetooth_location;
    private RelativeLayout relativelayout_bluetoothLoading_bluetooth_location;
    private BluetoothAdapter mBluetoothAdapter;
    private Button btn_openBluetooth_bluetooth_location;
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
        swiperefresh_bluetoothList_bluetooth_location = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh_bluetoothList_bluetooth_location);
        rv_bluetooth_bluetooth_location = (RecyclerView) getView().findViewById(R.id.rv_bluetooth_bluetooth_location);
        mMyBluetoothAdapter = new MyBluetoothAdapter(getActivity());
        rv_bluetooth_bluetooth_location.setAdapter(mMyBluetoothAdapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_bluetooth_bluetooth_location.setLayoutManager(mLinearLayoutManager);
        relativelayout_bluetoothFail_bluetooth_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_bluetoothFail_bluetooth_location);
        relativelayout_bluetoothLoading_bluetooth_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_bluetoothLoading_bluetooth_location);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btn_openBluetooth_bluetooth_location = (Button) getView().findViewById(R.id.btn_openBluetooth_bluetooth_location);
        btn_openBluetooth_bluetooth_location.setOnClickListener(this);
        mIntentFilterFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mIntentFilterFinished = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
                    Log.d("bluetooth", "找到设备");
                } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                    Log.d("bluetooth", "扫描完成");
                    relativelayout_bluetoothLoading_bluetooth_location.setVisibility(View.GONE);
                    rv_bluetooth_bluetooth_location.setVisibility(View.VISIBLE);
                    swiperefresh_bluetoothList_bluetooth_location.setVisibility(View.VISIBLE);
                }
            }
        };
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFound);
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFinished);


        //根据蓝牙是否打开显示相应界面
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.startDiscovery();
        } else {
            relativelayout_bluetoothLoading_bluetooth_location.setVisibility(View.GONE);
            relativelayout_bluetoothFail_bluetooth_location.setVisibility(View.VISIBLE);
        }
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openBluetooth_bluetooth_location:
                mBluetoothAdapter.enable();
                relativelayout_bluetoothFail_bluetooth_location.setVisibility(View.GONE);
                relativelayout_bluetoothLoading_bluetooth_location.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
