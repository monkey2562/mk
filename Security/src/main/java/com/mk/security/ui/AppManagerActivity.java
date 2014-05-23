package com.mk.security.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.domain.AppInfo;
import com.mk.security.engine.AppInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class AppManagerActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int GET_ALL_APP_FINISH = 1;
    private static final int GET_USER_APP_FINISH = 2;

    private ListView lv_app_manager;
    private LinearLayout ll_app_manager_progress;
    private AppInfoProvider provider;
    private AppManagerAdapter adapter;
    private PopupWindow popupWindow;
    private TextView tv_app_title;
    //判断是不是还在加载中，如果还在加载中的话，就不能进行应用的切换
    private boolean flag = false;
    private List<AppInfo> list;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //进度条设置为不可见
            ll_app_manager_progress.setVisibility(View.GONE);

            switch (msg.what) {
                case GET_ALL_APP_FINISH:
                    adapter = new AppManagerAdapter(list);
                    lv_app_manager.setAdapter(adapter);
                    flag = true;
                    break;
                case GET_USER_APP_FINISH:
                    adapter = new AppManagerAdapter(getUserApp());
                    lv_app_manager.setAdapter(adapter);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_manager);

        lv_app_manager = (ListView) findViewById(R.id.lv_app_manager);
        ll_app_manager_progress = (LinearLayout) findViewById(R.id.ll_app_manager_progress);

        tv_app_title = (TextView) findViewById(R.id.tv_app_title);
        tv_app_title.setOnClickListener(this);

        initUI(false);


        lv_app_manager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                dismissPopupWindow();
                //用来存放当前的item的坐标值，第一个是x的坐标，第二个是y的坐标
                int[] location = new int[2];
                //把当前的item的坐标值放到int数组里面
                view.getLocationInWindow(location);

                View popupView = View.inflate(AppManagerActivity.this, R.layout.popup_item, null);
                LinearLayout ll_app_uninstall = (LinearLayout) popupView.findViewById(R.id.ll_app_uninstall);
                LinearLayout ll_app_run = (LinearLayout) popupView.findViewById(R.id.ll_app_start);
                LinearLayout ll_app_share = (LinearLayout) popupView.findViewById(R.id.ll_app_share);
                ll_app_uninstall.setOnClickListener(AppManagerActivity.this);
                ll_app_run.setOnClickListener(AppManagerActivity.this);
                ll_app_share.setOnClickListener(AppManagerActivity.this);

                //拿到当时点击的条目，并设置到view里面
                AppInfo info = (AppInfo) lv_app_manager.getItemAtPosition(position);
                ll_app_uninstall.setTag(info);
                ll_app_run.setTag(info);
                ll_app_share.setTag(info);

                //添加动画
                LinearLayout ll_app_popup = (LinearLayout) popupView.findViewById(R.id.ll_app_popup);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
                scaleAnimation.setDuration(300);

                //new 一个PopupWindow出来
                popupWindow = new PopupWindow(popupView, 330, 100);
                //一定要给PopupWindow设置一个背景图片，不然的话，会有很多未知的问题的
                //如没办法给它加上动画，还有显示会有问题等，
                //如果我们没有要设置的图片，那么我们就给它加上了一个透明的背景图片
                Drawable drawable = new ColorDrawable(Color.TRANSPARENT);
                popupWindow.setBackgroundDrawable(drawable);

                int x = location[0] + 60;
                int y = location[1];
                //把PopupWindow显示出来
                popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, x, y);

                //开启动画
                ll_app_popup.startAnimation(scaleAnimation);
            }
        });

        lv_app_manager.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                dismissPopupWindow();
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                dismissPopupWindow();
            }
        });
    }

    //判断PopupWindow是不是存在，存在就把它dismiss掉
    private void dismissPopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_manager, menu);
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
        //加载过程中的时候，不能点击
        if (!flag) {
            return;
        }
        AppInfo item = (AppInfo) view.getTag();
        switch (view.getId()) {
            //当我们点击那个标题栏之后，我们就只把用户的应用列出来
            case R.id.tv_app_title:
                if ("所有应用".equals(tv_app_title.getText().toString().trim())) {
                    tv_app_title.setText("用户应用");
                    adapter.setAppInfos(getUserApp());
                    //通知ListView数据发生了变化
                    adapter.notifyDataSetChanged();
                } else {
                    tv_app_title.setText("所有应用");
                    adapter.setAppInfos(list);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.ll_app_uninstall:
                if (item.isSystemApp()) {
                    Toast.makeText(AppManagerActivity.this, "不能卸载系统的应用程序", Toast.LENGTH_SHORT).show();
                } else {
                    String strUri = "package:" + item.getPackageName();
                    Uri uri = Uri.parse(strUri);
                    Intent deleteIntent = new Intent();
                    deleteIntent.setAction(Intent.ACTION_DELETE);
                    deleteIntent.setData(uri);
                    startActivityForResult(deleteIntent, 0);
                }
                break;
            case R.id.ll_app_start:
                try {
                    //拿到这个包对应的PackageInfo对象，这里我们指定了两个flag，
                    //一个就是之前讲过的，所有的安装过的应用程序都找出来，包括卸载了但没清除数据的
                    //一个就是指定它去扫描这个应用的AndroidMainfest文件时候的activity节点，
                    //这样我们才能拿到具有启动意义的ActivityInfo，如果不指定，是无法扫描出来的
                    PackageInfo packageInfo = null;
                    packageInfo = getPackageManager().getPackageInfo(item.getPackageName(), PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES);
                    //扫描出来的所以activity节点的信息
                    ActivityInfo[] activityInfos = packageInfo.activities;
                    //有些应用是无法启动的，所以我们就要判断一下
                    if (activityInfos != null && activityInfos.length > 0) {
                        //在扫描出来的应用里面，第一个是具有启动意义的
                        ActivityInfo startActivity = activityInfos[0];
                        //设置好Intent，启动activity
                        Intent intent = new Intent();
                        intent.setClassName(item.getPackageName(), startActivity.name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AppManagerActivity.this, "这个应用程序无法启动", Toast.LENGTH_SHORT).show();
                    }

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll_app_share:
                Intent shareIntent = new Intent();
                //设置Intent的action
                shareIntent.setAction(Intent.ACTION_SEND);
                //设定分享的类型是纯文本的
                shareIntent.setType("text/plain");
                //设置分享主题
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                //设置分享的文本
                shareIntent.putExtra(Intent.EXTRA_TEXT, "有一个很好的应用程序哦！" + item.getAppName());
                //shareIntent = Intent.createChooser(shareIntent, "分享");
                startActivity(shareIntent);
                break;

            default:
                break;
        }
        dismissPopupWindow();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if("用户应用".equals(tv_app_title.getText().toString().trim())) {
            initUI(true);
            adapter.setAppInfos(getUserApp());
            //通知ListView数据发生了变化
            adapter.notifyDataSetChanged();
        } else {
            initUI(false);
        }
    }

    private void initUI(final boolean isUserApp) {
        flag = false;
        ll_app_manager_progress.setVisibility(View.VISIBLE);

        //因为搜索手机里面的应用程序有可能是非常耗时的，所以我们开启一个新的线程去进行搜索
        //当搜索完成之后，就把一个成功的消息发送给Handler，然后handler把搜索到的数据设置进入listview里面

        new Thread() {
            @Override
            public void run() {
                provider = new AppInfoProvider(AppManagerActivity.this);
                list = provider.getAllApps();
                Message msg = new Message();
                if (isUserApp) {
                    msg.what = GET_ALL_APP_FINISH;
                } else {
                    msg.what = GET_ALL_APP_FINISH;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }


    private List<AppInfo> getUserApp() {
        List<AppInfo> userApps = new ArrayList<AppInfo>();
        for (AppInfo info : list) {
            if (!info.isSystemApp()) {
                userApps.add(info);
            }
        }
        return userApps;
    }

    //======================================================================

    private class AppManagerAdapter extends BaseAdapter {
        private List<AppInfo> appInfos;

        public AppManagerAdapter(List<AppInfo> appInfos) {
            this.appInfos = appInfos;
        }

        //设置adapter的数据
        public void setAppInfos(List<AppInfo> appInfos) {
            this.appInfos = appInfos;
        }

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
                View view = View.inflate(AppManagerActivity.this, R.layout.app_manager_item, null);
                AppManagerViews views = new AppManagerViews();
                views.iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_manager_icon);
                views.tv_app_name = (TextView) view.findViewById(R.id.tv_app_manager_name);
                views.iv_app_icon.setImageDrawable(info.getIcon());
                views.tv_app_name.setText(info.getAppName());
                view.setTag(views);
                return view;
            } else {
                AppManagerViews views = (AppManagerViews) convertView.getTag();
                views.iv_app_icon.setImageDrawable(info.getIcon());
                views.tv_app_name.setText(info.getAppName());
                return convertView;
            }
        }
    }

    //用来优化listview的类
    private class AppManagerViews {
        ImageView iv_app_icon;
        TextView tv_app_name;
    }
}
