package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.SelectUploadFileAdapter;
import com.hxp.happyschool.beans.FileScanBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by hxp on 16-3-12.
 */
public class SelectUploadFileActivity extends Activity {

    //获取控件和设置成员变量
    private RelativeLayout relativelayout_fileScan_classunion;
    private RecyclerView rv_uploadFile_classunion;
    private SelectUploadFileAdapter mSelectUploadFileAdapter;
    private List<FileScanBean> datas;
    private File sdCardPath;
    private ProgressBar probar_fileScan_classunion;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectuploadfile);

        //初始化控件和成员变量
        initParams();

        //扫描本地文件
        relativelayout_fileScan_classunion.setVisibility(View.VISIBLE);
        rv_uploadFile_classunion.setVisibility(View.GONE);
        new Thread() {
            @Override
            public void run() {
                super.run();
                getFileDatas(sdCardPath);
                Message mMessage = Message.obtain();
                mMessage.what = 100;
            }
        }.start();

        //为rv_uploadFile_classunion设置适配器
        setAdapter();
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        relativelayout_fileScan_classunion = (RelativeLayout) findViewById(R.id.relativelayout_fileScan_classunion);
        rv_uploadFile_classunion = (RecyclerView) findViewById(R.id.rv_uploadFile_classunion);
        datas = new ArrayList<FileScanBean>();
        sdCardPath = new File("/storage");
        probar_fileScan_classunion = (ProgressBar) findViewById(R.id.probar_fileScan_classunion);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    relativelayout_fileScan_classunion.setVisibility(View.GONE);
                    rv_uploadFile_classunion.setVisibility(View.VISIBLE);
                    setAdapter();
                }
            }
        };
    }


    //定义为rv_uploadFile_classunion设置适配器方法
    public void setAdapter() {
        mSelectUploadFileAdapter = new SelectUploadFileAdapter(this, datas);
        rv_uploadFile_classunion.setAdapter(mSelectUploadFileAdapter);
        LinearLayoutManager rvSelectFileLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_uploadFile_classunion.setLayoutManager(rvSelectFileLinearLayoutManager);
    }

    //定义扫描本地文件方法
    public List<FileScanBean> getFileDatas(File file) {
        File[] fileArr = file.listFiles();
        if (fileArr.length > 0) {
            Log.d("length1", "" + fileArr.length);
            for (int i = 0; i < fileArr.length; i++) {
                if (!fileArr[i].isDirectory()) {
                    FileScanBean mFileScanBean = new FileScanBean();
                    mFileScanBean.setFileName(fileArr[i].getName());
                    datas.add(mFileScanBean);
                } else if (fileArr[i].isDirectory()) {
                    File dirPath = new File(fileArr[i].getAbsolutePath());
                    Log.d("length", "" + dirPath.toString());
                    if (dirPath != null) {
                        getFileDatas(dirPath);
                    }
                }
            }
        }
        return datas;
    }
}

