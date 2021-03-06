package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxp.happyschool.R;
import com.hxp.happyschool.utils.FileOperate;
import com.hxp.happyschool.utils.HttpConnect;

import java.io.File;
import java.util.PriorityQueue;

/**
 * 图片上传前预览Fragment
 * Created by hxp on 16-2-14.
 */
public class Preview_PictureActivity extends Activity implements OnClickListener {


    //设置控件和成员变量
    private LinearLayout linearlayout_previewno_picture_location;
    private LinearLayout linearlayout_previewok_picture_location;
    private ImageView img_preview_picture_location;
    private FileOperate mFileOperate;
    private HttpConnect mHttpConnect;
    private Bitmap mBitmap;
    private String pictureFilePath;
    private TextView tv_status;
    private String strHttpConnectResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_picture_location);

        //初始化控件和成员变量
        linearlayout_previewno_picture_location = (LinearLayout) findViewById(R.id.linearlayout_previewno_picture_location);
        linearlayout_previewok_picture_location = (LinearLayout) findViewById(R.id.linearlayout_previewok_picture_location);
        img_preview_picture_location = (ImageView) findViewById(R.id.img_preview_picture_location);
        tv_status = (TextView) findViewById(R.id.tv_status);
        mFileOperate = new FileOperate();
        mHttpConnect = new HttpConnect();
        linearlayout_previewno_picture_location.setOnClickListener(this);
        linearlayout_previewok_picture_location.setOnClickListener(this);

        //设置imagview显示预览图
        pictureFilePath = mFileOperate.mPath + File.separator + "Location" + File.separator + "Picture" + File.separator + "temp.jpg";
        mBitmap = BitmapFactory.decodeFile(pictureFilePath);
        Log.d("click","文件路径:"+pictureFilePath);
        img_preview_picture_location.setImageBitmap(mBitmap);
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearlayout_previewok_picture_location:
                //上传图片到服务器
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        startUpload();
                    }
                }.start();

                break;

            case R.id.linearlayout_previewno_picture_location:
                this.finish();
                break;
        }
    }


    //定义上传图片到服务器方法
    private void startUpload() {
        Log.d("click", "进入上传方法");
        strHttpConnectResult = mHttpConnect.httpConnect("http://121.43.116.174/class/Location/Location_Picture_class.php", "picture", pictureFilePath);
        Log.d("click", "接收到的状态码:" + strHttpConnectResult);
        /*switch (strHttpConnectResult) {
            case "0":
                tv_status.setText("状态：不能移动文件");
                break;

            case "1":
                tv_status.setText("状态：上传成功");
                break;

            case "2":
                tv_status.setText("状态：不是post上传");
                break;
        }*/
    }
}
