package com.mk.security.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.mk.security.utils.ProcessUtil;

public class LockScreenReceiver extends BroadcastReceiver {
    private static final String TAG = "LockScreenReceiver";

    public LockScreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.i(TAG, "已经锁屏了");
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        boolean killProcess = sp.getBoolean("killProcess", false);
        if (killProcess)
        {
            Log.i(TAG, "开始清理内存");
            ProcessUtil.killAllProcess(context);
        }
    }
}
