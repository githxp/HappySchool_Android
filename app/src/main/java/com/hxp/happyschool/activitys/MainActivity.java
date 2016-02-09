package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private ImageView img_study_main;
    private ImageView img_life_main;
    private ImageView img_entertainment_main;
    private ImageView img_more_main;

    private LinearLayout linearlayout_study_main;
    private LinearLayout linearlayout_life_main;
    private LinearLayout linearlayout_entertainment_main;
    private LinearLayout linearlayout_more_main;

    private TextView tvStudy_main;
    private TextView tvLife_main;
    private TextView tvEntertainment_main;
    private TextView tvMore_main;

    private StudyFragment mStudyFragment;
    private LifeFragment mLifeFragment;
    private EntertainmentFragment mEntertainmentFragment;

    private boolean isStudyClicked;
    private boolean isLifeClicked;
    private boolean isEntertainmentClicked;
    private boolean isMorecliCked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化控件和成员变量和ViewPager适配器
        initParams();

        linearlayout_study_main.performClick();
    }


    //定义初始化控件和成员变量和ViewPager适配器方法
    private void initParams() {
        imgbtn_setting_main = (ImageButton) findViewById(R.id.imgbtn_setting_main);
        imgbtn_person_main = (ImageButton) findViewById(R.id.imgbtn_person_main);

        img_study_main = (ImageView) findViewById(R.id.img_study_main);
        img_life_main = (ImageView) findViewById(R.id.img_life_main);
        img_entertainment_main = (ImageView) findViewById(R.id.img_entertainment_main);
        img_more_main = (ImageView) findViewById(R.id.img_more_main);

        linearlayout_study_main = (LinearLayout) findViewById(R.id.linearlayout_study_main);
        linearlayout_life_main = (LinearLayout) findViewById(R.id.linearlayout_life_main);
        linearlayout_entertainment_main = (LinearLayout) findViewById(R.id.linearlayout_entertainment_main);
        linearlayout_more_main = (LinearLayout) findViewById(R.id.linearlayout_more_main);

        tvStudy_main = (TextView) findViewById(R.id.tvStudy_main);
        tvLife_main = (TextView) findViewById(R.id.tvLife_main);
        tvEntertainment_main = (TextView) findViewById(R.id.tvEntertainment_main);
        tvMore_main = (TextView) findViewById(R.id.tvMore_main);

        imgbtn_setting_main.setOnClickListener(this);
        imgbtn_person_main.setOnClickListener(this);

        linearlayout_study_main.setOnClickListener(this);
        linearlayout_life_main.setOnClickListener(this);
        linearlayout_entertainment_main.setOnClickListener(this);
        linearlayout_more_main.setOnClickListener(this);

        isStudyClicked = true;
        isLifeClicked = true;
        isEntertainmentClicked = true;
        isMorecliCked = true;

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

            case R.id.linearlayout_study_main:
                if (isStudyClicked) {
                    Log.d("click", "studyclick");
                    backInitColor();
                    img_study_main.setImageResource(R.drawable.ic_buttombar_study_pressed);
                    tvStudy_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mStudyFragment).commit();
                    isLifeClicked = true;
                    isEntertainmentClicked = true;
                    isMorecliCked = true;
                    isStudyClicked = false;
                }
                break;

            case R.id.linearlayout_life_main:
                if (isLifeClicked) {
                    Log.d("click", "lifeclick");
                    backInitColor();
                    img_life_main.setImageResource(R.drawable.ic_buttombar_life_pressed);
                    tvLife_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mLifeFragment).commit();
                    isStudyClicked = true;
                    isEntertainmentClicked = true;
                    isMorecliCked = true;
                    isLifeClicked = false;
                }
                break;

            case R.id.linearlayout_entertainment_main:
                if (isEntertainmentClicked) {
                    Log.d("click", "entertainmentclick");
                    backInitColor();
                    img_entertainment_main.setImageResource(R.drawable.ic_buttombar_entertainment_pressed);
                    tvEntertainment_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    backInitFragment();
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();
                    isStudyClicked = true;
                    isLifeClicked = true;
                    isMorecliCked = true;
                    isEntertainmentClicked = false;
                }
                break;

            case R.id.linearlayout_more_main:
                if (isMorecliCked) {
                    Log.d("click", "moretclick");
                    backInitColor();
                    img_more_main.setImageResource(R.drawable.ic_buttombar_more_pressed);
                    tvMore_main.setTextColor(getResources().getColor(R.color.primaryBlue));
                    /*backInitFragment();
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();*/
                    isStudyClicked = true;
                    isLifeClicked = true;
                    isEntertainmentClicked = true;
                    isMorecliCked = false;
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
        img_study_main.setImageResource(R.drawable.ic_buttombar_study_unpressed);
        img_life_main.setImageResource(R.drawable.ic_buttombar_life_unpressed);
        img_entertainment_main.setImageResource(R.drawable.ic_buttombar_entertainment_unpressed);
        img_more_main.setImageResource(R.drawable.ic_buttombar_more_unpressed);

        tvStudy_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tvLife_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tvEntertainment_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tvMore_main.setTextColor(getResources().getColor(R.color.primaryGrey));
    }
}
