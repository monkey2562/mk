package com.mk.security.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.security.R;
import com.mk.security.dao.BlackNumberDao;

import java.util.List;

public class NumberSecurityActivity extends ActionBarActivity {
    private ListView lv_number;
    private Button bt_number_add;
    private BlackNumberDao dao;
    private List<String> numbers;
    private NumberAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_number_security);

        dao = new BlackNumberDao(this);
        numbers = dao.findAll();
        adapter = new NumberAdapter();
        lv_number = (ListView) findViewById(R.id.lv_number);
        lv_number.setAdapter(adapter);
        //给listview注册一个上下文菜单
        registerForContextMenu(lv_number);

        bt_number_add = (Button) findViewById(R.id.bt_number_add);
        bt_number_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NumberSecurityActivity.this);
                builder.setTitle("添加黑名单");
                final EditText et_number = new EditText(NumberSecurityActivity.this);
                et_number.setInputType(InputType.TYPE_CLASS_PHONE);
                et_number.setHint("请输入黑名单号码");
                builder.setView(et_number);
                builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String number = et_number.getText().toString().trim();
                        if(TextUtils.isEmpty(number)){
                            Toast.makeText(NumberSecurityActivity.this, "添加号码不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            dao.add(number);
                            numbers = dao.findAll();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.number, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //拿到点击的菜单的信息
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.update_number:
                String oldNumber = numbers.get((int) info.id);
                updateNumber(oldNumber);
                break;
            case R.id.delete_number :
                int id = (int) info.id;
                String number = numbers.get(id);
                dao.delete(number);
                numbers = dao.findAll();
                adapter.notifyDataSetChanged();
                break;

            default :
                break;
        }


        return super.onContextItemSelected(item);
    }

    private void updateNumber(final String oldNumber) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NumberSecurityActivity.this);
        builder.setTitle("更新黑名单");
        final EditText et_number = new EditText(NumberSecurityActivity.this);
        et_number.setInputType(InputType.TYPE_CLASS_PHONE);
        et_number.setHint("请输入新的号码");
        builder.setView(et_number);
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String number = et_number.getText().toString().trim();
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(NumberSecurityActivity.this, "添加号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    dao.update(oldNumber, number);
                    numbers = dao.findAll();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.number_security, menu);
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


    //==================================================================

    private class  NumberAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return numbers.size();
        }

        @Override
        public Object getItem(int i) {
            return numbers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                View view = View.inflate(NumberSecurityActivity.this, R.layout.number_security_item, null);
                TextView tv_item = (TextView) view.findViewById(R.id.tv_number_item);
                tv_item.setText(numbers.get(position));
                return view;
            }else {
                TextView tv_item = (TextView) convertView.findViewById(R.id.tv_number_item);
                tv_item.setText(numbers.get(position));
                return convertView;
            }
        }
    }
}
