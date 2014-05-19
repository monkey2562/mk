package com.mk.security.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2014/5/19.
 */
public class AddressDao {
    public static SQLiteDatabase getAddressDB(String path) {
        //打开那个存放电话号码的数据库
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }
}
