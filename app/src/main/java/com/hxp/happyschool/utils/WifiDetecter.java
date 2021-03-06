package com.hxp.happyschool.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by hxp on 16-1-25.
 */
public class WifiDetecter {


    //设置成员变量
    private WifiManager mWifiManager;
    private WifiInfo mWifiInfo;
    private List<ScanResult> mScanResult;


    //创建构造方法
    public WifiDetecter(Context context) {
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
    }


    public List<ScanResult> getWifiList(){
        //扫描wifi网络
        mWifiManager.startScan();
        mScanResult = mWifiManager.getScanResults();
        //返回wifi列表
        return mScanResult;
    }


    public int getWifiStatus(){
        return mWifiManager.getWifiState();
    }


    public void setWifiOpen(){
        mWifiManager.setWifiEnabled(true);
    }


    public void setWifiClose(){
        mWifiManager.setWifiEnabled(false);
    }
}
