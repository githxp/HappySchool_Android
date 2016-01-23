package com.hxp.happyschool.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hxp on 16-1-23.
 */
public class DatabaseImplement {

    //创建成员变量
    private Context mContext;
    private BaseDatabase mBaseDatabase;
    private SQLiteDatabase mSQLiteDatabase;


    //创建构造方法
    public DatabaseImplement(Context context) {
        this.mContext = context;
        mBaseDatabase = new BaseDatabase(context);
        mSQLiteDatabase = mBaseDatabase.getWritableDatabase();
    }
}
