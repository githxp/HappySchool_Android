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
import com.hxp.happyschool.activitys.HotNewsActivity;
import com.hxp.happyschool.activitys.LibraryActivity;
import com.hxp.happyschool.activitys.ScoreActivity;
import com.hxp.happyschool.activitys.WebLogActivity;
import com.hxp.happyschool.adapters.EntertainmentAdapter;
import com.hxp.happyschool.adapters.EntertainmentAdapter.OnItemClickListener;
import com.hxp.happyschool.beans.EntertainmentBean;
import com.hxp.happyschool.beans.StudyBean;

import java.util.ArrayList;
import java.util.List;

/**娱乐Fragment
 * Created by hxp on 16-2-3.
 */
public class EntertainmentFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rv_entertaiment_main;
    private List<EntertainmentBean> mEntertainmentListDatas;
    private EntertainmentBean mEntertainmentBean;
    private EntertainmentAdapter mEntertainmentAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;
    private Intent mIntent;


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
        rv_entertaiment_main = (RecyclerView) getView().findViewById(R.id.rv_entertaiment_main);
        mEntertainmentListDatas = new ArrayList<EntertainmentBean>();
        strarrItenName_main = new String[]{"微博", "头条"};
        intarrImg_main = new int[]{R.drawable.ic_studyrv_classunion, R.drawable.ic_studyrv_classtable};
        for (int i = 0; i < strarrItenName_main.length; i++) {
            mEntertainmentBean = new EntertainmentBean();
            mEntertainmentBean.setmItemName(strarrItenName_main[i]);
            mEntertainmentBean.setmImageResource(intarrImg_main[i]);
            mEntertainmentListDatas.add(mEntertainmentBean);
        }
        mEntertainmentAdapter_Main = new EntertainmentAdapter(getActivity(), mEntertainmentListDatas);
        rv_entertaiment_main.setAdapter(mEntertainmentAdapter_Main);
        mEntertainmentAdapter_Main.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(), "123" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        mIntent = new Intent(getActivity(), WebLogActivity.class);
                        startActivity(mIntent);
                        break;

                    case 1:
                        mIntent = new Intent(getActivity(), HotNewsActivity.class);
                        startActivity(mIntent);
                        break;

                    default:
                        break;
                }
            }
        });
        LinearLayoutManager rvEntertainment_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_entertaiment_main.setLayoutManager(rvEntertainment_mainLinearLayoutManager);
    }


}
