package com.mk.security.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.utils.MD5Encoder;
import com.mk.security.utils.MkUtils;

public class LostProtectedActivity extends ActionBarActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private Dialog dialog;
    private EditText password;
    private EditText confirmPassword;
    private TextView tv_protectedNumber;
    private TextView tv_protectedGuide;
    private CheckBox cb_isProtected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_protected);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        if (isSetPassword()) {
            showLoginDialog();
        } else {
            showFirstDialog();
        }
    }

    private void showLoginDialog() {
        dialog = new Dialog(this, R.style.MyDialog);
        View view = View.inflate(this, R.layout.login_dialog, null);
        password = (EditText) view.findViewById(R.id.et_protected_password);
        Button yes = (Button) view.findViewById(R.id.bt_protected_login_yes);
        Button cancel = (Button) view.findViewById(R.id.bt_protected_login_no);
        yes.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();
    }

    private void showFirstDialog() {
        dialog = new Dialog(this, R.style.MyDialog);
        //这样来填充一个而已文件，比较方便
        View view = View.inflate(this, R.layout.first_dialog,null);
        password = (EditText) view.findViewById(R.id.et_protected_first_password);
        confirmPassword = (EditText) view.findViewById(R.id.et_protected_confirm_password);
        Button yes = (Button) view.findViewById(R.id.bt_protected_first_yes);
        Button cancel = (Button) view.findViewById(R.id.bt_protected_first_no);
        yes.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();

    }

    private boolean isSetPassword() {
        String pwd = sp.getString("password", "");
        if (pwd.equals("") || pwd == null) {
            return false;
        }
        return true;
    }

    private boolean isSetupGuide()
    {
        return sp.getBoolean("setupGuide", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost_protected, menu);
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
        switch (view.getId()) {
            case  R.id.bt_protected_first_yes:
                String fp = password.getText().toString().trim();
                String cp = confirmPassword.getText().toString().trim();
                if (fp.equals("") || cp.equals("")) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (fp.equals(cp)) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("password", MD5Encoder.encode(fp));
                        editor.commit();

                        dialog.dismiss();

                        if (!isSetupGuide()) {
                            finish();
                            Intent intent = new Intent(this, SetupGuide1Activity.class);
                            startActivity(intent);
                        }


                    }else {
                        Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.dismiss();
                break;

            case R.id.bt_protected_first_no:
                dialog.dismiss();
                finish();
                break;

            case  R.id.bt_protected_login_yes:
                String pwd = password.getText().toString();
                if (pwd.equals("")) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    String str = sp.getString("password", "");
                    if (MD5Encoder.encode(pwd).equals(str)) {
                        if(isSetupGuide())
                        {
                            setContentView(R.layout.lost_protected);
                            tv_protectedNumber = (TextView) findViewById(R.id.tv_lost_protected_number);
                            tv_protectedGuide = (TextView) findViewById(R.id.tv_lost_protected_guide);
                            cb_isProtected = (CheckBox) findViewById(R.id.cb_lost_protected_isProtected);
                            tv_protectedNumber.setText("手机安全号码为：" + sp.getString("number", ""));
                            tv_protectedGuide.setOnClickListener(this);

                            boolean isProtecting = sp.getBoolean("isProtected", false);
                            if(isProtecting)
                            {
                                cb_isProtected.setText("已经开启保护");
                                cb_isProtected.setChecked(true);
                            }

                            cb_isProtected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                            {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                                {
                                    if(isChecked)
                                    {
                                        cb_isProtected.setText("已经开启保护");
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putBoolean("isProtected", true);
                                        editor.commit();
                                    }
                                    else
                                    {
                                        cb_isProtected.setText("没有开启保护");
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putBoolean("isProtected", false);
                                        editor.commit();
                                    }
                                }
                            });

                        }
                        dialog.dismiss();
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_protected_login_no:
                dialog.dismiss();
                finish();
                break;

            case R.id.tv_lost_protected_guide : //重新进入设置向导
                Log.i(MkUtils.TAG, "重新进入设置向导");
                finish();
                Intent setupGuideIntent = new Intent(this, SetupGuide1Activity.class);
                startActivity(setupGuideIntent);
                break;
            default:
                break;
        }
    }
}
