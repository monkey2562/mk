package com.mk.security.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.engine.NumberAddressService;

public class AddressService extends Service {

    private TelephonyManager telephonyManager;
    private MyPhoneListener listener;
    private WindowManager windowManager;
    private View view;

    private SharedPreferences sp;
    public AddressService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        listener = new MyPhoneListener();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止监听
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_NONE);
    }

    //显示归属地的窗体
    private void showLocation(String address) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE //无法获取焦点
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE //无法点击
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;//保持屏幕亮
        params.format = PixelFormat.TRANSLUCENT;//设置成半透明的
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");

        //主要是确定坐标系是从左上角开始的，不然呆会设置位置的时候有些麻烦
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = sp.getInt("lastX", 0);
        params.y = sp.getInt("lastY", 0);

        view = View.inflate(getApplicationContext(), R.layout.show_location, null);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_location);
        int type = sp.getInt("background", 0);

        switch (type) {
            case 0 :
                ll.setBackgroundResource(R.drawable.call_locate_white);
                break;

            case 1 :
                ll.setBackgroundResource(R.drawable.call_locate_orange);
                break;

            case 2 :
                ll.setBackgroundResource(R.drawable.call_locate_green);
                break;

            case 3 :
                ll.setBackgroundResource(R.drawable.call_locate_blue);
                break;

            case 4 :
                ll.setBackgroundResource(R.drawable.call_locate_gray);
                break;

            default :
                break;
        }

        TextView tv = (TextView) view.findViewById(R.id.tv_show_location);
        tv.setText("归属地： " + address);
        windowManager.addView(view, params);

    }



    //========================================================================

    private class MyPhoneListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://空闲状态
                    if (view != null) {
                        windowManager.removeView(view);//移除显示归属地的那个view
                        view = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK : //接通电话
                    if (view != null) {
                        windowManager.removeView(view);//移除显示归属地的那个view
                        view = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //铃响状态
                    String address = NumberAddressService.getAddress(incomingNumber);
                    showLocation(address);
                    break;
                default:
                    break;

            }
        }
    }
}
