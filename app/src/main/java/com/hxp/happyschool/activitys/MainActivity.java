package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.StudyAdapter_Main;
import com.hxp.happyschool.beans.StudyBean;
import com.hxp.happyschool.fragments.PersonFragment;
import com.hxp.happyschool.fragments.SettingFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * App主活动
 */
public class MainActivity extends Activity implements OnClickListener {


    //获取控件和设置成员变量
    private ImageButton imgbtn_person_main;
    private ImageButton imgbtn_setting_main;
    private ImageButton imgbtn_study_main;
    private ImageButton imgbtn_life_main;
    private ImageButton imgbtn_entertainment_main;
    private TextView tvStudy_main;
    private TextView tvLife_main;
    private TextView tvEntertainment_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化控件和成员变量和ViewPager适配器
        initParams();
    }


    //定义初始化控件和成员变量和ViewPager适配器方法
    private void initParams() {
        imgbtn_setting_main = (ImageButton) findViewById(R.id.imgbtn_setting_main);
        imgbtn_person_main = (ImageButton) findViewById(R.id.imgbtn_person_main);
        imgbtn_study_main = (ImageButton) findViewById(R.id.imgbtn_study_main);
        imgbtn_life_main = (ImageButton) findViewById(R.id.imgbtn_life_main);
        imgbtn_entertainment_main = (ImageButton) findViewById(R.id.imgbtn_entertainment_main);
        tvStudy_main = (TextView) findViewById(R.id.tvStudy_main);
        tvLife_main = (TextView) findViewById(R.id.tvLife_main);
        tvEntertainment_main = (TextView) findViewById(R.id.tvEntertainment_main);
        imgbtn_setting_main.setOnClickListener(this);
        imgbtn_person_main.setOnClickListener(this);
        imgbtn_study_main.setOnClickListener(this);
        imgbtn_life_main.setOnClickListener(this);
        imgbtn_entertainment_main.setOnClickListener(this);
    }


    //实现OnclickListener接口方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_person_main:
                getFragmentManager().beginTransaction().add(R.id.main, new SettingFragment()).commit();
                break;

            case R.id.imgbtn_setting_main:
                getFragmentManager().beginTransaction().add(R.id.main, new PersonFragment()).commit();
                break;

            case R.id.imgbtn_study_main:
                backInitColor();
                imgbtn_study_main.setImageResource(R.drawable.ic_buttombar_study_pressed);
                tvStudy_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                break;

            case R.id.imgbtn_life_main:
                backInitColor();
                imgbtn_life_main.setImageResource(R.drawable.ic_buttombar_life_pressed);
                tvLife_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                break;

            case R.id.imgbtn_entertainment_main:
                backInitColor();
                imgbtn_entertainment_main.setImageResource(R.drawable.ic_buttombar_entertainment_pressed);
                tvEntertainment_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                break;

            default:
                break;
        }
    }


    //定义恢复初始颜色方法
    private void backInitColor() {
        imgbtn_study_main.setImageResource(R.drawable.ic_buttombar_study_unpressed);
        imgbtn_life_main.setImageResource(R.drawable.ic_buttombar_life_unpressed);
        imgbtn_entertainment_main.setImageResource(R.drawable.ic_buttombar_entertainment_unpressed);
        tvStudy_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tvLife_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tvEntertainment_main.setTextColor(getResources().getColor(R.color.primaryGrey));

    }
}
