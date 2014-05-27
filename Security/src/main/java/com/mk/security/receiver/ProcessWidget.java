package com.mk.security.receiver;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.mk.security.R;
import com.mk.security.service.UpdateWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class ProcessWidget extends AppWidgetProvider {

    private Intent intent;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        //停止服务
        intent = new Intent(context, UpdateWidgetService.class);
        context.stopService(intent);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        //开启服务
        intent = new Intent(context, UpdateWidgetService.class);
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context) {
        //停止服务
        intent = new Intent(context, UpdateWidgetService.class);
        context.stopService(intent);
    }

}


