package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.ModuleAdapter_main;
import java.util.ArrayList;
import java.util.List;

/**
 * App主活动
 */
public class MainActivity extends Activity implements OnClickListener{


    //获取控件和设置成员变量
    private RecyclerView rvModule_main;
    private List<String> listModuleDatas;
    private ModuleAdapter_main moduleAdapter_main;
    private ImageButton imgbtn_person_main;
    private ImageButton imgbtn_setting_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //初始化控件和成员变量
        initParams();


        //初始化数据集
        initDatas();

        //为模块栏绑定适配器
        initAdapter();
    }


    //实现OnclickListener接口方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_person_main:
                Toast.makeText(MainActivity.this, "个人中心正在开发中", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imgbtn_setting_main:
                Toast.makeText(MainActivity.this, "控制中心正在开发中", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        rvModule_main = (RecyclerView) findViewById(R.id.rvModule_main);
        listModuleDatas = new ArrayList<String>();
        imgbtn_setting_main = (ImageButton) findViewById(R.id.imgbtn_setting_main);
        imgbtn_person_main = (ImageButton) findViewById(R.id.imgbtn_person_main);
        imgbtn_setting_main.setOnClickListener(this);
        imgbtn_person_main.setOnClickListener(this);
    }


    //定义初始化数据集方法
    private void initDatas(){

        String strClassUnion = "课堂";
        String strClassTable = "课表";
        String strLeader = "导航";
        String strCatery = "食堂";
        String strHotnews = "头条";
        String strWeblog = "微博";
        String strLibrary = "书馆";
        String strExam = "考场";
        String strScore = "课堂";

        listModuleDatas.add(strClassUnion);
        listModuleDatas.add(strClassTable);
        listModuleDatas.add(strLeader);
        listModuleDatas.add(strCatery);
        listModuleDatas.add(strHotnews);
        listModuleDatas.add(strWeblog);
        listModuleDatas.add(strLibrary);
        listModuleDatas.add(strExam);
        listModuleDatas.add(strScore);
    }


    //定义为模块栏绑定适配器方法
    private void initAdapter() {
        moduleAdapter_main = new ModuleAdapter_main(this, listModuleDatas);
        rvModule_main.setAdapter(moduleAdapter_main);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvModule_main.setLayoutManager(mLinearLayoutManager);
    }
}
