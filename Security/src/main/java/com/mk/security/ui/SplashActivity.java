package com.mk.security.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.domain.UpdateInfo;
import com.mk.security.engine.DownloadTask;
import com.mk.security.engine.UpdateInfoService;

import java.io.File;


public class SplashActivity extends ActionBarActivity {

    private TextView tv_version;
    private LinearLayout ll;
    private ProgressDialog progressDialog;

    private UpdateInfo info;
    private String version;

    private static final String TAG = "Security";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            if(isNeedUpdate(version)){
//                showUpdateDialog();
//            }
            super.handleMessage(msg);
            Log.i("SplashActivity", msg.what + "");
            switch (msg.what) {
                case 0:
                    loadMainUI();
                    break;
                case 1:
                    showUpdateDialog();
                    break;
//                default:
//                    loadMainUI();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置不要显示标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        initUI();

        //启动线程
        Runnable r = new UpdateHandler();
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

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("升级提醒");
        builder.setMessage(info.getDescription());
        builder.setCancelable(false);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File dir = new File(Environment.getExternalStorageDirectory(), "/security/update");
                    if (!dir.exists()) {
                        Boolean suc = dir.mkdirs();
                        Log.i("Security",suc.toString());

                    }
                    String apkPath = Environment.getExternalStorageDirectory() + "/security/update/new.apk";
                    UpdateTask task = new UpdateTask(info.getUrl(), apkPath);
                    progressDialog.show();
                    new Thread(task).start();
                } else {
                    Toast.makeText(SplashActivity.this, "SD卡不可用，请插入SD卡", Toast.LENGTH_SHORT).show();
                    loadMainUI();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                loadMainUI();

            }
        });
        builder.create().show();

    }

    /**
     * 是否需要更新
     * @param version 版本号
     * @return
     */
    private boolean isNeedUpdate(String version) {
        UpdateInfoService updateInfoService = new UpdateInfoService(this);

        try {
            info = updateInfoService.getUpdateInfo(R.string.serverUrl);
            String v = info.getVersion();
            if (v.equals(version)) {
                System.out.println("不用更新");
                Toast.makeText(this, "不用更新", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                System.out.println("要更新");
                Toast.makeText(this, "要更新", Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "获取更新信息异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

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
     * 安装apk
     * @param file 要安装的apk的目录
     */
    private void install(File file)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        finish();
        startActivity(intent);
    }

    /**
     * 下载的线程
     */
    class UpdateTask implements Runnable{
        private String path;
        private String filePath;

        public UpdateTask(String path, String filePath) {
            this.path = path;
            this.filePath = filePath;
        }

        @Override
        public void run() {
            try {
                File file = DownloadTask.getFile(path,filePath, progressDialog);
                progressDialog.dismiss();
                install(file);

            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(SplashActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                loadMainUI();
            }
        }
    }


    /**
     * 查询更新的线程
     * 0.4以后不能在主线程处理网络相关
     * @author quan
     *
     */
    private class UpdateHandler implements Runnable {
        Message msg = new Message();
        @Override
        public void run() {
            Looper.prepare();
            if(isNeedUpdate(getVersion())) {
                msg.what = 1;
            }
            handler.sendMessage(msg);
            Looper.loop();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
