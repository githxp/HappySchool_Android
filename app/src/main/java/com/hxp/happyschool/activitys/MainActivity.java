package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.fragments.CateryFragment;
import com.hxp.happyschool.fragments.ClassTableFragment;
import com.hxp.happyschool.fragments.ClassUnionFragment;
import com.hxp.happyschool.fragments.ExamFragment;
import com.hxp.happyschool.fragments.HotNewsFragment;
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
    private TabLayout taboutModule_main;
    private String[] mTabModuleDatas;
    private List<Fragment> mFragmentViews = new ArrayList<Fragment>();
    private ImageButton imgbtn_person_main;
    private ImageButton imgbtn_setting_main;
    private ViewPager vpFragment_main;
    private FragmentPagerAdapter mFragmentAdapter;
    private ImageButton imgbtn_more_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //初始化控件和成员变量和ViewPager适配器
        initParams();


        //初始化FragmentPagerAdapter数据集
        initPagerAdapterDatas();


        //绑定适配器
        vpFragment_main.setAdapter(mFragmentAdapter);


        //建立TabLayout和ViewPager间的联系
        taboutModule_main.setupWithViewPager(vpFragment_main);
    }


    //定义初始化控件和成员变量和ViewPager适配器方法
    private void initParams() {
        taboutModule_main = (TabLayout) findViewById(R.id.taboutModule_main);
        vpFragment_main = (ViewPager) findViewById(R.id.vpFragment_main);
        imgbtn_setting_main = (ImageButton) findViewById(R.id.imgbtn_setting_main);
        imgbtn_person_main = (ImageButton) findViewById(R.id.imgbtn_person_main);
        imgbtn_more_main = (ImageButton) findViewById(R.id.imgbtn_more_main);
        mTabModuleDatas = new String[]{"课堂", "课表", "导航", "食堂", "微博", "头条", "书馆", "考场", "成绩"};
        imgbtn_setting_main.setOnClickListener(this);
        imgbtn_person_main.setOnClickListener(this);
        imgbtn_more_main.setOnClickListener(this);
        mFragmentAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return mFragmentViews.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentViews.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabModuleDatas[position];
            }
        };
    }


    //定义初始化FragmentPagerAdapter数据集方法
    private void initPagerAdapterDatas() {

        Fragment classUnionFragmentView = new ClassUnionFragment();
        Fragment classTableFragmentView = new ClassTableFragment();
        Fragment leaderFragmentView = new LeaderFragment();
        Fragment cateryFragmentView = new CateryFragment();
        Fragment hotNewsFragmentView = new HotNewsFragment();
        Fragment webLogFragmentView = new WebLogFragment();
        Fragment libraryFragmentView = new LibraryFragment();
        Fragment examFragmentView = new ExamFragment();
        Fragment scoreFragmentView = new ScoreFragment();
        mFragmentViews.add(classUnionFragmentView);
        mFragmentViews.add(classTableFragmentView);
        mFragmentViews.add(leaderFragmentView);
        mFragmentViews.add(cateryFragmentView);
        mFragmentViews.add(hotNewsFragmentView);
        mFragmentViews.add(webLogFragmentView);
        mFragmentViews.add(libraryFragmentView);
        mFragmentViews.add(examFragmentView);
        mFragmentViews.add(scoreFragmentView);
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

            case R.id.imgbtn_more_main:
                Toast.makeText(MainActivity.this, "更多 正在开发中", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
