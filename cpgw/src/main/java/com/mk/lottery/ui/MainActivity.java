package com.mk.lottery.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.mk.lottery.R;
import com.mk.lottery.adapter.MainUIAdapter;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ImageButton imageButton;
    private GridView gridView;
    private MainUIAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置没有标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gv_main);
        adapter = new MainUIAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);



    }

    //不让用户按后退键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //屏蔽后退键
//        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
//            return true;//阻止事件继续向下分发
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch(position)
        {
            case 0 : //双色球
                Intent intent = new Intent(MainActivity.this, UnionLottoActivity.class);
                startActivity(intent);
                break;

            case 1 : //大乐透
                Intent dltIntent = new Intent(MainActivity.this, SuperLottoActivity.class);
                startActivity(dltIntent);
                break;
            default :
                break;
        }
    }
}
