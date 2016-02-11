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
import com.hxp.happyschool.activitys.EateryActivity;
import com.hxp.happyschool.activitys.LocationActivity;
import com.hxp.happyschool.activitys.MapActivity;
import com.hxp.happyschool.adapters.LifeAdapter_Main;
import com.hxp.happyschool.adapters.LifeAdapter_Main.OnItemClickListener;
import com.hxp.happyschool.beans.LifeBean;

import java.util.ArrayList;
import java.util.List;

/**生活Fragment
 * Created by hxp on 16-2-3.
 */
public class LifeFragment extends Fragment {


    //定义成员变量
    private View view;
    private RecyclerView rv_life_main;
    private List<LifeBean> mLifeListDatas;
    private LifeBean mLifeBean;
    private LifeAdapter_Main mLifeAdapter_Main;
    private String[] strarrItenName_main;
    private int[] intarrImg_main;
    private Intent mIntent;


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
        rv_life_main = (RecyclerView) getView().findViewById(R.id.rv_life_main);
        mLifeListDatas = new ArrayList<LifeBean>();
        strarrItenName_main = new String[]{"地图", "位置", "食堂"};
        intarrImg_main = new int[]{R.drawable.ic_studyrv_library, R.drawable.ic_studyrv_exam, R.drawable.ic_studyrv_score};
        for (int i = 0; i < strarrItenName_main.length; i++) {
            mLifeBean = new LifeBean();
            mLifeBean.setmItemName(strarrItenName_main[i]);
            mLifeBean.setmImageResource(intarrImg_main[i]);
            mLifeListDatas.add(mLifeBean);
        }
        mLifeAdapter_Main = new LifeAdapter_Main(getActivity(), mLifeListDatas);
        rv_life_main.setAdapter(mLifeAdapter_Main);
        mLifeAdapter_Main.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(), "123" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        mIntent = new Intent(getActivity(), MapActivity.class);
                        startActivity(mIntent);
                        break;

                    case 1:
                        mIntent = new Intent(getActivity(), LocationActivity.class);
                        startActivity(mIntent);
                        break;

                    case 2:
                        mIntent = new Intent(getActivity(), EateryActivity.class);
                        startActivity(mIntent);
                        break;

                    default:
                        break;
                }
            }
        });
        LinearLayoutManager rvLife_mainLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_life_main.setLayoutManager(rvLife_mainLinearLayoutManager);
    }
}
