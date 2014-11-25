package com.mk.lottery.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mk.lottery.R;
import com.mk.lottery.model.UnionLottoBO;

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
public class UnionLottoDao {

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private Context context;


    public UnionLottoDao(Context context) {
        helper = new DBOpenHelper(context);
        this.context = context;
    }

    public UnionLottoDao(){}

    /**
     * 保存双色球数据
     * @param unionLottoBO
     */
    public void save(UnionLottoBO unionLottoBO) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into union_lotto (lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[] { unionLottoBO.getLotteryIssue(), unionLottoBO.getLotteryDate(), unionLottoBO.getRed1(), unionLottoBO.getRed2(),
                        unionLottoBO.getRed3(), unionLottoBO.getRed4(), unionLottoBO.getRed5(), unionLottoBO.getRed6(), unionLottoBO.getBlue(),
                        unionLottoBO.getReds1(), unionLottoBO.getReds2(), unionLottoBO.getReds3(), unionLottoBO.getReds4(), unionLottoBO.getReds5(),
                        unionLottoBO.getReds6(), unionLottoBO.getTotalAmount(), unionLottoBO.getPoolAmount(),
                        unionLottoBO.getFirstCount(), unionLottoBO.getFirstAmount(), unionLottoBO.getSecondCount(), unionLottoBO.getSecondAmount(),
                        unionLottoBO.getThirdCount(), unionLottoBO.getThirdAmount(), unionLottoBO.getFourthCount(), unionLottoBO.getFourthAmount(),
                        unionLottoBO.getFifthCount(), unionLottoBO.getFifthAmount(), unionLottoBO.getSixthCount(), unionLottoBO.getSixthAmount()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 更新双色球数据
     * @param unionLottoBO
     */
    public void update(UnionLottoBO unionLottoBO) {
        db = helper.getWritableDatabase();
        db.execSQL("update union_lotto set lottery_issue = ?", new Object[]{unionLottoBO.getLotteryIssue()});
    }



    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public UnionLottoBO find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from union_lotto where _id = ? ",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            UnionLottoBO bo = new UnionLottoBO();
//            bo.setLotteryIssue(cursor.getInt(cursor.getColumnIndex("lottery_issue")));
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getString(3));
            bo.setRed2(cursor.getString(4));
            bo.setRed3(cursor.getString(5));
            bo.setRed4(cursor.getString(6));
            bo.setRed5(cursor.getString(7));
            bo.setRed6(cursor.getString(8));
            bo.setBlue(cursor.getString(9));
            bo.setReds1(cursor.getString(10));
            bo.setReds2(cursor.getString(11));
            bo.setReds3(cursor.getString(12));
            bo.setReds4(cursor.getString(13));
            bo.setReds5(cursor.getString(14));
            bo.setReds6(cursor.getString(15));
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
     * 通过期号查找
     * @param lotteryIssue 期号
     * @return
     */
    public UnionLottoBO findByLotteryIssue(int lotteryIssue) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from union_lotto where lottery_issue = ? ",
                new String[]{String.valueOf(lotteryIssue)});
        if (cursor.moveToNext()) {
            UnionLottoBO bo = new UnionLottoBO();
//            bo.setLotteryIssue(cursor.getInt(cursor.getColumnIndex("lottery_issue")));
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getString(3));
            bo.setRed2(cursor.getString(4));
            bo.setRed3(cursor.getString(5));
            bo.setRed4(cursor.getString(6));
            bo.setRed5(cursor.getString(7));
            bo.setRed6(cursor.getString(8));
            bo.setBlue(cursor.getString(9));
            bo.setReds1(cursor.getString(10));
            bo.setReds2(cursor.getString(11));
            bo.setReds3(cursor.getString(12));
            bo.setReds4(cursor.getString(13));
            bo.setReds5(cursor.getString(14));
            bo.setReds6(cursor.getString(15));
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
     * 查找所有记录
     * @return
     */
    public List<UnionLottoBO> getALLSsqList() {
        List<UnionLottoBO> unionLottoBOs = new ArrayList<UnionLottoBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from union_lotto ",null);
        while (cursor.moveToNext()) {
            UnionLottoBO bo =  new UnionLottoBO();
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getString(3));
            bo.setRed2(cursor.getString(4));
            bo.setRed3(cursor.getString(5));
            bo.setRed4(cursor.getString(6));
            bo.setRed5(cursor.getString(7));
            bo.setRed6(cursor.getString(8));
            bo.setBlue(cursor.getString(9));
            bo.setReds1(cursor.getString(10));
            bo.setReds2(cursor.getString(11));
            bo.setReds3(cursor.getString(12));
            bo.setReds4(cursor.getString(13));
            bo.setReds5(cursor.getString(14));
            bo.setReds6(cursor.getString(15));
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
            unionLottoBOs.add(bo);
        }
        return unionLottoBOs;
    }

    /**
     * 通过红球号码查找所有带此号码的记录
     * @param redBall 红球号码
     * @return
     */
    public List<UnionLottoBO> getSsqListByRedBall(String redBall) {
        long time = System.currentTimeMillis();
        List<UnionLottoBO> unionLottoBOs = new ArrayList<UnionLottoBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
//                        "from ssq where red_1=? or red_2=? or red_3=? or red_4=? or red_5=? or red_6=?",
                        "from union_lotto where red_1=? or red_2=? or red_3=? or red_4=? or red_5=? or red_6=?",
                new String[]{redBall,redBall,redBall,redBall,redBall,redBall});
        while (cursor.moveToNext()) {
            UnionLottoBO bo =  new UnionLottoBO();
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getString(3));
            bo.setRed2(cursor.getString(4));
            bo.setRed3(cursor.getString(5));
            bo.setRed4(cursor.getString(6));
            bo.setRed5(cursor.getString(7));
            bo.setRed6(cursor.getString(8));
            bo.setBlue(cursor.getString(9));
            bo.setReds1(cursor.getString(10));
            bo.setReds2(cursor.getString(11));
            bo.setReds3(cursor.getString(12));
            bo.setReds4(cursor.getString(13));
            bo.setReds5(cursor.getString(14));
            bo.setReds6(cursor.getString(15));
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
            unionLottoBOs.add(bo);
        }

        Log.v("TIME","查询用时---"+String.valueOf(System.currentTimeMillis()-time));
        return unionLottoBOs;
    }

    /**
     * 通过蓝球号码查找所有带此号码的记录
     * @param blueBall 蓝球号码
     * @return
     */
    public List<UnionLottoBO> getSsqListByBlueBall(String blueBall) {
        List<UnionLottoBO> unionLottoBOs = new ArrayList<UnionLottoBO>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,lottery_issue,lottery_date,red_1,red_2,red_3,red_4,red_5,red_6," +
                        "blue,reds_1,reds_2,reds_3,reds_4,reds_5,reds_6,total_amount,pool_amount," +
                        "first_count,first_amount,second_count,second_amount,third_count,third_amount," +
                        "fourth_count,fourth_amount,fifth_count,fifth_amount,sixth_count,sixth_amount " +
                        "from union_lotto where blue=? ",
                new String[]{blueBall});
        while (cursor.moveToNext()) {
            UnionLottoBO bo =  new UnionLottoBO();
            bo.setId(cursor.getInt(0));
            bo.setLotteryIssue(cursor.getInt(1));
            bo.setLotteryDate(cursor.getString(2));
            bo.setRed1(cursor.getString(3));
            bo.setRed2(cursor.getString(4));
            bo.setRed3(cursor.getString(5));
            bo.setRed4(cursor.getString(6));
            bo.setRed5(cursor.getString(7));
            bo.setRed6(cursor.getString(8));
            bo.setBlue(cursor.getString(9));
            bo.setReds1(cursor.getString(10));
            bo.setReds2(cursor.getString(11));
            bo.setReds3(cursor.getString(12));
            bo.setReds4(cursor.getString(13));
            bo.setReds5(cursor.getString(14));
            bo.setReds6(cursor.getString(15));
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
            unionLottoBOs.add(bo);
        }
        return unionLottoBOs;
    }

    /**
     * 初始化双色球TXT里面的数据
     */
    public void initUnionLottoData(){
        String path = "txt/union_lotto.txt";
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
                    UnionLottoBO bo = new UnionLottoBO();
                    bo.setLotteryIssue(Integer.valueOf(strs[0]));
                    bo.setLotteryDate(strs[1]);
                    bo.setRed1(strs[2]);
                    bo.setRed2(strs[3]);
                    bo.setRed3(strs[4]);
                    bo.setRed4(strs[5]);
                    bo.setRed5(strs[6]);
                    bo.setRed6(strs[7]);
                    bo.setBlue(strs[8]);
                    bo.setReds1(strs[9]);
                    bo.setReds2(strs[10]);
                    bo.setReds3(strs[11]);
                    bo.setReds4(strs[12]);
                    bo.setReds5(strs[13]);
                    bo.setReds6(strs[14]);
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
                }
                reader.close();
                isr.close();
                is.close();
                Log.v("UnionLottoDAO","读取文件完毕");
            }else {
                Log.v("UnionLottoDAO","读取文件流出错");
            }

        } catch (Exception e) {
            Log.v("UnionLottoDAO","读取文件内容出错");
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

    /**
     * 查找本地库最新的那条记录ID
     * @return
     */
    public int findNewestId(){
        int id = 0;
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id from union_lotto order by _id desc ",null);
        if (cursor.moveToNext()) {
            id = cursor.getInt(0);
        }
        return id;
    }
}
