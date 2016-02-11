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
import com.hxp.happyschool.databases.DatabaseImplement;
import com.hxp.happyschool.fragments.EntertainmentFragment;
import com.hxp.happyschool.fragments.LifeFragment;
import com.hxp.happyschool.fragments.StudyFragment;


/**
 * 主Activity
 */
public class MainActivity extends Activity implements OnClickListener {

    //获取控件和设置成员变量
    //顶部图片按钮
    private ImageButton imgbtn_person_main;
    private ImageButton imgbtn_setting_main;

    //底部tab图片
    private ImageView img_studyTab_main;
    private ImageView img_lifeTab_main;
    private ImageView img_entertainmentTab_main;
    private ImageView img_moreTab_main;

    //容纳图片的线性布局
    private LinearLayout linearlayout_studyTab_main;
    private LinearLayout linearlayout_lifeTab_main;
    private LinearLayout linearlayout_entertainmentTab_main;
    private LinearLayout linearlayout_moreTab_main;

    //底部tab文字
    private TextView tv_studyTab_main;
    private TextView tv_lifeTab_main;
    private TextView tv_entertainmentTab_main;
    private TextView tv_moreTab_main;

    //中间Fragment
    private StudyFragment mStudyFragment;
    private LifeFragment mLifeFragment;
    private EntertainmentFragment mEntertainmentFragment;

    //底部tab点击标记
    private boolean isStudyClicked;
    private boolean isLifeClicked;
    private boolean isEntertainmentClicked;
    private boolean isMoreCliCked;

    //数据库实现类
    private DatabaseImplement mDatabaseImplement;


    //重写父类onCreate方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //初始化控件和成员变量
        initParams();

