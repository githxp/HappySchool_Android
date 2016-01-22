package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.hxp.happyschool.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hxp on 16-1-20.
 */
public class LeaderFragment extends Fragment {


    //获取控件和设置成员变量
    private View view;
    private MapView mapViewAmap_leader;
    private AMap mAmap;
    private UiSettings mUiSettings;


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
        mapViewAmap_leader = (MapView) view.findViewById(R.id.mapViewAmap_leader);
        mapViewAmap_leader.onCreate(savedInstanceState);
        mAmap = mapViewAmap_leader.getMap();
        mUiSettings = mAmap.getUiSettings();


        //设置地图属性
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
        //设置定位到地图中心并自动旋转
        mAmap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
        //设置地图默认中心点
        mAmap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(28.296474, 112.873986)));
        //设置地图缩放级别
        mAmap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //添加地图标记
        mAmap.addMarker(new MarkerOptions().position(new LatLng(28.296663,112.875708)).perspective(true)
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
                .title("十栋学生公寓").snippet("十栋学生公寓")).showInfoWindow();




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
    }
}