package com.mk.lottery.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mk.lottery.R;
import com.mk.lottery.dao.DBOpenHelper;
import com.mk.lottery.model.LottoVO;
import com.mk.lottery.util.MkContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionLottoResultActivity extends ActionBarActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private DBOpenHelper db;
    private SQLiteDatabase dbRead, dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_union_lotto_result);
        db = new DBOpenHelper(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        Intent intent = getIntent();
        LottoVO lottoVO = (LottoVO) intent.getSerializableExtra("lottoVO");
        Button btn01 = (Button) findViewById(R.id.btn01);
        Button btn02 = (Button) findViewById(R.id.btn02);
        Button btn03 = (Button) findViewById(R.id.btn03);
        Button btn04 = (Button) findViewById(R.id.btn04);
        Button btn05 = (Button) findViewById(R.id.btn05);
        Button btn06 = (Button) findViewById(R.id.btn06);
        Button btn07 = (Button) findViewById(R.id.btn07);
        String first = lottoVO.getFirst();
        String second = lottoVO.getSecond();
        String third = lottoVO.getThird();
        String fourth = lottoVO.getFourth();
        String fifth = lottoVO.getFifth();
        String sixth = lottoVO.getSixth();
        String seventh = lottoVO.getSeventh();
        btn01.setText(first);
        btn02.setText(second);
        btn03.setText(third);
        btn04.setText(fourth);
        btn05.setText(fifth);
        btn06.setText(sixth);
        btn07.setText(seventh);

        List<Integer> firstPrizeList = lottoVO.getFirstPrizeList();
        List<Integer> secondPrizeList = lottoVO.getSecondPrizeList();
        List<Integer> thirdPrizeList = lottoVO.getThirdPrizeList();
        List<Integer> fourthPrizeList = lottoVO.getFourthPrizeList();

        listView = (ListView) findViewById(R.id.lvListView01);
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        Cursor cursor;
        int qiHao;
        //一等奖
        if (firstPrizeList != null && firstPrizeList.size() > 0) {
            for (int i = 0; i < firstPrizeList.size(); i++) {
                qiHao = firstPrizeList.get(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
                cursor = dbRead.query(MkContent.TABLE_NAME_UNION_LOTTO, null, "lottery_issue = ?",
                        new String[]{qiHao + ""}, null, null, null);
                if (cursor.moveToNext()) {
                    map.put("Prize", "一等奖" + cursor.getInt(cursor.getColumnIndex("first_amount")) + "元");
                    map.put("LotteryIssue", qiHao);//期号
                    map.put("Number", cursor.getString(3) + "." +
                            cursor.getString(4) + "." +
                            cursor.getString(5) + "." +
                            cursor.getString(6) + "." +
                            cursor.getString(7) + "." +
                            cursor.getString(8) + "." +
                            cursor.getString(9));
                }


                listItem.add(map);
            }
        }
        //二等奖
        if (secondPrizeList != null && secondPrizeList.size() > 0) {
            for (int i = 0; i < secondPrizeList.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                qiHao = secondPrizeList.get(i);
                cursor = dbRead.query(MkContent.TABLE_NAME_UNION_LOTTO, null, "lottery_issue = ?",
                        new String[]{qiHao + ""}, null, null, null);
                if (cursor.moveToNext()) {
                    map.put("Prize", "二等奖" + cursor.getInt(cursor.getColumnIndex("second_amount")) + "元");
                    map.put("LotteryIssue", qiHao);//期号
                    map.put("Number", cursor.getString(3) + "." +
                            cursor.getString(4) + "." +
                            cursor.getString(5) + "." +
                            cursor.getString(6) + "." +
                            cursor.getString(7) + "." +
                            cursor.getString(8) + "." +
                            cursor.getString(9));

                }

                listItem.add(map);
            }
        }
        //三等奖
        if (thirdPrizeList != null && thirdPrizeList.size() > 0) {
            for (int i = 0; i < thirdPrizeList.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                qiHao = thirdPrizeList.get(i);
                cursor = dbRead.query(MkContent.TABLE_NAME_UNION_LOTTO, null, "lottery_issue = ?",
                        new String[]{qiHao + ""}, null, null, null);
                if (cursor.moveToNext()) {
                    map.put("Prize", "三等奖" + cursor.getInt(23));//third_amount
                    map.put("LotteryIssue", qiHao);//期号
                    map.put("Number", cursor.getString(3) + "." +
                            cursor.getString(4) + "." +
                            cursor.getString(5) + "." +
                            cursor.getString(6) + "." +
                            cursor.getString(7) + "." +
                            cursor.getString(8) + "." +
                            cursor.getString(9));

                }

                listItem.add(map);
            }
        }
        //四等奖
        if (fourthPrizeList != null && fourthPrizeList.size() > 0) {
            for (int i = 0; i < fourthPrizeList.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                qiHao = fourthPrizeList.get(i);
                cursor = dbRead.query(MkContent.TABLE_NAME_UNION_LOTTO, null, "lottery_issue = ?",
                        new String[]{qiHao + ""}, null, null, null);
                if (cursor.moveToNext()) {
                    map.put("Prize", "四等奖" + cursor.getInt(cursor.getColumnIndex("fourth_amount")) + "元");
                    map.put("LotteryIssue", qiHao);//期号
                    map.put("Number", cursor.getString(3) + "." +
                            cursor.getString(4) + "." +
                            cursor.getString(5) + "." +
                            cursor.getString(6) + "." +
                            cursor.getString(7) + "." +
                            cursor.getString(8) + "." +
                            cursor.getString(9));
                }
                listItem.add(map);
            }
        }


        //生成适配器的Item和动态数组对应的元素
        adapter = new SimpleAdapter(this, listItem, //数据源
                R.layout.activity_search_item, //listItem的XML实现
                new String[]{"Prize", "LotteryIssue", "Number"},//动态数组与item对应的子项
                new int[]{R.id.tvSsqSearch01, R.id.tvSsqSearch02, R.id.tvSsqSearch03});//Item的XML文件里面对应的ID
        //添加并显示
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbRead != null) {
            dbRead.close();
        }
        if (dbWrite != null) {
            dbWrite.close();
        }
    }
}
