package com.mk.security.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mk.security.utils.ProcessUtil;

public class ProcessClearReceiver extends BroadcastReceiver {
    public ProcessClearReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //清理内存
        ProcessUtil.killAllProcess(context);
    }
}
