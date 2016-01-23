package com.hxp.happyschool.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hxp on 16-1-23.
 */
public class BaseDatabase extends SQLiteOpenHelper {


    //创建构造方法
    public BaseDatabase(Context context) {
        super(context, "happyschool", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建课堂表
        db.execSQL("create table classunion (id integer primary key autoincrement,title text," +
                "sort text,desc text,download_volume integer,time text,resource text,sign_official blob," +
                "sign_boutique blob,like_number integer,dislike_number integer,screen_shot1 text," +
                "screen_shot2 text,screen_shot3 text)");
        //创建课表表
        db.execSQL("create table classtable (id integer primary key autoincrement,school text," +
                "major text,grade text,class text)");
        //创建食堂表
        db.execSQL("create table catery (id integer primary key autoincrement,name text,desc text," +
                "takeaway blob,telephone text,address text,iscatery blob,comment text," +
                "like_number integer,dislike_number,cover text,screen_shot1 text,screen_shot2 text," +
                "screen_shot3 text)");
        //创建头条表
        db.execSQL("create table hotnews (id integer primary key autoincrement,title text,desc text," +
                "text_resource text,comment text,like_number integer,dislike_number integer," +
                "cover text,time text)");
        //创建书馆表
        db.execSQL("create table library (id integer primary key autoincrement,title text,desc text," +
                "resource text,comment text,like_number integer,dislike_number integer,covver text," +
                "download_volume integer)");
        //创建考场表
        db.execSQL("create table exam (id integer primary key autoincrement,title text,desc text," +
                "sort text,grade text,like_number integer,dislike_number integer,comment text," +
                "scan_number integer)");
        db.execSQL("create table score (id integer primary key autoincrement,school text," +
                "student_id text,password text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除已存在的表
        db.execSQL("drop table if exist classunion");
        db.execSQL("drop table if exist classtable");
        db.execSQL("drop table if exist catery");
        db.execSQL("drop table if exist hotnews");
        db.execSQL("drop table if exist library");
        db.execSQL("drop table if exist exam");
        db.execSQL("drop table if exist score");

        //创建课堂表
        db.execSQL("create table classunion (id integer primary key autoincrement,title text," +
                "sort text,desc text,download_volume integer,time text,resource text,sign_official blob," +
                "sign_boutique blob,like_number integer,dislike_number integer,screen_shot1 text," +
                "screen_shot2 text,screen_shot3 text)");
        //创建课表表
        db.execSQL("create table classtable (id integer primary key autoincrement,school text," +
                "major text,grade text,class text)");
        //创建食堂表
        db.execSQL("create table catery (id integer primary key autoincrement,name text,desc text," +
                "takeaway blob,telephone text,address text,iscatery blob,comment text," +
                "like_number integer,dislike_number,cover text,screen_shot1 text,screen_shot2 text," +
                "screen_shot3 text)");
        //创建头条表
        db.execSQL("create table hotnews (id integer primary key autoincrement,title text,desc text," +
                "text_resource text,comment text,like_number integer,dislike_number integer," +
                "cover text,time text)");
        //创建书馆表
        db.execSQL("create table library (id integer primary key autoincrement,title text,desc text," +
                "resource text,comment text,like_number integer,dislike_number integer,covver text," +
                "download_volume integer)");
        //创建考场表
        db.execSQL("create table exam (id integer primary key autoincrement,title text,desc text," +
                "sort text,grade text,like_number integer,dislike_number integer,comment text," +
                "scan_number integer)");
        db.execSQL("create table score (id integer primary key autoincrement,school text," +
                "student_id text,password text)");

    }
}
