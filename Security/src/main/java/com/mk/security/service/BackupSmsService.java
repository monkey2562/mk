package com.mk.security.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.util.Xml;
import android.widget.Toast;

import com.mk.security.domain.SmsInfo;
import com.mk.security.engine.SmsService;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class BackupSmsService extends Service {
    SmsService smsService;

    public BackupSmsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        smsService = new SmsService(this);

        new Thread() {
            @Override
            public void run() {
                List<SmsInfo> infos = smsService.getSmsInfo();
                File dir = new File(Environment.getExternalStorageDirectory(), "/security/backup");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(Environment.getExternalStorageDirectory() + "/security/backup/smsbackup.xml");

                //创建一个xml的序列化器
                XmlSerializer xmlSerializer = Xml.newSerializer();
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    //设置写xml的编码
                    xmlSerializer.setOutput(fos, "utf-8");
                    xmlSerializer.startDocument("utf-8", true);
                    xmlSerializer.startTag(null, "smss");
                    for (SmsInfo info : infos) {
                        xmlSerializer.startTag(null, "sms");

                        xmlSerializer.startTag(null, "id");
                        xmlSerializer.text(info.getId());
                        xmlSerializer.endTag(null, "id");

                        xmlSerializer.startTag(null, "address");
                        xmlSerializer.text(info.getAddress());
                        xmlSerializer.endTag(null, "address");

                        xmlSerializer.startTag(null, "date");
                        xmlSerializer.text(info.getDate());
                        xmlSerializer.endTag(null, "date");

                        xmlSerializer.startTag(null, "type");
                        xmlSerializer.text(info.getType() + "");
                        xmlSerializer.endTag(null, "type");

                        xmlSerializer.startTag(null, "body");
                        xmlSerializer.text(info.getBody());
                        xmlSerializer.endTag(null, "body");

                        xmlSerializer.endTag(null, "sms");
                    }
                    xmlSerializer.endTag(null, "smss");
                    xmlSerializer.endDocument();

                    fos.flush();
                    fos.close();
                    //在子线程里面是不能弹出一个Toast的，因为子线程里面没有Looper，
                    //但我们通过下面几个步骤就可以在子线程里面弹出Toast了
                    Looper.prepare();//创建一个Looper
                    Toast.makeText(getApplicationContext(), "备份成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();//轮循一次Looper


                } catch (Exception e) {
                    Looper.prepare();//创建一个Looper
                    Toast.makeText(getApplicationContext(), "备份失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();//轮循一次Looper
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
