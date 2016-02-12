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
import com.hxp.happyschool.fragments.BluetoothFragment;
import com.hxp.happyschool.fragments.PictureFragment;
import com.hxp.happyschool.fragments.WifiFragment;

/**
 * 位置Activity
 * Created by hxp on 16-2-10.
 */
public class LocationActivity extends Activity implements OnClickListener {

    //获取控件和设置成员变量
    //顶部图片按钮
    private ImageButton imgbtn_goback_location;

    //底部tab图片
    private ImageView img_wifiTab_location;
    private ImageView img_bluetoothTab_location;
    private ImageView img_pictureTab_location;
    private ImageView img_chartTab_location;

    //容纳图片的线性布局
    private LinearLayout linearlayout_wifiTab_location;
    private LinearLayout linearlayout_bluetoothTab_location;
    private LinearLayout linearlayout_pictureTab_location;
    private LinearLayout linearlayout_chartTab_location;

    //底部tab文字
    private TextView tv_wifiTab_location;
    private TextView tv_bluetoothTab_location;
    private TextView tv_pictureTab_location;
    private TextView tv_chartTab_location;

    //中间Fragment
    private WifiFragment mWifiFragment;
    private BluetoothFragment mBluetoothFragment;
    private PictureFragment mPictureFragment;

    //底部tab点击标记
    private boolean isWifiClicked;
    private boolean isBluetoothClicked;
    private boolean isPictureClicked;
    private boolean isChartCliCked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        //初始化控件和成员变量
        initParams();

