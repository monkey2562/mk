package com.mk.security.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.engine.DownloadTask;
import com.mk.security.engine.SmsService;
import com.mk.security.service.AddressService;
import com.mk.security.service.BackupSmsService;

import java.io.File;

public class AToolActivity extends ActionBarActivity implements View.OnClickListener {
    private static final int ERROR = 0;
    private static final int SUCCESS = 1;
    private ProgressDialog pd;
    private TextView tv_atool_query;
    private TextView tv_atool_number_service_state;
    private CheckBox cb_atool_state;
    private TextView tv_atool_select_bg;
    private TextView tv_atool_change_location;
    private TextView tv_atool_sms_backup;
    private TextView tv_atool_sms_restore;
    private TextView tv_atool_app_lock;
    private Intent serviceIntent;
    private SharedPreferences sp;

    private TextView tv_atool_number_security;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERROR:
                    Toast.makeText(AToolActivity.this, "下载数据库失败，请检查网络！", Toast.LENGTH_SHORT).show();
                    break;

                case SUCCESS:
                    Toast.makeText(AToolActivity.this, "数据库下载成功！", Toast.LENGTH_SHORT).show();
                    break;

                default :
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atool);

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        tv_atool_query = (TextView) findViewById(R.id.tv_atool_query);
        tv_atool_query.setOnClickListener(this);

        tv_atool_select_bg = (TextView) findViewById(R.id.tv_atool_select_bg);
        tv_atool_select_bg.setOnClickListener(this);

        tv_atool_change_location = (TextView) findViewById(R.id.tv_atool_change_location);
        tv_atool_change_location.setOnClickListener(this);

        tv_atool_number_service_state = (TextView) findViewById(R.id.tv_atool_number_service_state);
        cb_atool_state = (CheckBox) findViewById(R.id.cb_atool_state);
        serviceIntent = new Intent(this, AddressService.class);

        cb_atool_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startService(serviceIntent);
                    tv_atool_number_service_state.setTextColor(Color.BLACK);
                    tv_atool_number_service_state.setText("归属地服务已开启");
                } else {
                    stopService(serviceIntent);
                    tv_atool_number_service_state.setTextColor(Color.RED);
                    tv_atool_number_service_state.setText(R.string.number_service_state);
                }
            }
        });

        //通讯卫士
        tv_atool_number_security = (TextView) findViewById(R.id.tv_atool_number_security);
        tv_atool_number_security.setOnClickListener(this);

        //短信备份
        tv_atool_sms_backup = (TextView) findViewById(R.id.tv_atool_sms_backup);
        tv_atool_sms_backup.setOnClickListener(this);

        //短信还原
        tv_atool_sms_restore = (TextView) findViewById(R.id.tv_atool_sms_restore);
        tv_atool_sms_restore.setOnClickListener(this);

        //程序锁
        tv_atool_app_lock = (TextView) findViewById(R.id.tv_atool_app_lock);
        tv_atool_app_lock.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atool, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_atool_query://归属地查询
                query();
                break;
            case R.id.tv_atool_select_bg://归属地显示风格
                selectStyle();
                break;
            case R.id.tv_atool_change_location ://显示位置的改变
                Intent intent = new Intent(this, DragViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_atool_number_security://通讯卫士
                Intent numberIntent = new Intent(this, NumberSecurityActivity.class);
                startActivity(numberIntent);
                break;
            case R.id.tv_atool_sms_backup :
                Intent backupIntent = new Intent(this, BackupSmsService.class);
                startService(backupIntent);
                break;
            case R.id.tv_atool_sms_restore :
                restore();
                break;
            case R.id.tv_atool_app_lock :
                Intent appLockIntent = new Intent(this, AppLockActivity.class);
                startActivity(appLockIntent);
                break;
            default :
                break;
        }
    }

    private void query(){
        if(isDBExist()){
            Intent intent = new Intent(this,QueryNumberActivity.class);
            startActivity(intent);
        } else {
            //提示用户下载数据库
            pd = new ProgressDialog(this);
            pd.setMessage("正在下载数据库...");
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setCancelable(false);
            pd.show();
            new Thread(){
                @Override
                public void run() {
                    String path = getResources().getString(R.string.serverdb);
                    File dir = new File(Environment.getExternalStorageDirectory(), "/security/db");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String dbPath = Environment.getExternalStorageDirectory()+ "/security/db/data.db";
                    try {
                        //这个类，我们在做更新apk的时候已经写好的啦，现在直接拿过来用就可以啦
                        DownloadTask.getFile(path, dbPath, pd);
                        pd.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        pd.dismiss();
                        Message message = new Message();
                        message.what = ERROR;
                        handler.sendMessage(message);
                    }

                };
            }.start();
        }
    }

    //显示风格的对话框，我准备了5张不同风格的背景图片，根据用户选择的风格不一样，设置不同的背景
    private void selectStyle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("归属地显示风格");
        String[] items = new String[] {"半透明", "活力橙", "苹果绿", "孔雀蓝", "金属灰"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("background", i);
                editor.commit();
            }
        });

        builder.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private boolean isDBExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory() + "/security/db/data.db");
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    private void restore(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("还原短信");
        pd.setMessage("正在还原短信...");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();
        final SmsService smsService = new SmsService(this);
        new Thread(){
            @Override
            public void run() {
                try {
                    smsService.restore(Environment.getExternalStorageDirectory() + "/security/backup/smsbackup.xml", pd);
                    pd.dismiss();
                    Looper.prepare();//创建一个Looper
                    Toast.makeText(getApplicationContext(), "还原成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();//轮循一次Looper
                } catch (Exception e) {
                    e.printStackTrace();
                    Looper.prepare();//创建一个Looper
                    Toast.makeText(getApplicationContext(), "还原失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();//轮循一次Looper
                }
            }
        }.start();
    }
}
