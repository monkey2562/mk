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
        Cursor cursor = db.rawQuery("select id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount from ssq where id = ? ",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            SsqBO bo = new SsqBO();
//            bo.setLotteryIssue(cursor.getInt(cursor.getColumnIndex("lottery_issue")));
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getInt(3));
            bo.setRed2(cursor.getInt(4));
            bo.setRed3(cursor.getInt(5));
            bo.setRed4(cursor.getInt(6));
            bo.setRed5(cursor.getInt(7));
            bo.setRed6(cursor.getInt(8));
            bo.setBlue(cursor.getInt(9));
            bo.setReds1(cursor.getInt(10));
            bo.setReds2(cursor.getInt(11));
            bo.setReds3(cursor.getInt(12));
            bo.setReds4(cursor.getInt(13));
            bo.setReds5(cursor.getInt(14));
            bo.setReds6(cursor.getInt(15));
            bo.setTotalAmount(cursor.getInt(16));
            bo.setPoolAmount(cursor.getInt(17));
            bo.setFirstCount(cursor.getInt(18));
            bo.setFirstAmount(cursor.getInt(19));
            bo.setSecondCount(cursor.getInt(20));
            bo.setSecondAmount(cursor.getInt(21));
            bo.setThirdCount(cursor.getInt(22));
            bo.setThirdAmount(cursor.getInt(23));
            bo.setFourthCount(cursor.getInt(24));
            bo.setFourthAmount(cursor.getInt(25));
            bo.setFifthCount(cursor.getInt(26));
            bo.setFifthAmount(cursor.getInt(27));
            bo.setSixthCount(cursor.getInt(28));
            bo.setSixthAmount(cursor.getInt(29));
            return bo;
        }
        return null;
    }

    /**
     * 通过红球号码查找所有带此号码的记录
     * @param redBall 红球号码
     * @return
     */
    public List<SsqBO> getSsqListByRedBall(int redBall) {
        List<SsqBO> ssqBOs = new ArrayList<SsqBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from ssq where red_1=? or red_2=? or red_3=? or red_4=? or red_5=? or red_6=?",
                new String[]{String.valueOf(redBall),String.valueOf(redBall),String.valueOf(redBall),
                        String.valueOf(redBall),String.valueOf(redBall),String.valueOf(redBall)});
        while (cursor.moveToNext()) {
            SsqBO bo =  new SsqBO();
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getInt(3));
            bo.setRed2(cursor.getInt(4));
            bo.setRed3(cursor.getInt(5));
            bo.setRed4(cursor.getInt(6));
            bo.setRed5(cursor.getInt(7));
            bo.setRed6(cursor.getInt(8));
            bo.setBlue(cursor.getInt(9));
            bo.setReds1(cursor.getInt(10));
            bo.setReds2(cursor.getInt(11));
            bo.setReds3(cursor.getInt(12));
            bo.setReds4(cursor.getInt(13));
            bo.setReds5(cursor.getInt(14));
            bo.setReds6(cursor.getInt(15));
            bo.setTotalAmount(cursor.getInt(16));
            bo.setPoolAmount(cursor.getInt(17));
            bo.setFirstCount(cursor.getInt(18));
            bo.setFirstAmount(cursor.getInt(19));
            bo.setSecondCount(cursor.getInt(20));
            bo.setSecondAmount(cursor.getInt(21));
            bo.setThirdCount(cursor.getInt(22));
            bo.setThirdAmount(cursor.getInt(23));
            bo.setFourthCount(cursor.getInt(24));
            bo.setFourthAmount(cursor.getInt(25));
            bo.setFifthCount(cursor.getInt(26));
            bo.setFifthAmount(cursor.getInt(27));
            bo.setSixthCount(cursor.getInt(28));
            bo.setSixthAmount(cursor.getInt(29));
            ssqBOs.add(bo);
        }
        return ssqBOs;
    }

    /**
     * 通过蓝球号码查找所有带此号码的记录
     * @param blueBall 蓝球号码
     * @return
     */
    public List<SsqBO> getSsqListByBlueBall(int blueBall) {
        List<SsqBO> ssqBOs = new ArrayList<SsqBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from ssq where blue=? ",
                new String[]{String.valueOf(blueBall)});
        while (cursor.moveToNext()) {
            SsqBO bo =  new SsqBO();
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getInt(3));
            bo.setRed2(cursor.getInt(4));
            bo.setRed3(cursor.getInt(5));
            bo.setRed4(cursor.getInt(6));
            bo.setRed5(cursor.getInt(7));
            bo.setRed6(cursor.getInt(8));
            bo.setBlue(cursor.getInt(9));
            bo.setReds1(cursor.getInt(10));
            bo.setReds2(cursor.getInt(11));
            bo.setReds3(cursor.getInt(12));
            bo.setReds4(cursor.getInt(13));
            bo.setReds5(cursor.getInt(14));
            bo.setReds6(cursor.getInt(15));
            bo.setTotalAmount(cursor.getInt(16));
            bo.setPoolAmount(cursor.getInt(17));
            bo.setFirstCount(cursor.getInt(18));
            bo.setFirstAmount(cursor.getInt(19));
            bo.setSecondCount(cursor.getInt(20));
            bo.setSecondAmount(cursor.getInt(21));
            bo.setThirdCount(cursor.getInt(22));
            bo.setThirdAmount(cursor.getInt(23));
            bo.setFourthCount(cursor.getInt(24));
            bo.setFourthAmount(cursor.getInt(25));
            bo.setFifthCount(cursor.getInt(26));
            bo.setFifthAmount(cursor.getInt(27));
            bo.setSixthCount(cursor.getInt(28));
            bo.setSixthAmount(cursor.getInt(29));
            ssqBOs.add(bo);
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
