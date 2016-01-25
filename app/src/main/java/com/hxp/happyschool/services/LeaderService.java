package com.hxp.happyschool.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hxp on 16-1-25.
 */
public class LeaderService extends Service {


    private String mac;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获取LeaderFragment传递来的wifi参数
        this.mac = intent.getStringExtra("extra_mac");
        Log.d("aaaa",""+mac);
        if ("act_mac".equals(intent.getAction())) {
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
                try {
                    URL mURL = new URL("http://121.43.116.174/class/Leader/Leader_class.php");
                    HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
                    mHttpURLConnection.setRequestMethod("POST");
                    mHttpURLConnection.setReadTimeout(5000);
                    OutputStream mOutputStream = mHttpURLConnection.getOutputStream();
                    Log.d("aabb",""+mac);
                    String mPostContent = "mac="+mac;
                    mOutputStream.write(mPostContent.getBytes());
                    BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
                    StringBuffer mStringBuffer = new StringBuffer();
                    String str;
                    while ((str = mBufferedReader.readLine()) != null){
                        mStringBuffer.append(str);
                    }
                    Log.d("address",""+mStringBuffer.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
