package com.hxp.happyschool.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hxp.happyschool.R;
import com.hxp.happyschool.activitys.SelectUploadFileActivity;

import java.io.File;

/**
 * 上传Fragment
 * Created by hxp on 16-1-31.
 */
public class Upload_Classunion_Fragment extends Fragment implements OnClickListener {


    //定义成员变量
    private View view;
    private Button btn_selectUploadFile_clssunion;
    private RecyclerView rv_uploadTask_classunion;
    private RelativeLayout relativelayout_selectUploadFile_clssunion;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.upload_classunion_fragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化成员变量
        btn_selectUploadFile_clssunion = (Button) getView().findViewById(R.id.btn_selectUploadFile_clssunion);
        rv_uploadTask_classunion = (RecyclerView) getView().findViewById(R.id.rv_uploadTask_classunion);
        relativelayout_selectUploadFile_clssunion = (RelativeLayout) getView().findViewById(R.id.relativelayout_selectUploadFile_clssunion);
        btn_selectUploadFile_clssunion.setOnClickListener(this);
    }


    //实现OnClickListener接口
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selectUploadFile_clssunion:
                Intent intent = new Intent(getActivity(), SelectUploadFileActivity.class);
                startActivityForResult(intent,100);
                break;

            default:
                break;
        }
    }
}
