package com.mk.lottery.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mk.lottery.R;
import com.mk.lottery.dao.SsqDao;
import com.mk.lottery.model.SsqBO;
import com.mk.lottery.model.SsqVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SsqActivity extends Activity implements View.OnClickListener{
    private Map<String,String> clickRedMap = new HashMap<String, String>();
    private Map<String,String> clickBlueMap = new HashMap<String, String>();
    private static final String TAG = "SsqActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ssq);
        // 将字体文件保存在assets/fonts/目录下，www.linuxidc.com创建Typeface对象
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/fzmwt.ttf");
        Button btn_r_01 = (Button) findViewById(R.id.btn_ssq_red_01);
        Button btn_r_02 = (Button) findViewById(R.id.btn_ssq_red_02);
        Button btn_r_03 = (Button) findViewById(R.id.btn_ssq_red_03);
        Button btn_r_04 = (Button) findViewById(R.id.btn_ssq_red_04);
        Button btn_r_05 = (Button) findViewById(R.id.btn_ssq_red_05);
        Button btn_r_06 = (Button) findViewById(R.id.btn_ssq_red_06);
        Button btn_r_07 = (Button) findViewById(R.id.btn_ssq_red_07);
        Button btn_r_08 = (Button) findViewById(R.id.btn_ssq_red_08);
        Button btn_r_09 = (Button) findViewById(R.id.btn_ssq_red_09);
        Button btn_r_10 = (Button) findViewById(R.id.btn_ssq_red_10);
        Button btn_r_11 = (Button) findViewById(R.id.btn_ssq_red_11);
        Button btn_r_12 = (Button) findViewById(R.id.btn_ssq_red_12);
        Button btn_r_13 = (Button) findViewById(R.id.btn_ssq_red_13);
        Button btn_r_14 = (Button) findViewById(R.id.btn_ssq_red_14);
        Button btn_r_15 = (Button) findViewById(R.id.btn_ssq_red_15);
        Button btn_r_16 = (Button) findViewById(R.id.btn_ssq_red_16);
        Button btn_r_17 = (Button) findViewById(R.id.btn_ssq_red_17);
        Button btn_r_18 = (Button) findViewById(R.id.btn_ssq_red_18);
        Button btn_r_19 = (Button) findViewById(R.id.btn_ssq_red_19);
        Button btn_r_20 = (Button) findViewById(R.id.btn_ssq_red_20);
        Button btn_r_21 = (Button) findViewById(R.id.btn_ssq_red_21);
        Button btn_r_22 = (Button) findViewById(R.id.btn_ssq_red_22);
        Button btn_r_23 = (Button) findViewById(R.id.btn_ssq_red_23);
        Button btn_r_24 = (Button) findViewById(R.id.btn_ssq_red_24);
        Button btn_r_25 = (Button) findViewById(R.id.btn_ssq_red_25);
        Button btn_r_26 = (Button) findViewById(R.id.btn_ssq_red_26);
        Button btn_r_27 = (Button) findViewById(R.id.btn_ssq_red_27);
        Button btn_r_28 = (Button) findViewById(R.id.btn_ssq_red_28);
        Button btn_r_29 = (Button) findViewById(R.id.btn_ssq_red_29);
        Button btn_r_30 = (Button) findViewById(R.id.btn_ssq_red_30);
        Button btn_r_31 = (Button) findViewById(R.id.btn_ssq_red_31);
        Button btn_r_32 = (Button) findViewById(R.id.btn_ssq_red_32);
        Button btn_r_33 = (Button) findViewById(R.id.btn_ssq_red_33);
        btn_r_01.setTypeface(typeFace);
        btn_r_02.setTypeface(typeFace);
        btn_r_03.setTypeface(typeFace);
        btn_r_04.setTypeface(typeFace);
        btn_r_05.setTypeface(typeFace);
        btn_r_06.setTypeface(typeFace);
        btn_r_07.setTypeface(typeFace);
        btn_r_08.setTypeface(typeFace);
        btn_r_09.setTypeface(typeFace);
        btn_r_10.setTypeface(typeFace);
        btn_r_11.setTypeface(typeFace);
        btn_r_12.setTypeface(typeFace);
        btn_r_13.setTypeface(typeFace);
        btn_r_14.setTypeface(typeFace);
        btn_r_15.setTypeface(typeFace);
        btn_r_16.setTypeface(typeFace);
        btn_r_17.setTypeface(typeFace);
        btn_r_18.setTypeface(typeFace);
        btn_r_19.setTypeface(typeFace);
        btn_r_20.setTypeface(typeFace);
        btn_r_21.setTypeface(typeFace);
        btn_r_22.setTypeface(typeFace);
        btn_r_23.setTypeface(typeFace);
        btn_r_24.setTypeface(typeFace);
        btn_r_25.setTypeface(typeFace);
        btn_r_26.setTypeface(typeFace);
        btn_r_27.setTypeface(typeFace);
        btn_r_28.setTypeface(typeFace);
        btn_r_29.setTypeface(typeFace);
        btn_r_30.setTypeface(typeFace);
        btn_r_31.setTypeface(typeFace);
        btn_r_32.setTypeface(typeFace);
        btn_r_33.setTypeface(typeFace);

        btn_r_01.setOnClickListener(this);
        btn_r_02.setOnClickListener(this);
        btn_r_03.setOnClickListener(this);
        btn_r_04.setOnClickListener(this);
        btn_r_05.setOnClickListener(this);
        btn_r_06.setOnClickListener(this);
        btn_r_07.setOnClickListener(this);
        btn_r_08.setOnClickListener(this);
        btn_r_09.setOnClickListener(this);
        btn_r_10.setOnClickListener(this);
        btn_r_11.setOnClickListener(this);
        btn_r_12.setOnClickListener(this);
        btn_r_13.setOnClickListener(this);
        btn_r_14.setOnClickListener(this);
        btn_r_15.setOnClickListener(this);
        btn_r_16.setOnClickListener(this);
        btn_r_17.setOnClickListener(this);
        btn_r_18.setOnClickListener(this);
        btn_r_19.setOnClickListener(this);
        btn_r_20.setOnClickListener(this);
        btn_r_21.setOnClickListener(this);
        btn_r_22.setOnClickListener(this);
        btn_r_23.setOnClickListener(this);
        btn_r_24.setOnClickListener(this);
        btn_r_25.setOnClickListener(this);
        btn_r_26.setOnClickListener(this);
        btn_r_27.setOnClickListener(this);
        btn_r_28.setOnClickListener(this);
        btn_r_29.setOnClickListener(this);
        btn_r_30.setOnClickListener(this);
        btn_r_31.setOnClickListener(this);
        btn_r_32.setOnClickListener(this);
        btn_r_33.setOnClickListener(this);

        Button btn_b_01 = (Button) findViewById(R.id.btn_ssq_blue_01);
        Button btn_b_02 = (Button) findViewById(R.id.btn_ssq_blue_02);
        Button btn_b_03 = (Button) findViewById(R.id.btn_ssq_blue_03);
        Button btn_b_04 = (Button) findViewById(R.id.btn_ssq_blue_04);
        Button btn_b_05 = (Button) findViewById(R.id.btn_ssq_blue_05);
        Button btn_b_06 = (Button) findViewById(R.id.btn_ssq_blue_06);
        Button btn_b_07 = (Button) findViewById(R.id.btn_ssq_blue_07);
        Button btn_b_08 = (Button) findViewById(R.id.btn_ssq_blue_08);
        Button btn_b_09 = (Button) findViewById(R.id.btn_ssq_blue_09);
        Button btn_b_10 = (Button) findViewById(R.id.btn_ssq_blue_10);
        Button btn_b_11 = (Button) findViewById(R.id.btn_ssq_blue_11);
        Button btn_b_12 = (Button) findViewById(R.id.btn_ssq_blue_12);
        Button btn_b_13 = (Button) findViewById(R.id.btn_ssq_blue_13);
        Button btn_b_14 = (Button) findViewById(R.id.btn_ssq_blue_14);
        Button btn_b_15 = (Button) findViewById(R.id.btn_ssq_blue_15);
        Button btn_b_16 = (Button) findViewById(R.id.btn_ssq_blue_16);
        btn_b_01.setTypeface(typeFace);
        btn_b_02.setTypeface(typeFace);
        btn_b_03.setTypeface(typeFace);
        btn_b_04.setTypeface(typeFace);
        btn_b_05.setTypeface(typeFace);
        btn_b_06.setTypeface(typeFace);
        btn_b_07.setTypeface(typeFace);
        btn_b_08.setTypeface(typeFace);
        btn_b_09.setTypeface(typeFace);
        btn_b_10.setTypeface(typeFace);
        btn_b_11.setTypeface(typeFace);
        btn_b_12.setTypeface(typeFace);
        btn_b_13.setTypeface(typeFace);
        btn_b_14.setTypeface(typeFace);
        btn_b_15.setTypeface(typeFace);
        btn_b_16.setTypeface(typeFace);
        btn_b_01.setOnClickListener(this);
        btn_b_02.setOnClickListener(this);
        btn_b_03.setOnClickListener(this);
        btn_b_04.setOnClickListener(this);
        btn_b_05.setOnClickListener(this);
        btn_b_06.setOnClickListener(this);
        btn_b_07.setOnClickListener(this);
        btn_b_08.setOnClickListener(this);
        btn_b_09.setOnClickListener(this);
        btn_b_10.setOnClickListener(this);
        btn_b_11.setOnClickListener(this);
        btn_b_12.setOnClickListener(this);
        btn_b_13.setOnClickListener(this);
        btn_b_14.setOnClickListener(this);
        btn_b_15.setOnClickListener(this);
        btn_b_16.setOnClickListener(this);

        Button btnInit = (Button) findViewById(R.id.btn_ssq_init);
        btnInit.setOnClickListener(this);

        Button btnImport = (Button) findViewById(R.id.btn_ssq_import);
        btnImport.setOnClickListener(this);

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_ssq_search);
        imageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SsqDao ssqDao = new SsqDao(this);

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
                        Toast.makeText(SsqActivity.this, "只能选择6个红球。", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SsqActivity.this,"只能选择1个蓝球。",Toast.LENGTH_SHORT).show();
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
                ssqDao.initData();
                break;
            case R.id.btn_ssq_import://把raw里面的数据文件拷贝到databases文件夹下
                ssqDao.importDatabase();
                Toast.makeText(SsqActivity.this,"数据库移动完成！！！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ssq_search://查询按钮
                //判断是否有6个红球和1个蓝球
//                if(clickRedMap.size() < 6 ){
//                    Toast.makeText(SsqActivity.this,"必须选择6个红球！",Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                if(clickBlueMap.size() < 1 ){
//                    Toast.makeText(SsqActivity.this,"必须选择1个蓝球！",Toast.LENGTH_SHORT).show();
//                    break;
//                }

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
                int temp = 1;
                SsqVO ssqVO = new SsqVO();
                for(Map.Entry<String,String> mapping:mappingList){
                    System.out.println(mapping.getKey()+":"+mapping.getValue());
                    switch (temp) {
                        case 1:
                            ssqVO.setFirst(mapping.getValue());
                            temp++;
                            break;
                        case 2:
                            ssqVO.setSecond(mapping.getValue());
                            temp++;
                            break;
                        case 3:
                            ssqVO.setThird(mapping.getValue());
                            temp++;
                            break;
                        case 4:
                            ssqVO.setFourth(mapping.getValue());
                            temp++;
                            break;
                        case 5:
                            ssqVO.setFifth(mapping.getValue());
                            temp++;
                            break;
                        case 6:
                            ssqVO.setSixth(mapping.getValue());
                            temp++;
                            break;
                        default:
                            break;
                    }
                }
                Set<Map.Entry<String, String>>  set  = clickBlueMap.entrySet();
                for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                    ssqVO.setBlue(entry.getValue());
                }

                String first = ssqVO.getFirst();
                String second = ssqVO.getSecond();
                String third = ssqVO.getThird();
                String fourth = ssqVO.getFourth();
                String fifth = ssqVO.getFifth();
                String sixth = ssqVO.getSixth();
                String blue = ssqVO.getBlue();

                //第一个数字
                Map<Integer, Integer> lotteryIssueMap = new HashMap<Integer, Integer>();
                List<SsqBO> firstBOs = ssqDao.getSsqListByRedBall(first);
                Log.v(TAG, "第一个数字: " + first + "  开奖期数 = " + firstBOs.size());
                for (int i = 0; i < firstBOs.size(); i++) {
                    SsqBO firstBO =  firstBOs.get(i);
                    lotteryIssueMap.put(firstBO.getLotteryIssue(), 1);
                }

                //第二个数字
                List<SsqBO> secondBOs = ssqDao.getSsqListByRedBall(second);
                Log.v(TAG,"第二个数字: "+second +"  开奖期数 = " + secondBOs.size());
                for (int i = 0; i < secondBOs.size(); i++) {
                    SsqBO ssqBO =  secondBOs.get(i);
                    //判断期号有相同的再MAP VALUE上面加上1
                    Set<Map.Entry<Integer, Integer>> issueSet = lotteryIssueMap.entrySet();
                    int count = 0;//用于判断是否这一期在这个MAP里面已经有值。
                    for (Iterator<Map.Entry<Integer, Integer>> it = issueSet.iterator(); it.hasNext();) {
                        Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                        if(entry.getKey() == ssqBO.getLotteryIssue()){
                            count++;
                        }
                    }
                    if (count > 0) {
                        //给相应的MAP值加1
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(),lotteryIssueMap.get(ssqBO.getLotteryIssue()).intValue()+1);
                    }else {
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(), 1);
                    }
                }

                //第三个数字
                List<SsqBO> thirdBOs = ssqDao.getSsqListByRedBall(third);
                Log.v(TAG, "第三个数字: " + third + "  开奖期数 = " + thirdBOs.size());
                for (int i = 0; i < thirdBOs.size(); i++) {
                    SsqBO ssqBO =  thirdBOs.get(i);
                    //判断期号有相同的再MAP VALUE上面加上1
                    Set<Map.Entry<Integer, Integer>> issueSet = lotteryIssueMap.entrySet();
                    int count = 0;//用于判断是否这一期在这个MAP里面已经有值。
                    for (Iterator<Map.Entry<Integer, Integer>> it = issueSet.iterator(); it.hasNext();) {
                        Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                        if(entry.getKey() == ssqBO.getLotteryIssue()){
                            count++;
                        }
                    }
                    if (count > 0) {
                        //给相应的MAP值加1
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(),lotteryIssueMap.get(ssqBO.getLotteryIssue()).intValue()+1);
                    }else {
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(), 1);
                    }
                }

                //第四个数字
                List<SsqBO> fourthBOs = ssqDao.getSsqListByRedBall(fourth);
                Log.v(TAG, "第四个数字: " + fourth + "  开奖期数 = " + fourthBOs.size());
                for (int i = 0; i < fourthBOs.size(); i++) {
                    SsqBO ssqBO =  fourthBOs.get(i);
                    //判断期号有相同的再MAP VALUE上面加上1
                    Set<Map.Entry<Integer, Integer>> issueSet = lotteryIssueMap.entrySet();
                    int count = 0;//用于判断是否这一期在这个MAP里面已经有值。
                    for (Iterator<Map.Entry<Integer, Integer>> it = issueSet.iterator(); it.hasNext();) {
                        Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                        if(entry.getKey() == ssqBO.getLotteryIssue()){
                            count++;
                        }
                    }
                    if (count > 0) {
                        //给相应的MAP值加1
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(),lotteryIssueMap.get(ssqBO.getLotteryIssue()).intValue()+1);
                    }else {
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(), 1);
                    }
                }

                //第五个数字
                List<SsqBO> fifthBOs = ssqDao.getSsqListByRedBall(fifth);
                Log.v(TAG, "第五个数字: " + fifth + "  开奖期数 = " + fifthBOs.size());
                for (int i = 0; i < fifthBOs.size(); i++) {
                    SsqBO ssqBO =  fifthBOs.get(i);
                    //判断期号有相同的再MAP VALUE上面加上1
                    Set<Map.Entry<Integer, Integer>> issueSet = lotteryIssueMap.entrySet();
                    int count = 0;//用于判断是否这一期在这个MAP里面已经有值。
                    for (Iterator<Map.Entry<Integer, Integer>> it = issueSet.iterator(); it.hasNext();) {
                        Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                        if(entry.getKey() == ssqBO.getLotteryIssue()){
                            count++;
                        }
                    }
                    if (count > 0) {
                        //给相应的MAP值加1
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(),lotteryIssueMap.get(ssqBO.getLotteryIssue()).intValue()+1);
                    }else {
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(), 1);
                    }
                }


                //第六个数字
                List<SsqBO> sixthBOs = ssqDao.getSsqListByRedBall(sixth);
                Log.v(TAG, "第六个数字: " + sixth + "  开奖期数 = " + sixthBOs.size());
                for (int i = 0; i < sixthBOs.size(); i++) {
                    SsqBO ssqBO =  sixthBOs.get(i);
                    //判断期号有相同的再MAP VALUE上面加上1
                    Set<Map.Entry<Integer, Integer>> issueSet = lotteryIssueMap.entrySet();
                    int count = 0;//用于判断是否这一期在这个MAP里面已经有值。
                    for (Iterator<Map.Entry<Integer, Integer>> it = issueSet.iterator(); it.hasNext();) {
                        Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                        if(entry.getKey() == ssqBO.getLotteryIssue()){
                            count++;
                        }
                    }
                    if (count > 0) {
                        //给相应的MAP值加1
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(),lotteryIssueMap.get(ssqBO.getLotteryIssue()).intValue()+1);
                    }else {
                        lotteryIssueMap.put(ssqBO.getLotteryIssue(), 1);
                    }
                }


                //蓝球
                Map<Integer, Integer> blueMap = new HashMap<Integer, Integer>();
                List<SsqBO> blueBOs = ssqDao.getSsqListByBlueBall(blue);
                Log.v(TAG, "蓝色号码: " + blue + "  开奖期数 = " + blueBOs.size());
                for (int i = 0; i < blueBOs.size(); i++) {
                    SsqBO firstBO =  blueBOs.get(i);
                    blueMap.put(firstBO.getLotteryIssue(), 1);
                }


                //一等奖 中6+1
                //二等奖 中6+0  当期高等奖奖金的30%
                //三等奖 中5+1  单注奖金额固定为3000元
                //四等级 中 4+1 或者 5+0  单注奖金额固定为200元

                //获取红球为六，五，四的MAP
                List<Integer> sixRedList = new ArrayList<Integer>();
                List<Integer> fiveRedList = new ArrayList<Integer>();
                List<Integer> fourRedList = new ArrayList<Integer>();
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
                List<Integer> firstPrizeList = new ArrayList<Integer>();
                List<Integer> secondPrizeList = new ArrayList<Integer>();
                List<Integer> thirdPrizeList = new ArrayList<Integer>();
                List<Integer> fourthPrizeList = new ArrayList<Integer>();

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

                if(fourRedList.size() > 0){
                    int fourSize = fourRedList.size();
                    for (int i = 0; i < fourSize; i++) {
                        int qiHao = fourRedList.get(i);
                        if(blueMap.get(qiHao)!= null){
                            fourthPrizeList.add(qiHao);//添加四等奖
                        }
                    }
                }
                ssqVO.setFirstPrizeList(firstPrizeList);
                ssqVO.setSecondPrizeList(secondPrizeList);
                ssqVO.setThirdPrizeList(thirdPrizeList);
                ssqVO.setFourthPrizeList(fourthPrizeList);

                Intent intent = new Intent(this,SsqSearchActivity.class);
                intent.putExtra("ssqVO", ssqVO);
                startActivity(intent);

                break;
            default:
        }




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
