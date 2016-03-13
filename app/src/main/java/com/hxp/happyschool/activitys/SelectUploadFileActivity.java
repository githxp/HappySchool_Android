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
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.SelectUploadFileAdapter;
import com.hxp.happyschool.beans.FileResultBean;
import com.hxp.happyschool.beans.FileScanBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by hxp on 16-3-12.
 */
public class SelectUploadFileActivity extends Activity implements OnClickListener {

    //获取控件和设置成员变量
    private RelativeLayout relativelayout_fileScan_classunion;
    private RecyclerView rv_uploadFile_classunion;
    private SelectUploadFileAdapter mSelectUploadFileAdapter;
    private List<FileScanBean> datas;
    private File sdCardPath;
    private Handler mHandler;
    private Button btn_uploadFile_clssunion;
    private List<FileResultBean> resultDatas;
    private int fileResultNum;


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
                Log.d("length", "发送消息");
                Message mMessage = Message.obtain();
                mMessage.what = 100;
                mHandler.sendMessage(mMessage);
            }
        }.start();

        //为rv_uploadFile_classunion设置适配器
        setAdapter();

        //返回选择结果给Upload_Classunion_Fragment
        setResult(200);
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        relativelayout_fileScan_classunion = (RelativeLayout) findViewById(R.id.relativelayout_fileScan_classunion);
        rv_uploadFile_classunion = (RecyclerView) findViewById(R.id.rv_uploadFile_classunion);
        datas = new ArrayList<FileScanBean>();
        btn_uploadFile_clssunion = (Button) findViewById(R.id.btn_uploadFile_clssunion);
        btn_uploadFile_clssunion.setOnClickListener(this);
        resultDatas = new ArrayList<FileResultBean>();
        fileResultNum = 0;
        sdCardPath = new File("/storage");
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
        mSelectUploadFileAdapter.setOnItemClickListener(new SelectUploadFileAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(SelectUploadFileActivity.this, "ok" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //定义扫描本地文件方法
    public List<FileScanBean> getFileDatas(File file) {
        File[] fileArr = file.listFiles();
        if (fileArr != null) {
            Log.d("length1", "" + fileArr.length);
            for (int i = 0; i < fileArr.length; i++) {
                if (fileArr[i].isFile()) {
                    if (fileArr[i].getName().substring(fileArr[i].getName().lastIndexOf(".") + 1).equals("ppt") ||
                            fileArr[i].getName().substring(fileArr[i].getName().lastIndexOf(".") + 1).equals("pptx") ||
                            fileArr[i].getName().substring(fileArr[i].getName().lastIndexOf(".") + 1).equals("pps") ||
                            fileArr[i].getName().substring(fileArr[i].getName().lastIndexOf(".") + 1).equals("ppsx")) {
                        FileScanBean mFileScanBean = new FileScanBean();
                        mFileScanBean.setFileName(fileArr[i].getName());
                        datas.add(mFileScanBean);
                        fileResultNum++;
                    }
                } else if (fileArr[i].isDirectory()) {
                    Log.d("length", "" + fileArr[i].toString());
                    getFileDatas(fileArr[i]);
                }
            }
        }
        Log.d("length", "结束");
        Log.d("length", "数量" + fileResultNum);

        return datas;
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_uploadFile_clssunion:
                for (int i = 0; i < fileResultNum; i++) {
                }
                break;

            default:

                break;
        }
    }
}

