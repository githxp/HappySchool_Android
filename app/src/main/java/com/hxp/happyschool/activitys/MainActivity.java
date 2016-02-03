package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.fragments.EntertainmentFragment;
import com.hxp.happyschool.fragments.LifeFragment;
import com.hxp.happyschool.fragments.PersonFragment;
import com.hxp.happyschool.fragments.SettingFragment;
import com.hxp.happyschool.fragments.StudyFragment;

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
    private StudyFragment mStudyFragment;
    private LifeFragment mLifeFragment;
    private EntertainmentFragment mEntertainmentFragment;
    private boolean isStudyclicked;
    private boolean isLifeclicked;
    private boolean isEntertainmentclicked;


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
        isStudyclicked = false;
        isLifeclicked = true;
        isEntertainmentclicked = true;
        mStudyFragment = new StudyFragment();
        mLifeFragment = new LifeFragment();
        mEntertainmentFragment = new EntertainmentFragment();
        getFragmentManager().beginTransaction().add(R.id.fragmentContent_main, mStudyFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.fragmentContent_main, mLifeFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.fragmentContent_main, mEntertainmentFragment).commit();
        getFragmentManager().beginTransaction().hide(mLifeFragment).commit();
        getFragmentManager().beginTransaction().hide(mEntertainmentFragment).commit();
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
                if (isStudyclicked) {
                    Log.d("click", "studyclick");
                    backInitColor();
                    imgbtn_study_main.setImageResource(R.drawable.ic_buttombar_study_pressed);
                    tvStudy_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mStudyFragment).commit();
                    isLifeclicked = true;
                    isEntertainmentclicked = true;
                    isStudyclicked = false;
                }
                break;

            case R.id.imgbtn_life_main:
                if (isLifeclicked) {
                    Log.d("click", "lifeclick");
                    backInitColor();
                    imgbtn_life_main.setImageResource(R.drawable.ic_buttombar_life_pressed);
                    tvLife_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mLifeFragment).commit();
                    isStudyclicked = true;
                    isEntertainmentclicked = true;
                    isLifeclicked = false;
                }
                break;

            case R.id.imgbtn_entertainment_main:
                if (isEntertainmentclicked) {
                    Log.d("click", "entertainmentclick");
                    backInitColor();
                    imgbtn_entertainment_main.setImageResource(R.drawable.ic_buttombar_entertainment_pressed);
                    tvEntertainment_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();
                    isStudyclicked = true;
                    isLifeclicked = true;
                    isEntertainmentclicked = false;
                }
                break;

            default:
                break;
        }
    }


    //定义恢复初始fragment方法
    private void backInitFragment() {
        getFragmentManager().beginTransaction().hide(mStudyFragment).commit();
        getFragmentManager().beginTransaction().hide(mLifeFragment).commit();
        getFragmentManager().beginTransaction().hide(mEntertainmentFragment).commit();
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
