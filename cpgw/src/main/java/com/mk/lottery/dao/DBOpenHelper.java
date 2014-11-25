package com.mk.lottery.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2014/7/10.
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "lottery.db"; //数据库名称
    private static final int version = 2; //数据库版本

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
        //创建union_lotto表
        db.execSQL("CREATE TABLE IF NOT EXISTS union_lotto (_id INTEGER PRIMARY KEY AUTOINCREMENT, lottery_issue INTEGER, lottery_date VARCHAR," +
                " red_1 VARCHAR, red_2 VARCHAR, red_3 VARCHAR, red_4 VARCHAR, red_5 VARCHAR, red_6 VARCHAR, blue VARCHAR,reds_1 VARCHAR," +
                " reds_2 VARCHAR, reds_3 VARCHAR, reds_4 VARCHAR, reds_5 VARCHAR, reds_6 VARCHAR,total_amount INTEGER,pool_amount INTEGER," +
                " first_count INTEGER, first_amount INTEGER, second_count INTEGER, second_amount INTEGER, " +
                " third_count INTEGER, third_amount INTEGER, fourth_count INTEGER, fourth_amount INTEGER," +
                " fifth_count INTEGER, fifth_amount INTEGER, sixth_count INTEGER, sixth_amount INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS super_lotto (_id INTEGER PRIMARY KEY AUTOINCREMENT, lottery_issue INTEGER, lottery_date VARCHAR," +
                " red_1 VARCHAR, red_2 VARCHAR, red_3 VARCHAR, red_4 VARCHAR, red_5 VARCHAR, blue_1 VARCHAR, blue_2 VARCHAR,total_amount INTEGER,pool_amount INTEGER," +
                " first_count INTEGER, first_amount INTEGER, second_count INTEGER, second_amount INTEGER, " +
                " third_count INTEGER, third_amount INTEGER, fourth_count INTEGER, fourth_amount INTEGER," +
                " fifth_count INTEGER, fifth_amount INTEGER, sixth_count INTEGER, sixth_amount INTEGER," +
                " seventh_count INTEGER, seventh_amount INTEGER, eighth_count INTEGER, eighth_amount INTEGER," +
                " add_first_count INTEGER,add_first_amount INTEGER,add_second_count INTEGER,add_second_amount INTEGER," +
                " add_third_count INTEGER,add_third_amount INTEGER,additional_play_amount INTEGER," +
                " additional_play_first_count INTEGER,additional_play_first_amount INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onCreate(sqLiteDatabase);
    }

}
