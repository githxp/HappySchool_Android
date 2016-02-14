package com.hxp.happyschool.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Created by hxp on 16-2-14.
 */
public class HttpConnect {

    //设置成员变量
    private BufferedReader mBufferedReader;
    private StringBuffer mStringBuffer;
    private String strHttpConnectResult;

    //定义网络连接方法(目标url,要写入的json数据)
    public String httpConnect(String url, byte[] datas) {
        try {
            //建立http连接
            URL mURL = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();

            //设置http连接参数
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setDoOutput(true);

            //获取输出流对象
            OutputStream mOutputStream = mHttpURLConnection.getOutputStream();

            mOutputStream.write(datas);

            //获取服务器数据
            mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
            mStringBuffer = new StringBuffer();
            String str;
            while ((str = mBufferedReader.readLine()) != null) {
                mStringBuffer.append(str);
            }
            strHttpConnectResult = mStringBuffer.toString();

            //关闭流和连接
            mBufferedReader.close();
            mOutputStream.close();
            mHttpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strHttpConnectResult;
    }


    //定义网络连接方法(目标url,要写入的文件数据)
    public String httpConnect(String url, String filename, byte[] datas) {
        try {
            //建立http连接
            URL mURL = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();

            //设置http连接参数
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setDoOutput(true);

            //建立要写入数据流对象
            File mFile = new File(filename);
            FileInputStream mFileInputStream = new FileInputStream(mFile);

            //获取输出流对象
            OutputStream mOutputStream = mHttpURLConnection.getOutputStream();

            //写入post请求头
            mOutputStream.write(datas);

            //写入输出流
            byte[] byt = new byte[1024 * 2];
            int len;
            while ((len = mFileInputStream.read(byt)) != -1) {
                mOutputStream.write(byt, 0, len);
            }


            //获取服务器数据
            mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
            mStringBuffer = new StringBuffer();
            String str;
            while ((str = mBufferedReader.readLine()) != null) {
                mStringBuffer.append(str);
            }
            strHttpConnectResult = mStringBuffer.toString();

            //关闭流和连接
            mBufferedReader.close();
            mOutputStream.close();
            mHttpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strHttpConnectResult;
    }
}
