package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.LifeAdapter_Main;
import com.hxp.happyschool.adapters.StudyAdapter_Main;
import com.hxp.happyschool.beans.LifeBean;
import com.hxp.happyschool.beans.StudyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-2-3.
 */
public class LifeFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rvLife_main;
    private List<LifeBean> mLifeListDatas;
    private LifeBean mLifeBean;
    private LifeAdapter_Main mLifeAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lifefragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //初始化成员变量
        rvLife_main = (RecyclerView) getView().findViewById(R.id.rvLife_main);
        mLifeListDatas = new ArrayList<LifeBean>();
        strarrItenName_main = new String[]{"导航", "地图", "WIFI定位", "蓝牙定位", "图像定位", "位置图", "食堂"};
        intarrImg_main = new int[]{R.drawable.ic_studyrv_classunion, R.drawable.ic_studyrv_classtable,
                R.drawable.ic_studyrv_library, R.drawable.ic_studyrv_exam, R.drawable.ic_studyrv_score,
                R.drawable.ic_studyrv_score, R.drawable.ic_studyrv_score};
        for (int i = 0; i < strarrItenName_main.length; i++) {
            mLifeBean = new LifeBean();
            mLifeBean.setmItemName(strarrItenName_main[i]);
            mLifeBean.setmImageResource(intarrImg_main[i]);
            mLifeListDatas.add(mLifeBean);
        }
        mLifeAdapter_Main = new LifeAdapter_Main(getActivity(), mLifeListDatas);
        rvLife_main.setAdapter(mLifeAdapter_Main);
        LinearLayoutManager rvLife_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvLife_main.setLayoutManager(rvLife_mainLinearLayoutManager);
    }
}
