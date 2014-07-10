package com.mk.lottery.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mk.lottery.module.SsqBO;
import com.mk.lottery.util.DatabaseHelper;

/**
 * Created by Administrator on 2014/7/10.
 */
public class SsqService {
    private DatabaseHelper dbHelper;

    public SsqService(Context context) {
        // this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void save(SsqBO ssqBO) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL("insert into ssq(lottery_issue,lottery_date)values(?,?)",
                new Object[] { ssqBO.getLotteryIssue(), ssqBO.getLotteryDate() });
        database.setTransactionSuccessful();
        database.endTransaction();
    }
}
