package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.activitys.ClassTableActivity;
import com.hxp.happyschool.activitys.ClassUnionActivity;
import com.hxp.happyschool.activitys.ExamActivity;
import com.hxp.happyschool.activitys.LibraryActivity;
import com.hxp.happyschool.activitys.ScoreActivity;
import com.hxp.happyschool.adapters.StudyAdapter_Main;
import com.hxp.happyschool.beans.StudyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习Fragment
 */
public class StudyFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rv_study_main;
    private List<StudyBean> mStudyListDatas;
    private StudyBean mStudyBean;
    private StudyAdapter_Main mStudyAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;
    private Intent mIntent;


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
        rv_study_main = (RecyclerView) getView().findViewById(R.id.rv_study_main);
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
        rv_study_main.setAdapter(mStudyAdapter_Main);
        mStudyAdapter_Main.setOnItemClickListener(new StudyAdapter_Main.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(), "123" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        mIntent = new Intent(getActivity(), ClassUnionActivity.class);
                        startActivity(mIntent);
                        break;

                    case 1:
                        mIntent = new Intent(getActivity(), ClassTableActivity.class);
                        startActivity(mIntent);
                        break;

                    case 2:
                        mIntent = new Intent(getActivity(), LibraryActivity.class);
                        startActivity(mIntent);
                        break;

                    case 3:
                        mIntent = new Intent(getActivity(), ExamActivity.class);
                        startActivity(mIntent);
                        break;

                    case 4:
                        mIntent = new Intent(getActivity(), ScoreActivity.class);
                        startActivity(mIntent);
                        break;

                    default:
                        break;
                }
            }
        });
        LinearLayoutManager rvStudy_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_study_main.setLayoutManager(rvStudy_mainLinearLayoutManager);
    }


}
