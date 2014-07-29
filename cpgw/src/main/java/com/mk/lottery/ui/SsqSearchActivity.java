package com.mk.lottery.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mk.lottery.R;
import com.mk.lottery.dao.SsqDao;
import com.mk.lottery.model.SsqBO;
import com.mk.lottery.model.SsqVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SsqSearchActivity extends ActionBarActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ssq_search);
        Intent intent = getIntent();
        SsqVO ssqVO = (SsqVO) intent.getSerializableExtra("ssqVO");
        Button btnRed1 = (Button) findViewById(R.id.btn_ssq_search_01);
        Button btnRed2 = (Button) findViewById(R.id.btn_ssq_search_02);
        Button btnRed3 = (Button) findViewById(R.id.btn_ssq_search_03);
        Button btnRed4 = (Button) findViewById(R.id.btn_ssq_search_04);
        Button btnRed5 = (Button) findViewById(R.id.btn_ssq_search_05);
        Button btnRed6 = (Button) findViewById(R.id.btn_ssq_search_06);
        Button btnRed7 = (Button) findViewById(R.id.btn_ssq_search_07);
        String first = ssqVO.getFirst();
        String second = ssqVO.getSecond();
        String third = ssqVO.getThird();
        String fourth = ssqVO.getFourth();
        String fifth = ssqVO.getFifth();
        String sixth = ssqVO.getSixth();
        String blue = ssqVO.getBlue();
        btnRed1.setText(first);
        btnRed2.setText(second);
        btnRed3.setText(third);
        btnRed4.setText(fourth);
        btnRed5.setText(fifth);
        btnRed6.setText(sixth);
        btnRed7.setText(blue);

        List<Integer> firstPrizeList =ssqVO.getFirstPrizeList();
        List<Integer> secondPrizeList =ssqVO.getSecondPrizeList();
        List<Integer> thirdPrizeList =ssqVO.getThirdPrizeList();
        List<Integer> fourthPrizeList =ssqVO.getFourthPrizeList();

        listView = (ListView) findViewById(R.id.lvListView01);
        SsqDao ssqDao = new SsqDao(this);
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        //一等奖
        if (firstPrizeList != null && firstPrizeList.size() > 0) {
            for(int i = 0 ;i < firstPrizeList.size();i++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                SsqBO bo = ssqDao.findByLotteryIssue(firstPrizeList.get(i));
                map.put("Prize","一等奖" + bo.getFirstAmount() + "元");
                map.put("LotteryIssue", firstPrizeList.get(i));//期号
                map.put("Number",bo.getRed1() + "." + bo.getRed2() + "." + bo.getRed3() + "." +
                        bo.getRed4() + "." + bo.getRed5() + "." + bo.getRed6() + "." + bo.getBlue());
                listItem.add(map);
            }
        }
        //二等奖
        if (secondPrizeList != null && secondPrizeList.size() > 0) {
            for(int i = 0 ;i < secondPrizeList.size();i++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                SsqBO bo = ssqDao.findByLotteryIssue(secondPrizeList.get(i));
                map.put("Prize","二等奖"+bo.getSecondAmount() + "元");
                map.put("LotteryIssue", secondPrizeList.get(i));//期号
                map.put("Number",bo.getRed1() + "." + bo.getRed2() + "." + bo.getRed3() + "." +
                        bo.getRed4() + "." + bo.getRed5() + "." + bo.getRed6() + "." + bo.getBlue());
                listItem.add(map);
            }
        }
        //三等奖
        if (thirdPrizeList != null && thirdPrizeList.size() > 0) {
            for(int i = 0 ;i < thirdPrizeList.size();i++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                SsqBO bo = ssqDao.findByLotteryIssue(thirdPrizeList.get(i));
                map.put("Prize","三等奖3000元");
                map.put("LotteryIssue", thirdPrizeList.get(i));//期号
                map.put("Number",bo.getRed1() + "." + bo.getRed2() + "." + bo.getRed3() + "." +
                        bo.getRed4() + "." + bo.getRed5() + "." + bo.getRed6() + "." + bo.getBlue());
                listItem.add(map);
            }
        }
        //四等奖
        if (fourthPrizeList != null && fourthPrizeList.size() > 0) {
            for(int i = 0 ;i < fourthPrizeList.size();i++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                SsqBO bo = ssqDao.findByLotteryIssue(fourthPrizeList.get(i));
                map.put("Prize","四等奖200元");
                map.put("LotteryIssue", fourthPrizeList.get(i));//期号
                map.put("Number",bo.getRed1() + "." + bo.getRed2() + "." + bo.getRed3() + "." +
                        bo.getRed4() + "." + bo.getRed5() + "." + bo.getRed6() + "." + bo.getBlue());
                listItem.add(map);
            }
        }

        //生成适配器的Item和动态数组对应的元素
        adapter = new SimpleAdapter(this, listItem, //数据源
                R.layout.activity_ssq_search_item, //listItem的XML实现
                new String[] {"Prize","LotteryIssue","Number"},//动态数组与item对应的子项
                new int[] {R.id.tvSsqSearch01,R.id.tvSsqSearch02,R.id.tvSsqSearch03}  );//Item的XML文件里面对应的ID
        //添加并显示
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ssq_search, menu);
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
