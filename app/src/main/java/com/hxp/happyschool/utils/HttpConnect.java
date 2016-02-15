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
import java.util.UUID;

/**
 * Created by hxp on 16-2-14.
 */
public class HttpConnect {

    //设置成员变量
    private BufferedReader mBufferedReader;
    private StringBuffer mStringBuffer;
    private String strHttpConnectResult;
    //边界符
    private String strBoundary;
    //换行符
    private String strChangeLine;
    //boundary前缀
    private String strPrefix;


    //定义网络连接方法(目标url,要写入的json数据)
    public String httpConnect(String url, byte[] datas) {
        Log.d("click", "进入byte构造函数");
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
    public String httpConnect(String url, String name, String filepath) {
        Log.d("click", "进入三个参数的构造函数");

        //设置边界符
        strBoundary = UUID.randomUUID().toString();
        Log.d("click", "UUID是：" + strBoundary);
        //设置换行符
        strChangeLine = "\r\n";
        //设置boundary前缀
        strPrefix = "--";

        try {
            //建立http连接
            URL mURL = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mURL.openConnection();

            //设置http连接参数
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + strBoundary);

            //获取输出流对象
            OutputStream mOutputStream = mHttpURLConnection.getOutputStream();

            //创建post
            mOutputStream.write((strPrefix + strBoundary + strChangeLine).getBytes());
            mOutputStream.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + filepath.substring(filepath.lastIndexOf("/") + 1) + "\"" + strChangeLine).getBytes());
            Log.d("click", "Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + filepath.substring(filepath.lastIndexOf("/") + 1) + "\"");

            //写入要发送的文件内容
            File mFile = new File(filepath);
            FileInputStream mFileInputStream = new FileInputStream(mFile);
            byte[] byt = new byte[1024 * 2];
            int len;
            while ((len = mFileInputStream.read(byt)) != -1) {
                mOutputStream.write(byt, 0, len);
            }

            //写入换行
            mOutputStream.write(strChangeLine.getBytes());

            //写入结束标记
            mOutputStream.write((strPrefix + strBoundary + strPrefix + strChangeLine).getBytes());


            //获取服务器数据
            mBufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
            mStringBuffer = new StringBuffer();
            String str;
            while ((str = mBufferedReader.readLine()) != null) {
                mStringBuffer.append(str);
            }
            strHttpConnectResult = mStringBuffer.toString();

            //关闭流和连接
            mOutputStream.close();
            mFileInputStream.close();
            mBufferedReader.close();
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
