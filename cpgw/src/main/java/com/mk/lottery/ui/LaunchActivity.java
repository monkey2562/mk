package com.mk.lottery.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.lottery.R;
import com.mk.lottery.util.UpdateSsqDataService;

public class LaunchActivity extends ActionBarActivity {
    private TextView tv_version;
    private LinearLayout ll;
    private ProgressDialog progressDialog;
    private String version;
    private static final String TAG = "LaunchActivity";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            if(isNeedUpdate(version)){
//                showUpdateDialog();
//            }
            super.handleMessage(msg);
            Log.v("LaunchActivity", msg.what + "");
            switch (msg.what) {
                case 0:
                    Toast.makeText(LaunchActivity.this,"已经是最新的数据，不需要更新",Toast.LENGTH_SHORT).show();
                    loadMainUI();
                    break;
                case 1:
//                    Toast.makeText(LaunchActivity.this, "数据已更新！", Toast.LENGTH_SHORT).show();
//                    loadMainUI();
//                    showUpdateDialog();
                    break;
//                default:
//                    loadMainUI();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initUI();

        //启动线程
        Runnable r = new RequestHandler();
        Thread thread = new Thread(r);
        thread.start();


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("正在下载...");

        new Thread(){
            public  void run(){
                try {
                    sleep(3000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        tv_version = (TextView) findViewById(R.id.tv_splash_version);
        version = getVersion();
        tv_version.setText("版本号 "  + version);

        ll = (LinearLayout) findViewById(R.id.ll_splash_main);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(2000);
        ll.startAnimation(alphaAnimation);
    }
    /**
     * 获取版本号
     * @return
     */
    private String getVersion(){
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    private void loadMainUI()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    /**
     * 查询更新的线程
     * 0.4以后不能在主线程处理网络相关
     * @author quan
     *
     */
    private class RequestHandler implements Runnable {
        Message msg = new Message();
        @Override
        public void run() {
            Looper.prepare();
            UpdateSsqDataService service = new UpdateSsqDataService(LaunchActivity.this);
            service.updateData();
            msg.what = 1;
            handler.sendMessage(msg);
            Looper.loop();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.launch, menu);
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
