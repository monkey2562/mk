package com.mk.lottery.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mk.lottery.dao.SsqDao;
import com.mk.lottery.model.SsqBO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 更新双色球本地数据
 * Created by mk on 2014/8/3.
 */
public class UpdateSsqDataService {
    private Context context;
    private SsqDao ssqDao;
    public UpdateSsqDataService(Context context){
        this.context = context;
    }

    public void updateData(){
        String url = "http://www.17500.cn/getData/ssq.TXT";
        HttpGet getMethod = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = httpClient.execute(getMethod);
            Log.i("SimpleClient", "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
//                Log.i("SimpleClient", "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            String[] lines = responseBody.split("\n");
//            for(int i = 0 ; i < lines.length; i++){
//                Log.v("SimpleClient",i+1 +" " + lines[i]);
//            }
            //插入数据库操作
            // 1.先查找本地库最新的一期
            ssqDao =new SsqDao(context);
            int localNewestId = ssqDao.findNewestId();
            if(lines.length == localNewestId){
                Log.v("UpdateSsqDataService","已经是最新的数据，不需要更新");
            }else if(lines.length < localNewestId){
                Toast.makeText(context,"数据出错，请重置！",Toast.LENGTH_SHORT).show();
                Log.v("UpdateSsqDataService","数据出错，请重置！");
            }else {
                Log.v("UpdateSsqDataService", "本地最新ID = " + localNewestId);
                Log.v("UpdateSsqDataService", "服务器最新数据 = " + lines.length);
                for(int i = 0 ; i < lines.length; i++){
                    if(i+1 > localNewestId){
//                        Log.v("UpdateSsqDataService",i+1 +" " + lines[i]);
                        String line = lines[i];
                        String[] column = line.split(" ");
                        SsqBO bo = new SsqBO();
                        bo.setLotteryIssue(Integer.valueOf(column[0]));
                        bo.setLotteryDate(column[1]);
                        bo.setRed1(column[2]);
                        bo.setRed2(column[3]);
                        bo.setRed3(column[4]);
                        bo.setRed4(column[5]);
                        bo.setRed5(column[6]);
                        bo.setRed6(column[7]);
                        bo.setBlue(column[8]);
                        bo.setReds1(column[9]);
                        bo.setReds2(column[10]);
                        bo.setReds3(column[11]);
                        bo.setReds4(column[12]);
                        bo.setReds5(column[13]);
                        bo.setReds6(column[14]);
                        bo.setTotalAmount(Integer.valueOf(column[15]));
                        bo.setPoolAmount(Integer.valueOf(column[16]));
                        bo.setFirstCount(Integer.valueOf(column[17]));
                        bo.setFirstAmount(Integer.valueOf(column[18]));
                        bo.setSecondCount(Integer.valueOf(column[19]));
                        bo.setSecondAmount(Integer.valueOf(column[20]));
                        bo.setThirdCount(Integer.valueOf(column[21]));
                        bo.setThirdAmount(Integer.valueOf(column[22]));
                        bo.setFourthCount(Integer.valueOf(column[23]));
                        bo.setFourthAmount(Integer.valueOf(column[24]));
                        bo.setFifthCount(Integer.valueOf(column[25]));
                        bo.setFifthAmount(Integer.valueOf(column[26]));
                        bo.setSixthCount(Integer.valueOf(column[27]));
                        bo.setSixthAmount(Integer.valueOf(column[28]));
                        ssqDao.save(bo);
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
