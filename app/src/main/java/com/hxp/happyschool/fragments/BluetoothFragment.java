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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.MyBluetoothAdapter;
import com.hxp.happyschool.beans.BluetoothBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 蓝牙Fragment
 * Created by hxp on 16-1-31.
 */
public class BluetoothFragment extends Fragment implements OnClickListener, OnRefreshListener {


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
    private List<BluetoothBean> mBluetoothBeanList;
    private BluetoothBean mBluetoothBean;
    private MyBluetoothAdapter mMyBluetoothAdapter;
    private Set<BluetoothDevice> mBluetoothDeviceSet;
    private Intent mIntent;


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
        relativelayout_bluetoothFail_bluetooth_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_bluetoothFail_bluetooth_location);
        relativelayout_bluetoothLoading_bluetooth_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_bluetoothLoading_bluetooth_location);
        mBluetoothBeanList = new ArrayList<BluetoothBean>();
        mMyBluetoothAdapter = new MyBluetoothAdapter(getActivity(), mBluetoothBeanList);
        rv_bluetooth_bluetooth_location.setAdapter(mMyBluetoothAdapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_bluetooth_bluetooth_location.setLayoutManager(mLinearLayoutManager);
        swiperefresh_bluetoothList_bluetooth_location.setColorSchemeResources(R.color.primaryBlue, R.color.primaryGreen, R.color.primaryRed);
        swiperefresh_bluetoothList_bluetooth_location.setOnRefreshListener(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btn_openBluetooth_bluetooth_location = (Button) getView().findViewById(R.id.btn_openBluetooth_bluetooth_location);
        btn_openBluetooth_bluetooth_location.setOnClickListener(this);

        //IntentFilter——找到蓝牙设备
        mIntentFilterFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        //IntentFilter——扫描完成
        mIntentFilterFinished = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        //定义广播接收器
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mIntent = intent;
                if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
                    BluetoothDevice mBluetoothDeviceNew = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (mBluetoothDeviceNew.getBondState() != BluetoothDevice.BOND_BONDED) {
                        mBluetoothBean = new BluetoothBean();
                        mBluetoothBean.setName(mBluetoothDeviceNew.getName());
                        mBluetoothBean.setRssi(intent.getExtras().getString(BluetoothDevice.EXTRA_RSSI));
                        mBluetoothBeanList.add(mBluetoothBean);
                        mMyBluetoothAdapter.notifyDataSetChanged();
                        if (relativelayout_bluetoothLoading_bluetooth_location.getVisibility() == View.VISIBLE) {
                            relativelayout_bluetoothLoading_bluetooth_location.setVisibility(View.GONE);
                            Log.d("bluetooth", "loadingview");
                        }
                        if (rv_bluetooth_bluetooth_location.getVisibility() == View.GONE) {
                            rv_bluetooth_bluetooth_location.setVisibility(View.VISIBLE);
                            Log.d("bluetooth", "rvview");
                        }
                        if (swiperefresh_bluetoothList_bluetooth_location.getVisibility() == View.GONE) {
                            swiperefresh_bluetoothList_bluetooth_location.setVisibility(View.VISIBLE);
                            Log.d("bluetooth", "swview");
                        }

                        Log.d("bluetooth", "找到设备");
                    }
                } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                    Log.d("bluetooth", "扫描完成");
                    Toast.makeText(getActivity(), "已完成蓝牙扫描", Toast.LENGTH_SHORT).show();
                }
            }
        };

        //注册广播接收器
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFound);
        getActivity().registerReceiver(mBroadcastReceiver, mIntentFilterFinished);

        //为recycleview设置adapter


        //根据蓝牙是否打开显示相应界面
        if (mBluetoothAdapter.isEnabled()) {

            //根据蓝牙是否打开执行变量初始化
            mBluetoothDeviceSet = mBluetoothAdapter.getBondedDevices();

            //获取扫描信息
            if (mBluetoothDeviceSet.size() > 0) {
                Log.d("bluetooth", "发现老设备");
                for (BluetoothDevice mBluetoothDevice : mBluetoothDeviceSet) {
                    mBluetoothBean = new BluetoothBean();
                    mBluetoothBean.setName(mBluetoothDevice.getName());
                    mBluetoothBean.setRssi(mIntent.getExtras().getString(BluetoothDevice.EXTRA_RSSI));
                    mBluetoothBeanList.add(mBluetoothBean);
                }
            }

            //根据蓝牙是否正在搜索执行搜索或不做操作
            if (!mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.startDiscovery();
                Toast.makeText(getActivity(), "开始蓝牙搜索", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "蓝牙正在搜索", Toast.LENGTH_SHORT).show();
            }
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


    //实现OnRefreshListener接口
    @Override
    public void onRefresh() {
        mBluetoothBeanList.clear();
        //根据蓝牙是否正在搜索执行搜索或不做操作
        if (!mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.startDiscovery();
            Toast.makeText(getActivity(), "刷新蓝牙设备", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "蓝牙正在搜索", Toast.LENGTH_SHORT).show();
        }
        swiperefresh_bluetoothList_bluetooth_location.setRefreshing(false);
    }


    //实现父类onDestroy方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
