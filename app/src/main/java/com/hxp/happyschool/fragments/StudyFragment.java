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
import com.hxp.happyschool.adapters.StudyAdapter_Main;
import com.hxp.happyschool.beans.StudyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 16-2-2.
 */
public class StudyFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rvStudy_main;
    private List<StudyBean> mStudyListDatas;
    private StudyBean mStudyBean;
    private StudyAdapter_Main mStudyAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.studyfragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //初始化成员变量
        rvStudy_main = (RecyclerView) getView().findViewById(R.id.rvStudy_main);
        mStudyListDatas = new ArrayList<StudyBean>();
        strarrItenName_main = new String[]{"课堂", "课表", "书馆", "考场", "成绩"};
        intarrImg_main = new int[]{R.drawable.ic_studyrv_classunion, R.drawable.ic_studyrv_classtable,
                R.drawable.ic_studyrv_library, R.drawable.ic_studyrv_exam, R.drawable.ic_studyrv_score};
        for (int i = 0; i < strarrItenName_main.length; i++) {
            mStudyBean = new StudyBean();
            mStudyBean.setmItemName(strarrItenName_main[i]);
            mStudyBean.setmImageResource(intarrImg_main[i]);
            mStudyListDatas.add(mStudyBean);
        }
        mStudyAdapter_Main = new StudyAdapter_Main(getActivity(), mStudyListDatas);
        rvStudy_main.setAdapter(mStudyAdapter_Main);
        LinearLayoutManager rvStudy_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvStudy_main.setLayoutManager(rvStudy_mainLinearLayoutManager);
    }


}
