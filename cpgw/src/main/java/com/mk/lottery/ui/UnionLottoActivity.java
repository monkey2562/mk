package com.mk.lottery.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mk.lottery.R;
import com.mk.lottery.dao.UnionLottoDao;
import com.mk.lottery.handler.RequestHandler;
import com.mk.lottery.model.LottoVO;
import com.mk.lottery.model.UnionLottoBO;
import com.mk.lottery.util.MkContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UnionLottoActivity extends Activity implements View.OnClickListener{
    private HashMap<String,String> clickRedMap = new HashMap<String, String>();
    private HashMap<String,String> clickBlueMap = new HashMap<String, String>();
    private static final String TAG = "UnionLottoActivity";

    Button btnRed01,btnRed02,btnRed03,btnRed04,btnRed05,btnRed06,btnRed07,btnRed08,btnRed09,btnRed10,
            btnRed11,btnRed12,btnRed13,btnRed14,btnRed15,btnRed16,btnRed17,btnRed18,btnRed19,btnRed20,
            btnRed21,btnRed22,btnRed23,btnRed24,btnRed25,btnRed26,btnRed27,btnRed28,btnRed29,btnRed30,
            btnRed31,btnRed32,btnRed33,btnBlue01,btnBlue02,btnBlue03,btnBlue04,btnBlue05,btnBlue06,
            btnBlue07,btnBlue08,btnBlue09,btnBlue10,btnBlue11,btnBlue12,btnBlue13,btnBlue14,btnBlue15,
            btnBlue16;

    Button btnInit,btnImport,btnRequest;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("SsqActivity", msg.what + "");
            switch (msg.what) {
                case 0:
//                    loadMainUI();
                    break;
                case 1:
//                    showUpdateDialog();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_union_lotto);
        //初始化按钮
        initButton();
        //按钮设置监听事件
        initOnClick();
        //按钮设置字体
        setTypeface();


        btnInit = (Button) findViewById(R.id.btn_ssq_init);
        btnInit.setOnClickListener(this);

        btnImport = (Button) findViewById(R.id.btn_ssq_import);
        btnImport.setOnClickListener(this);

        btnRequest = (Button) findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(this);

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_ssq_search);
        imageButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        UnionLottoDao unionLottoDao = new UnionLottoDao(this);

        switch (view.getId()) {
            case R.id.btn_ssq_red_01:
            case R.id.btn_ssq_red_02:
            case R.id.btn_ssq_red_03:
            case R.id.btn_ssq_red_04:
            case R.id.btn_ssq_red_05:
            case R.id.btn_ssq_red_06:
            case R.id.btn_ssq_red_07:
            case R.id.btn_ssq_red_08:
            case R.id.btn_ssq_red_09:
            case R.id.btn_ssq_red_10:
            case R.id.btn_ssq_red_11:
            case R.id.btn_ssq_red_12:
            case R.id.btn_ssq_red_13:
            case R.id.btn_ssq_red_14:
            case R.id.btn_ssq_red_15:
            case R.id.btn_ssq_red_16:
            case R.id.btn_ssq_red_17:
            case R.id.btn_ssq_red_18:
            case R.id.btn_ssq_red_19:
            case R.id.btn_ssq_red_20:
            case R.id.btn_ssq_red_21:
            case R.id.btn_ssq_red_22:
            case R.id.btn_ssq_red_23:
            case R.id.btn_ssq_red_24:
            case R.id.btn_ssq_red_25:
            case R.id.btn_ssq_red_26:
            case R.id.btn_ssq_red_27:
            case R.id.btn_ssq_red_28:
            case R.id.btn_ssq_red_29:
            case R.id.btn_ssq_red_30:
            case R.id.btn_ssq_red_31:
            case R.id.btn_ssq_red_32:
            case R.id.btn_ssq_red_33:
                Button btnRed = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if(clickRedMap.get(btnRed.getText().toString()) == null){
                    //判断只能按下六个红球
                    if(clickRedMap.size()>=6){
                        Toast.makeText(UnionLottoActivity.this, "只能选择6个红球。", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickRedMap.put(btnRed.getText().toString(), btnRed.getText().toString());
                    btnRed.setBackgroundResource(R.drawable.ball_red);
                    btnRed.setTextColor(Color.WHITE);
                }else{
                    btnRed.setBackgroundResource(R.drawable.ball_gray);
                    clickRedMap.remove(btnRed.getText().toString());
                    btnRed.setTextColor(getResources().getColor(R.color.redBall));
                }

                break;
            case R.id.btn_ssq_blue_01:
            case R.id.btn_ssq_blue_02:
            case R.id.btn_ssq_blue_03:
            case R.id.btn_ssq_blue_04:
            case R.id.btn_ssq_blue_05:
            case R.id.btn_ssq_blue_06:
            case R.id.btn_ssq_blue_07:
            case R.id.btn_ssq_blue_08:
            case R.id.btn_ssq_blue_09:
            case R.id.btn_ssq_blue_10:
            case R.id.btn_ssq_blue_11:
            case R.id.btn_ssq_blue_12:
            case R.id.btn_ssq_blue_13:
            case R.id.btn_ssq_blue_14:
            case R.id.btn_ssq_blue_15:
            case R.id.btn_ssq_blue_16:

                Button btnBlue = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if(clickBlueMap.get(btnBlue.getText().toString()) == null){
                    //判断只能按下1个蓝球
                    if(clickBlueMap.size()>=1){
                        Toast.makeText(UnionLottoActivity.this,"只能选择1个蓝球。",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickBlueMap.put(btnBlue.getText().toString(),btnBlue.getText().toString());
                    btnBlue.setBackgroundResource(R.drawable.ball_blue);
                    btnBlue.setTextColor(Color.WHITE);
                }else{
                    btnBlue.setBackgroundResource(R.drawable.ball_gray);
                    clickBlueMap.remove(btnBlue.getText().toString());
                    btnBlue.setTextColor(getResources().getColor(R.color.blueBall));
                }
                break;
            case R.id.btn_ssq_init://初始化数据库
                unionLottoDao.initUnionLottoData();
                break;
            case R.id.btn_ssq_import://把raw里面的数据文件拷贝到databases文件夹下
                unionLottoDao.importDatabase();
                Toast.makeText(UnionLottoActivity.this,"数据库移动完成！！！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ssq_search://查询按钮
                search(unionLottoDao);
               break;

            //请求网站数据
            case R.id.btnRequest:
                //启动线程
                Runnable r = new RequestHandler(UnionLottoActivity.this, RequestHandler.UPDATE_TYPE_UNION_LOTTO);
                Thread thread = new Thread(r);
                thread.start();


                break;
        }




    }

    void initButton(){
        btnRed01 = (Button) findViewById(R.id.btn_ssq_red_01);
        btnRed02 = (Button) findViewById(R.id.btn_ssq_red_02);
        btnRed03 = (Button) findViewById(R.id.btn_ssq_red_03);
        btnRed04 = (Button) findViewById(R.id.btn_ssq_red_04);
        btnRed05 = (Button) findViewById(R.id.btn_ssq_red_05);
        btnRed06 = (Button) findViewById(R.id.btn_ssq_red_06);
        btnRed07 = (Button) findViewById(R.id.btn_ssq_red_07);
        btnRed08 = (Button) findViewById(R.id.btn_ssq_red_08);
        btnRed09 = (Button) findViewById(R.id.btn_ssq_red_09);
        btnRed10 = (Button) findViewById(R.id.btn_ssq_red_10);
        btnRed11 = (Button) findViewById(R.id.btn_ssq_red_11);
        btnRed12 = (Button) findViewById(R.id.btn_ssq_red_12);
        btnRed13 = (Button) findViewById(R.id.btn_ssq_red_13);
        btnRed14 = (Button) findViewById(R.id.btn_ssq_red_14);
        btnRed15 = (Button) findViewById(R.id.btn_ssq_red_15);
        btnRed16 = (Button) findViewById(R.id.btn_ssq_red_16);
        btnRed17 = (Button) findViewById(R.id.btn_ssq_red_17);
        btnRed18 = (Button) findViewById(R.id.btn_ssq_red_18);
        btnRed19 = (Button) findViewById(R.id.btn_ssq_red_19);
        btnRed20 = (Button) findViewById(R.id.btn_ssq_red_20);
        btnRed21 = (Button) findViewById(R.id.btn_ssq_red_21);
        btnRed22 = (Button) findViewById(R.id.btn_ssq_red_22);
        btnRed23 = (Button) findViewById(R.id.btn_ssq_red_23);
        btnRed24 = (Button) findViewById(R.id.btn_ssq_red_24);
        btnRed25 = (Button) findViewById(R.id.btn_ssq_red_25);
        btnRed26 = (Button) findViewById(R.id.btn_ssq_red_26);
        btnRed27 = (Button) findViewById(R.id.btn_ssq_red_27);
        btnRed28 = (Button) findViewById(R.id.btn_ssq_red_28);
        btnRed29 = (Button) findViewById(R.id.btn_ssq_red_29);
        btnRed30 = (Button) findViewById(R.id.btn_ssq_red_30);
        btnRed31 = (Button) findViewById(R.id.btn_ssq_red_31);
        btnRed32 = (Button) findViewById(R.id.btn_ssq_red_32);
        btnRed33 = (Button) findViewById(R.id.btn_ssq_red_33);
        btnBlue01 = (Button) findViewById(R.id.btn_ssq_blue_01);
        btnBlue02 = (Button) findViewById(R.id.btn_ssq_blue_02);
        btnBlue03 = (Button) findViewById(R.id.btn_ssq_blue_03);
        btnBlue04 = (Button) findViewById(R.id.btn_ssq_blue_04);
        btnBlue05 = (Button) findViewById(R.id.btn_ssq_blue_05);
        btnBlue06 = (Button) findViewById(R.id.btn_ssq_blue_06);
        btnBlue07 = (Button) findViewById(R.id.btn_ssq_blue_07);
        btnBlue08 = (Button) findViewById(R.id.btn_ssq_blue_08);
        btnBlue09 = (Button) findViewById(R.id.btn_ssq_blue_09);
        btnBlue10 = (Button) findViewById(R.id.btn_ssq_blue_10);
        btnBlue11 = (Button) findViewById(R.id.btn_ssq_blue_11);
        btnBlue12 = (Button) findViewById(R.id.btn_ssq_blue_12);
        btnBlue13 = (Button) findViewById(R.id.btn_ssq_blue_13);
        btnBlue14 = (Button) findViewById(R.id.btn_ssq_blue_14);
        btnBlue15 = (Button) findViewById(R.id.btn_ssq_blue_15);
        btnBlue16 = (Button) findViewById(R.id.btn_ssq_blue_16);
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
        btnBlue13.setOnClickListener(this);
        btnBlue14.setOnClickListener(this);
        btnBlue15.setOnClickListener(this);
        btnBlue16.setOnClickListener(this);
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
        btnBlue13.setTypeface(typeFace);
        btnBlue14.setTypeface(typeFace);
        btnBlue15.setTypeface(typeFace);
        btnBlue16.setTypeface(typeFace);
    }

    void search(UnionLottoDao unionLottoDao){
        //判断是否有6个红球和1个蓝球
//                if(clickRedMap.size() < 6 ){
//                    Toast.makeText(SsqActivity.this,"必须选择6个红球！",Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                if(clickBlueMap.size() < 1 ){
//                    Toast.makeText(SsqActivity.this,"必须选择1个蓝球！",Toast.LENGTH_SHORT).show();
//                    break;
//                }

        long currentTime = System.currentTimeMillis();
        Log.v("TIME","Start..." + String.valueOf(currentTime));


        //1.获取一组球，2.对MAP进行排序 3.带参数跳转
        List<Map.Entry<String, String>> mappingList = null;
        //通过ArrayList构造函数把map.entrySet()转换成list
        mappingList = new ArrayList<Map.Entry<String,String>>(clickRedMap.entrySet());
        //通过比较器实现比较排序
        Collections.sort(mappingList, new Comparator<Map.Entry<String, String>>() {

            @Override
            public int compare(Map.Entry<String, String> mapping1,
                               Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }

        });
        Log.v("TIME","排序..." + String.valueOf(System.currentTimeMillis()-currentTime));

        int temp = 1;
        LottoVO lottoVO = new LottoVO();
        for(Map.Entry<String,String> mapping:mappingList){
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
                case 6:
                    lottoVO.setSixth(mapping.getValue());
                    temp++;
                    break;
                default:
                    break;
            }
        }
        Set<Map.Entry<String, String>>  set  = clickBlueMap.entrySet();
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            lottoVO.setSeventh(entry.getValue());
        }

        Log.v("TIME","封装红球蓝球..." + String.valueOf(System.currentTimeMillis()-currentTime));

        String first = lottoVO.getFirst();
        String second = lottoVO.getSecond();
        String third = lottoVO.getThird();
        String fourth = lottoVO.getFourth();
        String fifth = lottoVO.getFifth();
        String sixth = lottoVO.getSixth();
        String blue = lottoVO.getSeventh();
        //用于统计期号--中几个红球
        Map<Integer, Integer> lotteryIssueMap = new HashMap<Integer, Integer>();
        //第一个数字
        List<UnionLottoBO> firstBOs = unionLottoDao.getSsqListByRedBall(first);
        Log.v(TAG, "第一个数字: " + first + "  开奖期数 = " + firstBOs.size());
        for (UnionLottoBO firstBO : firstBOs) {

            lotteryIssueMap.put(firstBO.lotteryIssue, 1);
        }
        Log.v("TIME","第一个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第二个数字
        List<UnionLottoBO> secondBOs = unionLottoDao.getSsqListByRedBall(second);
        Log.v(TAG,"第二个数字: "+second +"  开奖期数 = " + secondBOs.size());
        for (UnionLottoBO unionLottoBO : secondBOs) {
            //判断期号有相同的再MAP VALUE上面加上1
            if(lotteryIssueMap.get(unionLottoBO.lotteryIssue)!=null){
                //给相应的MAP值加1
                lotteryIssueMap.put(unionLottoBO.lotteryIssue,lotteryIssueMap.get(unionLottoBO.lotteryIssue).intValue()+1);
            } else {
                lotteryIssueMap.put(unionLottoBO.lotteryIssue, 1);
            }
        }
        Log.v("TIME","第二个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第三个数字
        List<UnionLottoBO> thirdBOs = unionLottoDao.getSsqListByRedBall(third);
        Log.v(TAG, "第三个数字: " + third + "  开奖期数 = " + thirdBOs.size());
        for (UnionLottoBO unionLottoBO : thirdBOs) {
            //判断期号有相同的再MAP VALUE上面加上1
            if(lotteryIssueMap.get(unionLottoBO.lotteryIssue)!=null){
                //给相应的MAP值加1
                lotteryIssueMap.put(unionLottoBO.lotteryIssue,lotteryIssueMap.get(unionLottoBO.lotteryIssue).intValue()+1);
            } else {
                lotteryIssueMap.put(unionLottoBO.lotteryIssue, 1);
            }
        }
        Log.v("TIME","第三个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第四个数字
        List<UnionLottoBO> fourthBOs = unionLottoDao.getSsqListByRedBall(fourth);
        Log.v(TAG, "第四个数字: " + fourth + "  开奖期数 = " + fourthBOs.size());
        for (UnionLottoBO unionLottoBO : fourthBOs) {
            //判断期号有相同的再MAP VALUE上面加上1
            if(lotteryIssueMap.get(unionLottoBO.lotteryIssue)!=null){
                //给相应的MAP值加1
                lotteryIssueMap.put(unionLottoBO.lotteryIssue,lotteryIssueMap.get(unionLottoBO.lotteryIssue).intValue()+1);
            } else {
                lotteryIssueMap.put(unionLottoBO.lotteryIssue, 1);
            }
        }
        Log.v("TIME","第四个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //第五个数字
        List<UnionLottoBO> fifthBOs = unionLottoDao.getSsqListByRedBall(fifth);
        Log.v(TAG, "第五个数字: " + fifth + "  开奖期数 = " + fifthBOs.size());
        for (UnionLottoBO unionLottoBO : fifthBOs) {
            //判断期号有相同的再MAP VALUE上面加上1
            if(lotteryIssueMap.get(unionLottoBO.lotteryIssue)!=null){
                //给相应的MAP值加1
                lotteryIssueMap.put(unionLottoBO.lotteryIssue,lotteryIssueMap.get(unionLottoBO.lotteryIssue).intValue()+1);
            } else {
                lotteryIssueMap.put(unionLottoBO.lotteryIssue, 1);
            }
        }
        Log.v("TIME","第五个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));

        //第六个数字
        List<UnionLottoBO> sixthBOs = unionLottoDao.getSsqListByRedBall(sixth);
        Log.v(TAG, "第六个数字: " + sixth + "  开奖期数 = " + sixthBOs.size());
        for (UnionLottoBO unionLottoBO : sixthBOs) {
            //判断期号有相同的再MAP VALUE上面加上1
            if(lotteryIssueMap.get(unionLottoBO.lotteryIssue)!=null){
                //给相应的MAP值加1
                lotteryIssueMap.put(unionLottoBO.lotteryIssue,lotteryIssueMap.get(unionLottoBO.lotteryIssue).intValue()+1);
            } else {
                lotteryIssueMap.put(unionLottoBO.lotteryIssue, 1);
            }
        }
        Log.v("TIME","第六个数字..." + String.valueOf(System.currentTimeMillis()-currentTime));

        //蓝球
        Map<Integer, Integer> blueMap = new HashMap<Integer, Integer>();
        List<UnionLottoBO> blueBOs = unionLottoDao.getSsqListByBlueBall(blue);
        Log.v(TAG, "蓝色号码: " + blue + "  开奖期数 = " + blueBOs.size());
        for (UnionLottoBO firstBO : blueBOs) {
            blueMap.put(firstBO.lotteryIssue, 1);
        }

        Log.v("TIME","蓝球..." + String.valueOf(System.currentTimeMillis()-currentTime));
        //一等奖 中6+1
        //二等奖 中6+0  当期高等奖奖金的30%
        //三等奖 中5+1  单注奖金额固定为3000元
        //四等级 中 4+1 或者 5+0  单注奖金额固定为200元

        //获取红球为六，五，四的MAP
        ArrayList<Integer> sixRedList = new ArrayList<Integer>();
        ArrayList<Integer> fiveRedList = new ArrayList<Integer>();
        ArrayList<Integer> fourRedList = new ArrayList<Integer>();
        Set<Integer> key = lotteryIssueMap.keySet();
        for(Iterator it = key.iterator();it.hasNext();){
            Integer qiHao = (Integer) it.next();
            Integer value = lotteryIssueMap.get(qiHao);

            if(value == 6){//中六个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                sixRedList.add(qiHao);
            }else if (value == 5){//中五个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                fiveRedList.add(qiHao);
            }else if( value == 4){//中四个红球
                Log.v(TAG,"期号："+ qiHao + " 次数：" + value );
                fourRedList.add(qiHao);
            }
        }

        Log.v("TIME","分析红球四五六..." + String.valueOf(System.currentTimeMillis()-currentTime));
        ArrayList<Integer> firstPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> secondPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> thirdPrizeList = new ArrayList<Integer>();
        ArrayList<Integer> fourthPrizeList = new ArrayList<Integer>();

        //判断获奖 红色+蓝色
        if(sixRedList.size() > 0){
            int sixSize =  sixRedList.size();
            for( int i = 0 ; i < sixSize ; i++) {
                int qiHao = sixRedList.get(i);
                if(blueMap.get(qiHao)!= null){
                    firstPrizeList.add(qiHao);//添加一等奖
                }else {
                    secondPrizeList.add(qiHao);//添加二等奖
                }
            }
        }
        Log.v("TIME","添加一二等奖..." + String.valueOf(System.currentTimeMillis()-currentTime));
        if(fiveRedList.size() > 0){
            int fiveSize = fiveRedList.size();
            for (int i = 0; i < fiveSize; i++) {
                int qiHao = fiveRedList.get(i);
                if(blueMap.get(qiHao)!= null){
                    thirdPrizeList.add(qiHao);//添加三等奖
                } else {
                    fourthPrizeList.add(qiHao);//添加四等奖
                }
            }
        }
        Log.v("TIME","添加三四等奖..." + String.valueOf(System.currentTimeMillis()-currentTime));
        if(fourRedList.size() > 0){
            int fourSize = fourRedList.size();
            for (int i = 0; i < fourSize; i++) {
                int qiHao = fourRedList.get(i);
                if(blueMap.get(qiHao)!= null){
                    fourthPrizeList.add(qiHao);//添加四等奖
                }
            }
        }
        lottoVO.setFirstPrizeList(firstPrizeList);
        lottoVO.setSecondPrizeList(secondPrizeList);
        lottoVO.setThirdPrizeList(thirdPrizeList);
        lottoVO.setFourthPrizeList(fourthPrizeList);
        lottoVO.setType(MkContent.LOTTERY_TYPE_UNION_LOTTO);
        Intent intent = new Intent(this,UnionLottoResultActivity.class);
        intent.putExtra("lottoVO", lottoVO);
        startActivity(intent);
        Log.v("TIME","end..." + String.valueOf(System.currentTimeMillis()-currentTime));
        Toast.makeText(this,"查询用时：" + String.valueOf(System.currentTimeMillis()-currentTime),Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ssq, menu);
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
}
