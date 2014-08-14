package com.buaa.ericsson.gridview.timeline;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter
{

	private Context context;
	private HashMap<String, ArrayList<ImageInfo>> imageInfoMap;
	private String[] timeMap;
	private LayoutInflater layoutInflater;

	public ListViewAdapter(Context context, String[] timeMap, HashMap<String, ArrayList<ImageInfo>> imageInfoMap)
	{

		this.context = context;
		this.timeMap = timeMap;
		this.imageInfoMap = imageInfoMap;

		this.layoutInflater = LayoutInflater.from(context);
	}

	public int getCount()
	{
		// TODO Auto-generated method stub
		return imageInfoMap.size();
	}

	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{

		TextView tv;
		MyGridView gv;
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.listview_item, null);
		}

		tv = (TextView) convertView.findViewById(R.id.TextView01);
		gv = (MyGridView) convertView.findViewById(R.id.gridview1);
	
		int[] colors = { Color.RED, Color.GREEN, Color.BLUE};//RGB颜色
		tv.setTextColor(colors[position%3]);
		//if (position == 0)
		//	tv.setVisibility(View.GONE);
		//else
		tv.setVisibility(View.VISIBLE);
		tv.setText(timeMap[position]);
		gv.setAdapter(new GridViewAdapter(context, position));

		gv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				System.out.println(position);
			}
		});

		return convertView;
	}

}
