package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.hxp.happyschool.adapters.WifiAdapter;
import com.hxp.happyschool.beans.WifiBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hxp on 16-1-27.
 */
public class LocationFragment extends Fragment implements OnClickListener, OnRefreshListener {


    //设置控件和成员变量
    private View view;
    private RecyclerView rvWifi_location;
    private WifiDetecter mWifiDetecter;
    private WifiAdapter mWifiAdapter;
    private List<WifiBean> mWifiBeanList;
    private List<ScanResult> mWifiList;
    private WifiBean mWifiBean;
    private RelativeLayout layoutWifiFail_location;
    private RelativeLayout layoutWifiLoading_location;
    private Button btnOpenWifi_location;
    private FloatingActionButton fabBluetooth_location;
    private FloatingActionButton fabPicture_location;
    private FloatingActionButton fabChart_location;
    private SwipeRefreshLayout swipeRefreshLayout_location;
    Bluetooth_LocationFragment mBluetooth_LocationFragment;
    private boolean mOnClick = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //初始化控件和成员变量
        rvWifi_location = (RecyclerView) getView().findViewById(R.id.rvWifi_location);
        btnOpenWifi_location = (Button) getView().findViewById(R.id.btnOpenWifi_location);
        fabBluetooth_location = (FloatingActionButton) getView().findViewById(R.id.fabBluetooth_location);
        fabPicture_location = (FloatingActionButton) getView().findViewById(R.id.fabPicture_location);
        fabChart_location = (FloatingActionButton) getView().findViewById(R.id.fabChart_location);
        layoutWifiFail_location = (RelativeLayout) getView().findViewById(R.id.layoutWifiFail_location);
        layoutWifiLoading_location = (RelativeLayout) getView().findViewById(R.id.layoutWifiLoading_location);
        swipeRefreshLayout_location = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout_location);
        swipeRefreshLayout_location.setColorSchemeResources(R.color.primaryBlue, R.color.primaryGreen, R.color.primaryRed);
        swipeRefreshLayout_location.setOnRefreshListener(this);
        btnOpenWifi_location.setOnClickListener(this);
        fabBluetooth_location.setOnClickListener(this);
        fabPicture_location.setOnClickListener(this);
        fabChart_location.setOnClickListener(this);
        mWifiDetecter = new WifiDetecter(getActivity());
        mWifiBeanList = new ArrayList<WifiBean>();
        mBluetooth_LocationFragment = new Bluetooth_LocationFragment();

        //判断wifi状态
        if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
            //wifi不可用时显示打开wifi界面布局
            showOpenWifiLayout();
        } else {
            getMacAndSsid();
            //wifi可用时异步获取服务器wifi地址并显示
            new WifiAddressAsyncTask().execute(mWifiBeanList);
        }
    }


    //定义显示打开wifi界面布局方法
    public void showOpenWifiLayout() {
        layoutWifiLoading_location.setVisibility(View.GONE);
        layoutWifiFail_location.setVisibility(View.VISIBLE);
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
            mWifiBeanList.add(mWifiBean);
        }
        Log.d("流程控制", "mWifiList size" + mWifiList.size());
        Log.d("流程控制", "mWifiBeanList size" + mWifiBeanList.size());
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOpenWifi_location:
                //打开wifi
                mWifiDetecter.setWifiOpen();
                layoutWifiFail_location.setVisibility(View.GONE);
                layoutWifiLoading_location.setVisibility(View.VISIBLE);
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
                            Log.d("流程控制", "mWifiBeanList size" + mWifiBeanList.size());
                            new WifiAddressAsyncTask().execute(mWifiBeanList);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;

            case R.id.fabBluetooth_location:
                if (mOnClick) {
                    swipeRefreshLayout_location.setVisibility(View.GONE);
                    rvWifi_location.setVisibility(View.GONE);
                    layoutWifiFail_location.setVisibility(View.GONE);
                    layoutWifiLoading_location.setVisibility(View.GONE);
                    //第一次添加蓝牙Fragment
                    getFragmentManager().beginTransaction().add(R.id.layoutLocaton, mBluetooth_LocationFragment).commit();
                    fabBluetooth_location.setImageResource(R.drawable.ic_location_wifi);
                    mOnClick = false;
                } else {
                    if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
                        layoutWifiFail_location.setVisibility(View.VISIBLE);
                    } else {
                        swipeRefreshLayout_location.setVisibility(View.VISIBLE);
                        rvWifi_location.setVisibility(View.VISIBLE);
                        //layoutWifiLoading_location.setVisibility(View.VISIBLE);
                    }
                    getFragmentManager().beginTransaction().remove(mBluetooth_LocationFragment).commit();
                    fabBluetooth_location.setImageResource(R.drawable.ic_location_bluetooth);
                    mOnClick = true;
                }


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


    //实现OnRefreshListener接口方法
    @Override
    public void onRefresh() {
        if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
            //wifi不可用时显示打开wifi界面布局
            layoutWifiLoading_location.setVisibility(View.GONE);
            rvWifi_location.setVisibility(View.GONE);
            swipeRefreshLayout_location.setVisibility(View.GONE);
            layoutWifiFail_location.setVisibility(View.VISIBLE);
            swipeRefreshLayout_location.setRefreshing(false);
        } else {
            Log.d("流程控制", "刷新方法");
            mWifiList.clear();
            mWifiBeanList.clear();
            getMacAndSsid();
            new WifiAddressAsyncTask().execute(mWifiBeanList);
        }
    }


    //异步获取服务器wifi地址并显示
    private class WifiAddressAsyncTask extends AsyncTask<List<WifiBean>, Void, List<WifiBean>> {


        private BufferedReader mBufferedReader;
        private StringBuffer mStringBuffer;


        @Override
        protected List<WifiBean> doInBackground(List<WifiBean>... params) {
            Log.d("流程控制", "doinbackground");
            //与服务器连接
            try {
                URL mURL = new URL("http://121.43.116.174/class/Location/Location_class.php");
                HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
                mHttpURLConnection.setRequestMethod("POST");
                mHttpURLConnection.setReadTimeout(5000);
                mHttpURLConnection.setDoOutput(true);
                OutputStream mOutputStream = mHttpURLConnection.getOutputStream();
                //组装要发送的json,格式为：数组包裹对象[{"mac":xxx,"mdb":xxx},{"mac":xxx,"mdb":xxx}...]
                JSONArray mJsonArraySend = new JSONArray();
                JSONObject mJsonObjectSend;
                for (int i = 0; i < params[0].size(); i++) {
                    mJsonObjectSend = new JSONObject();
                    mJsonObjectSend.put("mac", params[0].get(i).getMac());
                    mJsonObjectSend.put("mdb", params[0].get(i).getMdb());
                    mJsonArraySend.put(mJsonObjectSend);
                }

                mOutputStream.write(("json=" + mJsonArraySend.toString()).getBytes());
                Log.d("json:", "mac=" + mJsonArraySend.toString());

                //获取服务器数据
                mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
                mStringBuffer = new StringBuffer();
                String str;
                while ((str = mBufferedReader.readLine()) != null) {
                    mStringBuffer.append(str);
                }
                mBufferedReader.close();
                mOutputStream.close();
                //进行json解析
                Log.d("json", "json解析时");
                JSONArray mJsonArrayReceive = new JSONArray(mStringBuffer.toString());
                Log.d("jsondoc", mStringBuffer.toString());
                //保存json数据作为本地适配器数据源,格式为：(mac,地址,距离)
                for (int i = 0; i < params[0].size(); i++) {
                    for (int k = 0; k < mJsonArrayReceive.length(); k++) {
                        JSONObject mJsonObjectReceive = mJsonArrayReceive.getJSONObject(k);
                        if (params[0].get(i).getMac().equals(mJsonObjectReceive.optString("mac"))) {
                            params[0].get(i).setAddress(mJsonObjectReceive.optString("address"));
                            params[0].get(i).setDistance(mJsonObjectReceive.optInt("distance"));
                            break;
                        }
                    }
                }
                mHttpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mWifiBeanList;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(List<WifiBean> list) {
            Log.d("流程控制", "onPostExecute");
            super.onPostExecute(list);
            mWifiAdapter = new WifiAdapter(getActivity(), list);
            rvWifi_location.setAdapter(mWifiAdapter);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvWifi_location.setLayoutManager(mLinearLayoutManager);
            swipeRefreshLayout_location.setVisibility(View.VISIBLE);
            swipeRefreshLayout_location.setRefreshing(false);
            rvWifi_location.setVisibility(View.VISIBLE);
            layoutWifiLoading_location.setVisibility(View.GONE);
        }
    }
}