package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hxp.happyschool.R;
import com.hxp.happyschool.activitys.Preview_PictureActivity;
import com.hxp.happyschool.utils.FileOperate;

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
    private FileOperate mFileOperate;
    private Camera.PictureCallback mCameraPictureCallback;


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
        mFileOperate = new FileOperate();
        getCamera();
        mSurfaceHolder = surfaceview_camera_picture_location.getHolder();
        mSurfaceHolder.addCallback(this);
        startCameraPreview(mCamera, mSurfaceHolder);
        btn_openCamera_picture_location.setOnClickListener(this);
        surfaceview_camera_picture_location.setOnClickListener(this);
        mCameraPictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                //设置子目录路径
                String childDirectoryPath = "Location" + File.separator + "Picture";

                //创建照片保存文件夹
                mFileOperate.createChildDirectory(childDirectoryPath);

                //创建要保存文件File对象
                File mPictureSaveFile = new File(mFileOperate.mPath + File.separator + childDirectoryPath + File.separator + "temp.jpg");

                //创建拍照图片Bitmap对象
                Bitmap mBitmapResource = BitmapFactory.decodeByteArray(data, 0, data.length);

                //创建要保存图片Bitmap对象
                Bitmap mBitmapToSave = BitmapFactory.decodeByteArray(data, 0, data.length);

                //创建Matrix对象mMatrix
                Matrix mMatrix = new Matrix();

                //设置mMatrix旋转90度
                mMatrix.setRotate(90, mBitmapResource.getWidth() / 2, mBitmapResource.getHeight() / 2);

                //创建要保存图片Bitmap
                mBitmapToSave = Bitmap.createBitmap(mBitmapResource, 0, 0, mBitmapResource.getWidth(), mBitmapResource.getHeight(), mMatrix, true);


                Log.d("click", "file");
                try {
                    if (mPictureSaveFile.exists()) {
                        mPictureSaveFile.delete();
                    }
                    FileOutputStream mFileOutputStream = new FileOutputStream(mPictureSaveFile);
                    mBitmapToSave.compress(Bitmap.CompressFormat.JPEG, 50, mFileOutputStream);
                    mFileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openCamera_picture_location:

                //Camera拍照功能
                getCameraPicture();

                //开始预览Camera
                //startCameraPreview(mCamera, mSurfaceHolder);

                //跳转到上传前预览Activity
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(2000);
                            startActivity(new Intent(getActivity(), Preview_PictureActivity.class));
                            Log.d("click", "fragment");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;

            case R.id.surfaceview_camera_picture_location:
                mCamera.autoFocus(null);
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

        mCamera.setParameters(mCameraParameters);

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
    public void releaseCamera() {
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


    //重写父类onPause方法
    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
        Log.d("click", "camerarelease");
    }
}
