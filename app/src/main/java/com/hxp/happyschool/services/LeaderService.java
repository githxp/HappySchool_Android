package com.hxp.happyschool.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.hxp.happyschool.adapters.WifiAdapter;
import com.hxp.happyschool.beans.WifiBean;
import com.hxp.happyschool.fragments.LeaderFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-1-25.
 */
public class LeaderService extends Service {


    private ArrayList<String> mMacList;
    private WifiBean mWifiBean;
    private Intent mIntent;
    private List<WifiBean> mWifiBeanList;
    private WifiAdapter mWifiAdapter;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWifiBean = new WifiBean();
        mWifiBeanList = new ArrayList<WifiBean>();
        mWifiAdapter = new WifiAdapter(this, mWifiBeanList);
        if ("act_mac".equals(intent.getAction())) {
            //获取LeaderFragment传递来的wifi参数
            this.mMacList = intent.getStringArrayListExtra("extra_mac");
            //开启网络线程获取wifi地址
            getAddress();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //定义开启网络线程获取wifi地址方法
    private void getAddress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行服务器查询
                for (int i = 0; i < mMacList.size(); i++) {
                    Log.d("mac", "" + mMacList.get(i));
                    try {
                        URL mURL = new URL("http://121.43.116.174/class/Leader/Leader_class.php");
                        HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
                        mHttpURLConnection.setRequestMethod("POST");
                        mHttpURLConnection.setReadTimeout(5000);
                        OutputStream mOutputStream = mHttpURLConnection.getOutputStream();
                        String mPostContent = "mac=" + "'" + mMacList.get(i) + "'";
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
                        //更新WifiBean地址;
                        mWifiBean.setAddress(mJSONObject.getString("'" + mMacList.get(i) + "'"));
                        mWifiBeanList.add(mWifiBean);
                        Log.d("addressad", "" + mJSONObject.getString("'" + mMacList.get(i) + "'"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mWifiAdapter.notifyDataSetChanged();
            }
        }).start();
    }
}
