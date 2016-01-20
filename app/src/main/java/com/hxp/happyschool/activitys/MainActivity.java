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
import com.hxp.happyschool.fragments.CateryFragment;
import com.hxp.happyschool.fragments.ClassTableFragment;
import com.hxp.happyschool.fragments.ClassUnionFragment;
import com.hxp.happyschool.fragments.ExamFragment;
import com.hxp.happyschool.fragments.HotnewsFragment;
import com.hxp.happyschool.fragments.LeaderFragment;
import com.hxp.happyschool.fragments.LibraryFragment;
import com.hxp.happyschool.fragments.ScoreFragment;
import com.hxp.happyschool.fragments.WebLogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * App主活动
 */
public class MainActivity extends Activity implements OnClickListener {


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


        //为模块栏按钮绑定事件侦听
        onBindClickListener();


        //Fragment左右滑动切换
        fragmentSwitch();
    }


    //实现OnclickListener接口方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
    private void initDatas() {

        String strClassUnion = "课堂";
        String strClassTable = "课表";
        String strLeader = "导航";
        String strCatery = "食堂";
        String strHotnews = "头条";
        String strWeblog = "微博";
        String strLibrary = "书馆";
        String strExam = "考场";
        String strScore = "成绩";

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


    //定义模块栏按钮绑定事件侦听方法
    private void onBindClickListener() {
        moduleAdapter_main.setOnItemClickListener(new ModuleAdapter_main.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positon) {
                switch (positon) {
                    case 0:
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_classUnion, new ClassTableFragment())
                                .commit();
                        break;

                    case 2:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new LeaderFragment())
                                .commit();
                        break;

                    case 3:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new CateryFragment())
                                .commit();
                        break;

                    case 4:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new HotnewsFragment())
                                .commit();
                        break;

                    case 5:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new WebLogFragment())
                                .commit();
                        break;

                    case 6:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new LibraryFragment())
                                .commit();
                        break;

                    case 7:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new ExamFragment())
                                .commit();
                        break;

                    case 8:
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main, new ScoreFragment())
                                .commit();
                        break;

                    default:
                        break;
                }
            }
        });
    }


    //定义左右滑动切换Fragment方法
    private void fragmentSwitch() {

    }
}
