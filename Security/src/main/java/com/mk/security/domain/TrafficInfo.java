package com.mk.security.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2014/5/27.
 */
public class TrafficInfo {
    private String name;
    private Drawable icon;
    private int uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
