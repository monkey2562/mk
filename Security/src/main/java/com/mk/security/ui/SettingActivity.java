package com.mk.security.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mk.security.R;
import com.mk.security.service.WatchDogService;

public class SettingActivity extends ActionBarActivity {

    private TextView tv_lock_tips;
    private CheckBox cb_lock_state;
    private Intent appLockIntent;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);

        appLockIntent = new Intent(this, WatchDogService.class);

        tv_lock_tips = (TextView) findViewById(R.id.tv_lock_tips);
        cb_lock_state = (CheckBox) findViewById(R.id.cb_lock_state);

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isAppLockStart = sp.getBoolean("appLock", false);
        if (isAppLockStart) {
            tv_lock_tips.setText("服务已经开启");
            cb_lock_state.setChecked(true);
        } else {
            tv_lock_tips.setText("服务没有开启");
            cb_lock_state.setChecked(false);
        }
        cb_lock_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    startService(appLockIntent);
                    tv_lock_tips.setText("服务已经开启");
                } else {
                    stopService(appLockIntent);
                    tv_lock_tips.setText("服务没有开启");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
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
