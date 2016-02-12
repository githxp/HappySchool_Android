package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.hxp.happyschool.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图像定位Fragment
 * Created by hxp on 16-2-12.
 */
public class PictureFragment extends Fragment implements OnClickListener, SurfaceHolder.Callback {


    //设置控件和成员变量
    private View view;
    private SurfaceView surfaceview_camera_picture_location;
    private Button btn_openCamera_picture_location;
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;
    private Camera.PictureCallback mCameraPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            String mPath = Environment.getExternalStorageDirectory().getPath();
            File mFile = new File(mPath + "/HappySchool/Location/Picture/temp.jpg");
            try {
                FileOutputStream mFileOutputStream = new FileOutputStream(mFile);
                mFileOutputStream.write(data);
                mFileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.picture_location, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化控件和成员变量
        surfaceview_camera_picture_location = (SurfaceView) getView().findViewById(R.id.surfaceview_camera_picture_location);
        btn_openCamera_picture_location = (Button) getView().findViewById(R.id.btn_openCamera_picture_location);
        getCamera();
        mSurfaceHolder = surfaceview_camera_picture_location.getHolder();
        mSurfaceHolder.addCallback(this);
        startCameraPreview(mCamera, mSurfaceHolder);
        btn_openCamera_picture_location.setOnClickListener(this);
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openCamera_picture_location:

                //Camera拍照功能
                getCameraPicture();

                //释放Camera资源
                releaseCamera();
                break;
        }
    }


    //定义获取Camera对象方法
    private Camera getCamera() {
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            mCamera = null;
            e.printStackTrace();
        }
        return mCamera;
    }


    //定义Camera拍照功能方法
    private void getCameraPicture() {
        Camera.Parameters mCameraParameters = mCamera.getParameters();

        //设置保存照片格式
        mCameraParameters.setPictureFormat(ImageFormat.JPEG);

        //设置预览尺寸大小
        //mCameraParameters.setPreviewSize();

        //设置对焦模式为自动对焦
        mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        //开启拍照功能
        mCamera.takePicture(null, null, mCameraPictureCallback);
    }


    //定义开始预览Camera方法
    private void startCameraPreview(Camera camera, SurfaceHolder surfaceHolder) {
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //定义释放Camera资源方法
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


    //实现SurfaceHolder.Callback方法
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startCameraPreview(mCamera, mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        startCameraPreview(mCamera, mSurfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
