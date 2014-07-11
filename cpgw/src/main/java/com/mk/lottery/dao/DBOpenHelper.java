package com.mk.lottery.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mk.lottery.model.SsqBO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2014/7/10.
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "lottery.db"; //数据库名称
    private static final int version = 1; //数据库版本
    private Context context;
    public DBOpenHelper(Context context) {
        // 第一个参数是应用的上下文
        // 第二个参数是应用的数据库名字
        // 第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        // 第四个参数是数据库版本，必须是大于0的int（即非负数）
        super(context, DB_NAME, null, version);
        this.context = context;
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建person表
        db.execSQL("CREATE TABLE IF NOT EXISTS ssq (id INTEGER PRIMARY KEY AUTOINCREMENT, lottery_issue INTEGER, lottery_date VARCHAR," +
                " red_1 INTEGER, red_2 INTEGER, red_3 INTEGER, red_4 INTEGER, red_5 INTEGER, red_6 INTEGER, blue INTEGER,reds_1 INTEGER," +
                " reds_2 INTEGER, reds_3 INTEGER, reds_4 INTEGER, reds_5 INTEGER, reds_6 INTEGER,total_amount INTEGER,pool_amount INTEGER," +
                " first_count INTEGER, first_amount INTEGER, second_count INTEGER, second_amount INTEGER, " +
                " third_count INTEGER, third_amount INTEGER, fourth_count INTEGER, fourth_amount INTEGER," +
                " fifth_count INTEGER, fifth_amount INTEGER, sixth_count INTEGER, sixth_amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

}
