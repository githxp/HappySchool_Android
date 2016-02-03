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
import com.hxp.happyschool.adapters.EntertainmentAdapter;
import com.hxp.happyschool.adapters.StudyAdapter_Main;
import com.hxp.happyschool.beans.EntertainmentBean;
import com.hxp.happyschool.beans.StudyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-2-3.
 */
public class EntertainmentFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rvEntertainment_main;
    private List<EntertainmentBean> mEntertainmentListDatas;
    private EntertainmentBean mEntertainmentBean;
    private EntertainmentAdapter mEntertainmentAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.entertainmentfragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //初始化成员变量
        rvEntertainment_main = (RecyclerView) getView().findViewById(R.id.rvEntertaiment_main);
        mEntertainmentListDatas = new ArrayList<EntertainmentBean>();
        strarrItenName_main = new String[]{"课堂", "课表", "书馆", "考场", "成绩", "考场", "成绩"};
        intarrImg_main = new int[]{R.drawable.ic_studyrv_classunion, R.drawable.ic_studyrv_classtable,
                R.drawable.ic_studyrv_library, R.drawable.ic_studyrv_exam, R.drawable.ic_studyrv_score,
                R.drawable.ic_studyrv_exam, R.drawable.ic_studyrv_score};
        for (int i = 0; i < strarrItenName_main.length; i++) {
            mEntertainmentBean = new EntertainmentBean();
            mEntertainmentBean.setmItemName(strarrItenName_main[i]);
            mEntertainmentBean.setmImageResource(intarrImg_main[i]);
            mEntertainmentListDatas.add(mEntertainmentBean);
        }
        mEntertainmentAdapter_Main = new EntertainmentAdapter(getActivity(), mEntertainmentListDatas);
        rvEntertainment_main.setAdapter(mEntertainmentAdapter_Main);
        LinearLayoutManager rvEntertainment_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvEntertainment_main.setLayoutManager(rvEntertainment_mainLinearLayoutManager);
    }


}
