package com.mk.lottery.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import com.mk.lottery.R;
import com.mk.lottery.dao.SsqDao;
import com.mk.lottery.model.SsqVO;

public class SsqSearchActivity extends ActionBarActivity {

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
        btnRed1.setText(ssqVO.getFirst());
        btnRed2.setText(ssqVO.getSecond());
        btnRed3.setText(ssqVO.getThird());
        btnRed4.setText(ssqVO.getFourth());
        btnRed5.setText(ssqVO.getFifth());
        btnRed6.setText(ssqVO.getSixth());
        btnRed7.setText(ssqVO.getBlue());

        
        SsqDao ssqDao = new SsqDao(this);
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
