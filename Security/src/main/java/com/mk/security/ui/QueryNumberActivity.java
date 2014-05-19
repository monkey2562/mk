package com.mk.security.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mk.security.R;
import com.mk.security.engine.NumberAddressService;

public class QueryNumberActivity extends ActionBarActivity {

    private TextView tv_result;
    private EditText et_query_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_number);

        tv_result = (TextView) findViewById(R.id.tv_query_result);
        et_query_number = (EditText) findViewById(R.id.et_query_number);
    }

    public void query(View v){
        String number = et_query_number.getText().toString().trim();
        //如果查询内容为空，那么就抖动输入框
        if(TextUtils.isEmpty(number)){
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            et_query_number.startAnimation(shake);

        }else {
            String address = NumberAddressService.getAddress(number);
            tv_result.setText("归属地信息：" + address);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.query_number, menu);
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
