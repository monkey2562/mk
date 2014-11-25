package com.mk.lottery.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.mk.lottery.dao.DBOpenHelper;
import com.mk.lottery.util.MkContent;
import com.mk.lottery.util.UpdateSsqDataService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
* Created by mk on 2014/8/3.
*/
public class RequestHandler implements Runnable{
    DBOpenHelper db;
    SQLiteDatabase dbRead,dbWrite;
    public static final String TAG = "RequestHandler";
    Context context;
    int type = 0;
    public static final int UPDATE_TYPE_UNION_LOTTO = 1;
    public static final int UPDATE_TYPE_SUPER_LOTTO = 2;

    /**
     *
     * @param context
     * @param type 1 - 双色球，2-大乐透，0-全部
     */
    public RequestHandler(Context context,int type){
        this.context = context;
        this.type = type;
        db = new DBOpenHelper(context);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
    }


    @Override
    public void run() {
//            Looper.prepare();
//            msg.what = 1;
//            handler.sendMessage(msg);
//            Looper.loop();
        if(type ==UPDATE_TYPE_UNION_LOTTO ){
            UpdateSsqDataService service = new UpdateSsqDataService(context);
            service.updateData();
        }else if (type ==UPDATE_TYPE_SUPER_LOTTO) {
            updateSuperLottoData();
        }

        closeDB();

    }

    private void closeDB() {
        if(dbRead!=null){
            dbRead.close();
        }
        if(dbWrite!=null){
            dbWrite.close();
        }
    }


    void updateSuperLottoData(){
        int localNewestId = 0 ;
        String url = "http://www.17500.cn/getData/dlt.TXT";
        HttpGet getMethod = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = httpClient.execute(getMethod);
            Log.i( TAG, "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
//            Log.i("SimpleClient", "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            String[] lines = responseBody.split("\n");
            for(int i = 0 ; i < lines.length; i++){
                Log.v("SimpleClient",i+1 +" " + lines[i]);
            }
            //插入数据库操作
            // 1.先查找本地库最新的一期
            Cursor cursor = dbRead.query(MkContent.TABLE_NAME_SUPER_LOTTO, new String[]{"_id"}, null, null, null, null, "_id desc");
            if(cursor.moveToNext()) {
                localNewestId = cursor.getInt(cursor.getColumnIndex("_id"));
                Log.v(TAG,"localNewestId=="+localNewestId);
            }
            if(lines.length == localNewestId){
                Log.v(TAG,"已经是最新的数据，不需要更新");
            }else if(lines.length < localNewestId){
                Toast.makeText(context, "数据出错，请重置！", Toast.LENGTH_SHORT).show();
                Log.v(TAG,"数据出错，请重置！");
            }else {
                Log.v(TAG, "本地最新ID = " + localNewestId);
                Log.v(TAG, "服务器最新数据 = " + lines.length);
                for(int i = 0 ; i < lines.length; i++){
                    if(i+1 > localNewestId){
                        Log.v("UpdateSsqDataService",i+1 +" " + lines[i]);
                        String line = lines[i];
                        String[] column = line.split(" ");
                        ContentValues values = new ContentValues();
                        values.put("lottery_issue",Integer.valueOf(column[0]));
                        values.put("lottery_date",column[1]);
                        values.put("red_1",column[2]);
                        values.put("red_2",column[3]);
                        values.put("red_3",column[4]);
                        values.put("red_4",column[5]);
                        values.put("red_5",column[6]);
                        values.put("blue_1",column[7]);
                        values.put("blue_2",column[8]);
                        values.put("total_amount",Integer.valueOf(column[9]));
                        values.put("pool_amount",Integer.valueOf(column[10]));
                        values.put("first_count",Integer.valueOf(column[11]));
                        values.put("first_amount",Integer.valueOf(column[12]));
                        values.put("second_count",Integer.valueOf(column[13]));
                        values.put("second_amount",Integer.valueOf(column[14]));
                        values.put("third_count",Integer.valueOf(column[15]));
                        values.put("third_amount",Integer.valueOf(column[16]));
                        values.put("fourth_count",Integer.valueOf(column[17]));
                        values.put("fourth_amount",Integer.valueOf(column[18]));
                        values.put("fifth_count",Integer.valueOf(column[19]));
                        values.put("fifth_amount",Integer.valueOf(column[20]));
                        values.put("sixth_count",Integer.valueOf(column[21]));
                        values.put("sixth_amount",Integer.valueOf(column[22]));
                        values.put("seventh_count",Integer.valueOf(column[23]));
                        values.put("seventh_amount",Integer.valueOf(column[24]));
                        values.put("eighth_count",Integer.valueOf(column[25]));
                        values.put("eighth_amount",Integer.valueOf(column[26]));
                        values.put("add_first_count",Integer.valueOf(column[27]));
                        values.put("add_first_amount",Integer.valueOf(column[28]));
                        values.put("add_second_count",Integer.valueOf(column[29]));
                        values.put("add_second_amount",Integer.valueOf(column[30]));
                        values.put("add_third_count",Integer.valueOf(column[31]));
                        values.put("add_third_amount",Integer.valueOf(column[32]));
                        values.put("additional_play_amount",Integer.valueOf(column[33]));
                        values.put("additional_play_first_count",Integer.valueOf(column[34]));
                        values.put("additional_play_first_amount",Integer.valueOf(column[35]));
                        dbWrite.insert(MkContent.TABLE_NAME_SUPER_LOTTO, null, values);
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
