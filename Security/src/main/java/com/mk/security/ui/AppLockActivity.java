package com.mk.security.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mk.security.R;
import com.mk.security.dao.AppLockDao;
import com.mk.security.domain.AppInfo;
import com.mk.security.engine.AppInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class AppLockActivity extends ActionBarActivity {

    private ListView lv_app_lock;
    private LinearLayout ll_load;
    private AppLockAdapter adapter;
    private List<AppInfo> list;
    private AppInfoProvider provider;
    private AppLockDao dao;
    private List<String> lockApps;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ll_load.setVisibility(View.GONE);
            adapter = new AppLockAdapter();
            lv_app_lock.setAdapter(adapter);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.app_lock);

        ll_load = (LinearLayout) findViewById(R.id.ll_app_lock_progress);
        dao = new AppLockDao(this);
        lockApps = new ArrayList<String>();
        lv_app_lock = (ListView) findViewById(R.id.lv_app_lock);
        lv_app_lock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //添加动画效果，动画结束后，就把锁的图片改变
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
                translateAnimation.setDuration(300);
                view.startAnimation(translateAnimation);

                AppInfo info = list.get(position);
                String packageName = info.getPackageName();
                ImageView iv_lock = (ImageView) view.findViewById(R.id.iv_app_lock);
                if (dao.find(packageName)) {
                    dao.delete(packageName);
                    lockApps.remove(packageName);
                    iv_lock.setImageResource(R.drawable.unlock);
                } else {
                    dao.add(info.getPackageName());
                    lockApps.add(packageName);
                    iv_lock.setImageResource(R.drawable.lock);
                }
            }
        });


        provider = new AppInfoProvider(this);

        initAppInfos();
    }

    private void initAppInfos() {
        new Thread() {
            @Override
            public void run() {
                list = provider.getAllApps();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_lock, menu);
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


    //===========================================================================

    private class AppLockAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AppInfo info = list.get(position);
            if (convertView == null) {
                View view = View.inflate(AppLockActivity.this, R.layout.app_lock_item, null);
                AppManagerViews views = new AppManagerViews();
                views.iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_lock_icon);
                views.tv_app_name = (TextView) view.findViewById(R.id.tv_app_lock_name);
                views.iv_app_lock = (ImageView) view.findViewById(R.id.iv_app_lock);
                views.iv_app_icon.setImageDrawable(info.getIcon());
                views.tv_app_name.setText(info.getAppName());

                if (lockApps.contains(info.getPackageName())) {
                    views.iv_app_lock.setImageResource(R.drawable.lock);
                } else {
                    views.iv_app_lock.setImageResource(R.drawable.unlock);
                }
                view.setTag(views);
                return view;
            } else {
                AppManagerViews views = (AppManagerViews) convertView.getTag();
                views.iv_app_icon.setImageDrawable(info.getIcon());
                views.tv_app_name.setText(info.getAppName());
                if (lockApps.contains(info.getPackageName())) {
                    views.iv_app_lock.setImageResource(R.drawable.lock);
                } else {
                    views.iv_app_lock.setImageResource(R.drawable.unlock);
                }
                return convertView;
            }
        }
    }


    //用来优化listview的类
    private class AppManagerViews {
        ImageView iv_app_icon;
        TextView tv_app_name;
        ImageView iv_app_lock;
    }

}
