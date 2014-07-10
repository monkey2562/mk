package com.mk.lottery.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.lottery.R;


public class MainUIAdapter extends BaseAdapter
{
	private static final String[] NAMES = new String[] {"双色球", "大乐透"};
	
	private static final int[] ICONS = new int[] {R.drawable.ssq_icon01, R.drawable.dlt_icon01};
	
	private static ImageView imageView;
//	private static TextView textView;
	
	private Context context;
	private LayoutInflater inflater;
	private SharedPreferences sp;
	
	public MainUIAdapter(Context context)
	{
		this.context = context;
		inflater = LayoutInflater.from(this.context);
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	@Override
	public int getCount()
	{
		return NAMES.length;
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = inflater.inflate(R.layout.main_item, null);
		imageView = (ImageView) view.findViewById(R.id.iv_main_icon);
//		textView = (TextView) view.findViewById(R.id.tv_main_name);
		imageView.setImageResource(ICONS[position]);
//		textView.setText(NAMES[position]);
			
//		if(position == 0)
//		{
//			String name = sp.getString("lostName", "");
//			if(!name.equals(""))
//			{
//				textView.setText(name);
//			}
//		}
		
		return view;
	}



}
