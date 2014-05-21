package com.mk.security.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mk.security.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/5/21.
 */
public class BlackNumberDao {

    private DBHelper dbHelper;
    public BlackNumberDao(Context context){
        dbHelper = new DBHelper(context);
    }

    /**
     * 查询黑名单
     * @param number
     * @return
     */
    public boolean find(String number){
        boolean result = false;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select number from blacknumber where number = ? ", new String[] {number});
            if (cursor.moveToNext()) {
                result = true;
            }
            cursor.close();
            db.close();
        }
        return result;
    }

    /**
     * 新增黑名单
     * @param number
     */
    public void add(String number) {
        find(number);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into blacknumber (number) values(?)", new Object[] {number});
            db.close();
        }
    }

    /**
     * 删除黑名单
     * @param number
     */
    public void delete(String number) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from blacknumber where number = ? ", new Object[] {number});
            db.close();
        }
    }

    /**
     * 更新黑名单
     * @param oldNumber
     * @param newNumber
     */
    public void update(String oldNumber, String newNumber) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            db.execSQL("update blacknumber set number = ? where number = ? ", new Object[] {newNumber, oldNumber});
            db.close();
        }
    }

    public List<String> findAll(){
        List<String> numbers = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select number from blacknumber", null);
            while(cursor.moveToNext()){
                numbers.add(cursor.getString(0));
            }
            cursor.close();
            db.close();
        }
        return numbers;
    }
}
