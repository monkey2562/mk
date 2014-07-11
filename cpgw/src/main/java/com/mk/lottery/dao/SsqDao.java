package com.mk.lottery.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mk.lottery.R;
import com.mk.lottery.model.SsqBO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 2014/7/10.
 */
public class SsqDao {

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private Context context;


    public SsqDao(Context context) {
        helper = new DBOpenHelper(context);
        this.context = context;
    }

    public SsqDao(){}

    public void save(SsqBO ssqBO) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into ssq (lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[] { ssqBO.getLotteryIssue(), ssqBO.getLotteryDate(),ssqBO.getRed1(),ssqBO.getRed2(),
                        ssqBO.getRed3(),ssqBO.getRed4(),ssqBO.getRed5(),ssqBO.getRed6(),ssqBO.getBlue(),
                        ssqBO.getReds1(),ssqBO.getReds2(),ssqBO.getReds3(),ssqBO.getReds4(),ssqBO.getReds5(),
                        ssqBO.getReds6(),ssqBO.getTotalAmount(), ssqBO.getPoolAmount(),
                        ssqBO.getFirstCount(),ssqBO.getFirstAmount(),ssqBO.getSecondCount(),ssqBO.getSecondAmount(),
                        ssqBO.getThirdCount(),ssqBO.getThirdAmount(),ssqBO.getFourthCount(),ssqBO.getFourthAmount(),
                        ssqBO.getFifthCount(), ssqBO.getFifthAmount(),ssqBO.getSixthCount(),ssqBO.getSixthAmount()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void update(SsqBO ssqBO) {
        db = helper.getWritableDatabase();
        db.execSQL("update ssq set lottery_issue = ?", new Object[]{ssqBO.getLotteryIssue()});
    }

    public SsqBO find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,lottery_issue from ssq ",null);
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

    /**
     * 初始化TXT里面的数据
     */
    public void initData(){
        String path = "txt/ssq.txt";
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(path);
            if (is != null) {
                System.out.println("读取文件开始");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                String lineTxt = null;
                while((lineTxt = reader.readLine()) != null) {
                    String[] strs = lineTxt.split(" ");
                    SsqBO bo = new SsqBO();
                    bo.setLotteryIssue(Integer.valueOf(strs[0]));
                    bo.setLotteryDate(strs[1]);
                    bo.setRed1(Integer.valueOf(strs[2]));
                    bo.setRed2(Integer.valueOf(strs[3]));
                    bo.setRed3(Integer.valueOf(strs[4]));
                    bo.setRed4(Integer.valueOf(strs[5]));
                    bo.setRed5(Integer.valueOf(strs[6]));
                    bo.setRed6(Integer.valueOf(strs[7]));
                    bo.setBlue(Integer.valueOf(strs[8]));
                    bo.setReds1(Integer.valueOf(strs[9]));
                    bo.setReds2(Integer.valueOf(strs[10]));
                    bo.setReds3(Integer.valueOf(strs[11]));
                    bo.setReds4(Integer.valueOf(strs[12]));
                    bo.setReds5(Integer.valueOf(strs[13]));
                    bo.setReds6(Integer.valueOf(strs[14]));
                    bo.setTotalAmount(Integer.valueOf(strs[15]));
                    bo.setPoolAmount(Integer.valueOf(strs[16]));
                    bo.setFirstCount(Integer.valueOf(strs[17]));
                    bo.setFirstAmount(Integer.valueOf(strs[18]));
                    bo.setSecondCount(Integer.valueOf(strs[19]));
                    bo.setSecondAmount(Integer.valueOf(strs[20]));
                    bo.setThirdCount(Integer.valueOf(strs[21]));
                    bo.setThirdAmount(Integer.valueOf(strs[22]));
                    bo.setFourthCount(Integer.valueOf(strs[23]));
                    bo.setFourthAmount(Integer.valueOf(strs[24]));
                    bo.setFifthCount(Integer.valueOf(strs[25]));
                    bo.setFifthAmount(Integer.valueOf(strs[26]));
                    bo.setSixthCount(Integer.valueOf(strs[27]));
                    bo.setSixthAmount(Integer.valueOf(strs[28]));
                    save(bo);


//                  System.out.println(lineTxt);
                }
                reader.close();
                isr.close();
                is.close();
                System.out.println("读取文件完毕");
            }else {
                System.out.println("读取文件流出错");
            }

        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }


    /**
     * 把raw文件下面的数据库导入到安装的程序中的database目录下
     */
    public void importDatabase() {
        //存放数据库的目录
        String dirPath="/data/data/com.mk.lottery/databases";
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        //数据库文件
        File file = new File(dir, "lottery.db");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            //加载需要导入的数据库
            InputStream is = context.getResources().openRawResource(R.raw.lottery);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer=new byte[is.available()];
            is.read(buffer);
            fos.write(buffer);
            is.close();
            fos.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
