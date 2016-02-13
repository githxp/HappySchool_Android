package com.hxp.happyschool.utils;

import android.os.Environment;

import java.io.File;

/**
 * 文件操作类
 * Created by hxp on 16-2-13.
 */
public class FileOperate {

    //设置程序保存路径
    public String mPath = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "HappySchool";


    //定义创建app目录方法
    public void createAppDirectory() {
        File appdDirectoryFile = new File(mPath);

        //判断文件夹是否存在
        if (appdDirectoryFile.exists() && appdDirectoryFile.isDirectory()) {
            return;
        } else {
            appdDirectoryFile.mkdirs();
        }

    }


    //定义创建子目录方法
    public void createChildDirectory(String childDirectoryPath) {
        File childDirectoryFile = new File(mPath + File.separator + childDirectoryPath);

        //判断子目录是否存在
        if (childDirectoryFile.exists() && childDirectoryFile.isDirectory()) {
            return;
        } else {
            childDirectoryFile.mkdirs();
        }

    }
}
