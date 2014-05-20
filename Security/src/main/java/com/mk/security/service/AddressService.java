package com.mk.security.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.TextView;

import com.mk.security.engine.NumberAddressService;

public class AddressService extends Service {

    private TelephonyManager telephonyManager;
    private MyPhoneListener listener;
    private WindowManager windowManager;
    private TextView tv;
    public AddressService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
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

        tv = new TextView(AddressService.this);
        tv.setText("归属地： " + address);
        windowManager.addView(tv,params);
    }



    //========================================================================

    private class MyPhoneListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://空闲状态
                    if (tv != null) {
                        windowManager.removeView(tv);//移除显示归属地的那个view
                        tv = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK : //接通电话
                    if (tv != null) {
                        windowManager.removeView(tv);//移除显示归属地的那个view
                        tv = null;
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
