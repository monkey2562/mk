package com.mk.lottery.ui;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.mk.lottery.R;
import com.mk.lottery.dao.SsqDao;
import com.mk.lottery.model.SsqBO;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SsqActivity extends Activity implements View.OnClickListener{
    private Map<Integer,String> clickRedMap = new HashMap<Integer, String>();
    private Map<Integer,String> clickBlueMap = new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ssq);
        // 将字体文件保存在assets/fonts/目录下，www.linuxidc.com创建Typeface对象
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/fzmwt.ttf");
        Button btn_r_01 = (Button) findViewById(R.id.btn_ssq_red_01);
        Button btn_r_02 = (Button) findViewById(R.id.btn_ssq_red_02);
        Button btn_r_03 = (Button) findViewById(R.id.btn_ssq_red_03);
        Button btn_r_04 = (Button) findViewById(R.id.btn_ssq_red_04);
        Button btn_r_05 = (Button) findViewById(R.id.btn_ssq_red_05);
        Button btn_r_06 = (Button) findViewById(R.id.btn_ssq_red_06);
        Button btn_r_07 = (Button) findViewById(R.id.btn_ssq_red_07);
        Button btn_r_08 = (Button) findViewById(R.id.btn_ssq_red_08);
        Button btn_r_09 = (Button) findViewById(R.id.btn_ssq_red_09);
        Button btn_r_10 = (Button) findViewById(R.id.btn_ssq_red_10);
        Button btn_r_11 = (Button) findViewById(R.id.btn_ssq_red_11);
        Button btn_r_12 = (Button) findViewById(R.id.btn_ssq_red_12);
        Button btn_r_13 = (Button) findViewById(R.id.btn_ssq_red_13);
        Button btn_r_14 = (Button) findViewById(R.id.btn_ssq_red_14);
        Button btn_r_15 = (Button) findViewById(R.id.btn_ssq_red_15);
        Button btn_r_16 = (Button) findViewById(R.id.btn_ssq_red_16);
        Button btn_r_17 = (Button) findViewById(R.id.btn_ssq_red_17);
        Button btn_r_18 = (Button) findViewById(R.id.btn_ssq_red_18);
        Button btn_r_19 = (Button) findViewById(R.id.btn_ssq_red_19);
        Button btn_r_20 = (Button) findViewById(R.id.btn_ssq_red_20);
        Button btn_r_21 = (Button) findViewById(R.id.btn_ssq_red_21);
        Button btn_r_22 = (Button) findViewById(R.id.btn_ssq_red_22);
        Button btn_r_23 = (Button) findViewById(R.id.btn_ssq_red_23);
        Button btn_r_24 = (Button) findViewById(R.id.btn_ssq_red_24);
        Button btn_r_25 = (Button) findViewById(R.id.btn_ssq_red_25);
        Button btn_r_26 = (Button) findViewById(R.id.btn_ssq_red_26);
        Button btn_r_27 = (Button) findViewById(R.id.btn_ssq_red_27);
        Button btn_r_28 = (Button) findViewById(R.id.btn_ssq_red_28);
        Button btn_r_29 = (Button) findViewById(R.id.btn_ssq_red_29);
        Button btn_r_30 = (Button) findViewById(R.id.btn_ssq_red_30);
        Button btn_r_31 = (Button) findViewById(R.id.btn_ssq_red_31);
        Button btn_r_32 = (Button) findViewById(R.id.btn_ssq_red_32);
        Button btn_r_33 = (Button) findViewById(R.id.btn_ssq_red_33);
        btn_r_01.setTypeface(typeFace);
        btn_r_02.setTypeface(typeFace);
        btn_r_03.setTypeface(typeFace);
        btn_r_04.setTypeface(typeFace);
        btn_r_05.setTypeface(typeFace);
        btn_r_06.setTypeface(typeFace);
        btn_r_07.setTypeface(typeFace);
        btn_r_08.setTypeface(typeFace);
        btn_r_09.setTypeface(typeFace);
        btn_r_10.setTypeface(typeFace);
        btn_r_11.setTypeface(typeFace);
        btn_r_12.setTypeface(typeFace);
        btn_r_13.setTypeface(typeFace);
        btn_r_14.setTypeface(typeFace);
        btn_r_15.setTypeface(typeFace);
        btn_r_16.setTypeface(typeFace);
        btn_r_17.setTypeface(typeFace);
        btn_r_18.setTypeface(typeFace);
        btn_r_19.setTypeface(typeFace);
        btn_r_20.setTypeface(typeFace);
        btn_r_21.setTypeface(typeFace);
        btn_r_22.setTypeface(typeFace);
        btn_r_23.setTypeface(typeFace);
        btn_r_24.setTypeface(typeFace);
        btn_r_25.setTypeface(typeFace);
        btn_r_26.setTypeface(typeFace);
        btn_r_27.setTypeface(typeFace);
        btn_r_28.setTypeface(typeFace);
        btn_r_29.setTypeface(typeFace);
        btn_r_30.setTypeface(typeFace);
        btn_r_31.setTypeface(typeFace);
        btn_r_32.setTypeface(typeFace);
        btn_r_33.setTypeface(typeFace);

        btn_r_01.setOnClickListener(this);
        btn_r_02.setOnClickListener(this);
        btn_r_03.setOnClickListener(this);
        btn_r_04.setOnClickListener(this);
        btn_r_05.setOnClickListener(this);
        btn_r_06.setOnClickListener(this);
        btn_r_07.setOnClickListener(this);
        btn_r_08.setOnClickListener(this);
        btn_r_09.setOnClickListener(this);
        btn_r_10.setOnClickListener(this);
        btn_r_11.setOnClickListener(this);
        btn_r_12.setOnClickListener(this);
        btn_r_13.setOnClickListener(this);
        btn_r_14.setOnClickListener(this);
        btn_r_15.setOnClickListener(this);
        btn_r_16.setOnClickListener(this);
        btn_r_17.setOnClickListener(this);
        btn_r_18.setOnClickListener(this);
        btn_r_19.setOnClickListener(this);
        btn_r_20.setOnClickListener(this);
        btn_r_21.setOnClickListener(this);
        btn_r_22.setOnClickListener(this);
        btn_r_23.setOnClickListener(this);
        btn_r_24.setOnClickListener(this);
        btn_r_25.setOnClickListener(this);
        btn_r_26.setOnClickListener(this);
        btn_r_27.setOnClickListener(this);
        btn_r_28.setOnClickListener(this);
        btn_r_29.setOnClickListener(this);
        btn_r_30.setOnClickListener(this);
        btn_r_31.setOnClickListener(this);
        btn_r_32.setOnClickListener(this);
        btn_r_33.setOnClickListener(this);

        Button btn_b_01 = (Button) findViewById(R.id.btn_ssq_blue_01);
        Button btn_b_02 = (Button) findViewById(R.id.btn_ssq_blue_02);
        Button btn_b_03 = (Button) findViewById(R.id.btn_ssq_blue_03);
        Button btn_b_04 = (Button) findViewById(R.id.btn_ssq_blue_04);
        Button btn_b_05 = (Button) findViewById(R.id.btn_ssq_blue_05);
        Button btn_b_06 = (Button) findViewById(R.id.btn_ssq_blue_06);
        Button btn_b_07 = (Button) findViewById(R.id.btn_ssq_blue_07);
        Button btn_b_08 = (Button) findViewById(R.id.btn_ssq_blue_08);
        Button btn_b_09 = (Button) findViewById(R.id.btn_ssq_blue_09);
        Button btn_b_10 = (Button) findViewById(R.id.btn_ssq_blue_10);
        Button btn_b_11 = (Button) findViewById(R.id.btn_ssq_blue_11);
        Button btn_b_12 = (Button) findViewById(R.id.btn_ssq_blue_12);
        Button btn_b_13 = (Button) findViewById(R.id.btn_ssq_blue_13);
        Button btn_b_14 = (Button) findViewById(R.id.btn_ssq_blue_14);
        Button btn_b_15 = (Button) findViewById(R.id.btn_ssq_blue_15);
        Button btn_b_16 = (Button) findViewById(R.id.btn_ssq_blue_16);
        btn_b_01.setTypeface(typeFace);
        btn_b_02.setTypeface(typeFace);
        btn_b_03.setTypeface(typeFace);
        btn_b_04.setTypeface(typeFace);
        btn_b_05.setTypeface(typeFace);
        btn_b_06.setTypeface(typeFace);
        btn_b_07.setTypeface(typeFace);
        btn_b_08.setTypeface(typeFace);
        btn_b_09.setTypeface(typeFace);
        btn_b_10.setTypeface(typeFace);
        btn_b_11.setTypeface(typeFace);
        btn_b_12.setTypeface(typeFace);
        btn_b_13.setTypeface(typeFace);
        btn_b_14.setTypeface(typeFace);
        btn_b_15.setTypeface(typeFace);
        btn_b_16.setTypeface(typeFace);
        btn_b_01.setOnClickListener(this);
        btn_b_02.setOnClickListener(this);
        btn_b_03.setOnClickListener(this);
        btn_b_04.setOnClickListener(this);
        btn_b_05.setOnClickListener(this);
        btn_b_06.setOnClickListener(this);
        btn_b_07.setOnClickListener(this);
        btn_b_08.setOnClickListener(this);
        btn_b_09.setOnClickListener(this);
        btn_b_10.setOnClickListener(this);
        btn_b_11.setOnClickListener(this);
        btn_b_12.setOnClickListener(this);
        btn_b_13.setOnClickListener(this);
        btn_b_14.setOnClickListener(this);
        btn_b_15.setOnClickListener(this);
        btn_b_16.setOnClickListener(this);

        Button btnInit = (Button) findViewById(R.id.btn_ssq_init);
        btnInit.setOnClickListener(this);

        Button btnImport = (Button) findViewById(R.id.btn_ssq_import);
        btnImport.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ssq_red_01:
            case R.id.btn_ssq_red_02:
            case R.id.btn_ssq_red_03:
            case R.id.btn_ssq_red_04:
            case R.id.btn_ssq_red_05:
            case R.id.btn_ssq_red_06:
            case R.id.btn_ssq_red_07:
            case R.id.btn_ssq_red_08:
            case R.id.btn_ssq_red_09:
            case R.id.btn_ssq_red_10:
            case R.id.btn_ssq_red_11:
            case R.id.btn_ssq_red_12:
            case R.id.btn_ssq_red_13:
            case R.id.btn_ssq_red_14:
            case R.id.btn_ssq_red_15:
            case R.id.btn_ssq_red_16:
            case R.id.btn_ssq_red_17:
            case R.id.btn_ssq_red_18:
            case R.id.btn_ssq_red_19:
            case R.id.btn_ssq_red_20:
            case R.id.btn_ssq_red_21:
            case R.id.btn_ssq_red_22:
            case R.id.btn_ssq_red_23:
            case R.id.btn_ssq_red_24:
            case R.id.btn_ssq_red_25:
            case R.id.btn_ssq_red_26:
            case R.id.btn_ssq_red_27:
            case R.id.btn_ssq_red_28:
            case R.id.btn_ssq_red_29:
            case R.id.btn_ssq_red_30:
            case R.id.btn_ssq_red_31:
            case R.id.btn_ssq_red_32:
            case R.id.btn_ssq_red_33:
                Button btnRed = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if(clickRedMap.get(btnRed.getId()) == null){
                    //判断只能按下六个红球
                    if(clickRedMap.size()>=6){
                        Toast.makeText(SsqActivity.this, "只能选择6个红球。", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickRedMap.put(btnRed.getId(), btnRed.getText().toString());
                    btnRed.setBackgroundResource(R.drawable.ball_red);
                    btnRed.setTextColor(Color.WHITE);
                }else{
                    btnRed.setBackgroundResource(R.drawable.ball_gray);
                    clickRedMap.remove(btnRed.getId());
                    btnRed.setTextColor(getResources().getColor(R.color.redBall));
                }

                break;
            case R.id.btn_ssq_blue_01:
            case R.id.btn_ssq_blue_02:
            case R.id.btn_ssq_blue_03:
            case R.id.btn_ssq_blue_04:
            case R.id.btn_ssq_blue_05:
            case R.id.btn_ssq_blue_06:
            case R.id.btn_ssq_blue_07:
            case R.id.btn_ssq_blue_08:
            case R.id.btn_ssq_blue_09:
            case R.id.btn_ssq_blue_10:
            case R.id.btn_ssq_blue_11:
            case R.id.btn_ssq_blue_12:
            case R.id.btn_ssq_blue_13:
            case R.id.btn_ssq_blue_14:
            case R.id.btn_ssq_blue_15:
            case R.id.btn_ssq_blue_16:
                Button btnBlue = (Button) view;
                //检查MAP里面的值，该按钮的状态
                if(clickBlueMap.get(btnBlue.getId()) == null){
                    //判断只能按下1个蓝球
                    if(clickBlueMap.size()>=1){
                        Toast.makeText(SsqActivity.this,"只能选择1个蓝球。",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    clickBlueMap.put(btnBlue.getId(),btnBlue.getText().toString());
                    btnBlue.setBackgroundResource(R.drawable.ball_blue);
                    btnBlue.setTextColor(Color.WHITE);
                }else{
                    btnBlue.setBackgroundResource(R.drawable.ball_gray);
                    clickBlueMap.remove(btnBlue.getId());
                    btnBlue.setTextColor(getResources().getColor(R.color.blueBall));
                }
                break;
            case R.id.btn_ssq_init://初始化数据库
                SsqDao ssqDao = new SsqDao(this);
                ssqDao.initData();
                break;
            case R.id.btn_ssq_import://把raw里面的数据文件拷贝到databases文件夹下
                SsqDao dao = new SsqDao(this);
                dao.importDatabase();
                break;
            default:
        }




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ssq, menu);
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
}
