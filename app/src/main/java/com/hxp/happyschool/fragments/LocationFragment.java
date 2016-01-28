package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
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
 * Created by hxp on 16-1-27.
 */
public class LocationFragment extends Fragment implements OnClickListener {


    //设置控件和成员变量
    private View view;
    private RecyclerView rvWifi_location;
    private WifiDetecter mWifiDetecter;
    private WifiAdapter mWifiAdapter;
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
    private OnRefreshListener mOnRefreshListener;
    private WifiAddressAsyncTask mWifiAddressAsyncTask;


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
        initParams();

        //判断wifi状态
        if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
            //wifi不可用时显示打开wifi界面布局
            showOpenWifiLayout();
        } else {
            //获取mac和ssid
            getMacAndSsid();
            //wifi可用时异步获取服务器wifi地址并显示
            mWifiAddressAsyncTask.execute(mMacList);
        }
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        rvWifi_location = (RecyclerView) getView().findViewById(R.id.rvWifi_location);
        btnOpenWifi_location = (Button) getView().findViewById(R.id.btnOpenWifi_location);
        fabWifi_location = (FloatingActionButton) getView().findViewById(R.id.fabWifi_location);
        fabPicture_location = (FloatingActionButton) getView().findViewById(R.id.fabPicture_location);
        fabChart_location = (FloatingActionButton) getView().findViewById(R.id.fabChart_location);
        layoutWifiFail_location = (RelativeLayout) getView().findViewById(R.id.layoutWifiFail_location);
        swfreshWifiList_location = (SwipeRefreshLayout) getView().findViewById(R.id.swfreshWifiList_location);
        swfreshWifiList_location.setColorSchemeResources(R.color.primaryBlue, R.color.primaryRed, R.color.primaryGreen);
        swfreshWifiList_location.setOnRefreshListener(mOnRefreshListener);
        btnOpenWifi_location.setOnClickListener(this);
        fabWifi_location.setOnClickListener(this);
        fabPicture_location.setOnClickListener(this);
        fabChart_location.setOnClickListener(this);
        mWifiDetecter = new WifiDetecter(getActivity());
        mWifiBeanList = new ArrayList<WifiBean>();
        mWifiList = mWifiDetecter.getWifiList();
        mWifiAddressAsyncTask = new WifiAddressAsyncTask();
        mOnRefreshListener = new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "进入onrefreh", Toast.LENGTH_SHORT).show();
                //wifi不可用或正在关闭时显示打开wifi界面
                if (mWifiDetecter.getWifiStatus() == 1 || mWifiDetecter.getWifiStatus() == 0) {
                    swfreshWifiList_location.setRefreshing(false);
                    //显示打开wifi界面布局
                    showOpenWifiLayout();
                } else {
                    //重置获取要刷新的mac数据集
                    Toast.makeText(getActivity(), "网络连通", Toast.LENGTH_SHORT).show();
                    mWifiList.clear();
                    mWifiList = mWifiDetecter.getWifiList();
                    mWifiBeanList.clear();
                    for (int i = 0; i < mWifiList.size(); i++) {
                        mWifiBean = new WifiBean();
                        mWifiBean.setSsid(mWifiList.get(i).SSID);
                        mWifiBean.setMac(mWifiList.get(i).BSSID);
                        mWifiBeanList.add(mWifiBean);
                    }
                    //获取mac
                    mMacList.clear();
                    mMacList = new ArrayList<String>();
                    for (int k = 0; k < mWifiBeanList.size(); k++) {
                        mMacList.add(mWifiBeanList.get(k).getMac());
                    }
                    //异步获取服务器wifi地址并显示
                    new WifiAddressAsyncTask().execute(mMacList);
                    swfreshWifiList_location.setRefreshing(false);
                }
            }
        };
    }


    //定义显示打开wifi界面布局方法
    public void showOpenWifiLayout() {
        swfreshWifiList_location.setVisibility(View.GONE);
        rvWifi_location.setVisibility(View.GONE);
        layoutWifiFail_location.setVisibility(View.VISIBLE);
    }


    //定义获取mac和ssid方法
    public void getMacAndSsid() {
        //获取mac和ssid和集合
        for (int i = 0; i < mWifiList.size(); i++) {
            mWifiBean = new WifiBean();
            mWifiBean.setSsid(mWifiList.get(i).SSID);
            mWifiBean.setMac(mWifiList.get(i).BSSID);
            mWifiBeanList.add(mWifiBean);
        }
        //获取mac
        mMacList = new ArrayList<String>();
        for (int k = 0; k < mWifiBeanList.size(); k++) {
            mMacList.add(mWifiBeanList.get(k).getMac());
        }
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOpenWifi_location:
                //打开wifi
                mWifiDetecter.setWifiOpen();
                //设置布局显示/隐藏
                rvWifi_location.setVisibility(View.VISIBLE);
                //显示刷新动画
                swfreshWifiList_location.setRefreshing(true);
                //执行刷新动作
                mOnRefreshListener.onRefresh();
                layoutWifiFail_location.setVisibility(View.GONE);
                swfreshWifiList_location.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "启动异步任务", Toast.LENGTH_SHORT).show();
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


    //异步获取服务器wifi地址并显示
    private class WifiAddressAsyncTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {

        //定义成员变量
        private ArrayList<String> mAddressList;


        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            Log.d("async", "doinbackground");

            //初始化成员变量
            mAddressList = new ArrayList<String>();
            //获取指定mac的地址信息

            for (int i = 0; i < params[0].size(); i++) {
                try {
                    URL mURL = new URL("http://121.43.116.174/class/Leader/Leader_class.php");
                    HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
                    mHttpURLConnection.setRequestMethod("POST");
                    mHttpURLConnection.setReadTimeout(5000);
                    OutputStream mOutputStream = mHttpURLConnection.getOutputStream();
                    String mPostContent = "mac=" + "'" + params[0].get(i) + "'";
                    mOutputStream.write(mPostContent.getBytes());
                    BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
                    StringBuffer mStringBuffer = new StringBuffer();
                    String str;
                    while ((str = mBufferedReader.readLine()) != null) {
                        mStringBuffer.append(str);
                    }
                    mBufferedReader.close();
                    mOutputStream.close();
                    //进行json解析
                    JSONObject mJSONObject = new JSONObject(mStringBuffer.toString());
                    Log.d("json",mJSONObject.getString("'" + mMacList.get(i) + "'"));
                    Log.d("json",mStringBuffer.toString());
                    //把地址存入mAddress变量中
                    mAddressList.add(mJSONObject.getString("'" + mMacList.get(i) + "'"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return mAddressList;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<String> list) {
            Log.d("async", "onPostExecute");
            super.onPostExecute(list);
            //为RecycleView设置适配器
            for (int i = 0; i < list.size(); i++) {
                mWifiBeanList.get(i).setAddress(list.get(i));
            }
            mWifiAdapter = new WifiAdapter(getActivity(), mWifiBeanList);
            rvWifi_location.setAdapter(mWifiAdapter);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvWifi_location.setLayoutManager(mLinearLayoutManager);
        }
    }
}
