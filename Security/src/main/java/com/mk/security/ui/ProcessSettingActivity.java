package com.mk.security.ui;

import android.content.Context;
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

public class ProcessSettingActivity extends ActionBarActivity {

    private TextView tv_process_setting_tips;
    private CheckBox cb_process_setting_state;
    private TextView tv_process_clean_tips;
    private CheckBox cb_process_clean_state;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.process_setting);

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean showSystemProcess = sp.getBoolean("showSystemProcess", false);
        tv_process_setting_tips = (TextView) findViewById(R.id.tv_process_setting_tips);
        cb_process_setting_state = (CheckBox) findViewById(R.id.cb_process_setting_state);
        if (showSystemProcess) {
            tv_process_setting_tips.setText("显示系统进程");
            cb_process_setting_state.setChecked(true);
        } else {
            tv_process_setting_tips.setText("不显示系统进程");
            cb_process_setting_state.setChecked(false);
        }

        cb_process_setting_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    tv_process_setting_tips.setText("显示系统进程");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("showSystemProcess", true);
                    editor.commit();
                    // 与前面的ProcessManagerActivity里面的那个resultCode相对应
                    setResult(200);
                } else {
                    tv_process_setting_tips.setText("不显示系统进程");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("showSystemProcess", false);
                    editor.commit();
                    // 与前面的ProcessManagerActivity里面的那个resultCode相对应
                    setResult(200);
                }
            }
        });


        tv_process_clean_tips = (TextView) findViewById(R.id.tv_process_clean_tips);
        cb_process_clean_state = (CheckBox) findViewById(R.id.cb_process_clean_state);
        boolean killProcess = sp.getBoolean("killProcess", false);
        if (killProcess) {
            tv_process_clean_tips.setText("锁屏清理内存");
            cb_process_clean_state.setChecked(true);
        } else {
            tv_process_clean_tips.setText("锁屏不清理内存");
            cb_process_clean_state.setChecked(false);
        }
        cb_process_clean_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_process_clean_tips.setText("锁屏清理内存");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("killProcess", true);
                    editor.commit();
                } else {
                    tv_process_clean_tips.setText("锁屏不清理内存");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("killProcess", false);
                    editor.commit();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.process_setting, menu);
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
