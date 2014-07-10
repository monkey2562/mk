package com.mk.lottery.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mk.lottery.model.SsqBO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 2014/7/10.
 */
public class SsqDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public SsqDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void save(SsqBO ssqBO) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into ssq (lottery_issue,lottery_date)values(?,?)",
                new Object[] { ssqBO.getLotteryIssue(), ssqBO.getLotteryDate() });
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void update(SsqBO ssqBO) {
        db = helper.getWritableDatabase();
        db.execSQL("update ssq set lottery_issue = ?", new Object[]{ssqBO.getLotteryIssue()});
    }

    public SsqBO find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,lottery_issue from ssq where sid = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            SsqBO bo = new SsqBO();
            bo.setLotteryIssue(cursor.getInt(cursor.getColumnIndex("lottery_issue")));
            return bo;
        }
        return null;
    }

    public List<SsqBO> getSsqList() {
        List<SsqBO> ssqBOs = new ArrayList<SsqBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ssq ", null);
        while (cursor.moveToNext()) {
            ssqBOs.add(new SsqBO());
        }
        return ssqBOs;
    }
}
