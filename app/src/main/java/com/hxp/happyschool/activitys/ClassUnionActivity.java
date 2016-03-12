package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.fragments.Computer_Classunion_Fragment;
import com.hxp.happyschool.fragments.Download_Classunion_Fragment;
import com.hxp.happyschool.fragments.Home_Classunion_Fragment;
import com.hxp.happyschool.fragments.Medicine_Classunion_Fragment;
import com.hxp.happyschool.fragments.Person_Classunion_Fragment;
import com.hxp.happyschool.fragments.Upload_Classunion_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 课堂Activity
 * Created by hxp on 16-1-20.
 */
public class ClassUnionActivity extends Activity implements OnClickListener {


    //设置控件和成员变量
    //底部tab图片
    private ImageView img_homeTab_classunion;
    private ImageView img_uploadTab_classunion;
    private ImageView img_downloadTab_classunion;
    private ImageView img_personTab_classunion;

    //容纳图片的线性布局
    private LinearLayout linearlayout_homeTab_classunion;
    private LinearLayout linearlayout_uploadTab_classunion;
    private LinearLayout linearlayout_downloadTab_classunion;
    private LinearLayout linearlayout_personTab_classunion;

    //底部tab文字
    private TextView tv_homeTab_classunion;
    private TextView tv_uploadTab_classunion;
    private TextView tv_downloadTab_classunion;
    private TextView tv_personTab_classunion;

    //中间Fragment
    private Home_Classunion_Fragment mHome_Classunion_Fragment;
    private Upload_Classunion_Fragment mUpload_Classunion_Fragment;
    private Download_Classunion_Fragment mDownload_Classunion_Fragment;
    private Person_Classunion_Fragment mPerson_Classunion_Fragment;

    //底部tab点击标记
    private boolean isHomeClicked;
    private boolean isUploadClicked;
    private boolean isDownloadClicked;
    private boolean isPersonClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classunion);

        //初始化控件和成员变量
        initParams();

        //预加载主界面Fragment
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_classunion, mHome_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_classunion, mUpload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_classunion, mDownload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_classunion, mPerson_Classunion_Fragment).commit();

        //隐藏Fragment
        getFragmentManager().beginTransaction().hide(mHome_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mUpload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mDownload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mPerson_Classunion_Fragment).commit();

        //预执行一次首页tab点击
        linearlayout_homeTab_classunion.performClick();
    }


    //定义初始化控件和成员变量方法
    private void initParams() {
        img_homeTab_classunion = (ImageView) findViewById(R.id.img_homeTab_classunion);
        img_uploadTab_classunion = (ImageView) findViewById(R.id.img_uploadTab_classunion);
        img_downloadTab_classunion = (ImageView) findViewById(R.id.img_downloadTab_classunion);
        img_personTab_classunion = (ImageView) findViewById(R.id.img_personTab_classunion);
        linearlayout_homeTab_classunion = (LinearLayout) findViewById(R.id.linearlayout_homeTab_classunion);
        linearlayout_uploadTab_classunion = (LinearLayout) findViewById(R.id.linearlayout_uploadTab_classunion);
        linearlayout_downloadTab_classunion = (LinearLayout) findViewById(R.id.linearlayout_downloadTab_classunion);
        linearlayout_personTab_classunion = (LinearLayout) findViewById(R.id.linearlayout_personTab_classunion);
        tv_homeTab_classunion = (TextView) findViewById(R.id.tv_homeTab_classunion);
        tv_uploadTab_classunion = (TextView) findViewById(R.id.tv_uploadTab_classunion);
        tv_downloadTab_classunion = (TextView) findViewById(R.id.tv_downloadTab_classunion);
        tv_personTab_classunion = (TextView) findViewById(R.id.tv_personTab_classunion);
        mHome_Classunion_Fragment = new Home_Classunion_Fragment();
        mUpload_Classunion_Fragment = new Upload_Classunion_Fragment();
        mDownload_Classunion_Fragment = new Download_Classunion_Fragment();
        mPerson_Classunion_Fragment = new Person_Classunion_Fragment();
        isHomeClicked = true;
        isUploadClicked = true;
        isDownloadClicked = true;
        isPersonClicked = true;
        linearlayout_homeTab_classunion.setOnClickListener(this);
        linearlayout_uploadTab_classunion.setOnClickListener(this);
        linearlayout_downloadTab_classunion.setOnClickListener(this);
        linearlayout_personTab_classunion.setOnClickListener(this);
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearlayout_homeTab_classunion:
                if (isHomeClicked) {
                    Log.d("click", "home");
                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_homeTab_classunion.setImageResource(R.drawable.ic_buttombar_home_pressed);
                    tv_homeTab_classunion.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mHome_Classunion_Fragment).commit();
                    Log.d("click", "homefragment");

                    //重置底部tab点击标记
                    isUploadClicked = true;
                    isDownloadClicked = true;
                    isPersonClicked = true;
                    isHomeClicked = false;
                }
                break;

            case R.id.linearlayout_uploadTab_classunion:
                if (isUploadClicked) {
                    Log.d("click", "upload");
                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_uploadTab_classunion.setImageResource(R.drawable.ic_buttombar_upload_pressed);
                    tv_uploadTab_classunion.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mUpload_Classunion_Fragment).commit();

                    //重置底部tab点击标记
                    isHomeClicked = true;
                    isDownloadClicked = true;
                    isPersonClicked = true;
                    isUploadClicked = false;
                }
                break;

            case R.id.linearlayout_downloadTab_classunion:
                if (isDownloadClicked) {
                    Log.d("click", "download");
                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_downloadTab_classunion.setImageResource(R.drawable.ic_buttombar_download_pressed);
                    tv_downloadTab_classunion.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mDownload_Classunion_Fragment).commit();

                    //重置底部tab点击标记
                    isHomeClicked = true;
                    isUploadClicked = true;
                    isPersonClicked = true;
                    isDownloadClicked = false;
                }

                break;

            case R.id.linearlayout_personTab_classunion:
                if (isPersonClicked) {
                    Log.d("click", "person");
                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_personTab_classunion.setImageResource(R.drawable.ic_buttombar_person_pressed);
                    tv_personTab_classunion.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mPerson_Classunion_Fragment).commit();

                    //重置底部tab点击标记
                    isHomeClicked = true;
                    isUploadClicked = true;
                    isDownloadClicked = true;
                    isPersonClicked = false;
                }

                break;

            default:
                break;
        }
    }


    //定义恢复初始fragment显示状态方法
    private void backInitFragment() {
        getFragmentManager().beginTransaction().hide(mHome_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mUpload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mDownload_Classunion_Fragment).commit();
        getFragmentManager().beginTransaction().hide(mPerson_Classunion_Fragment).commit();
    }


    //定义恢复初始颜色方法
    private void backInitColor() {
        //图片恢复初始颜色
        img_homeTab_classunion.setImageResource(R.drawable.ic_buttombar_home_unpressed);
        img_uploadTab_classunion.setImageResource(R.drawable.ic_buttombar_upload_unpressed);
        img_downloadTab_classunion.setImageResource(R.drawable.ic_buttombar_download_unpressed);
        img_personTab_classunion.setImageResource(R.drawable.ic_buttombar_person_unpressed);

        //文字恢复初始颜色
        tv_homeTab_classunion.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_uploadTab_classunion.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_downloadTab_classunion.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_personTab_classunion.setTextColor(getResources().getColor(R.color.primaryGrey));
    }
}
