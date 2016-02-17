package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
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

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.WifiAdapter;
import com.hxp.happyschool.beans.WifiBean;
import com.hxp.happyschool.utils.HttpConnect;
import com.hxp.happyschool.utils.WifiDetecter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * wifiFragment
 * Created by hxp on 16-1-27.
 */
public class WifiFragment extends Fragment implements OnClickListener, OnRefreshListener {


    //设置控件和成员变量
    private View view;
    private RecyclerView rv_wifi_wifi_location;
    private WifiDetecter mWifiDetecter;
    private WifiAdapter mWifiAdapter;
    private List<WifiBean> mWifiBeanListDoInBackground;
    private List<WifiBean> mWifiBeanListOnPost;
    private List<ScanResult> mWifiList;
    private WifiBean mWifiBean;
    private RelativeLayout relativelayout_wifiFail_wifi_location;
    private RelativeLayout relativelayout_wifiLoading_wifi_location;
    private Button btn_openWifi_wifi_location;
    private SwipeRefreshLayout swiperefresh_wifiList_wifi_location;
    private HttpConnect mHttpConnect;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wifi_location, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化控件和成员变量
        rv_wifi_wifi_location = (RecyclerView) getView().findViewById(R.id.rv_wifi_wifi_location);
        btn_openWifi_wifi_location = (Button) getView().findViewById(R.id.btn_openWifi_wifi_location);
        relativelayout_wifiFail_wifi_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_wifiFail_wifi_location);
        relativelayout_wifiLoading_wifi_location = (RelativeLayout) getView().findViewById(R.id.relativelayout_wifiLoading_wifi_location);
        swiperefresh_wifiList_wifi_location = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh_wifiList_wifi_location);
        mHttpConnect = new HttpConnect();
        swiperefresh_wifiList_wifi_location.setColorSchemeResources(R.color.primaryBlue, R.color.primaryGreen, R.color.primaryRed);
        swiperefresh_wifiList_wifi_location.setOnRefreshListener(this);
        btn_openWifi_wifi_location.setOnClickListener(this);
        mWifiDetecter = new WifiDetecter(getActivity());
        mWifiBeanListDoInBackground = new ArrayList<WifiBean>();
        mWifiBeanListOnPost = new ArrayList<WifiBean>();

        //判断wifi状态
        //wifi不可用
        if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
            //wifi不可用时显示打开wifi界面布局
            showOpenWifiLayout();
        } else {
            //获取mac和ssid数据
            getMacAndSsid();

            //wifi可用时异步获取服务器wifi地址并显示
            new WifiAddressAsyncTask().execute(mWifiBeanListDoInBackground);
        }
    }


    //定义显示打开wifi界面布局方法
    public void showOpenWifiLayout() {
        relativelayout_wifiLoading_wifi_location.setVisibility(View.GONE);
        relativelayout_wifiFail_wifi_location.setVisibility(View.VISIBLE);
    }


    //定义获取mac和ssid方法
    public void getMacAndSsid() {
        Log.d("流程控制", "执行了getmacandssid方法");
        mWifiList = mWifiDetecter.getWifiList();
        //获取mac和ssid和集合
        for (int i = 0; i < mWifiList.size(); i++) {
            mWifiBean = new WifiBean();
            mWifiBean.setSsid(mWifiList.get(i).SSID);
            mWifiBean.setMac(mWifiList.get(i).BSSID);
            mWifiBean.setMdb(mWifiList.get(i).level);
            mWifiBeanListDoInBackground.add(mWifiBean);
        }
        Log.d("流程控制", "mWifiList size" + mWifiList.size());
        Log.d("流程控制", "mWifiBeanList size" + mWifiBeanListDoInBackground.size());
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_openWifi_wifi_location:
                //打开wifi
                mWifiDetecter.setWifiOpen();
                relativelayout_wifiFail_wifi_location.setVisibility(View.GONE);
                relativelayout_wifiLoading_wifi_location.setVisibility(View.VISIBLE);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            while (mWifiDetecter.getWifiStatus() != 3) {
                                sleep(500);
                            }
                            sleep(2500);
                            Log.d("流程控制", "getWifiStatus size" + mWifiDetecter.getWifiStatus());
                            getMacAndSsid();
                            Log.d("流程控制", "mWifiList size" + mWifiList.size());
                            Log.d("流程控制", "mWifiBeanList size" + mWifiBeanListDoInBackground.size());
                            new WifiAddressAsyncTask().execute(mWifiBeanListDoInBackground);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }


    //实现OnRefreshListener接口
    @Override
    public void onRefresh() {
        if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
            //wifi不可用时显示打开wifi界面布局
            relativelayout_wifiLoading_wifi_location.setVisibility(View.GONE);
            rv_wifi_wifi_location.setVisibility(View.GONE);
            swiperefresh_wifiList_wifi_location.setVisibility(View.GONE);
            relativelayout_wifiFail_wifi_location.setVisibility(View.VISIBLE);
            swiperefresh_wifiList_wifi_location.setRefreshing(false);
        } else {
            Log.d("click", "刷新方法");
            mWifiList.clear();
            mWifiBeanListDoInBackground.clear();
            getMacAndSsid();
            new WifiAddressAsyncTask().execute(mWifiBeanListDoInBackground);
        }
    }


    //定义异步获取服务器wifi地址并显示内部类
    private class WifiAddressAsyncTask extends AsyncTask<List<WifiBean>, Void, List<WifiBean>> {

        private String strHttpConnectResult;

        @Override
        protected List<WifiBean> doInBackground(List<WifiBean>... params) {
            Log.d("流程控制", "doinbackground");
            //与服务器连接
            if (getActivity() != null) {
                Log.d("click", "执行activity不为null获取网络线程");

                //组装要发送的json,格式为：数组包裹对象[{"mac":xxx,"mdb":xxx},{"mac":xxx,"mdb":xxx}...]
                JSONArray mJsonArraySend = new JSONArray();
                JSONObject mJsonObjectSend;
                for (int i = 0; i < params[0].size(); i++) {
                    mJsonObjectSend = new JSONObject();
                    try {
                        mJsonObjectSend.put("mac", params[0].get(i).getMac());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        mJsonObjectSend.put("mdb", params[0].get(i).getMdb());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mJsonArraySend.put(mJsonObjectSend);
                }

                //进行网络连接并获取返回数据
                strHttpConnectResult = mHttpConnect.httpConnect("http://121.43.116.174/class/Location/Location_Wifi_class.php", ("json=" + mJsonArraySend.toString()).getBytes());

                //对接收到的json进行解析
                Log.d("json", "json解析时");
                JSONArray mJsonArrayReceive = null;
                try {
                    mJsonArrayReceive = new JSONArray(strHttpConnectResult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("jsondoc", strHttpConnectResult);

                //保存json数据作为本地适配器数据源,格式为：(mac,地址,距离)
                for (int i = 0; i < params[0].size(); i++) {
                    for (int k = 0; k < mJsonArrayReceive.length(); k++) {
                        JSONObject mJsonObjectReceive = null;
                        try {
                            mJsonObjectReceive = mJsonArrayReceive.getJSONObject(k);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (params[0].get(i).getMac().equals(mJsonObjectReceive.optString("mac"))) {
                            params[0].get(i).setAddress(mJsonObjectReceive.optString("address"));
                            params[0].get(i).setDistance(mJsonObjectReceive.optInt("distance"));
                            break;
                        }
                    }
                }
                mWifiBeanListOnPost = mWifiBeanListDoInBackground;
            } else {
                Log.d("click", "执行activity为null");
                mWifiBeanListOnPost = null;
            }
            return mWifiBeanListOnPost;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(List<WifiBean> list) {
            Log.d("流程控制", "onPostExecute");
            super.onPostExecute(list);
            if (getActivity() != null) {
                Log.d("click", "执行activity不为null获取网络线程post");
                mWifiAdapter = new WifiAdapter(getActivity(), list);
                rv_wifi_wifi_location.setAdapter(mWifiAdapter);
                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rv_wifi_wifi_location.setLayoutManager(mLinearLayoutManager);
                relativelayout_wifiLoading_wifi_location.setVisibility(View.GONE);
                swiperefresh_wifiList_wifi_location.setVisibility(View.VISIBLE);
                swiperefresh_wifiList_wifi_location.setRefreshing(false);
                rv_wifi_wifi_location.setVisibility(View.VISIBLE);
            } else {
                Log.d("click", "执行activity为null  post");
                return;
            }
        }
    }
}