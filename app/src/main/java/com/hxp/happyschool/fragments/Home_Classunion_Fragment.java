package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 医学Fragment
 * Created by hxp on 16-1-31.
 */
public class Home_Classunion_Fragment extends Fragment {


    //定义成员变量
    private View view;
    private TabLayout tablayout_topnav_classunion;
    private ViewPager viewpager_pageswitch_classunion;
    private String[] strarrTabname_classunion;
    private Medicine_Classunion_Fragment mMedicine_Classunion_Fragment;
    private Computer_Classunion_Fragment mComputer_Classunion_Fragment;
    private List<Fragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_classunion_fragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化成员变量
        initParams();

        mFragmentList.add(mMedicine_Classunion_Fragment);
        mFragmentList.add(mComputer_Classunion_Fragment);

        //设置ViewPager适配器
        setViewPagerAdapter();

        //设置tab和viewpager联系
        tablayout_topnav_classunion.setupWithViewPager(viewpager_pageswitch_classunion);
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        tablayout_topnav_classunion = (TabLayout) getView().findViewById(R.id.tablayout_topnav_classunion);
        viewpager_pageswitch_classunion = (ViewPager) getView().findViewById(R.id.viewpager_pageswitch_classunion);
        strarrTabname_classunion = new String[]{"医学", "计算机"};
        mMedicine_Classunion_Fragment = new Medicine_Classunion_Fragment();
        mComputer_Classunion_Fragment = new Computer_Classunion_Fragment();
        mFragmentList = new ArrayList<Fragment>();
    }

    //定义设置ViewPager适配器方法
    private void setViewPagerAdapter() {
        viewpager_pageswitch_classunion.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return strarrTabname_classunion[position];
            }
        });
    }
}