        //预执行一次学习tab点击
        linearlayout_studyTab_main.performClick();
    }


    //定义初始化控件和成员变量方法
    private void initParams() {

        //顶部图片按钮
        imgbtn_setting_main = (ImageButton) findViewById(R.id.imgbtn_setting_main);
        imgbtn_person_main = (ImageButton) findViewById(R.id.imgbtn_person_main);

        //底部tab图片
        img_studyTab_main = (ImageView) findViewById(R.id.img_studyTab_main);
        img_lifeTab_main = (ImageView) findViewById(R.id.img_lifeTab_main);
        img_entertainmentTab_main = (ImageView) findViewById(R.id.img_entertainmentTab_main);
        img_moreTab_main = (ImageView) findViewById(R.id.img_moreTab_main);

        //容纳图片的线性布局
        linearlayout_studyTab_main = (LinearLayout) findViewById(R.id.linearlayout_studyTab_main);
        linearlayout_lifeTab_main = (LinearLayout) findViewById(R.id.linearlayout_lifeTab_main);
        linearlayout_entertainmentTab_main = (LinearLayout) findViewById(R.id.linearlayout_entertainmentTab_main);
        linearlayout_moreTab_main = (LinearLayout) findViewById(R.id.linearlayout_moreTab_main);

        //底部tab文字
        tv_studyTab_main = (TextView) findViewById(R.id.tv_studyTab_main);
        tv_lifeTab_main = (TextView) findViewById(R.id.tv_lifeTab_main);
        tv_entertainmentTab_main = (TextView) findViewById(R.id.tv_entertainmentTab_main);
        tv_moreTab_main = (TextView) findViewById(R.id.tv_moreTab_main);

        //中间Fragment
        mStudyFragment = new StudyFragment();
        mLifeFragment = new LifeFragment();
        mEntertainmentFragment = new EntertainmentFragment();

        //底部tab点击标记
        isStudyClicked = true;
        isLifeClicked = true;
        isEntertainmentClicked = true;
        isMoreCliCked = true;

        //数据库实现类
        mDatabaseImplement = new DatabaseImplement(this.getApplicationContext());

        //添加顶部图片按钮点击事件侦听
        imgbtn_setting_main.setOnClickListener(this);
        imgbtn_person_main.setOnClickListener(this);

        //添加容纳图片的线性布局点击事件侦听
        linearlayout_studyTab_main.setOnClickListener(this);
        linearlayout_lifeTab_main.setOnClickListener(this);
        linearlayout_entertainmentTab_main.setOnClickListener(this);
        linearlayout_moreTab_main.setOnClickListener(this);

        //预加载主界面StudyFragment+LifeFragment+EntertainmentFragment
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_main, mStudyFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_main, mLifeFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_main, mEntertainmentFragment).commit();

        //隐藏LifeFragment+EntertainmentFragment
        getFragmentManager().beginTransaction().hide(mLifeFragment).commit();
        getFragmentManager().beginTransaction().hide(mEntertainmentFragment).commit();
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_person_main:
                //getFragmentManager().beginTransaction().add(R.id.main, new SettingFragment()).commit();
                break;

            case R.id.imgbtn_setting_main:
                //getFragmentManager().beginTransaction().add(R.id.main, new PersonFragment()).commit();
                break;

            case R.id.linearlayout_studyTab_main:
                if (isStudyClicked) {
                    Log.d("click", "studyclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_studyTab_main.setImageResource(R.drawable.ic_buttombar_study_pressed);
                    tv_studyTab_main.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mStudyFragment).commit();

                    //重置底部tab点击标记
                    isLifeClicked = true;
                    isEntertainmentClicked = true;
                    isMoreCliCked = true;
                    isStudyClicked = false;
                }
                break;

            case R.id.linearlayout_lifeTab_main:
                if (isLifeClicked) {
                    Log.d("click", "lifeclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_lifeTab_main.setImageResource(R.drawable.ic_buttombar_life_pressed);
                    tv_lifeTab_main.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mLifeFragment).commit();

                    //重置底部tab点击标记
                    isStudyClicked = true;
                    isEntertainmentClicked = true;
                    isMoreCliCked = true;
                    isLifeClicked = false;
                }
                break;

            case R.id.linearlayout_entertainmentTab_main:
                if (isEntertainmentClicked) {
                    Log.d("click", "entertainmentclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_entertainmentTab_main.setImageResource(R.drawable.ic_buttombar_entertainment_pressed);
                    tv_entertainmentTab_main.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();

                    //重置底部tab点击标记
                    isStudyClicked = true;
                    isLifeClicked = true;
                    isMoreCliCked = true;
                    isEntertainmentClicked = false;
                }
                break;

            case R.id.linearlayout_moreTab_main:
                if (isMoreCliCked) {
                    Log.d("click", "moretclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_moreTab_main.setImageResource(R.drawable.ic_buttombar_more_pressed);
                    tv_moreTab_main.setTextColor(getResources().getColor(R.color.primaryBlue));

                    /*backInitFragment();
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();*/

                    //重置底部tab点击标记
                    isStudyClicked = true;
                    isLifeClicked = true;
                    isEntertainmentClicked = true;
                    isMoreCliCked = false;
                }
                break;

            default:
                break;
        }
    }


    //定义恢复初始fragment显示状态方法
    private void backInitFragment() {
        getFragmentManager().beginTransaction().hide(mStudyFragment).commit();
        getFragmentManager().beginTransaction().hide(mLifeFragment).commit();
        getFragmentManager().beginTransaction().hide(mEntertainmentFragment).commit();
    }


    //定义恢复初始颜色方法
    private void backInitColor() {
        //图片恢复初始颜色
        img_studyTab_main.setImageResource(R.drawable.ic_buttombar_study_unpressed);
        img_lifeTab_main.setImageResource(R.drawable.ic_buttombar_life_unpressed);
        img_entertainmentTab_main.setImageResource(R.drawable.ic_buttombar_entertainment_unpressed);
        img_moreTab_main.setImageResource(R.drawable.ic_buttombar_more_unpressed);

        //文字恢复初始颜色
        tv_studyTab_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_lifeTab_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_entertainmentTab_main.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_moreTab_main.setTextColor(getResources().getColor(R.color.primaryGrey));
    }
}
