package com.mk.lottery.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mk.lottery.R;
import com.mk.lottery.dao.DBOpenHelper;
import com.mk.lottery.handler.RequestHandler;
import com.mk.lottery.model.LottoVO;
import com.mk.lottery.model.UnionLottoBO;
import com.mk.lottery.util.MapSort;
import com.mk.lottery.util.MkContent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SuperLottoActivity extends Activity implements View.OnClickListener{
    private HashMap<String,String> clickRedMap = new HashMap<String, String>();
    private HashMap<String,String> clickBlueMap = new HashMap<String, String>();
    private static final String TAG = "SuperLottoActivity";
    public static final String SUPER_LOTTO_TXT_PATH = "txt/super_lotto.txt";
    Button btnRed01,btnRed02,btnRed03,btnRed04,btnRed05,btnRed06,btnRed07,btnRed08,btnRed09,btnRed10,
            btnRed11,btnRed12,btnRed13,btnRed14,btnRed15,btnRed16,btnRed17,btnRed18,btnRed19,btnRed20,
            btnRed21,btnRed22,btnRed23,btnRed24,btnRed25,btnRed26,btnRed27,btnRed28,btnRed29,btnRed30,
            btnRed31,btnRed32,btnRed33,btnRed34,btnRed35,btnBlue01,btnBlue02,btnBlue03,btnBlue04,
            btnBlue05,btnBlue06,btnBlue07,btnBlue08,btnBlue09,btnBlue10,btnBlue11,btnBlue12;

    Button btnInit,btnImport,btnRequest;
    DBOpenHelper db;
    SQLiteDatabase dbRead,dbWrite;
    ImageView ivSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_super_lotto);

        //初始化按钮
        initButton();
        //按钮设置监听事件
        initOnClick();
        //按钮设置字体
        setTypeface();

        btnInit = (Button) findViewById(R.id.btnSuperLottoInit);
        btnInit.setOnClickListener(this);
        btnImport = (Button) findViewById(R.id.btnSuperLottoImport);
        btnImport.setOnClickListener(this);
        btnRequest = (Button) findViewById(R.id.btnSuperLottoRequest);
        btnRequest.setOnClickListener(this);
        ivSearch = (ImageView) findViewById(R.id.btnSuperLottoSearch);
        ivSearch.setOnClickListener(this);
        db = new DBOpenHelper(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSuperLottoRed01:
            case R.id.btnSuperLottoRed02:
            case R.id.btnSuperLottoRed03:
            case R.id.btnSuperLottoRed04:
            case R.id.btnSuperLottoRed05:
            case R.id.btnSuperLottoRed06:
            case R.id.btnSuperLottoRed07:
            case R.id.btnSuperLottoRed08:
            case R.id.btnSuperLottoRed09:
            case R.id.btnSuperLottoRed10:
            case R.id.btnSuperLottoRed11:
            case R.id.btnSuperLottoRed12:
            case R.id.btnSuperLottoRed13:
            case R.id.btnSuperLottoRed14:
            case R.id.btnSuperLottoRed15:
            case R.id.btnSuperLottoRed16:
            case R.id.btnSuperLottoRed17:
            case R.id.btnSuperLottoRed18:
            case R.id.btnSuperLottoRed19:
            case R.id.btnSuperLottoRed20:
            case R.id.btnSuperLottoRed21:
            case R.id.btnSuperLottoRed22:
            case R.id.btnSuperLottoRed23:
            case R.id.btnSuperLottoRed24:
            case R.id.btnSuperLottoRed25:
            case R.id.btnSuperLottoRed26:
            case R.id.btnSuperLottoRed27:
            case R.id.btnSuperLottoRed28:
            case R.id.btnSuperLottoRed29:
            case R.id.btnSuperLottoRed30:
            case R.id.btnSuperLottoRed31:
            case R.id.btnSuperLottoRed32:
            case R.id.btnSuperLottoRed33:
            case R.id.btnSuperLottoRed34:
            case R.id.btnSuperLottoRed35:
                Button btnRed = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if (clickRedMap.get(btnRed.getText().toString()) == null) {
                    //判断只能按下五个红球
                    if (clickRedMap.size() >= 5) {
                        Toast.makeText(SuperLottoActivity.this, "只能选择5个红球。", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickRedMap.put(btnRed.getText().toString(), btnRed.getText().toString());
                    btnRed.setBackgroundResource(R.drawable.ball_red);
                    btnRed.setTextColor(Color.WHITE);
                } else {
                    btnRed.setBackgroundResource(R.drawable.ball_gray);
                    clickRedMap.remove(btnRed.getText().toString());
                    btnRed.setTextColor(getResources().getColor(R.color.redBall));
                }

                break;
            case R.id.btnSuperLottoBlue01:
            case R.id.btnSuperLottoBlue02:
            case R.id.btnSuperLottoBlue03:
            case R.id.btnSuperLottoBlue04:
            case R.id.btnSuperLottoBlue05:
            case R.id.btnSuperLottoBlue06:
            case R.id.btnSuperLottoBlue07:
            case R.id.btnSuperLottoBlue08:
            case R.id.btnSuperLottoBlue09:
            case R.id.btnSuperLottoBlue10:
            case R.id.btnSuperLottoBlue11:
            case R.id.btnSuperLottoBlue12:

                Button btnBlue = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if (clickBlueMap.get(btnBlue.getText().toString()) == null) {
                    //判断只能按下2个蓝球
                    if (clickBlueMap.size() >= 2) {
                        Toast.makeText(SuperLottoActivity.this, "只能选择2个蓝球。", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickBlueMap.put(btnBlue.getText().toString(), btnBlue.getText().toString());
                    btnBlue.setBackgroundResource(R.drawable.ball_blue);
                    btnBlue.setTextColor(Color.WHITE);
                } else {
                    btnBlue.setBackgroundResource(R.drawable.ball_gray);
                    clickBlueMap.remove(btnBlue.getText().toString());
                    btnBlue.setTextColor(getResources().getColor(R.color.blueBall));
                }
                break;
            case R.id.btnSuperLottoInit://初始化数据库
                initData();
                break;
            case R.id.btnSuperLottoImport://把raw里面的数据文件拷贝到databases文件夹下
//                unionLottoDao.importDatabase();
//                Toast.makeText(UnionLottoActivity.this,"数据库移动完成！！！",Toast.LENGTH_SHORT).show();
                break;
            //请求网站数据
            case R.id.btnSuperLottoRequest:
                Log.v(TAG,"请求网站数据");
                //启动线程
                Runnable r = new RequestHandler(SuperLottoActivity.this, RequestHandler.UPDATE_TYPE_SUPER_LOTTO);
                Thread thread = new Thread(r);
                thread.start();


                break;
            case R.id.btnSuperLottoSearch://查询按钮
                search();
                break;
        }
    }

    /**
     * 初始化数据库
     */
    private void initData() {
        try {
            AssetManager am = this.getAssets();
            InputStream is = am.open(SUPER_LOTTO_TXT_PATH);
            if (is != null) {
                Log.v(TAG,"读取文件开始");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String lineTxt = null;
                while((lineTxt = reader.readLine()) != null) {
                    String[] column = lineTxt.split(" ");
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
                reader.close();
                is.close();
                am.close();
                Log.v(TAG, "读取文件完毕");
            }else {
                Log.v(TAG,"读取文件流出错");
            }

        } catch (Exception e) {
            Log.v(TAG,"读取文件内容出错");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.super_lotto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void initButton(){
        btnRed01 = (Button) findViewById(R.id.btnSuperLottoRed01);
        btnRed02 = (Button) findViewById(R.id.btnSuperLottoRed02);
        btnRed03 = (Button) findViewById(R.id.btnSuperLottoRed03);
        btnRed04 = (Button) findViewById(R.id.btnSuperLottoRed04);
        btnRed05 = (Button) findViewById(R.id.btnSuperLottoRed05);
        btnRed06 = (Button) findViewById(R.id.btnSuperLottoRed06);
        btnRed07 = (Button) findViewById(R.id.btnSuperLottoRed07);
        btnRed08 = (Button) findViewById(R.id.btnSuperLottoRed08);
        btnRed09 = (Button) findViewById(R.id.btnSuperLottoRed09);
        btnRed10 = (Button) findViewById(R.id.btnSuperLottoRed10);
        btnRed11 = (Button) findViewById(R.id.btnSuperLottoRed11);
        btnRed12 = (Button) findViewById(R.id.btnSuperLottoRed12);
        btnRed13 = (Button) findViewById(R.id.btnSuperLottoRed13);
        btnRed14 = (Button) findViewById(R.id.btnSuperLottoRed14);
        btnRed15 = (Button) findViewById(R.id.btnSuperLottoRed15);
        btnRed16 = (Button) findViewById(R.id.btnSuperLottoRed16);
        btnRed17 = (Button) findViewById(R.id.btnSuperLottoRed17);
        btnRed18 = (Button) findViewById(R.id.btnSuperLottoRed18);
        btnRed19 = (Button) findViewById(R.id.btnSuperLottoRed19);
        btnRed20 = (Button) findViewById(R.id.btnSuperLottoRed20);
        btnRed21 = (Button) findViewById(R.id.btnSuperLottoRed21);
        btnRed22 = (Button) findViewById(R.id.btnSuperLottoRed22);
        btnRed23 = (Button) findViewById(R.id.btnSuperLottoRed23);
        btnRed24 = (Button) findViewById(R.id.btnSuperLottoRed24);
        btnRed25 = (Button) findViewById(R.id.btnSuperLottoRed25);
        btnRed26 = (Button) findViewById(R.id.btnSuperLottoRed26);
        btnRed27 = (Button) findViewById(R.id.btnSuperLottoRed27);
        btnRed28 = (Button) findViewById(R.id.btnSuperLottoRed28);
        btnRed29 = (Button) findViewById(R.id.btnSuperLottoRed29);
        btnRed30 = (Button) findViewById(R.id.btnSuperLottoRed30);
        btnRed31 = (Button) findViewById(R.id.btnSuperLottoRed31);
        btnRed32 = (Button) findViewById(R.id.btnSuperLottoRed32);
        btnRed33 = (Button) findViewById(R.id.btnSuperLottoRed33);
        btnRed34 = (Button) findViewById(R.id.btnSuperLottoRed34);
        btnRed35 = (Button) findViewById(R.id.btnSuperLottoRed35);
        btnBlue01 = (Button) findViewById(R.id.btnSuperLottoBlue01);
        btnBlue02 = (Button) findViewById(R.id.btnSuperLottoBlue02);
        btnBlue03 = (Button) findViewById(R.id.btnSuperLottoBlue03);
        btnBlue04 = (Button) findViewById(R.id.btnSuperLottoBlue04);
        btnBlue05 = (Button) findViewById(R.id.btnSuperLottoBlue05);
        btnBlue06 = (Button) findViewById(R.id.btnSuperLottoBlue06);
        btnBlue07 = (Button) findViewById(R.id.btnSuperLottoBlue07);
        btnBlue08 = (Button) findViewById(R.id.btnSuperLottoBlue08);
        btnBlue09 = (Button) findViewById(R.id.btnSuperLottoBlue09);
        btnBlue10 = (Button) findViewById(R.id.btnSuperLottoBlue10);
        btnBlue11 = (Button) findViewById(R.id.btnSuperLottoBlue11);
        btnBlue12 = (Button) findViewById(R.id.btnSuperLottoBlue12);
    }

    void initOnClick(){
        btnRed01.setOnClickListener(this);
        btnRed02.setOnClickListener(this);
        btnRed03.setOnClickListener(this);
        btnRed04.setOnClickListener(this);
        btnRed05.setOnClickListener(this);
        btnRed06.setOnClickListener(this);
        btnRed07.setOnClickListener(this);
        btnRed08.setOnClickListener(this);
        btnRed09.setOnClickListener(this);
        btnRed10.setOnClickListener(this);
        btnRed11.setOnClickListener(this);
        btnRed12.setOnClickListener(this);
        btnRed13.setOnClickListener(this);
        btnRed14.setOnClickListener(this);
        btnRed15.setOnClickListener(this);
        btnRed16.setOnClickListener(this);
        btnRed17.setOnClickListener(this);
        btnRed18.setOnClickListener(this);
        btnRed19.setOnClickListener(this);
        btnRed20.setOnClickListener(this);
        btnRed21.setOnClickListener(this);
        btnRed22.setOnClickListener(this);
        btnRed23.setOnClickListener(this);
        btnRed24.setOnClickListener(this);
        btnRed25.setOnClickListener(this);
        btnRed26.setOnClickListener(this);
        btnRed27.setOnClickListener(this);
        btnRed28.setOnClickListener(this);
        btnRed29.setOnClickListener(this);
        btnRed30.setOnClickListener(this);
        btnRed31.setOnClickListener(this);
        btnRed32.setOnClickListener(this);
        btnRed33.setOnClickListener(this);
        btnRed34.setOnClickListener(this);
        btnRed35.setOnClickListener(this);
        btnBlue01.setOnClickListener(this);
        btnBlue02.setOnClickListener(this);
        btnBlue03.setOnClickListener(this);
        btnBlue04.setOnClickListener(this);
        btnBlue05.setOnClickListener(this);
        btnBlue06.setOnClickListener(this);
        btnBlue07.setOnClickListener(this);
        btnBlue08.setOnClickListener(this);
        btnBlue09.setOnClickListener(this);
        btnBlue10.setOnClickListener(this);
        btnBlue11.setOnClickListener(this);
        btnBlue12.setOnClickListener(this);
    }

    void setTypeface(){
        // 将字体文件保存在assets/fonts/目录下，www.linuxidc.com创建Typeface对象
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/fzmwt.ttf");
        btnRed01.setTypeface(typeFace);
        btnRed02.setTypeface(typeFace);
        btnRed03.setTypeface(typeFace);
        btnRed04.setTypeface(typeFace);
        btnRed05.setTypeface(typeFace);
        btnRed06.setTypeface(typeFace);
        btnRed07.setTypeface(typeFace);
        btnRed08.setTypeface(typeFace);
        btnRed09.setTypeface(typeFace);
        btnRed10.setTypeface(typeFace);
        btnRed11.setTypeface(typeFace);
        btnRed12.setTypeface(typeFace);
        btnRed13.setTypeface(typeFace);
        btnRed14.setTypeface(typeFace);
        btnRed15.setTypeface(typeFace);
        btnRed16.setTypeface(typeFace);
        btnRed17.setTypeface(typeFace);
        btnRed18.setTypeface(typeFace);
        btnRed19.setTypeface(typeFace);
        btnRed20.setTypeface(typeFace);
        btnRed21.setTypeface(typeFace);
        btnRed22.setTypeface(typeFace);
        btnRed23.setTypeface(typeFace);
        btnRed24.setTypeface(typeFace);
        btnRed25.setTypeface(typeFace);
        btnRed26.setTypeface(typeFace);
        btnRed27.setTypeface(typeFace);
        btnRed28.setTypeface(typeFace);
        btnRed29.setTypeface(typeFace);
        btnRed30.setTypeface(typeFace);
        btnRed31.setTypeface(typeFace);
        btnRed32.setTypeface(typeFace);
        btnRed33.setTypeface(typeFace);
        btnRed34.setTypeface(typeFace);
        btnRed35.setTypeface(typeFace);
        btnBlue01.setTypeface(typeFace);
        btnBlue02.setTypeface(typeFace);
        btnBlue03.setTypeface(typeFace);
        btnBlue04.setTypeface(typeFace);
        btnBlue05.setTypeface(typeFace);
        btnBlue06.setTypeface(typeFace);
        btnBlue07.setTypeface(typeFace);
        btnBlue08.setTypeface(typeFace);
        btnBlue09.setTypeface(typeFace);
        btnBlue10.setTypeface(typeFace);
        btnBlue11.setTypeface(typeFace);
        btnBlue12.setTypeface(typeFace);
    }

    void search(){
        //判断是否有5个红球和2个蓝球
        if(clickRedMap.size() < 5 ){
            Toast.makeText(this,"必须选择5个红球！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(clickBlueMap.size() < 2 ){
            Toast.makeText(this,"必须选择2个蓝球！",Toast.LENGTH_SHORT).show();
            return;
        }

        long currentTime = System.currentTimeMillis();
        Log.v("TIME","Start..." + String.valueOf(currentTime));

        //1.获取一组球，2.对MAP进行排序 3.带参数跳转
        List<Map.Entry<String, String>> mappingRedList = null;
        //通过ArrayList构造函数把map.entrySet()转换成list
        mappingRedList = new ArrayList<Map.Entry<String,String>>(clickRedMap.entrySet());
        //通过比较器实现比较排序
        Collections.sort(mappingRedList, new Comparator<Map.Entry<String, String>>() {

            @Override
            public int compare(Map.Entry<String, String> mapping1,
                               Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }

        });
        List<Map.Entry<String, String>> mappingBlueList = new ArrayList<Map.Entry<String,String>>(clickBlueMap.entrySet());
        MapSort.compareMap(mappingBlueList);


        Log.v("TIME","排序..." + String.valueOf(System.currentTimeMillis()-currentTime));

        //封装lottoVO
        int temp = 1;
        LottoVO lottoVO = new LottoVO();
        for(Map.Entry<String,String> mapping:mappingRedList){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
            switch (temp) {
                case 1:
                    lottoVO.setFirst(mapping.getValue());
                    temp++;
                    break;
                case 2:
                    lottoVO.setSecond(mapping.getValue());
                    temp++;
                    break;
                case 3:
                    lottoVO.setThird(mapping.getValue());
                    temp++;
                    break;
                case 4:
                    lottoVO.setFourth(mapping.getValue());
                    temp++;
                    break;
                case 5:
                    lottoVO.setFifth(mapping.getValue());
                    temp++;
                    break;
            }
        }
        int tempNum = 1;
        for(Map.Entry<String,String> mapping:mappingBlueList){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
            switch (tempNum) {
                case 1:
                    lottoVO.setSixth(mapping.getValue());
                    tempNum++;
                    break;
                case 2:
                    lottoVO.setSeventh(mapping.getValue());
                    tempNum++;
                    break;
            }
        }

        Log.v("TIME","封装红球蓝球..." + String.valueOf(System.currentTimeMillis()-currentTime));

        String first = lottoVO.getFirst();
        String second = lottoVO.getSecond();
        String third = lottoVO.getThird();
        String fourth = lottoVO.getFourth();
        String fifth = lottoVO.getFifth();
        String sixth = lottoVO.getSixth();
        String seventh = lottoVO.getSeventh();

        //用于统计期号--中几个红球
        HashMap<Integer, Integer> lotteryIssueRedMap = new HashMap<Integer, Integer>();
        //第一个数字
        searchRedBall(first, lotteryIssueRedMap);
        Log.v("TIME","第一个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第二个数字
        searchRedBall(second,lotteryIssueRedMap);
        Log.v("TIME","第二个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第三个数字
        searchRedBall(third,lotteryIssueRedMap);
        Log.v("TIME","第三个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第四个数字
        searchRedBall(fourth,lotteryIssueRedMap);
        Log.v("TIME","第四个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第五个数字
        searchRedBall(fifth,lotteryIssueRedMap);
        Log.v("TIME","第五个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //用于统计期号--中几个红球
        HashMap<Integer, Integer> lotteryIssueBlueMap = new HashMap<Integer, Integer>();
        //第六个数字
        searchBuleBall(sixth, lotteryIssueBlueMap);
        Log.v("TIME","第六个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第七个数字
        searchBuleBall(seventh, lotteryIssueBlueMap);
        Log.v("TIME","第七个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));

        //一等奖 中5+2  奖池低于1亿	（高等奖奖金x75%+奖池）/中奖注数
        //              奖池1亿-3亿	（高等奖奖金x58%+奖池+高等奖奖金x17%）/中奖注数
        //              奖池3亿以上	（高等奖奖金x42%+奖池+高等奖奖金x33%）/中奖注数
        //二等奖 中5+1   高等奖奖金x18%/中奖注数
        //三等奖 中5+0  或者 中4+2 高等奖奖金x7%/中奖注数
        //四等级 中4+1 或者 中3+2  单注奖金额固定为200元
        //五等奖 中4+0 或者 中3+1 或者 中2+2 10元
        //六等奖 中3+0 或者 中1+2 或者 中2+1 或者 中0+2 5元

        //旧奖项 2014年5月5日以前 第2014051期之前的奖项设置
        //一等奖 中5+2
        //二等奖 中5+1
        //三等奖 中5+0
        //四等级 中4+2 3000元
        //五等级 中4+1 600元
        //六等级 中4+0 或者 中3+2 100元
        //七等奖 中3+1 或者 中2+2 10元
        //八等奖 中3+0 或者 中1+2 或者 中2+1 或者 中0+2 5元

        //获取红球为六，五，四的MAP
        ArrayList<Integer> fiveRedList = new ArrayList<Integer>();
        ArrayList<Integer> fourRedList = new ArrayList<Integer>();
        ArrayList<Integer> threeRedList = new ArrayList<Integer>();
        Set<Integer> key = lotteryIssueRedMap.keySet();
        for(Iterator it = key.iterator();it.hasNext();){
            Integer qiHao = (Integer) it.next();
            Integer value = lotteryIssueRedMap.get(qiHao);

            if(value == 5){//中五个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                fiveRedList.add(qiHao);
            }else if (value == 4){//中四个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                fourRedList.add(qiHao);
            }else if( value == 3){//中三个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                threeRedList.add(qiHao);
            }
        }
//        //获取蓝球为二，一的MAP
//        ArrayList<Integer> twoBlueList = new ArrayList<Integer>();
//        ArrayList<Integer> oneBlueList = new ArrayList<Integer>();
//        Set<Integer> blueKey = lotteryIssueBlueMap.keySet();
//        for(Iterator it = blueKey.iterator();it.hasNext();){
//            Integer qiHao = (Integer) it.next();
//            Integer value = lotteryIssueRedMap.get(qiHao);
//
//            if(value == 2){//中2个蓝球
//                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
//                twoBlueList.add(qiHao);
//            }else if (value == 1){//中1个蓝球
//                Log.v(TAG, "期号：" + qiHao + " 次数：" + value);
//                oneBlueList.add(qiHao);
//            }
//        }

        Log.v("TIME","分析红球三四五..." + String.valueOf(System.currentTimeMillis()-currentTime));
        ArrayList<Integer> firstPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> secondPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> thirdPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> fourthPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> fifthPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> sixthPrizeList = new ArrayList<Integer>();

        //判断获奖 红色+蓝色
        if(fiveRedList.size() > 0){
            int size =  fiveRedList.size();
            for( int i = 0 ; i < size ; i++) {
                int qiHao = fiveRedList.get(i);
                if(lotteryIssueBlueMap.get(qiHao) !=null && lotteryIssueBlueMap.get(qiHao) == 2){
                    firstPrizeList.add(qiHao);//添加一等奖
                }else  if(lotteryIssueBlueMap.get(qiHao) !=null && lotteryIssueBlueMap.get(qiHao) == 1){
                    secondPrizeList.add(qiHao);//添加二等奖
                }else {
                    thirdPrizeList.add(qiHao);//添加三等奖
                }
            }
        }
        Log.v("TIME","五个红球全中..." + String.valueOf(System.currentTimeMillis()-currentTime));
        if(fourRedList.size() > 0){
            int size =  fourRedList.size();
            for( int i = 0 ; i < size ; i++) {
                int qiHao = fourRedList.get(i);
                if(lotteryIssueBlueMap.get(qiHao) !=null && lotteryIssueBlueMap.get(qiHao) == 2) {
                    if (qiHao > 2014051) {
                        thirdPrizeList.add(qiHao);//添加三等奖
                    } else {
                        fourthPrizeList.add(qiHao);//添加四等奖
                    }
                }else  if(lotteryIssueBlueMap.get(qiHao) !=null && lotteryIssueBlueMap.get(qiHao) == 1){
                    if (qiHao > 2014051) {
                        fourthPrizeList.add(qiHao);//添加四等奖
                    } else {
                        fifthPrizeList.add(qiHao);//添加五等奖
                    }
                }else {
                    if (qiHao <= 2014051) {
                        sixthPrizeList.add(qiHao);//添加六等奖
                    }
                }
            }
        }
        Log.v("TIME","中四个红球..." + String.valueOf(System.currentTimeMillis()-currentTime));

        if(threeRedList.size() > 0){
            int size =  threeRedList.size();
            for( int i = 0 ; i < size ; i++) {
                int qiHao = threeRedList.get(i);
                if(lotteryIssueBlueMap.get(qiHao) !=null && lotteryIssueBlueMap.get(qiHao) == 2) {
                    if (qiHao > 2014051) {
                        fourthPrizeList.add(qiHao);//添加四等奖
                    } else {
                        sixthPrizeList.add(qiHao);//添加六等奖
                    }
                }
            }
        }
        Log.v("TIME","中三个红球..." + String.valueOf(System.currentTimeMillis()-currentTime));

        lottoVO.setFirstPrizeList(firstPrizeList);
        lottoVO.setSecondPrizeList(secondPrizeList);
        lottoVO.setThirdPrizeList(thirdPrizeList);
        lottoVO.setFourthPrizeList(fourthPrizeList);
        lottoVO.setFifthPrizeList(fifthPrizeList);
        lottoVO.setSixthPrizeList(sixthPrizeList);
        lottoVO.setType(MkContent.LOTTERY_TYPE_SUPER_LOTTO);
        Intent intent = new Intent(this,SuperLottoResultActivity.class);
        intent.putExtra("lottoVO", lottoVO);
        startActivity(intent);
        Log.v("TIME","end..." + String.valueOf(System.currentTimeMillis()-currentTime));
        Toast.makeText(this,"查询用时：" + String.valueOf(System.currentTimeMillis()-currentTime),Toast.LENGTH_SHORT).show();

    }


    void searchRedBall(String ballNum, HashMap<Integer,Integer> lotteryIssueMap) {

        Cursor cursor = dbRead.query(MkContent.TABLE_NAME_SUPER_LOTTO, null, "red_1=? or red_2=? or red_3=? or red_4=? or red_5=?",
                new String[]{ballNum, ballNum, ballNum, ballNum, ballNum}, null, null, null);
        int lotteryNum = 0;
        while (cursor.moveToNext()) {
            lotteryNum++;
            int lotteryIssue = cursor.getInt(cursor.getColumnIndex("lottery_issue"));
            if(lotteryIssueMap.get(lotteryIssue) == null){
                lotteryIssueMap.put(lotteryIssue, 1);
            } else {
                lotteryIssueMap.put(lotteryIssue,lotteryIssueMap.get(lotteryIssue).intValue()+1);
            }
        }
        Log.v(TAG, "红色数字: " + ballNum + "  开奖期数 = " + lotteryNum);
    }

    void searchBuleBall(String ballNum, HashMap<Integer,Integer> lotteryIssueMap) {

        Cursor cursor = dbRead.query(MkContent.TABLE_NAME_SUPER_LOTTO, null, "blue_1=? or blue_2=?",
                new String[]{ballNum, ballNum}, null, null, null);
        int lotteryNum = 0;
        while (cursor.moveToNext()) {
            lotteryNum++;
            int lotteryIssue = cursor.getInt(cursor.getColumnIndex("lottery_issue"));
            if(lotteryIssueMap.get(lotteryIssue) == null){
                lotteryIssueMap.put(lotteryIssue, 1);
            } else {
                lotteryIssueMap.put(lotteryIssue,lotteryIssueMap.get(lotteryIssue).intValue()+1);
            }
        }
        Log.v(TAG, "蓝色数字: " + ballNum + "  开奖期数 = " + lotteryNum);
    }
}