        //预执行一次wifiTab点击
        linearlayout_wifiTab_location.performClick();
    }


    //定义初始化控件和成员变量方法
    private void initParams() {

        //顶部图片按钮
        imgbtn_goback_location = (ImageButton) findViewById(R.id.imgbtn_goback_location);

        //底部tab图片
        img_wifiTab_location = (ImageView) findViewById(R.id.img_wifiTab_location);
        img_bluetoothTab_location = (ImageView) findViewById(R.id.img_bluetoothTab_location);
        img_pictureTab_location = (ImageView) findViewById(R.id.img_pictureTab_location);
        img_chartTab_location = (ImageView) findViewById(R.id.img_chartTab_location);

        //容纳图片的线性布局
        linearlayout_wifiTab_location = (LinearLayout) findViewById(R.id.linearlayout_wifiTab_location);
        linearlayout_bluetoothTab_location = (LinearLayout) findViewById(R.id.linearlayout_bluetoothTab_location);
        linearlayout_pictureTab_location = (LinearLayout) findViewById(R.id.linearlayout_pictureTab_location);
        linearlayout_chartTab_location = (LinearLayout) findViewById(R.id.linearlayout_chartTab_location);

        //底部tab文字
        tv_wifiTab_location = (TextView) findViewById(R.id.tv_wifiTab_location);
        tv_bluetoothTab_location = (TextView) findViewById(R.id.tv_bluetoothTab_location);
        tv_pictureTab_location = (TextView) findViewById(R.id.tv_pictureTab_location);
        tv_chartTab_location = (TextView) findViewById(R.id.tv_chartTab_location);

        //中间Fragment
        mWifiFragment = new WifiFragment();
        mBluetoothFragment = new BluetoothFragment();
        mPictureFragment = new PictureFragment();

        //底部tab点击标记
        isWifiClicked = true;
        isBluetoothClicked = true;
        isPictureClicked = true;
        isChartCliCked = true;

        //添加顶部图片按钮点击事件侦听
        imgbtn_goback_location.setOnClickListener(this);

        //添加容纳图片的线性布局点击事件侦听
        linearlayout_wifiTab_location.setOnClickListener(this);
        linearlayout_bluetoothTab_location.setOnClickListener(this);
        linearlayout_pictureTab_location.setOnClickListener(this);
        linearlayout_chartTab_location.setOnClickListener(this);

        //预加载WifiFragment+LifeFragment+PictureFragment+ChartFragment
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_location, mWifiFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_location, mBluetoothFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.linearlayout_fragmentContent_location, mPictureFragment).commit();

        //隐藏WifiFragment+LifeFragment+PictureFragment+ChartFragment
        getFragmentManager().beginTransaction().hide(mWifiFragment).commit();
        getFragmentManager().beginTransaction().hide(mBluetoothFragment).commit();
        getFragmentManager().beginTransaction().hide(mPictureFragment).commit();
    }


    //实现OnclickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_goback_location:
                Log.d("click", "gebackclick");
                this.finish();
                break;

            case R.id.linearlayout_wifiTab_location:
                if (isWifiClicked) {
                    Log.d("click", "wificlick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_wifiTab_location.setImageResource(R.drawable.ic_buttombar_wifi_location_pressed);
                    tv_wifiTab_location.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mWifiFragment).commit();

                    //重置底部tab点击标记
                    isBluetoothClicked = true;
                    isPictureClicked = true;
                    isChartCliCked = true;
                    isWifiClicked = false;
                }
                break;

            case R.id.linearlayout_bluetoothTab_location:
                if (isBluetoothClicked) {
                    Log.d("click", "bluetoothclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_bluetoothTab_location.setImageResource(R.drawable.ic_buttombar_bluetooth_location_pressed);
                    tv_bluetoothTab_location.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mBluetoothFragment).commit();

                    //重置底部tab点击标记
                    isWifiClicked = true;
                    isPictureClicked = true;
                    isChartCliCked = true;
                    isBluetoothClicked = false;
                }
                break;

            case R.id.linearlayout_pictureTab_location:
                if (isPictureClicked) {
                    Log.d("click", "Pictureclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_pictureTab_location.setImageResource(R.drawable.ic_buttombar_picture_location_pressed);
                    tv_pictureTab_location.setTextColor(getResources().getColor(R.color.primaryBlue));

                    //恢复初始fragment显示状态
                    backInitFragment();

                    //加载tab按下时Fragment的显示状态
                    getFragmentManager().beginTransaction().show(mPictureFragment).commit();

                    //重置底部tab点击标记
                    isWifiClicked = true;
                    isBluetoothClicked = true;
                    isChartCliCked = true;
                    isPictureClicked = false;
                }
                break;

            case R.id.linearlayout_chartTab_location:
                if (isChartCliCked) {
                    Log.d("click", "chartclick");

                    //恢复初始颜色
                    backInitColor();

                    //加载tab按下时图片+文字的显示状态
                    img_chartTab_location.setImageResource(R.drawable.ic_buttombar_chart_location_pressed);
                    tv_chartTab_location.setTextColor(getResources().getColor(R.color.primaryBlue));

                    /*backInitFragment();
                    getFragmentManager().beginTransaction().show(mEntertainmentFragment).commit();*/

                    //重置底部tab点击标记
                    isWifiClicked = true;
                    isBluetoothClicked = true;
                    isPictureClicked = true;
                    isChartCliCked = false;
                }
                break;

            default:
                break;
        }
    }


    //定义恢复初始fragment显示状态方法
    private void backInitFragment() {
        getFragmentManager().beginTransaction().hide(mWifiFragment).commit();
        getFragmentManager().beginTransaction().hide(mBluetoothFragment).commit();
        getFragmentManager().beginTransaction().hide(mPictureFragment).commit();
    }


    //定义恢复初始颜色方法
    private void backInitColor() {
        //图片恢复初始颜色
        img_wifiTab_location.setImageResource(R.drawable.ic_buttombar_wifi_location_unpressed);
        img_bluetoothTab_location.setImageResource(R.drawable.ic_buttombar_bluetooth_location_unpressed);
        img_pictureTab_location.setImageResource(R.drawable.ic_buttombar_picture_location_unpressed);
        img_chartTab_location.setImageResource(R.drawable.ic_buttombar_chart_location_unpressed);

        //文字恢复初始颜色
        tv_wifiTab_location.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_bluetoothTab_location.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_pictureTab_location.setTextColor(getResources().getColor(R.color.primaryGrey));
        tv_chartTab_location.setTextColor(getResources().getColor(R.color.primaryGrey));
    }


    //重写父类onDestroy方法
    @Override
    protected void onDestroy() {
        Log.d("click", "ondestroy");
        super.onDestroy();
        mWifiFragment.onDetach();
        mBluetoothFragment.onDetach();
    }
}
