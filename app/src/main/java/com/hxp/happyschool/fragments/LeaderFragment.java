package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hxp.happyschool.databases.DatabaseImplement;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.hxp.happyschool.R;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hxp on 16-1-20.
 */
public class LeaderFragment extends Fragment implements AMapLocationListener, LocationSource, View.OnClickListener {


    //获取控件和设置成员变量
    //地图
    private View view;
    private MapView mapViewAmap_leader;
    private AMap mAmap;
    private UiSettings mUiSettings;
    private FloatingActionButton fabSearch_leader;
    private FloatingActionButton fabNavigation_leader;
    private DatabaseImplement mDatabaseImplement;


    //定位
    private AMapLocationClient mAMapLocationClient;
    private AMapLocationClientOption mAMapLocationClientOption;
    private OnLocationChangedListener mOnLocationChangedListener;
    private MyLocationStyle mMyLocationStyle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.leader, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //初始化控件和成员变量
        //地图
        mapViewAmap_leader = (MapView) view.findViewById(R.id.mapViewAmap_leader);
        mapViewAmap_leader.onCreate(savedInstanceState);
        mAmap = mapViewAmap_leader.getMap();
        mUiSettings = mAmap.getUiSettings();
        mDatabaseImplement = new DatabaseImplement(getActivity().getApplicationContext());
        //控件
        fabSearch_leader = (FloatingActionButton) getView().findViewById(R.id.fabSearch_leader);
        fabNavigation_leader = (FloatingActionButton) getView().findViewById(R.id.fabNavigation_leader);
        fabSearch_leader.setOnClickListener(this);
        fabNavigation_leader.setOnClickListener(this);
        //定位
        mAMapLocationClient = new AMapLocationClient(getActivity());
        mAMapLocationClientOption = new AMapLocationClientOption();


        //设置地图/定位属性
        initMap();
    }


    //定义设置地图属性方法
    private void initMap() {
        //设置Logo在底部左侧位置
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        //设置显示比例尺
        mUiSettings.setScaleControlsEnabled(true);
        //设置比例尺在底部右侧显示
        mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM);
        //设置显示定位按钮
        mUiSettings.setMyLocationButtonEnabled(true);
        //设置显示指南针按钮
        mUiSettings.setCompassEnabled(true);
        //显示室内地图
        mAmap.showIndoorMap(true);
        //关闭交通地图
        mAmap.setTrafficEnabled(false);
        mAmap.setLocationSource(this);
        mAmap.setMyLocationEnabled(true);
        //设置地图默认中心点
        //mAmap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(28.296474, 112.873986)));
        //设置地图缩放级别
        mAmap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //添加地图标记
        addMarks();
    }


    //定义添加地图标记方法
    private void addMarks() {
        /* mAmap.addMarker(new MarkerOptions().position(new LatLng(28.296663,112.875708)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("临床楼").snippet("临床教学楼")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.298174,112.873101)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("一栋学生公寓").snippet("鲁迅苑")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.297806,112.87317)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("二栋学生公寓").snippet("二栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.29663,112.873321)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("三栋学生公寓").snippet("三栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.295964,112.873412)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("四栋学生公寓").snippet("四栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.297461,112.873363)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("五栋学生公寓").snippet("五栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.29774,112.872055)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("六栋学生公寓").snippet("六栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.295747,112.873353)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("七栋学生公寓").snippet("仲景苑")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.295539,112.873315)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("八栋学生公寓").snippet("八栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.298165,112.871996)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("九栋学生公寓").snippet("九栋学生公寓")).showInfoWindow();

        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.29509,112.873423)).perspective(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                .anchor(0.5f,1.0f)
                .title("十栋学生公寓").snippet("十栋学生公寓")).showInfoWindow();*/
    }


    //定义实现AMapLocationListener接口方法
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mOnLocationChangedListener.onLocationChanged(aMapLocation);
                mMyLocationStyle = new MyLocationStyle();
                mMyLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location))
                        .radiusFillColor(Color.argb(0, 86, 171, 228))
                        .anchor(0.5f, 0.5f).strokeWidth(0f);
                mAmap.setMyLocationStyle(mMyLocationStyle);
            } else {
                Toast.makeText(getActivity(), "错误码：" + aMapLocation.getErrorCode() + "错误信息："
                        + aMapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    //定义实现LocationSource接口方法
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mOnLocationChangedListener = onLocationChangedListener;
        //设置定位监听
        mAMapLocationClient.setLocationListener(this);
        //高精度定位模式,开启网络定位和GPS定位,优先返回精度高的定位
        mAMapLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        //设置允许模拟位置
        mAMapLocationClientOption.setMockEnable(true);
        //绑定定位属性
        mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
        //设置定位到地图中心并自动旋转
        mAmap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        //开始定位
        mAMapLocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        mOnLocationChangedListener = null;
        if (mAMapLocationClient != null) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
        mAMapLocationClient = null;
        mAMapLocationClientOption = null;
    }


    //实现OnclickListener接口方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabSearch_leader:
                Toast.makeText(getActivity(), "搜索功能正在开发", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fabNavigation_leader:
                Toast.makeText(getActivity(), "导航功能正在开发", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapViewAmap_leader.onSaveInstanceState(outState);
    }


    @Override
    public void onResume() {
        super.onResume();
        mapViewAmap_leader.onResume();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewAmap_leader.onLowMemory();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapViewAmap_leader.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewAmap_leader.onDestroy();
        mAMapLocationClient.stopLocation();
        mAMapLocationClient.onDestroy();
        mAMapLocationClient = null;
        mAMapLocationClientOption = null;
    }
}